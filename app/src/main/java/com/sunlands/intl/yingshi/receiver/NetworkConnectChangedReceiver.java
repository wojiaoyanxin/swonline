package com.sunlands.intl.yingshi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.sunlands.intl.yingshi.bean.NetworkChangeEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yanxin on 2019/2/27.
 * 监听网络状态变更的广播接收器
 */

public class NetworkConnectChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION) {
            /*判断当前网络时候可用以及网络类型*/
            boolean isConnected = NetworkUtils.isConnected();
            NetworkUtils.NetworkType networkType = NetworkUtils.getNetworkType();
            EventBus.getDefault().post(new NetworkChangeEvent(isConnected, networkType));
        }
    }
}