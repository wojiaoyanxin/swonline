package com.sunlands.intl.yingshi.ui.activity.home.mythesisn;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.CommonActivity;

import java.util.List;


public class MyPapersActivity extends CommonActivity<PapersResponse> {

    private MyPapersAdapter mMyPapersAdapter;
    List<PagerBean> pagerBean;


    @Override
    public void onSuccess(PapersResponse bean) {
        super.onSuccess(bean);
        pagerBean = bean.getThesisList();
        mSwipeRefreshLayout.setRefreshing(false);
        mMyPapersAdapter.setNewData(pagerBean);
    }

    @Override
    public void onFailed(BaseModel model) {
        super.onFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMyPapersAdapter = new MyPapersAdapter(null);
        mMyPapersAdapter.addFooterView(getFooterView());
        mRecyclerView.setAdapter(mMyPapersAdapter);
        mMyPapersAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.fl_file) {
                    PaperAnalysisActivity.show(MyPapersActivity.this, pagerBean.get(position), 1);
                } else if (view.getId() == R.id.ll_jiexi) {
                    PaperAnalysisActivity.show(MyPapersActivity.this, pagerBean.get(position), 2);
                }
            }
        });
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(this::initDataAfterView);
        inflateEmptyView(mRecyclerView);
        mTvEmpty.setText("暂无数据");
        mMyPapersAdapter.setEmptyView(mEmptyView);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_papers;
    }

    @Override
    public String getTitleText() {
        return getResources().getString(R.string.my_paper);
    }


    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        getDataNet(true,"");
    }

    private View getFooterView() {
        View view = getLayoutInflater().inflate(R.layout.layout_exam_arrangement_footer,
                (ViewGroup) mRecyclerView.getParent(), false);
        return view;
    }

}
