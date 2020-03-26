package com.sunlands.intl.yingshi.bean;

import com.blankj.utilcode.util.NetworkUtils;

/**
 * Created by yanxin on 2019/2/27.
 */

public class NetworkChangeEvent {

    private boolean isConnected;
    private NetworkUtils.NetworkType networkType;

    public NetworkChangeEvent(boolean isConnected, NetworkUtils.NetworkType networkType) {
        this.isConnected = isConnected;
        this.networkType = networkType;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public NetworkUtils.NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkUtils.NetworkType networkType) {
        this.networkType = networkType;
    }
}
