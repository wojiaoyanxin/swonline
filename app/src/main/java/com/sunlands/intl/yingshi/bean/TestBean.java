package com.sunlands.intl.yingshi.bean;

import java.util.List;

/**
 * 文件名: TestBean
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/16
 */
public class TestBean {

    private List<BannersBean> banners;
    private List<FunctionPartsBean> functionParts;
    private List<CoursesBean> courses;
    private List<?> courseNotice;
    private List<ArticlesBean> articles;

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<FunctionPartsBean> getFunctionParts() {
        return functionParts;
    }

    public void setFunctionParts(List<FunctionPartsBean> functionParts) {
        this.functionParts = functionParts;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "banners=" + banners +
                ", functionParts=" + functionParts +
                ", courses=" + courses +
                ", courseNotice=" + courseNotice +
                ", articles=" + articles +
                '}';
    }

    public List<CoursesBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesBean> courses) {
        this.courses = courses;
    }

    public List<?> getCourseNotice() {
        return courseNotice;
    }

    public void setCourseNotice(List<?> courseNotice) {
        this.courseNotice = courseNotice;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class BannersBean {
        private String imgUrl;
        private String url;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class FunctionPartsBean {
        private String name;
        private String iconUrl;
        private String type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class CoursesBean {
        private String courseId;
        private String video_type;
        private String meeting_id;
        private String title;
        private String desc;
        private String poster;
        private String subject;
        private String teacher;
        private String playType;
        private String status;
        private int type;
        private String videoType;
        private String meetingId;
        private int orderStatus;
        private String startTime;
        private int playStatus;
        private int orderNum;
        private int viewNum;
        private List<String> headUrls;

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getVideo_type() {
            return video_type;
        }

        public void setVideo_type(String video_type) {
            this.video_type = video_type;
        }

        public String getMeeting_id() {
            return meeting_id;
        }

        public void setMeeting_id(String meeting_id) {
            this.meeting_id = meeting_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getPlayType() {
            return playType;
        }

        public void setPlayType(String playType) {
            this.playType = playType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVideoType() {
            return videoType;
        }

        public void setVideoType(String videoType) {
            this.videoType = videoType;
        }

        public String getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(String meetingId) {
            this.meetingId = meetingId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public int getPlayStatus() {
            return playStatus;
        }

        public void setPlayStatus(int playStatus) {
            this.playStatus = playStatus;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getViewNum() {
            return viewNum;
        }

        public void setViewNum(int viewNum) {
            this.viewNum = viewNum;
        }

        public List<String> getHeadUrls() {
            return headUrls;
        }

        public void setHeadUrls(List<String> headUrls) {
            this.headUrls = headUrls;
        }
    }

    public static class ArticlesBean {
        private String articleId;
        private String poster;
        private String title;
        private String explain;
        private String url;

        public String getArticleId() {
            return articleId;
        }

        public void setArticleId(String articleId) {
            this.articleId = articleId;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
