package com.sunlands.intl.yingshi.ui.my.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.MainlBean;
import com.sunlands.intl.yingshi.bean.MyCollectBean;
import com.sunlands.intl.yingshi.bean.event.PublishEvent;
import com.sunlands.intl.yingshi.common.CommonActivity;
import com.sunlands.intl.yingshi.ui.community.IMessageContract;
import com.sunlands.intl.yingshi.ui.community.adapter.ThreadPaginationAdapter;
import com.sunlands.intl.yingshi.ui.community.presenter.CommunityPresenter;
import com.sunlands.intl.yingshi.ui.community.presenter.MyCollectPresenter;
import com.sunlands.intl.yingshi.ui.community.view.MyCollectActivity;
import com.sunlands.intl.yingshi.ui.my.bean.UserInfo;
import com.sunlands.intl.yingshi.util.GlideUtils;


public class UserInfoActivity extends CommonActivity<UserInfo> implements IMessageContract.IMyCollect, IMessageContract.MailView {

    ImageView ivAvatar;
    TextView tvNickname;
    TextView zhuanye;
    TextView sex;
    TextView work;
    TextView company;
    TextView currentPosition;
    TextView city;
    private ImageView mIv_title_back;
    private UserInfo mUserInfo;
    private MyCollectPresenter myCollectPresenter;
    private CommunityPresenter mCommunityPresenter;
    ThreadPaginationAdapter mCommonAdapter;
    private RecyclerView mRv_my_collect_list;
    private View noDataView;
    private View tv_join;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        noDataView = FBIA(R.id.ll_no_data);
        mIv_title_back = FBIA(R.id.btn_back);
        ivAvatar = FBIA(R.id.iv_avatar);
        tvNickname = FBIA(R.id.tv_nickname);
        zhuanye = FBIA(R.id.zhuanye);
        sex = FBIA(R.id.sex);
        work = FBIA(R.id.work);
        company = FBIA(R.id.company);
        currentPosition = FBIA(R.id.currentPosition);
        city = FBIA(R.id.city);
        mRv_my_collect_list = FBIA(R.id.rv_my_collect_list);
        mRv_my_collect_list.setLayoutManager(new LinearLayoutManager(this));
        FBIA(R.id.tv_join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusHelper.post(new PublishEvent());
                onBackPressed();
            }
        });
    }

    @Override
    public void initDataAfterView() {
        super.initDataAfterView();
        myCollectPresenter = new MyCollectPresenter(this);
        mCommunityPresenter = new CommunityPresenter(this);
        mCommonAdapter = new ThreadPaginationAdapter(this, null, getSupportFragmentManager(), mCommunityPresenter);
        mRv_my_collect_list.setAdapter(mCommonAdapter);
    }

    @Override
    public void onSuccess(UserInfo userInfo) {
        mUserInfo = userInfo;
        tvNickname.setText(userInfo.getName());
        GlideUtils.loadRoundAvatarImage(UserInfoActivity.this, userInfo.getHeadUrl(), ivAvatar);
        zhuanye.setText("班型: " + userInfo.getCurrentUniversity());
        if (TextUtils.isEmpty(userInfo.getCurrentUniversity())) {
            zhuanye.setVisibility(View.GONE);
        }
        work.setText(userInfo.getIndustry());
        currentPosition.setText(userInfo.getPosition());
        city.setText(userInfo.getCity());
        sex.setText(userInfo.getSex());
        company.setText(userInfo.getCompany());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_info;
    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(mIv_title_back, this);
        RxBindingHelper.setOnClickListener(ivAvatar, this);
        RxBindingHelper.setOnClickListener(tvNickname, this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        if (id == R.id.btn_back) {
            onBackPressed();
        } else if (id == R.id.iv_avatar || id == R.id.tv_nickname) {
            toUserInfoSettingActivity();
        }

    }

    public void toUserInfoSettingActivity() {
        if (mUserInfo == null) {
            return;
        }
        UserInfoSettingActivity.show(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().autoDarkModeEnable(true).navigationBarEnable(false).init();
        getDataNet(true, "");
      //  myCollectPresenter.getMine(1000, MyCollectActivity.MY_PUBLISH,"");
    }

    @Override
    public void getPaginationSuccess(MainlBean mMainlBean) {

    }

    @Override
    public void onPostSubmitSuccess(String type) {
        myCollectPresenter.getMine(1000, MyCollectActivity.MY_PUBLISH,"");
    }

    @Override
    public void onMineSuccess(MyCollectBean data) {
        noDataView.setVisibility(View.GONE);
        mCommonAdapter.setUserInfo(data.getUser_info());
        mCommonAdapter.setDatas(data.getPagination().getList());
        if (data.getPagination().getList().size() == 0) {
            noDataView.setVisibility(View.VISIBLE);
        }
    }
}
