package com.sunlands.intl.yingshi.ui.home.bean;

import java.util.List;

/**
 * @author yxin
 * @date 2019-12-04 - 14:58
 * @des
 */
public class TodayHotListBean {


    /**
     * list : [{"sid":1394,"title":"免联考MBA需要参加什么考试？国内承认吗？","view_num":136,"time_str":"239天前","head_img":"https://bj-test-1254383113.file.myqcloud.com/15543458659506.png","url":""}]
     * total : 14
     */

    private int total;
    private List<HomeDataResponse.NewBean> list;
    private int hasMore;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<HomeDataResponse.NewBean> getList() {
        return list;
    }

    public void setList(List<HomeDataResponse.NewBean> list) {
        this.list = list;
    }

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

}
