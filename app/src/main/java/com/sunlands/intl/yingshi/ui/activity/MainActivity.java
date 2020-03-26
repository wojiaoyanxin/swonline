package com.sunlands.intl.yingshi.ui.activity;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.shangde.lepai.uilib.bottombar.BottomBarItem;
import com.shangde.lepai.uilib.bottombar.BottomBarLayout;
import com.shangde.lepai.uilib.statusbar.StatusBarHelper;
import com.sunlands.comm_core.base.DActivity;
import com.sunlands.comm_core.entity.LoginTimeOut;
import com.sunlands.comm_core.utils.rx.rxjava.RxJavaUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.bean.event.EventMessage;
import com.sunlands.intl.yingshi.bean.event.PublishEvent;
import com.sunlands.intl.yingshi.greendao.helper.DBSaveUtils;
import com.sunlands.intl.yingshi.helper.LoginInOutHelper;
import com.sunlands.intl.yingshi.helper.UpdataRequestHelper;
import com.sunlands.intl.yingshi.ui.classroom.view.ClassroomFragment;
import com.sunlands.intl.yingshi.ui.community.view.CommunityFragment;
import com.sunlands.intl.yingshi.ui.home.view.MyHomeFragment;
import com.sunlands.intl.yingshi.ui.login.view.NewLoginActivity;
import com.sunlands.intl.yingshi.ui.my.view.MyFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

import static com.sunlands.intl.yingshi.util.Utils.putFirstLoadTime;
import static com.sunlands.intl.yingshi.util.Utils.putLocation;

/**
 * app 主页
 */

public class MainActivity extends DActivity {

    BottomBarLayout mBottomBarLayout;
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragmentsList;
    public int mCurrentItem = 0;
    private long mFirstTime;


    public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().statusBarDarkFont(false).autoDarkModeEnable(true).navigationBarEnable(false).init();
        StatusBarHelper.setStatusBarLightMode(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginTimeOut(LoginTimeOut timeOut) {
        ToastUtils.showShort("登录超时请重新登录");
        LoginInOutHelper.loginOut(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventMessage eventMessage) {
        if (eventMessage.getEventType() == EventMessage.EVENT_RECREATE) {
            // recreate();
            mBottomBarLayout.setCurrentItem(1);
            selectTab(1);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMain(PublishEvent publishEvent) {
        mBottomBarLayout.setCurrentItem(2);
        selectTab(2);
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        ActivityUtils.finishActivity(NewLoginActivity.class);
        mFragmentManager = getSupportFragmentManager();
        //  final HomeFragment homeFragment = HomeFragment.newInstance();
        final MyHomeFragment homeFragment = MyHomeFragment.newInstance();
        final ClassroomFragment classRoomFragment = ClassroomFragment.newInstance("");
        final CommunityFragment communityFragment = CommunityFragment.newInstance();
        MyFragment mineFragment = MyFragment.newInstance();
        mFragmentsList = new ArrayList<>();
        mFragmentsList.add(homeFragment);//首页
        mFragmentsList.add(classRoomFragment);//课堂
        // mFragmentsList.add(communityFragment);//社区
        mFragmentsList.add(mineFragment);///我的
        for (Fragment fragment : mFragmentsList) {
            mFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commitAllowingStateLoss();
        }
        mBottomBarLayout.setOnItemSelectedListener(onItemSelectedListener);
        if (mBottomBarLayout.getCurrentItem() != mCurrentItem) {
            mBottomBarLayout.setCurrentItem(mCurrentItem);
        }
        selectTab(mCurrentItem);
    }

    BottomBarLayout.OnItemSelectedListener onItemSelectedListener = new BottomBarLayout.OnItemSelectedListener() {
        @Override
        public void onItemSelected(BottomBarItem bottomBarItem, int previousPosition, int currentPosition) {
            selectTab(currentPosition);
        }
    };

    public void selectTab(int index) {
        mCurrentItem = index;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        try {
            for (int i = 0; i < mFragmentsList.size(); i++) {
                fragmentTransaction.hide(mFragmentsList.get(i));
            }
            fragmentTransaction.show(mFragmentsList.get(index));

            switch (index) {
                case 0:
                    if (mImmersionBar != null) {
                        mImmersionBar.fitsSystemWindows(false).transparentStatusBar().statusBarDarkFont(false).navigationBarEnable(false).init();
                        StatusBarHelper.setStatusBarLightMode(this);
                    }
                    break;
                case 1:
                    mImmersionBar.fitsSystemWindows(false).statusBarColor(R.color.cl_ffffff).autoDarkModeEnable(true).navigationBarEnable(false).init();
                    break;
                case 3:
                    mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.cl_ffffff).autoDarkModeEnable(true).navigationBarEnable(false).init();
                    break;
                case 2:
                    mImmersionBar.fitsSystemWindows(false).transparentStatusBar().autoDarkModeEnable(true).navigationBarEnable(false).init();
                    break;
                default:
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            ae.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentTransaction.commitAllowingStateLoss();

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mFirstTime > 2000) {
                ToastUtils.showShort("再按一次退出程序");
                mFirstTime = System.currentTimeMillis();
            } else {
                super.onKeyDown(keyCode, event);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume() {
        super.onResume();
        RxJavaUtils.delay((long) 0.5, new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                KeyboardUtils.hideSoftInput(MainActivity.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public int getCreateViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        mBottomBarLayout = FBIA(R.id.bottom_bar_layout);
    }

    @Override
    public void initDataAfterView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "通知提醒";
            String channelName = "通知提醒";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
        }
        UpdataRequestHelper updataRequestHelper = new UpdataRequestHelper();
        //完善信息校验
//        updataRequestHelper.getInfoStatus(new UpdataRequestHelper.IUserInfoStatus() {
//            @Override
//            public void showDialog() {
//                if (SPHelper.getAPPStartCount() == 0 || SPHelper.getAPPStartCount() % 10 == 0) {
//                    CompleteUserInfoDialog.show(MainActivity.this);
//                }
//                SPHelper.setAPPStartCount((SPHelper.getAPPStartCount() + 1));
//            }
//        });
        //版本更新
        // updataRequestHelper.getAPPUpdata(getSupportFragmentManager());
        // MiPushClient.registerPush(this, Constants.MiPush.APP_ID, Constants.MiPush.APP_KEY);
        //MiPushClient.enablePush(this);
    }


    @Override
    public void initListener() {
        try {
            DBSaveUtils.removeOldDataToNew();
            putFirstLoadTime();
            putLocation();
        } catch (Exception x) {

        }

    }

}
