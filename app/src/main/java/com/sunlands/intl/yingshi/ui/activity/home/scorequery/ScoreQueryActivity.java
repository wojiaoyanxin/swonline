package com.sunlands.intl.yingshi.ui.activity.home.scorequery;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.PackageListBean;
import com.sunlands.intl.yingshi.bean.multi.ScoreQueryHeadItem;
import com.sunlands.intl.yingshi.bean.multi.ScoreQueryItem;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.widget.CustomLoadMoreView;
import com.sunlands.intl.yingshi.util.Utils;

import java.util.ArrayList;
import java.util.List;


//成绩查询

public class ScoreQueryActivity extends CommonActivity<Object> {


    private ScoreQueryAdapter mAdapter;
    private TextView tv_query_class_name;
    private List<PackageListBean.ListBean> list;
    private PackageListBean.ListBean listBean;
    private ArrayList<String> arrayListClassName;
    private int packAgeId = 0;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        tv_query_class_name = FBIA(R.id.tv_query_class_name);
        FBIA(R.id.rl_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWheel();
            }
        });
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ScoreQueryAdapter(null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        }, mRecyclerView);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setEnableLoadMore(false);
        inflateEmptyView(mRecyclerView);
        mTvEmpty.setText(R.string.no_more_content);
        ivEmpty.setImageResource(R.drawable.no_content);
        mAdapter.setEmptyView(mEmptyView);
    }

    private void loadMore() {
        reset(false);
        getDataNet(false, mNextPage, packAgeId);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_score_query;
    }

    @Override
    public String getTitleText() {
        return "成绩查询";
    }

    protected void getData() {
        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        mNames.clear();
        reset(true);
        getDataNet(true, mNextPage, packAgeId);
    }


    @Override
    public void onSuccess(Object object) {
        super.onSuccess(object);
        if (object instanceof ScoreResponse) {
            ScoreResponse bean = (ScoreResponse) object;
            List<MultiItemEntity> scoreList = convertData(bean);

            if (isRefresh) {
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setNewData(scoreList);
                mAdapter.loadMoreComplete();
            } else {
                if (!Utils.isEmpty(scoreList))
                    mAdapter.addData(scoreList);
                if (bean.getHasMore() == 1) {
                    mAdapter.loadMoreComplete();
                } else {
                    mAdapter.loadMoreEnd(false);
                }
            }
            mAdapter.setEnableLoadMore(true);
            mAdapter.expandAll();
        } else if (object instanceof PackageListBean) {
            PackageListBean packageListBean = (PackageListBean) object;
            list = packageListBean.getList();
            if (listBean == null) {
                listBean = new PackageListBean.ListBean();
                listBean.setName("全部");
                listBean.setId(0);
            }
            list.add(0, listBean);
            arrayListClassName = new ArrayList<>();
            for (PackageListBean.ListBean listBean : list) {
                arrayListClassName.add(listBean.getName());
            }
        }
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        if (isRefresh) {
            mSwipeRefreshLayout.setRefreshing(false);
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.loadMoreFail();
            mAdapter.setEnableLoadMore(true);
        }
    }

    private List<String> mNames = new ArrayList<>();

    private List<MultiItemEntity> convertData(ScoreResponse listBean) {


        if (Utils.isEmpty(listBean.getScoreList())) {
            return null;
        }
        List<MultiItemEntity> multiItemEntities = new ArrayList<>();

        for (ScoreResponse.ScoreListBean bean : listBean.getScoreList()) {

            ScoreQueryHeadItem scoreQueryItem = new ScoreQueryHeadItem();
            scoreQueryItem.setTitle(bean.getSubjectName());
            List<ScoreResponse.ScoreListBean.ExamListBean> examList = bean.getExamList();
            examList.add(0, new ScoreResponse.ScoreListBean.ExamListBean());
            if (!Utils.isEmpty(examList)) {
                for (ScoreResponse.ScoreListBean.ExamListBean examListBean : examList) {
                    scoreQueryItem.addSubItem(new ScoreQueryItem(examListBean));
                }
            }
            multiItemEntities.add(scoreQueryItem);
        }
        return multiItemEntities;
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataNet(true, mNextPage, packAgeId);
        getDataNet(false, "");
    }

    private void setWheel() {
        if (list == null || list.size() <= 0) {
            return;
        }

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tv_query_class_name.setText(arrayListClassName.get(options1));
                packAgeId = list.get(options1).getId();
                getData();
            }
        })
                .setTitleText("")
                .setTitleSize(16)
                .setContentTextSize(18)
                .build();

        pvOptions.setPicker(arrayListClassName, null, null);
        pvOptions.show();

    }
}
