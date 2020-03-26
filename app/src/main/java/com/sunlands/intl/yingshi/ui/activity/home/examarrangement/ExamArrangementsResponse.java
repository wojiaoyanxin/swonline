package com.sunlands.intl.yingshi.ui.activity.home.examarrangement;

import java.util.List;

/**
 * 文件名: ExamArrangementsResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/10
 */
public class ExamArrangementsResponse {

    /**
     * examList : [{"examId":"75","type":"1","name":"SEP阅读 Level1第三次课作业","datetime":"2018年12月22日 星期六 09:41","countDown":5,"isStart":1,"isFinish":0,"is_repeat":1,"is_result":1,"courseId":0,"statusCode":2,"status":"未参加","days":6},{"examId":"92","type":"1","name":"SEP Listening Level 1 Class 1 作业","datetime":"2019年01月01日 星期二 19:15","countDown":7,"isStart":1,"isFinish":0,"is_repeat":1,"is_result":1,"courseId":0,"statusCode":2,"status":"未参加","days":7}]
     * hasMore : 0
     */

    private int hasMore;
    private List<ExamBean> examList;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<ExamBean> getExamList() {
        return examList;
    }

    public void setExamList(List<ExamBean> examList) {
        this.examList = examList;
    }

    public static class ExamBean {
        /**
         * examId : 75
         * type : 1
         * name : SEP阅读 Level1第三次课作业
         * datetime : 2018年12月22日 星期六 09:41
         * countDown : 5
         * isStart : 1
         * isFinish : 0
         * is_repeat : 1
         * is_result : 1
         * courseId : 0
         * statusCode : 2
         * status : 未参加
         * days : 6
         */

        private String examId;
        private String type;
        private String name;
        private String datetime;
        private int countDown;
        private int isStart;
        private int isFinish;
        private int is_repeat;
        private int is_result;
        private int courseId;
        private int statusCode;
        private String status;
        private int days;
        private int isView;
        private int isResult;
        boolean isCountDown;
        private int left;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }
        public boolean isCountDown() {
            return isCountDown;
        }

        public void setCountDown(boolean countDown) {
            isCountDown = countDown;
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

        public String getExamId() {
            return examId;
        }

        public void setExamId(String examId) {
            this.examId = examId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public int getCountDown() {
            return countDown;
        }

        public void setCountDown(int countDown) {
            this.countDown = countDown;
        }

        public int getIsStart() {
            return isStart;
        }

        public void setIsStart(int isStart) {
            this.isStart = isStart;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public int getIs_repeat() {
            return is_repeat;
        }

        public void setIs_repeat(int is_repeat) {
            this.is_repeat = is_repeat;
        }

        public int getIs_result() {
            return is_result;
        }

        public void setIs_result(int is_result) {
            this.is_result = is_result;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }
    }
}
