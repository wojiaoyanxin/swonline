package com.sunlands.intl.yingshi.bean;

/**
 * 文件名: CustomPushMessage
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/16
 */
public class CustomPushMessage {

    /**
     * messageType : 1001
     * linkUrl : http://examArrangementTip?type=1
     */

    private int messageType;
    private String linkUrl;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
