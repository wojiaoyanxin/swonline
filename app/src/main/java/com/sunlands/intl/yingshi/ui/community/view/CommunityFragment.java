package com.sunlands.intl.yingshi.ui.community.view;

import android.content.Intent;
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
import com.sunlands.comm_core.base.BaseViewImpl;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpFragment;
import com.sunlands.comm_core.helper.ApplicationsHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.CommonAdapter;
import com.sunlands.comm_core.rvadapter.HeaderFooterAdapter;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.ui.activity.MainActivity;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.ThreadPaginationAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;

//站内信
public class CommunityFragment extends MvpFragment<CommunityPresenter> implements IMessageContract.MailView, BaseViewImpl.OnClickListener {

    private RecyclerView mRv_mail;
    public int limit = 20;
    private SmartRefreshLayout mSrl_mail;
    private CommonAdapter<MainlBean.ListBean> mCommonAdapter;
    private ImageView mIv_create_comm, mIv_comm_back;
    public static final int isFromCreateMail = 1;
    private String mCuttentType = IMessageContract.ConmmunityType.recommend;
    private TextView mEt_find;

    public static CommunityFragment newInstance() {
        CommunityFragment fragment = new CommunityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if (v == mIv_create_comm) {
            //新建站内信
            Intent intent = new Intent(getActivity(), CreateCommunityActivity.class);
            startActivityForResult(intent, isFromCreateMail);
        } else if (mEt_find == v) {
            //去搜索
            startActivity(new Intent(getActivity(), FindCommunityActivity.class));
        } else if (v == mIv_comm_back) {
            getActivity().finish();
        }

    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.fragment_community_layout;
    }

    @Override
    public void initDataBeforeView() {

    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mRv_mail = FBIF(R.id.rv_mail);
        mSrl_mail = FBIF(R.id.srl_mail);
        mIv_create_comm = FBIF(R.id.iv_create_comm);
        mEt_find = FBIF(R.id.et_find);
        mIv_comm_back = FBIF(R.id.iv_comm_back);
        if (getActivity() instanceof CommunityActivity) {
            mIv_comm_back.setVisibility(View.VISIBLE);
        }
        mRv_mail.setLayoutManager(new LinearLayoutManager(ApplicationsHelper.context()));
        mSrl_mail.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getMailListInfo(limit+=20, mCuttentType);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getMailListInfo(limit, mCuttentType);
            }
        });
    }

    @Override
    public void initDataAfterView() {
        getPresenter().getMailListInfo(limit, mCuttentType);
    }

    HeaderFooterAdapter mHeaderFooterAdapter;

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mCommonAdapter = new ThreadPaginationAdapter(getActivity(), null, getFragmentManager(),getPresenter());
        mHeaderFooterAdapter = new HeaderFooterAdapter(mCommonAdapter);
        mRv_mail.setAdapter(mHeaderFooterAdapter);
    }

    @Override
    public void initListener() {

        RxBindingHelper.setOnClickListener(mIv_create_comm, this);
        RxBindingHelper.setOnClickListener(mEt_find, this);
        RxBindingHelper.setOnClickListener(mIv_comm_back, this);
    }

    @Override
    protected CommunityPresenter createPresenter(IBaseView view) {
        return new CommunityPresenter(this);
    }

    @Override
    public void getPaginationSuccess(MainlBean mMainlBean) {
        mHeaderFooterAdapter.clearHeaderView();
        mHeaderFooterAdapter.addHeaderView(mMainlBean.setViewSelect(new MainlBean.IViewSelect() {
            @Override
            public void onViewSelect(String type) {
                mCuttentType = type;
                getPresenter().getMailListInfo(limit, type);
            }
        }));
        mCommonAdapter.setDatas(mMainlBean.getList());
        mHeaderFooterAdapter.notifyDataSetChanged();
        mSrl_mail.finishRefresh();
        if (mMainlBean.getList().size() == 0 || Integer.valueOf(mMainlBean.getTotal()) == mMainlBean.getList().size()) {
            mSrl_mail.finishLoadMoreWithNoMoreData();
        } else {
            mSrl_mail.finishLoadMore();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof MainActivity) {
            if (((MainActivity) (getActivity())).mCurrentItem == 2) {
                getPresenter().getMailListInfo(limit, mCuttentType);
            }
        } else {
            getPresenter().getMailListInfo(limit, mCuttentType);
        }
    }

    @Override
    public void onPostSubmitSuccess(String type) {
        //消息回复成功后刷新界面
        getPresenter().getMailListInfo(limit, mCuttentType);
    }


}
