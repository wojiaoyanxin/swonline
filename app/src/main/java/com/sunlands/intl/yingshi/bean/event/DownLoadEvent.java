package com.sunlands.intl.yingshi.bean.event;

/**
 * @author yxin
 * @date 2020-02-24 - 17:45
 * @des
 */
public class DownLoadEvent {


    /**
     * 0   取消  编辑
     * 1  全选
     * 2  取消全选
     *
      */

    public DownLoadEvent(int type) {
        this.type = type;
    }

    public DownLoadEvent(int type, int position) {
        this.type = type;
        this.position = position;
    }

    int type;
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
