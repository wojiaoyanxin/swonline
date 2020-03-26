package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean;

import java.util.List;

/**
 * Created by yanxin on 2019/4/9.
 */

public class CommonLeakBean {



    private List<LeakOtherBean.ListBean> list;
    private String  tab;

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public List<LeakOtherBean.ListBean> getList() {
        return list;
    }

    public void setList(List<LeakOtherBean.ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * rid : 364
         * startTime : 2019-03-11 11:18:11
         * endTime : 2019-03-11 11:40:11
         * packageName : MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd
         * startTimeFormat : 3月11日 11:18
         * endTimeFormat : 3月11日 11:40
         * courseId : 0
         * hasJoined : 1
         * hasBegin : 1
         * hasEnd : 1
         * canRepeat : 1
         */

        private int rid;
        private String startTime;
        private String endTime;
        private String packageName;
        private String startTimeFormat;
        private String endTimeFormat;
        private int courseId;
        private int hasJoined;
        private int hasBegin;
        private int hasEnd;
        private int canRepeat;
        private String title;
        private String tab;
        private int layout;

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getStartTimeFormat() {
            return startTimeFormat;
        }

        public void setStartTimeFormat(String startTimeFormat) {
            this.startTimeFormat = startTimeFormat;
        }

        public String getEndTimeFormat() {
            return endTimeFormat;
        }

        public void setEndTimeFormat(String endTimeFormat) {
            this.endTimeFormat = endTimeFormat;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getHasJoined() {
            return hasJoined;
        }

        public void setHasJoined(int hasJoined) {
            this.hasJoined = hasJoined;
        }

        public int getHasBegin() {
            return hasBegin;
        }

        public void setHasBegin(int hasBegin) {
            this.hasBegin = hasBegin;
        }

        public int getHasEnd() {
            return hasEnd;
        }

        public void setHasEnd(int hasEnd) {
            this.hasEnd = hasEnd;
        }

        public int getCanRepeat() {
            return canRepeat;
        }

        public void setCanRepeat(int canRepeat) {
            this.canRepeat = canRepeat;
        }

        public void setLayout(int layout) {
            this.layout = layout;
        }

        public int getLayout() {
            return layout;
        }
    }

    @Override
    public String toString() {
        return "CommonLeakBean{" +
                "list=" + list +
                ", tab='" + tab + '\'' +
                '}';
    }
}
