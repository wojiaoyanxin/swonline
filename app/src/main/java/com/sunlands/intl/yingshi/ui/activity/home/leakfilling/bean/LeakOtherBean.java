package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean;

import java.util.List;

/**
 * Created by yanxin on 2019/4/8.
 */

public class LeakOtherBean {

    /**
     * future : [{"rid":364,"startTime":"2019-03-11 11:18:11","endTime":"2019-03-11 11:40:11","packageName":"MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd","startTimeFormat":"3月11日 11:18","endTimeFormat":"3月11日 11:40","courseId":0,"hasJoined":1,"hasBegin":1,"hasEnd":1,"canRepeat":1}]
     * list : [{"rid":364,"startTime":"2019-03-11 11:18:11","endTime":"2019-03-11 11:40:11","packageName":"MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd","startTimeFormat":"3月11日 11:18","endTimeFormat":"3月11日 11:40","courseId":0,"hasJoined":1,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":354,"startTime":"2019-02-26 16:53:17","endTime":"2019-03-08 20:53:23","packageName":"MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd","startTimeFormat":"2月26日 16:53","endTimeFormat":"3月08日 20:53","courseId":0,"hasJoined":1,"hasBegin":1,"hasEnd":1,"canRepeat":0},{"rid":318,"startTime":"2019-03-07 16:35:04","endTime":"2019-03-07 16:40:04","packageName":"MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd","startTimeFormat":"3月07日 16:35","endTimeFormat":"3月07日 16:40","courseId":0,"hasJoined":1,"hasBegin":1,"hasEnd":1,"canRepeat":0},{"rid":316,"startTime":"2019-03-07 16:00:54","endTime":"2019-03-07 16:10:54","packageName":"MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd","startTimeFormat":"3月07日 16:00","endTimeFormat":"3月07日 16:10","courseId":0,"hasJoined":1,"hasBegin":1,"hasEnd":1,"canRepeat":0},{"rid":356,"startTime":"2019-02-28 17:01:47","endTime":"2019-03-29 17:01:58","packageName":"MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd","startTimeFormat":"2月28日 17:01","endTimeFormat":"3月29日 17:01","courseId":0,"hasJoined":1,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":320,"startTime":"2019-03-07 16:42:16","endTime":"2019-03-07 16:50:16","packageName":"MBA6666....安卓产品包ajhskdx dsacxdsc 就是个的角色上的讲话mdjsjd","startTimeFormat":"3月07日 16:42","endTimeFormat":"3月07日 16:50","courseId":0,"hasJoined":1,"hasBegin":1,"hasEnd":1,"canRepeat":1}]
     * hasMore : 0
     */

    private int hasMore;
    private List<FutureBean> future;


    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<FutureBean> getFuture() {
        return future;
    }

    public void setFuture(List<FutureBean> future) {
        this.future = future;
    }

    public static class FutureBean {
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
        private String thesis_time;
        private String time;
        private String tab;
        private int layout;
        private int left;

        public String getThesis_time() {
            return thesis_time;
        }

        public void setThesis_time(String thesis_time) {
            this.thesis_time = thesis_time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setLayout(int layout) {
            this.layout = layout;
        }

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

        public int getLayout() {
            return layout;
        }

        public int getLeft() {
            return left;
        }
    }

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
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
        private String thesis_time;
        private String time;
        private String packageName;
        private String startTimeFormat;
        private String endTimeFormat;
        private String isResult;
        private int courseId;
        private int hasJoined;
        private int hasBegin;
        private int hasEnd;
        private int canRepeat;
        private String title;
        private String tab;
        private int layout;
        private int isView;
        private int left;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }
        public String getIsResult() {
            return isResult;
        }

        public void setIsResult(String isResult) {
            this.isResult = isResult;
        }

        public int getIsView() {
            return isView;
        }

        public void setIsView(int isView) {
            this.isView = isView;
        }

        public String getThesis_time() {
            return thesis_time;
        }

        public void setThesis_time(String thesis_time) {
            this.thesis_time = thesis_time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

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

    private List<LeakThesisBean.RecentBean> recent;
    private List<LeakThesisBean.MoreBean> more;

    public List<LeakThesisBean.RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<LeakThesisBean.RecentBean> recent) {
        this.recent = recent;
    }

    public List<LeakThesisBean.MoreBean> getMore() {
        return more;
    }

    public void setMore(List<LeakThesisBean.MoreBean> more) {
        this.more = more;
    }

    public static class RecentBean {
        /**
         * rid : 84
         * title : 国台办批台湾高中历史课纲
         * thesis_time : 2018-11-16
         * time : 11-16 11:31
         * hasJoined : 0
         * hasBegin : 1
         * hasEnd : 1
         * canRepeat : 1
         */

        private String rid;
        private String title;
        private String thesis_time;
        private String time;
        private int hasJoined;
        private int hasBegin;
        private int hasEnd;
        private int canRepeat;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThesis_time() {
            return thesis_time;
        }

        public void setThesis_time(String thesis_time) {
            this.thesis_time = thesis_time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
    }

    public static class MoreBean {
        /**
         * rid : 84
         * title : 国台办批台湾高中历史课纲
         * thesis_time : 2018-11-16
         * time : 11-16 11:31
         * hasJoined : 0
         * hasBegin : 1
         * hasEnd : 1
         * canRepeat : 1
         */

        private String rid;
        private String title;
        private String thesis_time;
        private String time;
        private int hasJoined;
        private int hasBegin;
        private int hasEnd;
        private int canRepeat;

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThesis_time() {
            return thesis_time;
        }

        public void setThesis_time(String thesis_time) {
            this.thesis_time = thesis_time;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
    }

}
