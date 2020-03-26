package com.sunlands.intl.yingshi.ui.my.bean;

/**
 * Created by yanxin on 2019/2/20.
 */

public class TodayPunchCardBean {

    /**
     * sharePic : https://global.sunlands.com/append/runtime/res/shares/app_share_112_20190220.png
     * text :
     * shareId : 0
     * totalNum : 14
     */

    private String sharePic;
    private String text;
    private String shareId;
    private int totalNum;

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
