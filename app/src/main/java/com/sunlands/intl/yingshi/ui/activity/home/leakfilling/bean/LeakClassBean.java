package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean;

import java.util.List;

/**
 * Created by yanxin on 2019/4/8.
 */

public class LeakClassBean {


    /**
     * hasMore : 1
     * courseList : [{"courseId":"6373","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"4.4直播课","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"14:30:00","endTime":"19:30:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-04","status":2,"handoutName":"","time":"2019-04-04 14:30 ~ 19:30","hasJoined":0},{"courseId":"6374","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"4.4录播课","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"14:30:00","endTime":"20:30:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-04","status":3,"handoutName":"","time":"2019-04-04 14:30 ~ 20:30","hasJoined":0},{"courseId":"6375","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"4.4录播回放","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"14:29:00","endTime":"14:30:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-04","status":3,"handoutName":"","time":"2019-04-04 14:29 ~ 14:30","hasJoined":0},{"courseId":"6370","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"4.3录播课","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"10:58:00","endTime":"16:58:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-03","status":3,"handoutName":"","time":"2019-04-03 10:58 ~ 16:58","hasJoined":0},{"courseId":"6371","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"4.3录播回放","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"10:55:00","endTime":"10:57:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-03","status":3,"handoutName":"","time":"2019-04-03 10:55 ~ 10:57","hasJoined":0},{"courseId":"6369","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"4.3直播课","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"10:54:00","endTime":"16:54:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-03","status":2,"handoutName":"","time":"2019-04-03 10:54 ~ 16:54","hasJoined":0},{"courseId":"6368","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"录播课2","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"18:07:00","endTime":"18:09:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-02","status":3,"handoutName":"","time":"2019-04-02 18:07 ~ 18:09","hasJoined":0},{"courseId":"6366","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"录播课","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"17:47:00","endTime":"17:49:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-02","status":3,"handoutName":"","time":"2019-04-02 17:47 ~ 17:49","hasJoined":0},{"courseId":"6365","packageId":"3","packageName":"产品包1","headImg":"https://education-1254383113.file.myqcloud.com/54473111729378.jpg","title":"直播课","pic":"https://education-1254383113.file.myqcloud.com/124322162994881.jpg","teacher":"孙瑜","detail":"","startTime":"17:25:00","endTime":"21:45:00","task":0,"quiz":0,"handout":"","dateTime":"2019-04-02","status":2,"handoutName":"","time":"2019-04-02 17:25 ~ 21:45","hasJoined":0},{"courseId":"6362","packageId":"28","packageName":"测试产品包-张万龙01","headImg":"https://education-1254383113.file.myqcloud.com/27556686643170.png","title":"测试啵啵啵啵啵啵啵啵啵啵啵啵","pic":"https://education-1254383113.file.myqcloud.com/35253548937551.jpg","teacher":"张万龙","detail":"","startTime":"11:30:00","endTime":"13:00:00","task":0,"quiz":0,"handout":"","dateTime":"2019-03-29","status":3,"handoutName":"","time":"2019-03-29 11:30 ~ 13:00","hasJoined":0}]
     * monthList : ["2018-02","2018-03","2018-04","2018-05","2018-06","2018-07","2018-08","2018-09","2018-10","2018-11","2018-12","2019-01","2019-02","2019-03","2019-04"]
     */

    private int hasMore;
    private List<CourseListBean> courseList;
    private List<String> monthList;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<CourseListBean> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseListBean> courseList) {
        this.courseList = courseList;
    }

    public List<String> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<String> monthList) {
        this.monthList = monthList;
    }

    public static class CourseListBean {
        /**
         * courseId : 6373
         * packageId : 3
         * packageName : 产品包1
         * headImg : https://education-1254383113.file.myqcloud.com/54473111729378.jpg
         * title : 4.4直播课
         * pic : https://education-1254383113.file.myqcloud.com/124322162994881.jpg
         * teacher : 孙瑜
         * detail :
         * startTime : 14:30:00
         * endTime : 19:30:00
         * task : 0
         * quiz : 0
         * handout :
         * dateTime : 2019-04-04
         * status : 2
         * handoutName :
         * time : 2019-04-04 14:30 ~ 19:30
         * hasJoined : 0
         */

        private String courseId;
        private String packageId;
        private String packageName;
        private String headImg;
        private String title;
        private String pic;
        private String teacher;
        private String detail;
        private String startTime;
        private String endTime;
        private int task;
        private int quiz;
        private String handout;
        private String dateTime;
        private int status;
        private String handoutName;
        private String time;
        private int hasJoined;

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getPackageId() {
            return packageId;
        }

        public void setPackageId(String packageId) {
            this.packageId = packageId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
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

        public int getTask() {
            return task;
        }

        public void setTask(int task) {
            this.task = task;
        }

        public int getQuiz() {
            return quiz;
        }

        public void setQuiz(int quiz) {
            this.quiz = quiz;
        }

        public String getHandout() {
            return handout;
        }

        public void setHandout(String handout) {
            this.handout = handout;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getHandoutName() {
            return handoutName;
        }

        public void setHandoutName(String handoutName) {
            this.handoutName = handoutName;
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
    }
}
