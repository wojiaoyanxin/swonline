package com.sunlands.intl.yingshi.ui.community.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunlands.comm_core.base.ibase.IBaseView;
import com.sunlands.comm_core.base.mvp.MvpActivity;
import com.sunlands.comm_core.rvadapter.HeaderFooterAdapter;
import com.sunlands.comm_core.rvadapter.IHeaderHelper;
import com.sunlands.comm_core.rvadapter.ViewHolder;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.FriendInfoBean;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.bean.MsgListBean;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.ThreadPaginationAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;
import com.sunlands.intl.yingshi.ui.community.presenter.MyCollectPresenter;
import com.sunlands.intl.yingshi.ui.community.presenter.MyFriendPresenter;

public class MyFriendActivityActivity extends MvpActivity<MyFriendPresenter> implements IMessageContract.FriendView, IMessageContract.IMyCollect, IMessageContract.MailView {

    public static final String key = "friendinfo";
    private MsgListBean.ListBean.TeacherBean.FriendsBean mFriendsBean;
    private RecyclerView mRv_my_friend;
    private HeaderFooterAdapter mHeaderFooterAdapter;

    private MyCollectPresenter myCollectPresenter;
    private CommunityPresenter mCommunityPresenter;
    ThreadPaginationAdapter mCommonAdapter;

    @Override
    public void onClick(View v) {
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_my_friend_activity;
    }

    @Override
    public void initDataBeforeView() {
        mFriendsBean = (MsgListBean.ListBean.TeacherBean.FriendsBean) getIntent().getSerializableExtra(key);
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mRv_my_friend = FBIA(R.id.rv_my_friend);
    }

    @Override
    public void initDataAfterView() {
        getPresenter().getFriendInfo(mFriendsBean.getUser_id());
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        mRv_my_friend.setLayoutManager(new LinearLayoutManager(this));
        myCollectPresenter = new MyCollectPresenter(this);
        mCommunityPresenter = new CommunityPresenter(this);
        mCommonAdapter = new ThreadPaginationAdapter(this, null, getSupportFragmentManager(), mCommunityPresenter);
        mHeaderFooterAdapter = new HeaderFooterAdapter(mCommonAdapter);
        mRv_my_friend.setAdapter(mHeaderFooterAdapter);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionBar.fitsSystemWindows(false)
                .transparentStatusBar()
                .autoStatusBarDarkModeEnable(true, 0.3f)
                .autoDarkModeEnable(true, 0.2f)
                .navigationBarEnable(false)
                .init();
        myCollectPresenter.getMine(1000, MyCollectActivity.MY_PUBLISH, mFriendsBean.getUser_id());
    }

    @Override
    protected MyFriendPresenter createPresenter(IBaseView view) {
        return new MyFriendPresenter(this);
    }

    @Override
    public void onFriendInfoSuccess(FriendInfoBean mInfoBean) {
        mHeaderFooterAdapter.addHeaderView(mInfoBean);
        mHeaderFooterAdapter.notifyDataSetChanged();
    }

    @Override
    public void getPaginationSuccess(MainlBean mMainlBean) {

    }

    @Override
    public void onPostSubmitSuccess(String type) {
        myCollectPresenter.getMine(1000, MyCollectActivity.MY_PUBLISH, mFriendsBean.getUser_id());
    }

    @Override
    public void onMineSuccess(MyCollectBean data) {
        mCommonAdapter.setUserInfo(data.getUser_info());
        mCommonAdapter.setDatas(data.getPagination().getList());
        if (data.getPagination().getList().size() == 0) {
            mHeaderFooterAdapter.setHeaderView(1, new IHeaderHelper() {
                @Override
                public int getItemLayoutId() {
                    return R.layout.state_user_nodate_layout;
                }

                @Override
                public void onBind(ViewHolder holder) {

                }
            });
        } else {
            mHeaderFooterAdapter.removeHeaderView(1);
        }
        mHeaderFooterAdapter.notifyDataSetChanged();
    }

}
