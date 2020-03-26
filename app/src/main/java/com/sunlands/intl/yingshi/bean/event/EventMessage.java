package com.sunlands.intl.yingshi.bean.event;

/**
 * 文件名: EventMessage
 * 描    述: [消息通知ID]
 * 创建人: duzzi
 * 创建时间: 2018/8/2
 */
public class EventMessage {
    public static final int EVENT_RECREATE = 0x01;
    public static final int EVENT_CLICK_LESSON_CALENDAR = 0x02;
    public static final int EVENT_REFRESH_MINE = 0x03;
    public static final int EVENT_REFRESH_MY_HANDOUT = 0x04;

    public static final int EVENT_REFRESH_LESSON_CALENDAR = 0x05;
    public static final int EVENT_REFRESH_LIVE_LESSON = 0x06;
    public static final int EVENT_REFRESH_REPLAY_LESSON = 0x07;
    public static final int EVENT_REFRESH_DISCUSS_LESSON = 0x08;
    public static final int EVENT_HIDE_PROGRESSBAR = 0x09;
    public static final int EVENT_FINISH = 0x10;
    public static final int EVENT_MSGLIST = 0x11;
    public static final int EVENT_SHOW = 0x12;
    public static final int EVENT_HIDE = 0x13;
    public static final int EVENT_REFRESH_HOME = 0x14;
    public static final int EVENT_BAR_COLOR_NORMAL = 0x15;
    public static final int EVENT_BAR_COLOR_CHANGE = 0x16;
    public static final int EVENT_MY_HANDOUT = 0x17;
    public static final int EVENT_MY_HANDOUT_DOWNLOAD = 0x18;
    public static final int EVENT_MY_HANDOUT_DOWNLOAD_FINISH = 0x19;
    public static final int EVENT_PINGJIA = 0x20;
    public static final int EVENT_TO_ACTIVITY = 0x21;
    public static final int EVENT_MUTE = 0x22;
    public static final int EVENT_UNMUTE = 0x23;
    public static final int EVENT_APPLY_FINISH = 0x24;
    public static final int EVENT_DOWNLOAD_FINISH = 0x25;
    public static final int EVENT_SMALL_PAY = 0x26;


    public int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public EventMessage(int eventType) {
        this.eventType = eventType;
    }
}
