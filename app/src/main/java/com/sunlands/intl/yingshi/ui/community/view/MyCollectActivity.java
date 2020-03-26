package com.sunlands.intl.yingshi.ui.community.view;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.ThreadPaginationAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;
import com.sunlands.intl.yingshi.ui.community.presenter.MyCollectPresenter;

/**
 * 我的帖子
 */
public class MyCollectActivity extends MvpActivity<MyCollectPresenter> implements IMessageContract.IMyCollect, IMessageContract.MailView {

    private ImageView mIv_title_back;
    private SmartRefreshLayout mSrl_my_collect;
    private RecyclerView mRv_my_collect_list;
    private TextView mTv_my_publish, mTv_my_collect;
    public static final String MY_PUBLISH = "my_publish";
    public static final String MY_COLLECT = "my_collect";
    public String mCuttentType = MY_PUBLISH;
    private CommunityPresenter mCommunityPresenter;
    private int limit = 20;
    private View noDataView;

    @Override
    public void onClick(View v) {
        if (v == mIv_title_back) {
            finish();
        } else if (v == mTv_my_publish) {
            //我的发布
            onViewSelect(mTv_my_publish, FBIA(R.id.v_comm_my_publish), mTv_my_collect, FBIA(R.id.v_comm_my_collect));
        } else if (v == mTv_my_collect) {
            //我收藏的
            onViewSelect(mTv_my_collect, FBIA(R.id.v_comm_my_collect), mTv_my_publish, FBIA(R.id.v_comm_my_publish));
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_my_collect;
    }

    @Override
    public void initDataBeforeView() {

    }

    ThreadPaginationAdapter mCommonAdapter;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        TextView tv_title_content = FBIA(R.id.tv_title_content);
        tv_title_content.setText("我的贴子");
        mIv_title_back = FBIA(R.id.iv_title_back);
        mSrl_my_collect = FBIA(R.id.srl_my_collect);
        noDataView = FBIA(R.id.no_handout_layout);
        mRv_my_collect_list = FBIA(R.id.rv_my_collect_list);
        FBIA(R.id.v_title).setVisibility(View.GONE);
        mRv_my_collect_list.setLayoutManager(new LinearLayoutManager(this));

        mTv_my_publish = FBIA(R.id.tv_comm_my_publish);
        mTv_my_collect = FBIA(R.id.tv_comm_my_collect);
        mCommonAdapter = new ThreadPaginationAdapter(this, null, getSupportFragmentManager(), mCommunityPresenter);
        mRv_my_collect_list.setAdapter(mCommonAdapter);
    }

    @Override
    public void initDataAfterView() {
       // getPresenter().getMine(limit, mCuttentType);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mTv_my_publish, this);
        RxBindingHelper.setOnClickListener(mTv_my_collect, this);
        mSrl_my_collect.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getMine(limit + 20, mCuttentType,"");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getMine(limit, mCuttentType,"");
            }
        });
    }

    public void onViewSelect(TextView selectView, View selectLine, TextView noselectView, View noselectLine) {
        if (selectView.getId() == R.id.tv_comm_my_publish) {
            mCuttentType = MY_PUBLISH;
            getPresenter().getMine(limit, mCuttentType,"");
        }
        if (selectView.getId() == R.id.tv_comm_my_collect) {
            mCuttentType = MY_COLLECT;
            getPresenter().getMine(limit, mCuttentType,"");
        }
        selectView.setTextSize(18);
        selectView.setTextColor(CommonUtils.getColor(R.color.cl_333333));
        selectView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        selectLine.setVisibility(View.VISIBLE);

        noselectView.setTextSize(14);
        noselectView.setTextColor(CommonUtils.getColor(R.color.cl_999999));
        noselectLine.setVisibility(View.GONE);
        noselectView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }

    @Override
    protected MyCollectPresenter createPresenter(IBaseView view) {
        mCommunityPresenter = new CommunityPresenter(this);
        return new MyCollectPresenter(this);
    }

    @Override
    public void getPaginationSuccess(MainlBean mMainlBean) {
        //暂时无用
    }

    //只是用来刷新界面
    @Override
    public void onPostSubmitSuccess(String type) {
        getPresenter().getMine(limit, mCuttentType,"");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCommunityPresenter.onDestroy(this);
    }

    @Override
    public void onMineSuccess(MyCollectBean data) {
        noDataView.setVisibility(View.GONE);
        mCommonAdapter.setUserInfo(data.getUser_info());
        mCommonAdapter.setDatas(data.getPagination().getList());
        if (data.getPagination().getList().size() == 0) {
            noDataView.setVisibility(View.VISIBLE);
        }
        mSrl_my_collect.finishRefresh();
        if (data.getPagination().getList().size() == 0 || Integer.valueOf(data.getPagination().getTotal()) == data.getPagination().getList().size()) {
            mSrl_my_collect.finishLoadMoreWithNoMoreData();
        } else {
            mSrl_my_collect.finishLoadMore();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCuttentType == MY_COLLECT){
            getPresenter().getMine(limit, MY_COLLECT,"");
        }else {
            getPresenter().getMine(limit, MY_PUBLISH,"");
        }
    }
}
