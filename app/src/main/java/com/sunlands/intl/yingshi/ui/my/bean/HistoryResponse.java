package com.sunlands.intl.yingshi.ui.my.bean;

import java.util.List;

/**
 * 文件名: HistoryResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/11
 */
public class HistoryResponse {
    private int hasMore;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    private List<HistoryBean> today;
    private List<HistoryBean> week;
    private List<HistoryBean> more;

    public List<HistoryBean> getToday() {
        return today;
    }

    public void setToday(List<HistoryBean> today) {
        this.today = today;
    }

    public List<HistoryBean> getWeek() {
        return week;
    }

    public void setWeek(List<HistoryBean> week) {
        this.week = week;
    }

    public List<HistoryBean> getMore() {
        return more;
    }

    public void setMore(List<HistoryBean> more) {
        this.more = more;
    }

}
