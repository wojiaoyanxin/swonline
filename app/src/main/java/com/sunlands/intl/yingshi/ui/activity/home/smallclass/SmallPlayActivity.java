package com.sunlands.intl.yingshi.ui.activity.home.smallclass;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.barlibrary.ImmersionBar;
import com.shangde.lepai.uilib.statusbar.StatusBarHelper;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.sunlands.intl.yingshi.common.MyActivity;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.dialog.DialogUtils;
import com.sunlands.intl.yingshi.ui.home.bean.SmallClassWatchBean;
import com.sunlands.intl.yingshi.util.DensityUtil;
import com.sunlands.intl.yingshi.util.Utils;
import com.sunlands.sunlands_live_sdk.SunlandsLiveSdk;
import com.sunlands.sunlands_live_sdk.listener.ImListener;
import com.sunlands.sunlands_live_sdk.listener.OnLiveListener;
import com.sunlands.sunlands_live_sdk.listener.PlayerListener;
import com.sunlands.sunlands_live_sdk.offline.OfflineManager;
import com.sunlands.sunlands_live_sdk.websocket.WebSocketClient;
import com.sunlands.sunlands_live_sdk.websocket.packet.im.ImLiveForbidStatus;
import com.sunlands.sunlands_live_sdk.websocket.packet.im.ImLiveLoginRes;
import com.sunlands.sunlands_live_sdk.websocket.packet.im.ImLiveReceiveMsgNotify;
import com.sunlands.sunlands_live_sdk.websocket.packet.im.ImLiveSendMsgRes;
import com.sunlands.sunlands_live_sdk.websocket.packet.im.PullVideoMsgRecord;
import com.sunlands.sunlands_live_sdk.websocket.packet.im.base.ImLiveUserInOutNotify;
import com.sunlands.sunlands_live_sdk.websocket.packet.roomclient.BeginLive;
import com.sunlands.sunlands_live_sdk.websocket.packet.roomclient.ContinueLive;
import com.sunlands.sunlands_live_sdk.websocket.packet.roomclient.EndLive;
import com.sunlands.sunlands_live_sdk.websocket.packet.roomclient.PauseLive;
import com.sunlands.sunlands_live_sdk.websocket.packet.roomclient.PlatformInitParam;
import com.sunlands.sunlands_live_sdk.websocket.packet.roomclient.SuiTangKaoNotify;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IMediaPlayer;

public class SmallPlayActivity extends MyActivity<Object> {

    private ViewGroup pptContainer;
    private ViewGroup videoContainer;
    private PlatformInitParam platformInitParam;
    private SunlandsLiveSdk sunlandsLiveSdk;
    private VideoControlView videoControlView;
    SmallClassWatchBean classWatchData;
    private float currentRate = 1.0f;
    private int currentPosition;
    private View ll_no_net;
    private RelativeLayout rl_root;
    private EditText etMsg;
    private TextView tvSend;
    private View ll_send_msg;

    List<PullVideoMsgRecord.MessageRecord> list = new ArrayList<>();
    private View rl_loading;

    public static void show(Context context, String courseId, String title) {
        Intent intent = new Intent(context, SmallPlayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.Key.KEY_COURSE_ID, courseId);
        intent.putExtra(Constants.Key.KEY_ACTION, title);
        context.startActivity(intent);
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void findView(View inflateView, Bundle savedInstanceState) {
        super.findView(inflateView, savedInstanceState);
        pptContainer = FBIA(R.id.pptContainer);
        videoContainer = FBIA(R.id.videoContainer);
        ll_no_net = FBIA(R.id.ll_no_net);
        videoContainer = FBIA(R.id.videoContainer);
        rl_root = FBIA(R.id.rl_root);
        etMsg = FBIA(R.id.et_msg);
        tvSend = FBIA(R.id.tv_send);
        rl_root = FBIA(R.id.rl_root);
        ll_send_msg = FBIA(R.id.ll_send_msg);
        rl_loading = FBIA(R.id.rl_loading);
        if (!CommonUtils.hasNetWorkConection()) {
            ToastUtils.showShort("请检查网络设置");
            // ll_no_net.setVisibility(View.VISIBLE);
            rl_loading.setVisibility(View.GONE);
        }
        FBIA(R.id.iv_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!CommonUtils.hasNetWorkConection()) {
                    ToastUtils.showShort("请检查网络设置");
                    return;
                }
            }
        });
        FBIA(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etMsg.getText().toString().trim())) {
                    ToastUtils.showShort("发送内容不能为空");
                    return;
                }
                sunlandsLiveSdk.sendMsg(etMsg.getText().toString());
            }
        });
    }

    @Override
    public void initView(View inflateView, Bundle savedInstanceState) {
        super.initView(inflateView, savedInstanceState);
        ll_no_net.bringToFront();
        videoControlView = new VideoControlView(SmallPlayActivity.this);
        videoControlView.setDanmakuVisibility(false);
        videoControlView.setVideoChangeVisibility(false);
        videoControlView.setTitle(getIntent().getStringExtra(Constants.Key.KEY_ACTION));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        sunlandsLiveSdk = SunlandsLiveSdk.getInstance();
        getDataOnNet(getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID));
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(false)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(false, 0.2f)
                .autoStatusBarDarkModeEnable(true, 0.3f)
                .autoDarkModeEnable(true, 0.2f)
                .init();
    }

    @Override
    public void initListener() {
        super.initListener();
        // KeyboardUtils.registerSoftInputChangedListener(this, onSoftInputChangedListener);

    }

    KeyboardUtils.OnSoftInputChangedListener onSoftInputChangedListener = new KeyboardUtils.OnSoftInputChangedListener() {
        @Override
        public void onSoftInputChanged(int height) {
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ll_send_msg.getLayoutParams();
//            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//            layoutParams.bottomMargin = height;
//            ll_send_msg.setLayoutParams(layoutParams);
        }
    };

    @Override
    public void onSuccess(Object bean) {
        super.onSuccess(bean);
        if (bean instanceof SmallClassWatchBean) {
            SmallClassWatchBean smallClassWatchBean = (SmallClassWatchBean) bean;
            this.classWatchData = smallClassWatchBean;
            //  if (hasNet) {
            startPlay(classWatchData);
            // }
        }
    }


    /**
     * //1 未开课、2 直播中、3 暂停、 4 已结束（老师已经点击下课，视频已经合并完成）、5 合并中（老师已经点击下课，但视频正在合并中）
     * <p>
     * 教学端 0 - 尚直播 1
     * 教学端 1 - 尚直播 2,3
     * 教学端 2 - 尚直播 5
     * 教学端 3 - 尚直播 4
     */

    public void startPlay(SmallClassWatchBean watchBean) {
        SmallClassWatchBean.LiveDataBean bean = watchBean.getLiveData();
        platformInitParam = new PlatformInitParam(bean.getSign(), bean.getPartnerId(), Long.parseLong(bean.getRoomId() + ""), bean.getUserId() + "", bean.getUserName(),
                bean.getUserRole(), bean.getUserAvatar(), bean.getTs());
        sunlandsLiveSdk.initSDK(pptContainer, videoContainer,
                bean.getStatus() == 1 ? true : false, AppUtils.getAppVersionName(), platformInitParam);
        sunlandsLiveSdk.setMediaController(videoControlView);
        videoControlView.isLive(bean.getStatus() == 1 ? true : false);
        videoControlView.setControlListener(new VideoControlView.ControlListener() {
            @Override
            public void onBackClicked() {
                finish();
            }

            @Override
            public void fullScreen() {
                //如果是竖屏
                if (isPortrait()) {
                    setFullScreen(true);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置为全屏
                } else {//全屏
                    setFullScreen(false);
                    StatusBarHelper.setStatusBarLightMode(SmallPlayActivity.this);
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }

            @Override
            public void changeSubFloat() {

            }

            @Override
            public void changeMainSub() {

            }

            @Override
            public void showDanmaku(boolean b) {

            }

            @Override
            public void setSpeed(float v) {
                currentRate = v;
                sunlandsLiveSdk.setSpeed(v);
            }

            @Override
            public void currentPosition(int position) {
                if (position > 0) {
                    currentPosition = position;
                }
            }
        });
        sunlandsLiveSdk.setImListener(new ImListener() {
            @Override
            public void onImLoginSuccess(ImLiveLoginRes.DataBean dataBean) {
                Log.e("sunland_live_sdk", "onImLoginSuccess");
            }

            @Override
            public void onImLoginFailed(int i, String s) {
                Log.e("sunland_live_sdk", "onImLoginFailed");
            }

            @Override
            public void onSendMsgSuccess(ImLiveSendMsgRes.DataBean dataBean) {
                etMsg.setText("");
                list.clear();
                PullVideoMsgRecord.MessageRecord messageRecord = new PullVideoMsgRecord.MessageRecord();
                messageRecord.setMessage_content(dataBean.getMsgData());
                messageRecord.setUser_name(dataBean.getName());
                messageRecord.setUser_portrait(dataBean.getPortrait());
                list.add(messageRecord);
                isRefresh = false;
                setData(list, true);
                baseQuickAdapter.loadMoreEnd(true);
                KeyboardUtils.hideSoftInput(ll_send_msg);
                mRecyclerView.smoothScrollToPosition(baseQuickAdapter.getItemCount());
            }

            /**
             14：消息发送间隔超过限制2s/次
             6：用户被禁言
             7: 敏感词屏蔽
             13：消息长度超过限制
             * */
            @Override
            public void onSendMsgFailed(int i, ImLiveSendMsgRes.DataBean dataBean) {

                if (i == 7) {
                    ToastUtils.showShort("存在违规词，请重新编辑");
                } else if (i == 14) {
                    ToastUtils.showShort("消息发送间隔超过限制");
                } else if (i == 6) {
                    ToastUtils.showShort("您已被被禁言");
                } else if (i == 13) {
                    ToastUtils.showShort("消息长度超过限制");
                }
            }

            @Override
            public void onReceiveMsgNotify(ImLiveReceiveMsgNotify.DataBean dataBean) {
                list.clear();
                PullVideoMsgRecord.MessageRecord messageRecord = new PullVideoMsgRecord.MessageRecord();
                messageRecord.setMessage_content(dataBean.getMsgData());
                messageRecord.setUser_name(dataBean.getName());
                messageRecord.setUser_portrait(dataBean.getPortrait());
                list.add(messageRecord);
                isRefresh = false;
                setData(list, true);
                baseQuickAdapter.loadMoreEnd(true);
                mRecyclerView.smoothScrollToPosition(baseQuickAdapter.getItemCount());
            }

            @Override
            public void onChatRoomDissolve() {

            }

            @Override
            public void onUserBatchOffline(int i) {

            }

            @Override
            public void onUserInOutNotify(ImLiveUserInOutNotify.DataBean dataBean) {

            }

            @Override
            public void onImKickOutNotify(int i) {

            }

            @Override
            public void onForbidStatusNotify(ImLiveForbidStatus.DataBean dataBean, boolean b) {

            }

            @Override
            public void onVideoMsgRecordFetch(List<PullVideoMsgRecord.MessageRecord> list) {

                //拉取重播消息记录，每隔10s拉取一次，每次拉取当前时间至10s后的所有聊天记录（前开后闭），包含当前时间的，不包含10s时的
                isRefresh = false;
                setData(list, true);
                baseQuickAdapter.loadMoreEnd(true);
            }

            @Override
            public void onVideoMsgRecordFetchFailed(String s) {

            }
        });
        sunlandsLiveSdk.setPlayerListener(new PlayerListener() {
            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Utils.getMainThreadHandler().postDelayed(mReportProgressRunnable, Constants.REPORT_INTERVAL);
                FBIA(R.id.tv_change).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sunlandsLiveSdk.exchangeVideoAndPpt();
                    }
                });
                Log.e("sunland_live_sdk", "onPrepared");
                if (classWatchData.getLiveData().getLastProgress() > 0 && watchBean.getLiveData().getStatus() != 1) {
                    sunlandsLiveSdk.seekTo(classWatchData.getLiveData().getLastProgress() * 1000);
                }
            }

            @Override
            public void onVideoRenderingStart() {
                videoControlView.hide();
                rl_loading.setVisibility(View.GONE);
                Log.e("sunland_live_sdk", "onVideoRenderingStart");
            }

            @Override
            public void onVideoBufferingStart() {
                Log.e("sunland_live_sdk", "onVideoBufferingStart");
                if (currentPosition > 0) {
                    sunlandsLiveSdk.seekTo(currentPosition);
                }
                rl_loading.setVisibility(View.GONE);
            }

            @Override
            public void onVideoBufferingEnd() {
                Log.e("sunland_live_sdk", "onVideoBufferingEnd");

            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.e("sunland_live_sdk", currentPosition + " = onCompletion");
                Log.e("sunland_live_sdk", sunlandsLiveSdk.getDuration() + " = totla");
                baseQuickAdapter.setNewData(null);
                currentPosition = 0;
            }

            @Override
            public void onVideoTypeChange(int i) {
                Log.e("sunland_live_sdk", "onVideoTypeChange");

            }
        });
        sunlandsLiveSdk.setOnLiveListener(new OnLiveListener() {
            @Override
            public void onVideoWebSocketStateChanged(WebSocketClient.State state) {

            }

            @Override
            public void onImWebSocketStateChanged(WebSocketClient.State state) {

            }

            @Override
            public void onBeginLive(BeginLive beginLive) {
                ToastUtils.showShort("开始上课");
            }

            @Override
            public void onEndLive(EndLive endLive) {
                DialogUtils.exit(SmallPlayActivity.this, new DialogUtils.onClick() {
                    @Override
                    public void sure() {
                        finish();
                    }
                });
            }

            @Override
            public void onPauseLive(PauseLive pauseLive) {
                ToastUtils.showShort("暂停中，休息一下马上回来");
            }

            @Override
            public void onContinueLive(ContinueLive continueLive) {

                ToastUtils.showShort("继续上课");

            }

            @Override
            public void onVideoKickOutNotify(int i) {

            }

            @Override
            public void onReceiveSuiTangKaoNotify(SuiTangKaoNotify suiTangKaoNotify) {

            }

            @Override
            public void onUserCountChange(int i) {

            }
        });
        boolean offlineCompleted = OfflineManager.getInstance().isOfflineCompleted(platformInitParam.getLiveId() + "");
        if (bean.getStatus() == 1) {
            ll_send_msg.setVisibility(View.VISIBLE);
        } else {
            ll_send_msg.setVisibility(View.GONE);
        }
        if (watchBean.getLiveData().getIsPause() == 1) {
            ToastUtils.showShort("暂停中，休息一下马上回来");
            rl_loading.setVisibility(View.GONE);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewGroup.LayoutParams layoutParams = rl_root.getLayoutParams();
        if (isPortrait()) {
            layoutParams.height = DensityUtil.dip2px(this, 200);
        } else {
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        rl_root.setLayoutParams(layoutParams);
        KeyboardUtils.hideSoftInput(etMsg);
    }

    private boolean isPortrait() {
        int mOrientation = getApplicationContext().getResources().getConfiguration().orientation;
        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sunlandsLiveSdk.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        sunlandsLiveSdk.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.getMainThreadHandler().removeCallbacks(mReportProgressRunnable);
        //  KeyboardUtils.unregisterSoftInputChangedListener(this);
        sunlandsLiveSdk.onDestroy();
        sunlandsLiveSdk = null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_small_play;
    }

    @Override
    public String getTitleText() {
        return null;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return new SmallClassChatAdapter();
    }

    @Override
    public void inflateStateView(String tv, int imageId) {


    }

    Runnable mReportProgressRunnable = new Runnable() {
        @Override
        public void run() {
            if (isFinishing()) {
                return;
            }
            getDataOnNet(sunlandsLiveSdk.getCurrentPosition() / 1000, classWatchData.getLiveData().getStatus() == 1 ? 1 : 0, getIntent().getStringExtra(Constants.Key.KEY_COURSE_ID), currentRate);
            Utils.getMainThreadHandler().postDelayed(mReportProgressRunnable, Constants.REPORT_INTERVAL);
        }
    };

//    @Override
//    public void netChange(boolean isConnected, NetworkUtils.NetworkType networkType) {
//        super.netChange(isConnected, networkType);
//        if (sunlandsLiveSdk == null) {
//            return;
//        }
//        if(isConnected) {
//            sunlandsLiveSdk.start();
//            videoControlView.show();
//        }else {
//            sunlandsLiveSdk.pause();
//        }
//    }

}