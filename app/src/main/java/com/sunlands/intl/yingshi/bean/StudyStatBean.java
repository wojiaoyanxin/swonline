package com.sunlands.intl.yingshi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/3/14 15:13
 * 备注：
 */
public class StudyStatBean {

    /**
     * name : 11
     * avatar : https://education-1254383113.file.myqcloud.com/103982295236453.jpg
     * days : 72
     * rate : 0
     * beyondRate : 40
     * courseNum : 0
     * courseJoinedNum : 1
     * homeworkNum : 0
     * homeworkJoinedNum : 4
     * trendLineData : {"x":["04.29","04.30","05.01","05.02","05.03","05.04","05.05"],"y":[0,0,0,0,0,0,0]}
     * studyLength : 0分钟
     */

    public String name;
    public String avatar;
    public int days;
    public int rate;
    public int beyondRate;
    public int courseNum;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar == null ? "" : avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getBeyondRate() {
        return beyondRate;
    }

    public void setBeyondRate(int beyondRate) {
        this.beyondRate = beyondRate;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getCourseJoinedNum() {
        return courseJoinedNum;
    }

    public void setCourseJoinedNum(int courseJoinedNum) {
        this.courseJoinedNum = courseJoinedNum;
    }

    public int getHomeworkNum() {
        return homeworkNum;
    }

    public void setHomeworkNum(int homeworkNum) {
        this.homeworkNum = homeworkNum;
    }

    public int getHomeworkJoinedNum() {
        return homeworkJoinedNum;
    }

    public void setHomeworkJoinedNum(int homeworkJoinedNum) {
        this.homeworkJoinedNum = homeworkJoinedNum;
    }

    public TrendLineDataBean getTrendLineData() {
        return trendLineData;
    }

    public void setTrendLineData(TrendLineDataBean trendLineData) {
        this.trendLineData = trendLineData;
    }

    public String getStudyLength() {
        return studyLength == null ? "0" : studyLength;
    }

    public void setStudyLength(String studyLength) {
        this.studyLength = studyLength;
    }

    public int courseJoinedNum;
    public int homeworkNum;
    public int homeworkJoinedNum;
    public TrendLineDataBean trendLineData;
    public String studyLength;

    public static class TrendLineDataBean {
        public List<String> getX() {
            if (x == null) {
                return new ArrayList<>();
            }
            return x;
        }

        public void setX(List<String> x) {
            this.x = x;
        }

        public List<Integer> getY() {
            if (y == null) {
                return new ArrayList<>();
            }
            return y;
        }

        public void setY(List<Integer> y) {
            this.y = y;
        }

        public List<String> x;
        public List<Integer> y;
    }
}
