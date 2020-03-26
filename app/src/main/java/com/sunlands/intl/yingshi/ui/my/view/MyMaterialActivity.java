package com.sunlands.intl.yingshi.ui.my.view;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.download.DownloadTask;
import com.google.gson.Gson;
import com.sunlands.comm_core.helper.UserInfoHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.utils.rx.RxBindingUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.greendao.MyAllFileDbBeanDao;
import com.sunlands.intl.yingshi.greendao.db.MyAllFileDbBean;
import com.sunlands.intl.yingshi.greendao.db.MyFileBean;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.my.handout.view.OldDownLoadingActivity;
import com.sunlands.intl.yingshi.ui.my.handout.view.OldHandoutActivity;
import com.tencent.liteav.demo.play.bean.MaterialBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyMaterialActivity extends CommonActivity<Object> {

    private View ll;
    ConstraintLayout noHandoutLayout;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        ll = FBIA(R.id.ll);
        noHandoutLayout = FBIA(R.id.no_handout_layout);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setStackFromEnd(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingUtils.setOnClickListener(ll, this);
    }

    public void initData() {
        DbHelper.getInstance().getAllFileDbBeanDao().detachAll();
        List<MyFileBean> list = new ArrayList<>();
        QueryBuilder<MyAllFileDbBean> myAllFileDbBeanQueryBuilder = DbHelper.mDaoSession.queryBuilder(MyAllFileDbBean.class);
        List<MyAllFileDbBean> myAllFileDbBeans = myAllFileDbBeanQueryBuilder
                .where(MyAllFileDbBeanDao.Properties.UserId.eq(UserInfoHelper.getInstance().userId + "")).list();
        Collections.reverse(myAllFileDbBeans);
        Set set = new HashSet();
        for (MyAllFileDbBean myAllFileDbBean : myAllFileDbBeans) {
            if (set.add(myAllFileDbBean.getSubjectId())) {
                list.add(new MyFileBean(myAllFileDbBean.getSubjectName(), myAllFileDbBean.getSubjectId(), new ArrayList<>()));
            }
        }

        for (int i = 0; i < myAllFileDbBeans.size(); i++) {

            MyAllFileDbBean myAllFileDbBean = myAllFileDbBeans.get(i);
            String subjectId = myAllFileDbBean.getSubjectId();
            for (int k = 0; k < list.size(); k++) {
                MyFileBean myFileBean = list.get(k);
                if (myFileBean.getSubjectId().equals(subjectId)) {
                    myFileBean.getFile().add(myAllFileDbBean);
                }
            }
        }

        CommonAdapter commonAdapter = new CommonAdapter<MyFileBean>(this, list, R.layout.item_materail_subjectlist_layout) {
            @Override
            public void convert(ViewHolder headholder, MyFileBean listBean) {

                String name = listBean.getSubjectName();
                String subjectId = listBean.getSubjectId();
                headholder.setText(R.id.tv_subject_name, name);
                headholder.setText(R.id.tv_subject_counts, " (" + listBean.getFile().size() + ")");
                headholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OldHandoutActivity.show(mContext, subjectId, name);
                    }
                });

            }
        };
        mRecyclerView.setAdapter(commonAdapter);
        assert list != null;
        if (list.size() == 0 && ll.getVisibility() == View.GONE) {
            noHandoutLayout.setVisibility(View.VISIBLE);
        } else {
            noHandoutLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == ll) {
            startActivity(OldDownLoadingActivity.class);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_material;
    }

    @Override
    public String getTitleText() {
        return "我的文件";
    }

    @Override
    public void onResume() {
        super.onResume();
        Aria.download(this).register();
        setCounts();
        initData();
    }

    private void setCounts() {
        ll.setVisibility(View.GONE);
        List<DownloadEntity> allNotCompleteTask = Aria.download(this).getAllNotCompleteTask();
        if (allNotCompleteTask == null) {
            return;
        }
        if (allNotCompleteTask.size() != 0) {
            for (DownloadEntity downloadEntity : allNotCompleteTask) {
                MaterialBean.ListBean listBean1 = new Gson().fromJson(((DownloadEntity) downloadEntity).getMd5Code(), MaterialBean.ListBean.class);
                if (LoginUserInfoHelper.getInstance().getUserInfo().getUserId() == listBean1.getUserId()) {
                    ll.setVisibility(View.VISIBLE);
                    break;
                }
            }
        }
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        setCounts();
        initData();
    }
}
