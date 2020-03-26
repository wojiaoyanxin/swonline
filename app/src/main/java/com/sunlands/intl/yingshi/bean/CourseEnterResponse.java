package com.sunlands.intl.yingshi.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: CourseEnterResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/14
 */
public class CourseEnterResponse implements Serializable {

    /**
     * isPackage : 1
     * status : 0
     * url : [{"original":"https://1254383113.vod2.myqcloud.com/e7e620a1vodcq1254383113/305e08c95285890785458498786/f0.mp4","hd":"https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/305e08c95285890785458498786/v.f30.mp4","sd":"https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/305e08c95285890785458498786/v.f20.mp4","smooth":"https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/305e08c95285890785458498786/v.f10.mp4"},{"original":"https://1254383113.vod2.myqcloud.com/e7e620a1vodcq1254383113/456ba5145285890785462094940/f0.mp4","hd":"https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/456ba5145285890785462094940/v.f30.mp4","sd":"https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/456ba5145285890785462094940/v.f20.mp4","smooth":"https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/456ba5145285890785462094940/v.f10.mp4"}]
     * handout :
     * type : 1
     * task : 0
     * quiz : 0
     * poster : https://education-1254383113.file.myqcloud.com/108347054631003.jpg
     * commentList : []
     * intro :
     * title : 讨论课
     * roomId : 30219
     * subjectId : 5
     * sdkAppID :
     * userSig :
     * accountType :
     * firstPull :
     * screenPull :
     * identifier :
     * userNick :
     * userAvatar :
     * startTime : 16:55
     * endTime : 17:55
     * dateTime : 2018-12-03
     * subjectName : 内测
     * lastProgress : 0
     * handoutName :
     * channel : []
     * isToday : 0
     * liveTime : 12月03日16:55-17:55
     * isRepeat : 0
     * isQuizEnd : 1
     * isQuizStart : 0
     * isLecture : 0
     * liveStartTime : 0
     * meeting_id : 855502521
     * video_type : 3
     * countDown : 0
     * workStatus : 0
     * classTestStatus : 0
     * isEvaluate : 2
     * evaluate : {"star":0,"tags":[],"comment":""}
     * evaluateTime : 10000000
     * evaluateTags : []
     */

    public int isPackage;
    public int status;
    public String handout;
    public int type;
    public int task;
    public int quiz;
    public String poster;
    public String intro;
    public String title;
    public int roomId;
    public int subjectId;
    public String sdkAppID;
    public String userSig;
    public String accountType;
    public String firstPull;
    public String screenPull;
    public String identifier;
    public String userNick;
    public String userAvatar;
    public String startTime;
    public String endTime;
    public String dateTime;
    public String subjectName;
    public int lastProgress;
    public String handoutName;
    public int isToday;
    public String liveTime;
    public String handoutCreatedAt;
    public int isRepeat;
    private int hasClassTest;
    private int isResult;
    private String zoomPlayUrl;
    private int zoomPlaybackStatus;
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getZoomPlayUrl() {
        return zoomPlayUrl;
    }

    public void setZoomPlayUrl(String zoomPlayUrl) {
        this.zoomPlayUrl = zoomPlayUrl;
    }

    public int getZoomPlaybackStatus() {
        return zoomPlaybackStatus;
    }

    public void setZoomPlaybackStatus(int zoomPlaybackStatus) {
        this.zoomPlaybackStatus = zoomPlaybackStatus;
    }

    public String getHandoutCreatedAt() {
        return handoutCreatedAt;
    }

    public void setHandoutCreatedAt(String handoutCreatedAt) {
        this.handoutCreatedAt = handoutCreatedAt;
    }

    public int getIsResult() {
        return isResult;
    }

    public void setIsResult(int isResult) {
        this.isResult = isResult;
    }

    public int getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(int mIsPackage) {
        isPackage = mIsPackage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int mStatus) {
        status = mStatus;
    }

    public String getHandout() {
        return handout == null ? "" : handout;
    }

    public void setHandout(String mHandout) {
        handout = mHandout;
    }

    public int getType() {
        return type;
    }

    public void setType(int mType) {
        type = mType;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int mTask) {
        task = mTask;
    }

    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int mQuiz) {
        quiz = mQuiz;
    }

    public String getPoster() {
        return poster == null ? "" : poster;
    }

    public void setPoster(String mPoster) {
        poster = mPoster;
    }

    public String getIntro() {
        return intro == null ? "" : intro;
    }

    public void setIntro(String mIntro) {
        intro = mIntro;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String mTitle) {
        title = mTitle;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int mRoomId) {
        roomId = mRoomId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int mSubjectId) {
        subjectId = mSubjectId;
    }

    public String getSdkAppID() {
        return sdkAppID == null ? "" : sdkAppID;
    }

    public void setSdkAppID(String mSdkAppID) {
        sdkAppID = mSdkAppID;
    }

    public String getUserSig() {
        return userSig == null ? "" : userSig;
    }

    public void setUserSig(String mUserSig) {
        userSig = mUserSig;
    }

    public String getAccountType() {
        return accountType == null ? "" : accountType;
    }

    public void setAccountType(String mAccountType) {
        accountType = mAccountType;
    }

    public String getFirstPull() {
        return firstPull == null ? "" : firstPull;
    }

    public void setFirstPull(String mFirstPull) {
        firstPull = mFirstPull;
    }

    public String getScreenPull() {
        return screenPull == null ? "" : screenPull;
    }

    public void setScreenPull(String mScreenPull) {
        screenPull = mScreenPull;
    }

    public String getIdentifier() {
        return identifier == null ? "" : identifier;
    }

    public void setIdentifier(String mIdentifier) {
        identifier = mIdentifier;
    }

    public String getUserNick() {
        return userNick == null ? "" : userNick;
    }

    public void setUserNick(String mUserNick) {
        userNick = mUserNick;
    }

    public String getUserAvatar() {
        return userAvatar == null ? "" : userAvatar;
    }

    public void setUserAvatar(String mUserAvatar) {
        userAvatar = mUserAvatar;
    }

    public String getStartTime() {
        return startTime == null ? "" : startTime;
    }

    public void setStartTime(String mStartTime) {
        startTime = mStartTime;
    }

    public String getEndTime() {
        return endTime == null ? "" : endTime;
    }

    public void setEndTime(String mEndTime) {
        endTime = mEndTime;
    }

    public String getDateTime() {
        return dateTime == null ? "" : dateTime;
    }

    public void setDateTime(String mDateTime) {
        dateTime = mDateTime;
    }

    public String getSubjectName() {
        return subjectName == null ? "" : subjectName;
    }

    public void setSubjectName(String mSubjectName) {
        subjectName = mSubjectName;
    }

    public int getLastProgress() {
        return lastProgress;
    }

    public void setLastProgress(int mLastProgress) {
        lastProgress = mLastProgress;
    }

    public String getHandoutName() {
        return handoutName == null ? "" : handoutName;
    }

    public void setHandoutName(String mHandoutName) {
        handoutName = mHandoutName;
    }

    public int getIsToday() {
        return isToday;
    }

    public void setIsToday(int mIsToday) {
        isToday = mIsToday;
    }

    public String getLiveTime() {
        return liveTime == null ? "" : liveTime;
    }

    public void setLiveTime(String mLiveTime) {
        liveTime = mLiveTime;
    }

    public int getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(int mIsRepeat) {
        isRepeat = mIsRepeat;
    }

    public int getIsQuizEnd() {
        return isQuizEnd;
    }

    public void setIsQuizEnd(int mIsQuizEnd) {
        isQuizEnd = mIsQuizEnd;
    }

    public int getIsQuizStart() {
        return isQuizStart;
    }

    public void setIsQuizStart(int mIsQuizStart) {
        isQuizStart = mIsQuizStart;
    }

    public int getIsLecture() {
        return isLecture;
    }

    public void setIsLecture(int mIsLecture) {
        isLecture = mIsLecture;
    }

    public int getLiveStartTime() {
        return liveStartTime;
    }

    public void setLiveStartTime(int mLiveStartTime) {
        liveStartTime = mLiveStartTime;
    }

    public String getMeeting_id() {
        return meeting_id == null ? "" : meeting_id;
    }

    public void setMeeting_id(String mMeeting_id) {
        meeting_id = mMeeting_id;
    }

    public int getVideo_type() {
        return video_type;
    }

    public void setVideo_type(int mVideo_type) {
        video_type = mVideo_type;
    }

    public int getCountDown() {
        return countDown;
    }

    public void setCountDown(int mCountDown) {
        countDown = mCountDown;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int mWorkStatus) {
        workStatus = mWorkStatus;
    }

    public int getClassTestStatus() {
        return classTestStatus;
    }

    public void setClassTestStatus(int mClassTestStatus) {
        classTestStatus = mClassTestStatus;
    }

    public int getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(int mIsEvaluate) {
        isEvaluate = mIsEvaluate;
    }

    public EvaluateBean getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(EvaluateBean mEvaluate) {
        evaluate = mEvaluate;
    }

    public int getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(int mEvaluateTime) {
        evaluateTime = mEvaluateTime;
    }

    public List<Object> getUrl() {
        if (url == null) {
            return new ArrayList<>();
        }
        return url;
    }

    public void setUrl(List<Object> mUrl) {
        url = mUrl;
    }

    public List<CommentBean> getCommentList() {
        if (commentList == null) {
            return new ArrayList<>();
        }
        return commentList;
    }

    public void setCommentList(List<CommentBean> mCommentList) {
        commentList = mCommentList;
    }

    public List<ChannelBean> getChannel() {
        if (channel == null) {
            return new ArrayList<>();
        }
        return channel;
    }

    public void setChannel(List<ChannelBean> mChannel) {
        channel = mChannel;
    }


    public int isQuizEnd;
    public int isQuizStart;
    public int isLecture;
    public int liveStartTime;
    public String meeting_id;
    public int video_type;
    public int countDown;
    public int workStatus;
    public int classTestStatus;
    public int isEvaluate;
    public EvaluateBean evaluate;
    public int evaluateTime;
    public List<Object> url;
    public List<SubtitleBean> subtitle;
    private List<CommentBean> commentList;
    public List<ChannelBean> channel;

    public List<SubtitleBean> getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(List<SubtitleBean> subtitle) {
        this.subtitle = subtitle;
    }

    private List<EvaluateTagsBean> evaluateTags;

    public int getHasClassTest() {
        return hasClassTest;
    }

    public List<EvaluateTagsBean> getEvaluateTags() {
        return evaluateTags;
    }

    public void setEvaluateTags(List<EvaluateTagsBean> evaluateTags) {
        this.evaluateTags = evaluateTags;
    }

    public static class EvaluateBean implements Serializable {

        public int getStar() {
            return star;
        }

        public void setStar(int mStar) {
            star = mStar;
        }

        public String getComment() {
            return comment == null ? "" : comment;
        }

        public void setComment(String mComment) {
            comment = mComment;
        }

        public List<?> getTags() {
            if (tags == null) {
                return new ArrayList<>();
            }
            return tags;
        }

        public void setTags(List<?> mTags) {
            tags = mTags;
        }

        /**
         * star : 0
         * tags : []
         * comment :
         */

        public int star;
        public String comment;
        public List<?> tags;
    }


    public static class UrlBean implements Serializable {

        public String getOriginal() {
            return original == null ? "" : original;
        }

        public void setOriginal(String mOriginal) {
            original = mOriginal;
        }

        public String getHd() {
            return hd == null ? "" : hd;
        }

        public void setHd(String mHd) {
            hd = mHd;
        }

        public String getSd() {
            return sd == null ? "" : sd;
        }

        public void setSd(String mSd) {
            sd = mSd;
        }

        public String getSmooth() {
            return smooth == null ? "" : smooth;
        }

        public void setSmooth(String mSmooth) {
            smooth = mSmooth;
        }

        /**
         * original : https://1254383113.vod2.myqcloud.com/e7e620a1vodcq1254383113/305e08c95285890785458498786/f0.mp4
         * hd : https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/305e08c95285890785458498786/v.f30.mp4
         * sd : https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/305e08c95285890785458498786/v.f20.mp4
         * smooth : https://1254383113.vod2.myqcloud.com/bed00e49vodtranscq1254383113/305e08c95285890785458498786/v.f10.mp4
         */

        public String original;
        public String hd;
        public String sd;
        public String smooth;
    }

    public static class CommentBean implements Serializable {

        public CommentBean(String name, String avatar, String content) {
            this.name = name;
            this.avatar = avatar;
            this.content = content;
        }

        public CommentBean() {
        }

        /**
         * name : 明阳
         * avatar : https://education-1254383113.file.myqcloud.com/43463635607439.jpg
         * content : 11
         */

        private String name;
        private String avatar;
        private String content;
        private int role;

        private int videoTime;
        private long createdTime;

        public long getTime() {
            return createdTime;
        }

        public void setTime(long time) {
            this.createdTime = time;
        }

        public int getVideoTime() {
            return videoTime;
        }

        public void setVideoTime(int videoTime) {
            this.videoTime = videoTime;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ChannelBean implements Serializable {

        /**
         * original : rtmp://liveplayglobal-wh.ministudy.com/live/20118_5c64e339d874d
         * hd : rtmp://liveplayglobal-wh.ministudy.com/live/20118_5c64e339d874d_900
         * sd : rtmp://liveplayglobal-wh.ministudy.com/live/20118_5c64e339d874d_550
         * smooth : rtmp://liveplayglobal-wh.ministudy.com/live/20118_5c64e339d874d_400
         */

        public String original;

        public String getOriginal() {
            return original == null ? "" : original;
        }

        public void setOriginal(String mOriginal) {
            original = mOriginal;
        }

        public String getHd() {
            return hd == null ? "" : hd;
        }

        public void setHd(String mHd) {
            hd = mHd;
        }

        public String getSd() {
            return sd == null ? "" : sd;
        }

        public void setSd(String mSd) {
            sd = mSd;
        }

        public String getSmooth() {
            return smooth == null ? "" : smooth;
        }

        public void setSmooth(String mSmooth) {
            smooth = mSmooth;
        }

        public String hd;
        public String sd;
        public String smooth;
    }


    public static class SubtitleBean implements Serializable {

        String srt;
        String vtt;

        public String getSrt() {
            return srt;
        }

        public void setSrt(String srt) {
            this.srt = srt;
        }

        public String getVtt() {
            return vtt;
        }

        public void setVtt(String vtt) {
            this.vtt = vtt;
        }
    }


    public QuizElevantBean getQuizElevant() {
        return quizElevant;
    }

    public void setQuizElevant(QuizElevantBean quizElevant) {
        this.quizElevant = quizElevant;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherAvatar() {
        return teacherAvatar;
    }

    public void setTeacherAvatar(String teacherAvatar) {
        this.teacherAvatar = teacherAvatar;
    }

    public String getTeacherSummary() {
        return teacherSummary;
    }

    public void setTeacherSummary(String teacherSummary) {
        this.teacherSummary = teacherSummary;
    }

    public int getTeacherCoursesNumber() {
        return teacherCoursesNumber;
    }

    public void setTeacherCoursesNumber(int teacherCoursesNumber) {
        this.teacherCoursesNumber = teacherCoursesNumber;
    }

    public int getTeacherAverageStar() {
        return teacherAverageStar;
    }

    public void setTeacherAverageStar(int teacherAverageStar) {
        this.teacherAverageStar = teacherAverageStar;
    }

    public int getHasJoin() {
        return hasJoin;
    }

    public void setHasJoin(int hasJoin) {
        this.hasJoin = hasJoin;
    }

    public int getLiveOnlineNum() {
        return liveOnlineNum;
    }

    public void setLiveOnlineNum(int liveOnlineNum) {
        this.liveOnlineNum = liveOnlineNum;
    }

    public int getReplayOnlineNum() {
        return replayOnlineNum;
    }

    public void setReplayOnlineNum(int replayOnlineNum) {
        this.replayOnlineNum = replayOnlineNum;
    }


    private QuizElevantBean quizElevant;
    private String teacherId;
    private String teacherName;
    private String teacherAvatar;
    private String teacherSummary;
    private int teacherCoursesNumber;
    private int teacherAverageStar;
    private int hasJoin;
    private int liveOnlineNum;
    private int replayOnlineNum;


    public static  class QuizElevantBean {


        String number;
        String SubmissionTime;
        String isRepeat;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getSubmissionTime() {
            return SubmissionTime;
        }

        public void setSubmissionTime(String submissionTime) {
            SubmissionTime = submissionTime;
        }

        public String getIsRepeat() {
            return isRepeat;
        }

        public void setIsRepeat(String isRepeat) {
            this.isRepeat = isRepeat;
        }
    }

    public static class EvaluateTagsBean {
        /**
         * labels : ["讲的很好","二二二二嗯嗯#@mb","老师声音甜美人好漂凉","声音好听声音好","很好很好","\t非常棒，我喜欢","老师声音甜美人","22222","声音比较小，听不清楚","234"]
         * star : 5
         */

        private String star;
        private List<String> labels;

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }
    }
}
