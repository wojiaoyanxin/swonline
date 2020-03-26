package com.sunlands.intl.yingshi.ui.my.bean;

import java.util.List;

/**
 * 文件名: HistoryMoreResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/11
 */
public class HistoryMoreResponse {

    /**
     * hasMore : 0
     * courseList : [{"courseId":"217","title":"填空题测试","subject":"定量决策","teacher":"喻天骥","poster":"https://education-1254383113.file.myqcloud.com/84694666209148.jpg"}]
     */

    private int hasMore;
    private List<HistoryBean> courseList;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<HistoryBean> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<HistoryBean> courseList) {
        this.courseList = courseList;
    }


}
