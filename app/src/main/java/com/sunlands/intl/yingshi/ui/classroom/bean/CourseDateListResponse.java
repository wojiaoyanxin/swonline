package com.sunlands.intl.yingshi.ui.classroom.bean;

import java.util.List;

/**
 * 文件名: CourseDateListResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/10/11
 */
public class CourseDateListResponse {

    private List<String> courselist;
    private List<String> examtionlist;

    public List<String> getCourselist() {
        return courselist;
    }

    public void setCourselist(List<String> courselist) {
        this.courselist = courselist;
    }

    public List<String> getExamtionlist() {
        return examtionlist;
    }

    public void setExamtionlist(List<String> examtionlist) {
        this.examtionlist = examtionlist;
    }
}
