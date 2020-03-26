package com.sunlands.intl.yingshi.ui.community.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.rvadapter.HeaderFooterAdapter;
import com.sunlands.comm_core.rvadapter.IHeaderHelper;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.bean.PaginationBean;
import com.sunlands.intl.yingshi.bean.ThreadInfoBean;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.CommunityContentAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityContentPresenter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;

/**
 * 帖子详情
 */
public class CommunityContentActivity extends MvpActivity<CommunityContentPresenter> implements IMessageContract.MailContentView, IMessageContract.MailView {
    private ImageView mIv_title_back;
    private TextView mTv_title_content;
    public int limit = 20;
    public static final String MAIL_INFO = "mail_info";
    private MainlBean.ListBean.ThreadBean mMail_info;
    private RecyclerView mRv_mail_content;
    private TextView mEt_mail_input_content;
    private TextView tv_comm_content_comment;
    private CommunityContentAdapter mMailContentAdapter;
    private SmartRefreshLayout mSrl_omm_content_refreshLayout;
    private ImageView mIv_title_right;
    private CommunityPresenter mCommunityPresenter;
    private View mV_comm_content_like;
    private ImageView mIv_comm_content_like;
    InputMsgDialog mInputMsgDialog;
    private CommmunitCollectDialog mCollectDialog;
    private MyRecycleview myRecycleview;

    @Override
    public void onClick(View v) {
        if (mIv_title_back == v) {
            finish();
        } else if (mEt_mail_input_content == v) {
            if (mInputMsgDialog != null) {
                mInputMsgDialog.show(getSupportFragmentManager(), null);
            }
        } else if (tv_comm_content_comment == v) {
            //全部评论
            if ((mRv_mail_content.getLayoutManager()) != null) {
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(this) {
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
                smoothScroller.setTargetPosition(1);
                mRv_mail_content.getLayoutManager().startSmoothScroll(smoothScroller);
            }
        } else if (mV_comm_content_like == v) {
            if (mThreadInfoBean != null) {
                // 0 不喜欢  1喜欢
                if (mThreadInfoBean.getExtend().getIs_liked().equals("0")) {
                    mThreadInfoBean.getExtend().setIs_liked("1");
                    mIv_comm_content_like.setImageResource(R.drawable.iv_mail_like);
                    mCommunityPresenter.threadLiked(mIv_comm_content_like, mThreadInfoBean.getThread().getId());
                } else {
                    mThreadInfoBean.getExtend().setIs_liked("0");
                    mIv_comm_content_like.setImageResource(R.drawable.iv_mail_no_like);
                    mCommunityPresenter.unLiked(mIv_comm_content_like, mThreadInfoBean.getThread().getId());
                }
            }
        } else if (mIv_title_right == v) {
            mCollectDialog.show();
        }
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_mail_content;
    }

    @Override
    public void initDataBeforeView() {
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mMail_info = (MainlBean.ListBean.ThreadBean) getIntent().getSerializableExtra(MAIL_INFO);
        mTv_title_content = findViewById(R.id.tv_title_content);
        mIv_title_back = findViewById(R.id.iv_title_back);
        mRv_mail_content = findViewById(R.id.rv_mail_content);
        mEt_mail_input_content = findViewById(R.id.et_mail_input_content);
        tv_comm_content_comment = findViewById(R.id.tv_comm_content_comment);
        mV_comm_content_like = findViewById(R.id.v_comm_content_like);
        mSrl_omm_content_refreshLayout = findViewById(R.id.srl_omm_content_refreshLayout);
        mIv_comm_content_like = findViewById(R.id.iv_comm_content_like);
        mIv_title_right = findViewById(R.id.iv_title_right);
        mIv_title_right.setVisibility(View.VISIBLE);
        mIv_title_right.setImageResource(R.drawable.iv_comm_content_title_more);
        //自定义布局管理器
        myRecycleview = new MyRecycleview(this);
        myRecycleview.setOrientation(OrientationHelper.VERTICAL);  //垂直
        myRecycleview.setScrollEnabled(false);  //禁止滑动
        mRv_mail_content.setLayoutManager(myRecycleview);
        mInputMsgDialog = InputMsgDialog.getInstance();
    }

    @Override
    public void initDataAfterView() {
        getPresenter().getThreadInfo(mMail_info.getId());
        // getPresenter().getPagination(0, limit, 0, mMail_info.getId());
    }

    HeaderFooterAdapter headerFooterAdapter;

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mTv_title_content.setText("贴子详情");
        mCollectDialog = new CommmunitCollectDialog(this);
        mMailContentAdapter = new CommunityContentAdapter(this, null, getSupportFragmentManager(), getPresenter());
        headerFooterAdapter = new HeaderFooterAdapter(mMailContentAdapter);
        mRv_mail_content.setAdapter(headerFooterAdapter);
        mRv_mail_content.setNestedScrollingEnabled(false);
    }

    @Override
    public void initListener() {
        RxBindingHelper.setOnClickListener(mV_comm_content_like, this);
        RxBindingHelper.setOnClickListener(mIv_title_right, this);
        RxBindingHelper.setOnClickListener(tv_comm_content_comment, this);
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(mEt_mail_input_content, this);

        mSrl_omm_content_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getPresenter().getPagination(0, limit, 0, mMail_info.getId());
            }
        });
        mInputMsgDialog.setOnSenCallback(new InputMsgDialog.SendMsgCallBack() {
            @Override
            public void sendMsgCallBack(String sendMsgContent) {
                if (!TextUtils.isEmpty(sendMsgContent)) {
                    getPresenter().postSubmit(mMail_info.getId(), 0, 0, sendMsgContent);
                } else {
                    ToastUtils.showShort("回复内容不能为空");
                }
            }

            @Override
            public void onDissMissDialog(String sendMsgContent) {
                mEt_mail_input_content.setText(sendMsgContent);
            }
        });
    }

    @Override
    protected CommunityContentPresenter createPresenter(IBaseView view) {
        mCommunityPresenter = new CommunityPresenter(this);
        return new CommunityContentPresenter(this);
    }


    ThreadInfoBean mThreadInfoBean;

    @Override
    public void onThreadInfoSuccess(ThreadInfoBean bean) {
        mThreadInfoBean = bean;
        mCollectDialog.setData(bean.getExtend().getIs_collect(), bean.getUser_info().getId(), bean.getThread().getId(), getSupportFragmentManager(), mCommunityPresenter);
        if (bean.getExtend().getIs_liked().equals("0")) {
            mIv_comm_content_like.setImageResource(R.drawable.iv_mail_no_like);
        } else {
            mIv_comm_content_like.setImageResource(R.drawable.iv_mail_like);
        }
        headerFooterAdapter.clearHeaderView();
        headerFooterAdapter.addHeaderView(bean);
        headerFooterAdapter.notifyDataSetChanged();
        mRv_mail_content.postDelayed(new Runnable() {
            @Override
            public void run() {
               myRecycleview.setScrollEnabled(true);
            }
        }, 300);
    }


    @Override
    public void getPaginationSuccess(MainlBean mMainlBean) {
        //暂时无用
    }

    @Override
    public void onPostSubmitSuccess(String type) {
        // getPresenter().getThreadInfo(mMail_info.getId());//刷新点赞

        if ("取消收藏".equals(type) | "收藏".equals(type)) {
            if (mThreadInfoBean.getExtend().getIs_collect().equals("1")) {
                mThreadInfoBean.getExtend().is_collect = "0";
            } else {
                mThreadInfoBean.getExtend().is_collect = "1";
            }

            mCollectDialog.setData(mThreadInfoBean.getExtend().getIs_collect(), mThreadInfoBean.getUser_info().getId(), mThreadInfoBean.getThread().getId(), getSupportFragmentManager(), mCommunityPresenter);


        } else if ("点赞成功".equals(type)) {

            mThreadInfoBean.getExtend().setIs_liked("1");
            mThreadInfoBean.thread.liked_num++;
            headerFooterAdapter.clearHeaderView();
            headerFooterAdapter.addHeaderView(mThreadInfoBean);

        } else if ("取消点赞".equals(type)) {

            mThreadInfoBean.getExtend().setIs_liked("0");
            mThreadInfoBean.thread.liked_num--;
            headerFooterAdapter.clearHeaderView();
            headerFooterAdapter.addHeaderView(mThreadInfoBean);

        } else {

            mEt_mail_input_content.setText("");
            getPresenter().getPagination(0, limit, 0, mMail_info.getId());
            int i = Integer.parseInt(mThreadInfoBean.extend.getComment_num());
            mThreadInfoBean.extend.setComment_num((++i) + "");
            headerFooterAdapter.clearHeaderView();
            headerFooterAdapter.addHeaderView(mThreadInfoBean);
        }

        headerFooterAdapter.notifyDataSetChanged();
    }



    @Override
    public void onGetPaginationSuccess(PaginationBean bean) {
        mSrl_omm_content_refreshLayout.finishRefresh();
        mMailContentAdapter.setDatas(bean.getList());
        if (bean.getList().size() == 0) {
            headerFooterAdapter.setHeaderView(1, new IHeaderHelper() {
                @Override
                public int getItemLayoutId() {
                    return R.layout.state_comm_nodate_layout;
                }

                @Override
                public void onBind(ViewHolder holder) {

                }
            });
        } else {
            headerFooterAdapter.removeHeaderView(1);
        }
        headerFooterAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCommunityPresenter.onDestroy(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRv_mail_content.postDelayed(new Runnable() {
            @Override
            public void run() {
                getPresenter().getPagination(0, limit, 0, mMail_info.getId());
            }
        }, 400);

    }
}
