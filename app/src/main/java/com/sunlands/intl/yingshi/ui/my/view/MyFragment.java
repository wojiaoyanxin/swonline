package com.sunlands.intl.yingshi.ui.my.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.helper.EventBusHelper;
import com.sunlands.comm_core.helper.RxBindingHelper;
import com.sunlands.comm_core.helper.UserInfoHelper;
import com.sunlands.comm_core.net.BaseModel;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.common.CommonFragment;
import com.sunlands.intl.yingshi.helper.LoginUserInfoHelper;
import com.sunlands.intl.yingshi.ui.my.bean.UserInfo;
import com.sunlands.intl.yingshi.util.GlideUtils;
import com.sunlands.intl.yingshi.util.SPHelper;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


public class MyFragment extends CommonFragment<UserInfo> {

    ImageView mBtnSetting;
    ImageView mIvAvatar;
    TextView mTvNickname;
    TextView zhuanye;
    String mStringServiceTel;
    RelativeLayout shenqingjilu;
    RelativeLayout myOrder;
    RelativeLayout guanyuwomen;
    RecyclerView recyclerView;
    RelativeLayout myHandout;
    RelativeLayout myPrivate;
    RelativeLayout ll_contact_service;
    RelativeLayout rlLocationWatch;
    TextView more;
    ImageView imgNextArrow;
    private UserInfo mUserInfo;
    private final int REQUEST_CODE = 0x101;

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);

        mBtnSetting = FBIF(R.id.btn_setting);
        mIvAvatar = FBIF(R.id.iv_avatar);
        mTvNickname = FBIF(R.id.tv_nickname);
        zhuanye = FBIF(R.id.zhuanye);
        shenqingjilu = FBIF(R.id.shenqingjilu);
        guanyuwomen = FBIF(R.id.guanyuwomen);
        recyclerView = FBIF(R.id.recycler_view);
        myOrder = FBIF(R.id.my_order);
        more = FBIF(R.id.more);
        myHandout = FBIF(R.id.myhandout);
        myPrivate = FBIF(R.id.my_private);
        imgNextArrow = FBIF(R.id.imgNextArrow);
        ll_contact_service = FBIF(R.id.ll_contact_service);
        rlLocationWatch = FBIF(R.id.rl_location_watch);

    }

    @Override
    public void initListener() {
        super.initListener();
        RxBindingHelper.setOnClickListener(mBtnSetting, this);
        RxBindingHelper.setOnClickListener(mIvAvatar, this);
        RxBindingHelper.setOnClickListener(mTvNickname, this);
        RxBindingHelper.setOnClickListener(shenqingjilu, this);
        RxBindingHelper.setOnClickListener(guanyuwomen, this);
        RxBindingHelper.setOnClickListener(mBtnSetting, this);
        RxBindingHelper.setOnClickListener(imgNextArrow, this);
        RxBindingHelper.setOnClickListener(myPrivate, this);
        RxBindingHelper.setOnClickListener(myHandout, this);
        RxBindingHelper.setOnClickListener(more, this);
        RxBindingHelper.setOnClickListener(myOrder, this);
        // RxBindingHelper.setOnClickListener(ll_contact_service, this);
        RxBindingHelper.setOnClickListener(rlLocationWatch, this);
    }

    public MyFragment() {

    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        mSwipeRefreshLayout.setOnRefreshListener(this::getData);
        mStringServiceTel = "01052859623";
    }

    @Override
    public void onSuccess(UserInfo userInfo) {

        mSwipeRefreshLayout.setRefreshing(false);
        mUserInfo = userInfo;
        mTvNickname.setText(TextUtils.isEmpty(userInfo.getName()) ? userInfo.getNickname() : userInfo.getName());
        GlideUtils.loadRoundAvatarImage(mContext, userInfo.getHeadUrl(), mIvAvatar);
        LoginUserInfoHelper.getInstance().updataAvatar(userInfo.getHeadUrl());
        UserInfoHelper.getInstance().setAvatar(userInfo.getHeadUrl());
        zhuanye.setText("班型: " + userInfo.getCurrentUniversity());
        if (TextUtils.isEmpty(userInfo.getCurrentUniversity())) {
            zhuanye.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(SPHelper.getUserPhone())) {
            SPHelper.setUserPhone(userInfo.getTel());
        }
        EventBusHelper.post(userInfo);
        //     List<UserInfo.ViewListBean> viewList = userInfo.getViewList();
        //    LocalWatchAdapter localWatchAdapter = new LocalWatchAdapter();
        //     recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        //     recyclerView.setAdapter(localWatchAdapter);
        //   localWatchAdapter.setNewData(viewList);
//        localWatchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                try {
//                    UserInfo.ViewListBean viewListBean = (UserInfo.ViewListBean) adapter.getData().get(position);
//                    EnterPlayerUtils.enterClass(mContext, Integer.parseInt(viewListBean.getCourseId()));
//                } catch (Exception e) {
//                    ToastUtils.showShort("课程异常");
//                }
//
//            }
//        });
    }

    @Override
    public void getDataFailed(BaseModel model) {
        super.getDataFailed(model);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }


    protected void getData() {
        if (!CommonUtils.hasNetWorkConection()) {
            mSwipeRefreshLayout.setRefreshing(false);
            ToastUtils.showShort("请检查网络设置");
            return;
        }
        getDataNet(true, "");
    }


    @SuppressLint("CheckResult")
    public void onClickContactService() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.CALL_PHONE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + mStringServiceTel));
                        if (ActivityCompat.checkSelfPermission(mContext,
                                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intent);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        ToastUtils.showShort("未授予权限");
                    }
                })
                .start();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        int id = v.getId();
        if (id == R.id.btn_setting) {
            SettingsActivity.show((Activity) mContext, REQUEST_CODE);
        } else if (id == R.id.imgNextArrow) {//  ActivityUtils.startActivity(UserInfoActivity.class);
        } else if (id == R.id.iv_avatar || id == R.id.tv_nickname) {//跳转到编辑页面
            //  toEditUserInfo();
        } else if (id == R.id.ll_native_daka) {  //wode打卡

            //  ActivityUtils.startActivity(MyDaKaActivity.class);
        } else if (id == R.id.ll_contact_service) {
            onClickContactService();
        } else if (id == R.id.my_private) { //隐私

            ActivityUtils.startActivity(MyPrivateActivity.class);
        } else if (id == R.id.my_order) { //我的订单
            ActivityUtils.startActivity(MyOrderActivity.class);
            //    ActivityUtils.startActivity(SmallPlayActivity.class);
        } else if (id == R.id.shenqingjilu) {
            ActivityUtils.startActivity(MyApplyActivity.class);
        } else if (id == R.id.guanyuwomen) {
            ActivityUtils.startActivity(AboutActivity.class);
        } else if (id == R.id.more || id == R.id.rl_location_watch) {
            ActivityUtils.startActivity(HistoryActivity.class);
        } else if (id == R.id.myhandout) {//   ActivityUtils.startActivity(MyHandoutActivity.class);
            ActivityUtils.startActivity(MyMaterialActivity.class);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getDataNet(true, "");

    }

    @Subscribe(threadMode = ThreadMode.MAIN)

    public void onEventMainThread(EventMessage eventMessage) {

        if (eventMessage.getEventType() == EventMessage.EVENT_TO_ACTIVITY) {
            toEditUserInfo();
        }
    }

    private void toEditUserInfo() {
        toUserInfoSettingActivity();
    }

    public void toUserInfoSettingActivity() {
        if (mUserInfo == null) {
            return;
        }
        UserInfoSettingActivity.show(mContext);
    }
}
