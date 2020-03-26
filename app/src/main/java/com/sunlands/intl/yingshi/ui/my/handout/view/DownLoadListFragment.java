package com.sunlands.intl.yingshi.ui.my.handout.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadEntity;
import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.DownLoadEvent;
import com.sunlands.intl.yingshi.common.MyFragment;
import com.sunlands.intl.yingshi.greendao.MyDownLoadInfoDao;
import com.sunlands.intl.yingshi.greendao.db.MyDownLoadInfo;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.my.handout.adapter.DownloadCompleteAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class DownLoadListFragment extends MyFragment<Object> {

    private DownloadCompleteAdapter mAdapter;
    ConstraintLayout noHandoutLayout;

    int type = 1;
    String courseId;
    private List<MyDownLoadInfo> mDataTemp;

    public static String SHIPIN = "视频";
    public static String YINPIN = "音频";
    public static String KEJIAN = "课件";
    public static String QITA = "其它";
    private List<MyDownLoadInfo> mData;

    public DownLoadListFragment() {
    }

    public DownLoadListFragment(int i, String courseId) {
        this.type = i;
        this.courseId = courseId;
    }

    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnCheckHasGoodsListener(new DownloadCompleteAdapter.OnCheckHasGoodsListener() {
            @Override
            public void onCheckHasGoods(boolean isHasGoods) {
                if (isHasGoods == false) {
                    showEmptyState();
                }
            }
        });
        mAdapter.setOnAllCheckedBoxNeedChangeListener(new DownloadCompleteAdapter.OnAllCheckedBoxNeedChangeListener() {
            @Override
            public void onCheckedBoxNeedChange(boolean allIsChecked) {
                enableDeleteBtn();
                EventBusHelper.post(new DownLoadEvent(allIsChecked ? 5 : 6));
            }
        });
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        noHandoutLayout = FBIF(R.id.no_handout_layout);
    }


    //是否置灰删除按钮
    public void enableDeleteBtn() {
        boolean check = mAdapter.isCheck();
        if (check == true) {
            EventBusHelper.post(new DownLoadEvent(3));
        } else {
            EventBusHelper.post(new DownLoadEvent(4));
        }
    }


    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        noHandoutLayout.setVisibility(View.GONE);
        List<MyDownLoadInfo> listCompleteTask = new ArrayList<>();
        mData = DbHelper.getInstance().getMyDownLoadInfoDao().queryBuilder()
                .where(MyDownLoadInfoDao.Properties.UserId.eq(LoginUserInfoHelper.getInstance().getUserInfo().getUserId()))
                .where(MyDownLoadInfoDao.Properties.CourseId.eq(Integer.parseInt(courseId)))
                .list();
        List<DownloadEntity> allCompleteTask = Aria.download(this).getAllCompleteTask();
        if (allCompleteTask != null) {
            for (DownloadEntity downloadEntity : allCompleteTask) {
                for (MyDownLoadInfo myDownLoadInfo : mData) {
                    if (downloadEntity.getUrl().equals(myDownLoadInfo.getDownloadEntity().getUrl())) {
                        myDownLoadInfo.setDownloadEntity(downloadEntity);
                        listCompleteTask.add(myDownLoadInfo);
                    }
                }
            }
        }
        mDataTemp = new ArrayList<>(listCompleteTask);
        mAdapter = new DownloadCompleteAdapter(mContext, getCurrentData(type));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void loadData() {
        super.loadData();
        if (mAdapter.getItemCount() == 0) {
            showEmptyState();
        }
    }

    private List<MyDownLoadInfo> getCurrentData(int type) {
        for (int i = 0; i < mData.size(); i++) {
            MyDownLoadInfo absEntity = mData.get(i);
            if ((type == 2 && !SHIPIN.equals(absEntity.getFileType()))) {
                mDataTemp.remove(absEntity);
            } else if ((type == 3 && !YINPIN.equals(absEntity.getFileType()))) {
                mDataTemp.remove(absEntity);
            } else if ((type == 4 && !KEJIAN.equals(absEntity.getFileType()))) {
                mDataTemp.remove(absEntity);
            } else if ((type == 5 && !QITA.equals(absEntity.getFileType()))) {
                mDataTemp.remove(absEntity);
            }
        }
        return mDataTemp;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_down_load_list;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void downLoad(DownLoadEvent event) {
        if (event.getType() == 0) {
            if (mAdapter != null) {
                mAdapter.notifyDataSetChanged();
            }
        } else if (event.getType() == 1) {
            assert mAdapter != null;
            mAdapter.setupAllChecked(true);
            if ((type - 1) == event.getPosition()) {
                EventBusHelper.post(new DownLoadEvent(mAdapter.isCheck() ? 3 : 4));
            }
        } else if (event.getType() == 2) {
            assert mAdapter != null;
            mAdapter.setupAllChecked(false);
            if ((type - 1) == event.getPosition()) {
                EventBusHelper.post(new DownLoadEvent(mAdapter.isCheck() ? 3 : 4));
            }
        } else if (event.getType() == 7) {
            boolean check = mAdapter.isCheck();
            if (check == false) {
                return;
            }
//            if ((type - 1) == event.getPosition()) {
//                DialogUtils.deleteFile(mContext, new DialogUtils.onClick() {
//                    @Override
//                    public void sure() {
//                        mAdapter.remove();
//                        EventBusHelper.post(new DownLoadEvent(10));
//                    }
//                });
//            }
            mAdapter.remove();
            EventBusHelper.post(new DownLoadEvent(10));
        } else if (event.getType() == 12 && (type - 1) == event.getPosition()) {
            if (mAdapter.getItemCount() > 0) {
                showNormalState();
            } else {
                showEmptyState();
            }
        }
    }


    private void showEmptyState() {
        EventBusHelper.post(new DownLoadEvent(8));
        noHandoutLayout.setVisibility(View.VISIBLE);
    }

    private void showNormalState() {
        EventBusHelper.post(new DownLoadEvent(11));
        noHandoutLayout.setVisibility(View.GONE);
    }

}
