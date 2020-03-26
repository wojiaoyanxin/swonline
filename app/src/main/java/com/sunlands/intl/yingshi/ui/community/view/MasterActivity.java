package com.sunlands.intl.yingshi.ui.community.view;

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
import com.sunlands.comm_core.rvadapter.HeaderFooterAdapter;
import com.sunlands.comm_core.rvadapter.IHeaderHelper;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.comm_core.statemanager.StateLayout;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.helper.state.NoDataState;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.ThreadPaginationAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;
import com.sunlands.intl.yingshi.ui.community.presenter.MasterPresenter;

public class MasterActivity extends MvpActivity<MasterPresenter> implements IMessageContract.IMasterView, IMessageContract.MailView {

    private ImageView iv_title_back;
    private RecyclerView mRv_master_list;
    private ThreadPaginationAdapter mPaginationAdapter;
    public int limit = 20;
    public static final String CHANNEL_INFO = "channelInfo";

    private CommunityPresenter mCommunityPresenter;
    private MainlBean.ChannelListBean mChannelListBean;
    private SmartRefreshLayout mSrl_master;
    private HeaderFooterAdapter mHeaderFooterAdapter;

    @Override
    public void onClick(View v) {
        if (v == iv_title_back) {
            finish();
        }

    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_pub_med;
    }

    @Override
    public void initDataBeforeView() {
        mChannelListBean = (MainlBean.ChannelListBean) getIntent().getSerializableExtra(CHANNEL_INFO);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        iv_title_back = FBIA(R.id.iv_title_back);
        TextView tv_title_content = FBIA(R.id.tv_title_content);
        tv_title_content.setText(mChannelListBean.getTitle());
        mSrl_master = FBIA(R.id.srl_master);
        mRv_master_list = FBIA(R.id.rv_master_list);
        mRv_master_list.setLayoutManager(new LinearLayoutManager(this));
        mPaginationAdapter =new ThreadPaginationAdapter(this,null,getSupportFragmentManager(),mCommunityPresenter);
        mHeaderFooterAdapter = new HeaderFooterAdapter(mPaginationAdapter);
        mHeaderFooterAdapter.setHeaderView(0, new IHeaderHelper() {
            @Override
            public int getItemLayoutId() {
                return R.layout.header_master_layout;
            }

            @Override
            public void onBind(ViewHolder holder) {
                ImageView iv_header_master = holder.getView(R.id.iv_header_master);
                holder.setText(R.id.tv_header_master_title,mChannelListBean.getTitle());
                if (mChannelListBean.getTitle().equals("考研专区")) {
                    iv_header_master.setImageResource(R.drawable.iv_header_master_kyzq_icon);
                    holder.setText(R.id.tv_header_master_content,"交流考研经验，分享考研趣事");
                }else{
                    iv_header_master.setImageResource(R.drawable.iv_header_master_gjss_icon);
                    holder.setText(R.id.tv_header_master_content,"交流考研经验，分享考研趣事");
                }

            }
        });
        mRv_master_list.setAdapter(mHeaderFooterAdapter);
    }

    @Override
    public void initDataAfterView() {
       // getPresenter().getThread_Pagination(limit, "", mChannelListBean.getId());
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getThread_Pagination(limit, "", mChannelListBean.getId());
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(iv_title_back, this);
        mSrl_master.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getThread_Pagination(limit + 20, "", mChannelListBean.getId());
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getThread_Pagination(limit, "", mChannelListBean.getId());
            }
        });
    }

    @Override
    protected MasterPresenter createPresenter(IBaseView view) {
        mCommunityPresenter = new CommunityPresenter(this);
        return new MasterPresenter(this);
    }

    @Override
    public void getPaginationSuccess(MainlBean mMainlBean) {
        mSrl_master.finishRefresh();
        mSrl_master.finishLoadMore();
        if (mMainlBean.getTotal().equals("0")) {
            ((StateLayout) FBIA(R.id.sl_master)).showState(NoDataState.STATE);
        } else {
            mPaginationAdapter.setDatas(mMainlBean.getList());
            mHeaderFooterAdapter.notifyDataSetChanged();
        }
        if (Integer.valueOf(mMainlBean.getTotal()) == mMainlBean.getList().size()) {
            mSrl_master.finishLoadMoreWithNoMoreData();
        } else {
            mSrl_master.finishLoadMore();
        }
      //  mRv_master_list.smoothScrollToPosition(0);

    }

    @Override
    public void onPostSubmitSuccess(String type) {
        getPresenter().getThread_Pagination(limit, "", mChannelListBean.getId());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCommunityPresenter.onDestroy(this);
    }
}
