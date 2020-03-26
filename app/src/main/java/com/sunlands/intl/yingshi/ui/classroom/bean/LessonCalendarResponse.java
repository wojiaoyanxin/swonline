package com.sunlands.intl.yingshi.ui.classroom.bean;

import com.sunlands.intl.yingshi.bean.TempCourseBean;

import java.util.List;

/**
 * 文件名: LessonCalendarResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/13
 */
public class LessonCalendarResponse {

    /**
     * totalCount : 27
     * absenceCount : 27
     * absencePer : 100
     * courseList : [{"courseId":"118","title":"第一节","poster":"","startTime":"19:00:00","endTime":"21:00:00","dateTime":"2018-08-01","type":"2","handout":"https://education-1254383113.file.myqcloud.com/45523434.pdf","name":"商业原则II","teacher":"喻天骥","subjectId":"22","task":1,"quiz":"61","detail":"","workId":"62","hasClassTest":1,"hasHandout":1,"playStatus":3,"join":0,"isFinish":1,"classTestStatus":0,"orderStatus":1},{"courseId":"119","title":"第二节","poster":"https://education-1254383113.file.myqcloud.com/69300294147846.jpg","startTime":"19:00:00","endTime":"21:00:00","dateTime":"2018-08-01","type":"2","handout":"https://education-1254383113.file.myqcloud.com/45523434.pdf","name":"商业原则II","teacher":"喻天骥","subjectId":"22","task":1,"quiz":"60","detail":"","workId":"60","hasClassTest":1,"hasHandout":1,"playStatus":3,"join":0,"isFinish":0,"classTestStatus":0,"orderStatus":0},{"courseId":"120","title":"第三节","poster":"","startTime":"19:00:00","endTime":"21:00:00","dateTime":"2018-08-01","type":"2","handout":"https://education-1254383113.file.myqcloud.com/45523434.pdf","name":"商业原则II","teacher":"喻天骥","subjectId":"22","task":1,"quiz":"60","detail":"","workId":"60","hasClassTest":1,"hasHandout":1,"playStatus":3,"join":0,"isFinish":1,"classTestStatus":0,"orderStatus":0},{"courseId":"121","title":"第四节","poster":"","startTime":"19:00:00","endTime":"21:00:00","dateTime":"2018-08-01","type":"2","handout":"https://education-1254383113.file.myqcloud.com/11023764441394.doc","name":"商业原则II","teacher":"喻天骥","subjectId":"22","task":1,"quiz":"60","detail":"","workId":"60","hasClassTest":1,"hasHandout":1,"playStatus":3,"join":0,"isFinish":0,"classTestStatus":0,"orderStatus":0},{"courseId":"122","title":"第一节","poster":"","startTime":"19:00:00","endTime":"21:00:00","dateTime":"2018-08-01","type":"2","handout":"https://education-1254383113.file.myqcloud.com/45523434.pdf","name":"管理经济学","teacher":"喻天骥","subjectId":"23","task":1,"quiz":"60","detail":"","workId":"60","hasClassTest":1,"hasHandout":1,"playStatus":3,"join":0,"isFinish":0,"classTestStatus":0,"orderStatus":0},{"courseId":"123","title":"第二节","poster":"","startTime":"19:00:00","endTime":"21:00:00","dateTime":"2018-08-01","type":"2","handout":"https://education-1254383113.file.myqcloud.com/45523434.pdf","name":"管理经济学","teacher":"喻天骥","subjectId":"23","task":1,"quiz":"60","detail":"","workId":"60","hasClassTest":1,"hasHandout":1,"playStatus":3,"join":0,"isFinish":0,"classTestStatus":0,"orderStatus":0},{"courseId":"124","title":"第三节","poster":"","startTime":"19:00:00","endTime":"21:00:00","dateTime":"2018-08-01","type":"2","handout":"https://education-1254383113.file.myqcloud.com/45523434.pdf","name":"管理经济学","teacher":"喻天骥","subjectId":"23","task":1,"quiz":"60","detail":"","workId":"60","hasClassTest":1,"hasHandout":1,"playStatus":3,"join":0,"isFinish":0,"classTestStatus":0,"orderStatus":0}]
     */

    private int totalCount;
    private int absenceCount;
    private int absencePer;
    private List<LessonCalendarBean> courseList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public void setAbsenceCount(int absenceCount) {
        this.absenceCount = absenceCount;
    }

    public int getAbsencePer() {
        return absencePer;
    }

    public void setAbsencePer(int absencePer) {
        this.absencePer = absencePer;
    }

    public List<LessonCalendarBean> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<LessonCalendarBean> courseList) {
        this.courseList = courseList;
    }

    public static class LessonCalendarBean {
        /**
         * courseId : 118
         * title : 第一节
         * poster :
         * startTime : 19:00:00
         * endTime : 21:00:00
         * dateTime : 2018-08-01
         * type : 2
         * handout : https://education-1254383113.file.myqcloud.com/45523434.pdf
         * name : 商业原则II
         * teacher : 喻天骥
         * subjectId : 22
         * task : 1
         * quiz : 61
         * detail :
         * workId : 62
         * hasClassTest : 1
         * hasHandout : 1
         * playStatus : 3
         * join : 0
         * isFinish : 1
         * classTestStatus : 0
         * orderStatus : 1
         */
        private String courseId;
        private String title;
        private String poster;
        private String startTime;
        private String endTime;
        private String dateTime;
        private String type;
        private String handout;
        private String name;
        private String handoutName;
        private String teacher;
        private String subjectId;
        private int task;
        private String quiz;
        private String detail;
        private String workId;
        private int hasClassTest;
        private int hasHandout;
        private int playStatus;
        private int join;
        private int isFinish;
        private int orderStatus;
        private int classTestStatus;
        private int workStatus;
        private int isQuizEnd;
        private int isRepeat;
        private int isQuizStart;
        private int isView;
        private int hasJoined;
        private int sid;
        private String handoutCreatedAt;

        public String getHandoutCreatedAt() {
            return handoutCreatedAt;
        }

        public void setHandoutCreatedAt(String handoutCreatedAt) {
            this.handoutCreatedAt = handoutCreatedAt;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getHasJoin() {
            return hasJoined;
        }

        public void setHasJoin(int hasJoin) {
            this.hasJoined = hasJoin;
        }
        public String getHandoutName() {
            return handoutName;
        }

        public void setHandoutName(String handoutName) {
            this.handoutName = handoutName;
        }

        public int getIsView() {
            return isView;
        }

        public void setIsView(int isView) {
            this.isView = isView;
        }

        public int getIsQuizStart() {
            return isQuizStart;
        }

        public void setIsQuizStart(int isQuizStart) {
            this.isQuizStart = isQuizStart;
        }

        public int getIsQuizEnd() {
            return isQuizEnd;
        }

        public void setIsQuizEnd(int isQuizEnd) {
            this.isQuizEnd = isQuizEnd;
        }

        public TempCourseBean convertToTempCourseBean(){
            TempCourseBean tempCourseBean = new TempCourseBean(getSubjectId(), getName(),
                    getCourseId(), getTitle(), getHandout(),getHandoutCreatedAt(), getHandoutName(),getSid());
            return tempCourseBean;
        }

        public int getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(int workStatus) {
            this.workStatus = workStatus;
        }


        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
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

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHandout() {
            return handout;
        }

        public void setHandout(String handout) {
            this.handout = handout;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getSubjectId() {
            return subjectId;
        }

        public void setSubjectId(String subjectId) {
            this.subjectId = subjectId;
        }

        public int getTask() {
            return task;
        }

        public void setTask(int task) {
            this.task = task;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getWorkId() {
            return workId;
        }

        public void setWorkId(String workId) {
            this.workId = workId;
        }

        public int getHasClassTest() {
            return hasClassTest;
        }

        public void setHasClassTest(int hasClassTest) {
            this.hasClassTest = hasClassTest;
        }

        public int getHasHandout() {
            return hasHandout;
        }

        public void setHasHandout(int hasHandout) {
            this.hasHandout = hasHandout;
        }

        public int getPlayStatus() {
            return playStatus;
        }

        public void setPlayStatus(int playStatus) {
            this.playStatus = playStatus;
        }

        public int getJoin() {
            return join;
        }

        public void setJoin(int join) {
            this.join = join;
        }

        public int getIsFinish() {
            return isFinish;
        }

        public void setIsFinish(int isFinish) {
            this.isFinish = isFinish;
        }

        public int getClassTestStatus() {
            return classTestStatus;
        }

        public void setClassTestStatus(int classTestStatus) {
            this.classTestStatus = classTestStatus;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getIsRepeat() {
            return isRepeat;
        }
    }
}
