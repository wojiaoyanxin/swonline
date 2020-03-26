package com.sunlands.intl.yingshi.ui.activity.home.plan.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yanxin on 2019/3/11.
 */

public class PlanBean {

    private List<DateListBean> dateList;

    public List<DateListBean> getDateList() {
        return dateList;
    }

    public void setDateList(List<DateListBean> dateList) {
        this.dateList = dateList;
    }

    public static class DateListBean {
        /**
         * date : 02-25
         * list : [{"ThesisId":"103","title":"酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷","package":"产品包1","week":"周一","time":"15:11","date":"02-25","hasJoin":0,"thesisStatus":0}]
         */

        private String date;
        private List<ListBean> list;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ThesisId : 103
             * title : 酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷
             * package : 产品包1
             * week : 周一
             * time : 15:11
             * date : 02-25
             * hasJoin : 0
             * thesisStatus : 0
             */

            private String courseId;
            private String ThesisId;
            private String taskId;
            private String examId;
            private int isRepeat;
            private int isStart;
            private int isView;
            private int isEnd;
            private int playStatus;
            private String title;
            @SerializedName("package")
            private String packageX;
            private String week;
            private String time;
            private String date;
            private String thumb;
            private int hasJoin;
            private int thesisStatus;
            private int status;
            private String classify;
            private Boolean isShow;
            private int layout;
            private int isResult;
            private int left;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getIsResult() {
                return isResult;
            }

            public void setIsResult(int isResult) {
                this.isResult = isResult;
            }

            public int getIsView() {
                return isView;
            }

            public void setIsView(int isView) {
                this.isView = isView;
            }

            public int getLayout() {
                return layout;
            }

            public void setLayout(int layout) {
                this.layout = layout;
            }

            public Boolean getShow() {
                return isShow;
            }

            public void setShow(Boolean show) {
                isShow = show;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getPlayStatus() {
                return playStatus;
            }

            public void setPlayStatus(int playStatus) {
                this.playStatus = playStatus;
            }

            public String getTaskId() {
                return taskId;
            }

            public void setTaskId(String taskId) {
                this.taskId = taskId;
            }

            public String getExamId() {
                return examId;
            }

            public void setExamId(String examId) {
                this.examId = examId;
            }

            public int getIsRepeat() {
                return isRepeat;
            }

            public void setIsRepeat(int isRepeat) {
                this.isRepeat = isRepeat;
            }

            public int getIsStart() {
                return isStart;
            }

            public void setIsStart(int isStart) {
                this.isStart = isStart;
            }

            public int getIsEnd() {
                return isEnd;
            }

            public void setIsEnd(int isEnd) {
                this.isEnd = isEnd;
            }

            public String getClassify() {
                return classify;
            }

            public void setClassify(String classify) {
                this.classify = classify;
            }

            public String getCourseId() {
                return courseId;
            }

            public void setCourseId(String courseId) {
                this.courseId = courseId;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getThesisId() {
                return ThesisId;
            }

            public void setThesisId(String ThesisId) {
                this.ThesisId = ThesisId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getHasJoin() {
                return hasJoin;
            }

            public void setHasJoin(int hasJoin) {
                this.hasJoin = hasJoin;
            }

            public int getThesisStatus() {
                return thesisStatus;
            }

            public void setThesisStatus(int thesisStatus) {
                this.thesisStatus = thesisStatus;
            }
        }
    }
}
