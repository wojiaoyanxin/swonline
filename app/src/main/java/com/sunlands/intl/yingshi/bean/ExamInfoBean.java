package com.sunlands.intl.yingshi.bean;

import java.util.List;

public class ExamInfoBean {


    /**
     * sid : 694
     * title : zwl 测试vvvvvvvvvvvvvvvvvv
     * endTime : 2019年07月31日
     * objectiveScore : 38分
     * subjectiveScore : 无
     * totalScore : 38分
     * isDuration : 1
     * duration : 1.67分钟
     * isRepeat : 1
     * repeat : 不限
     * isAudio : 0
     * auth : 1
     * isAuth : 1
     * leftTime : 100
     * isInvigilate : 0
     * hasBegin : 1
     * hasEnd : 0
     * hasJoined : 1
     * examScore : {"dateTime":"2019年07月19日","useTime":"少于一分钟","objectiveScore":"0分","subjectiveScore":"0分","totalScore":"0分"}
     * notice : ["本考试为限时考试，请合理安排时间","选择再次考试将会覆盖上一次考试成绩（包含待评分）","本考试需人脸验证，请在进入考试前确认视频设备完善"]
     */

    private int sid;
    private String title;
    private String endTime;
    private String objectiveScore;
    private String subjectiveScore;
    private String totalScore;
    private int isDuration;
    private String duration;
    private int isRepeat;
    private String repeat;
    private int isAudio;
    private int auth;
    private int isAuth;
    private int leftTime;
    private int isInvigilate;
    private int hasBegin;
    private int hasEnd;
    private int hasJoined;
    private int isResult;
    private ExamScoreBean examScore;
    private List<String> notice;

    public int getIsResult() {
        return isResult;
    }

    public void setIsResult(int isResult) {
        this.isResult = isResult;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getObjectiveScore() {
        return objectiveScore;
    }

    public void setObjectiveScore(String objectiveScore) {
        this.objectiveScore = objectiveScore;
    }

    public String getSubjectiveScore() {
        return subjectiveScore;
    }

    public void setSubjectiveScore(String subjectiveScore) {
        this.subjectiveScore = subjectiveScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public int getIsDuration() {
        return isDuration;
    }

    public void setIsDuration(int isDuration) {
        this.isDuration = isDuration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(int isRepeat) {
        this.isRepeat = isRepeat;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public int getIsAudio() {
        return isAudio;
    }

    public void setIsAudio(int isAudio) {
        this.isAudio = isAudio;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(int isAuth) {
        this.isAuth = isAuth;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public int getIsInvigilate() {
        return isInvigilate;
    }

    public void setIsInvigilate(int isInvigilate) {
        this.isInvigilate = isInvigilate;
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

    public int getHasJoined() {
        return hasJoined;
    }

    public void setHasJoined(int hasJoined) {
        this.hasJoined = hasJoined;
    }

    public ExamScoreBean getExamScore() {
        return examScore;
    }

    public void setExamScore(ExamScoreBean examScore) {
        this.examScore = examScore;
    }

    public List<String> getNotice() {
        return notice;
    }

    public void setNotice(List<String> notice) {
        this.notice = notice;
    }

    public static class ExamScoreBean {
        /**
         * dateTime : 2019年07月19日
         * useTime : 少于一分钟
         * objectiveScore : 0分
         * subjectiveScore : 0分
         * totalScore : 0分
         */

        private String dateTime;
        private String useTime;
        private String objectiveScore;
        private String subjectiveScore;
        private String totalScore;

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }

        public String getObjectiveScore() {
            return objectiveScore;
        }

        public void setObjectiveScore(String objectiveScore) {
            this.objectiveScore = objectiveScore;
        }

        public String getSubjectiveScore() {
            return subjectiveScore;
        }

        public void setSubjectiveScore(String subjectiveScore) {
            this.subjectiveScore = subjectiveScore;
        }

        public String getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(String totalScore) {
            this.totalScore = totalScore;
        }
    }
}
