package com.sunlands.intl.yingshi.ui.home.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 文件名: HomeDataResponse
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/12
 */
public class HomeDataResponse {


    /**
     * unreadNum : 1
     * banners : [{"imgUrl":"https://education-1254383113.file.myqcloud.com/92951476854199.png","url":"http://www.baidu.com"},{"imgUrl":"https://education-1254383113.file.myqcloud.com/111095948557617.jpg","url":"www.baidu.com"}]
     * functionParts : [{"name":"考试安排","iconUrl":"https://education-1254383113.file.myqcloud.com/24781868119455.png","type":"2"},{"name":"成绩查询","iconUrl":"https://education-1254383113.file.myqcloud.com/8289193668288.png","type":"3"}]
     * courses : [{"courseId":"5733","video_type":"1","meeting_id":"","title":"3.5日直播课测试测试预告长度","desc":"","poster":"https://education-1254383113.file.myqcloud.com/107821865428536.jpg","subject":"MBA~1测试产品包后台功能测试产品包后台功能","teacher":"孙瑜behanvior teacher hollwood","playType":"1","status":"0","type":0,"videoType":"1","meetingId":"","orderStatus":0,"startTime":"03月05日 14:46分","playStatus":0,"headUrls":["https://education-1254383113.file.myqcloud.com/51165983039252.png","https://education-1254383113.file.myqcloud.com/106691320248623.png"],"orderNum":56077,"viewNum":0}]
     * weekPlan : {"week":"第10周","time":"03.04-03.10","courseNum":6,"taskNum":5,"examNum":0,"thesisNum":0,"courseList":[{"type":1,"courseId":"5713","title":"hhhhhh ","thumb":"https://education-1254383113.file.myqcloud.com/4467897693573.jpg","package":"MBA~1测试产品包后台功能测试产品包后台功能","status":0,"handout":"","week":"星期一","time":"03-04 14:00-16:00","hasJoin":0}],"taskList":[{"type":2,"examId":"116","title":"水水水水水水水水水水水水水水水水","package":"产品包1","classify":"1","week":"星期四","time":"11-29 17:26-17:26","isRepeat":0,"isStart":0,"isEnd":0,"hasJoin":0,"status":0}],"examList":[{"type":3,"examId":"116","title":"水水水水水水水水水水水水水水水水","package":"产品包1","classify":"1","week":"星期四","time":"11-29 17:26-17:26","isRepeat":0,"isStart":0,"isEnd":0,"hasJoin":0,"status":0}],"thesisList":[{"type":4,"thesisId":"116","title":"水水水水水水水水水水水水水水水水","package":"产品包1","week":"星期四","fileUrl":"https://education-1254383113.file.myqcloud.com/44678976.pdf","editFileUrl":"https://education-1254383113.file.myqcloud.com/44678976.pdf","time":"11-29 17:26-17:26","hasJoin":0,"status":0}]}
     * courseNotice : [{"courseId":"5749","videoType":"1","meetingId":"","title":"测试啊测试","desc":"","poster":"https://education-1254383113.file.myqcloud.com/4467897693573.jpg","subject":"产品包1","teacher":"李明明","playType":"1","type":0,"orderStatus":0,"startTime":"2019-03-06 13:00-15:00","playStatus":0,"viewNum":0,"headUrls":["https://mbapc-1254383113.cos.ap-beijing.myqcloud.com/15359675464736.jpg","https://education-1254383113.file.myqcloud.com/72056703966872.png"],"orderNum":26729}]
     * articles : [{"articleId":"98","poster":"https://education-1254383113.file.myqcloud.com/65497113450117.png","title":"界上至少有三分之一","explain":"特若非挖宝","url":"https://smallprogram.sunlands.com/education-app/news/index.html?id=98&sessionKey=5c077aeb4ccd9&userId=20&stuId=1"}]
     */

    private int unreadNum;
    private WeekPlanBean weekPlan;
    private List<BannersBean> banners;
    private List<FunctionPartsBean> functionParts;
    private List<CoursesBean> courses;
    private List<CourseNoticeBean> courseNotice;
    private List<ArticleBean> articles;
    private List<NewBean> news;
    private RankBean rank;

    public RankBean getRank() {
        return rank;
    }

    public void setNews(List<NewBean> news) {
        this.news = news;
    }

    public List<NewBean> getNews() {
        return news;
    }

    private List<ListBean> syCourses;

    public static class NewBean {


        /**
         * id : 1464
         * title : 2测试标题名称过长的展示，名称过长的罗斯福的就是的发生的概率空间上帝浪费三楼的看法就上路积分李开复但
         * author : 尚德小助手
         * desc : 描述的第三方第三方
         * publishTime : 1568618618
         * viewNum : 35
         * poster : https://bj-test-1254383113.file.myqcloud.com/15686186185129.jpg
         */

        private int id;
        private int type;
        private String title;
        private String author;
        private String desc;
        private int publishTime;
        private int viewNum;
        private String poster;
        private String url;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(int publishTime) {
            this.publishTime = publishTime;
        }

        public int getViewNum() {
            return viewNum;
        }

        public void setViewNum(int viewNum) {
            this.viewNum = viewNum;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class RankBean {

        private List<RankListBean> list;

        private String testUrl;
        private String moreUrl;

        public String getTestUrl() {
            return testUrl;
        }

        public String getMoreUrl() {
            return moreUrl;
        }

        public List<RankListBean> getList() {
            return list;
        }

        public static class RankListBean {


            /**
             * id : 1
             * rankNo : 1
             * name : 哈佛大学
             * nameEn : Harvard University
             * country : 美国
             * signed : 0
             * hasContent : 1
             * requirementUrl :
             */

            private int id;
            private String rankNo;
            private String name;
            private String nameEn;
            private String country;
            private int signed;
            private int hasContent;
            private String requirementUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRankNo() {
                return rankNo;
            }

            public void setRankNo(String rankNo) {
                this.rankNo = rankNo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNameEn() {
                return nameEn;
            }

            public void setNameEn(String nameEn) {
                this.nameEn = nameEn;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getSigned() {
                return signed;
            }

            public void setSigned(int signed) {
                this.signed = signed;
            }

            public int getHasContent() {
                return hasContent;
            }

            public void setHasContent(int hasContent) {
                this.hasContent = hasContent;
            }

            public String getRequirementUrl() {
                return requirementUrl;
            }

            public void setRequirementUrl(String requirementUrl) {
                this.requirementUrl = requirementUrl;
            }
        }

    }

    public static class ListBean {


        /**
         * id : 56
         * plat : 3
         * title : 20790603预约
         * type : 1
         * teacher_id : 1
         * img_id : 15595472741523.jpg
         * video_img_id : 15595472747400.jpg
         * file_id : 5285890789701869569
         * file_url : {"FLU":"http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/v.f10.mp4","SD":"http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/v.f20.mp4","HD":"http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/v.f30.mp4"}
         * file_info : {"FileId":"5285890789701869569","BasicInfo":{"Name":"mp4格式视频文件2点64M","Description":"","CreateTime":"2019-05-31T07:47:09Z","UpdateTime":"2019-05-31T07:47:11Z","ExpireTime":"9999-12-31T23:59:59Z","ClassId":0,"ClassName":"其他","ClassPath":"其他","CoverUrl":"http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_0.jpg","Type":"mp4","MediaUrl":"http:\/\/1254383113.vod2.myqcloud.com\/78f333bevodgzp1254383113\/eefbab455285890789701869569\/GddrwB6x9HIA.mp4","TagSet":[],"StorageRegion":"ap-guangzhou-2","SourceInfo":{"SourceType":"Upload","SourceContext":""},"Vid":"5285890789701869569"},"MetaData":{"AudioDuration":0,"AudioStreamSet":[{"Bitrate":64013,"Codec":"aac","SamplingRate":44100}],"Bitrate":1002299,"Container":"mov,mp4,m4a,3gp,3g2,mj2","Duration":21.93798828125,"Height":192,"Rotate":0,"Size":2761153,"VideoDuration":0,"VideoStreamSet":[{"Bitrate":938286,"Codec":"h264","Fps":16,"Height":192,"Width":480}],"Width":480},"TranscodeInfo":{"TranscodeSet":[{"Url":"http:\/\/1254383113.vod2.myqcloud.com\/78f333bevodgzp1254383113\/eefbab455285890789701869569\/GddrwB6x9HIA.mp4","Definition":0,"Bitrate":1002299,"Height":192,"Width":480,"Size":2761153,"Duration":21.93798828125,"Container":"mov,mp4,m4a,3gp,3g2,mj2","Md5":"","AudioStreamSet":[{"Bitrate":64013,"Codec":"aac","SamplingRate":44100}],"VideoStreamSet":[{"Bitrate":938286,"Codec":"h264","Fps":16,"Height":192,"Width":480}]},{"Url":"http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/v.f10.mp4","Definition":10,"Bitrate":286800,"Height":128,"Width":320,"Size":804948,"Duration":21.9582996368408203125,"Container":"mov,mp4,m4a,3gp,3g2,mj2","Md5":"663a457e1a2387a5f6d30909b5d3f72f","AudioStreamSet":[{"Bitrate":48036,"Codec":"aac","SamplingRate":44100}],"VideoStreamSet":[{"Bitrate":238764,"Codec":"h264","Fps":24,"Height":128,"Width":320}]},{"Url":"http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/v.f20.mp4","Definition":20,"Bitrate":526657,"Height":256,"Width":640,"Size":1463315,"Duration":21.9582996368408203125,"Container":"mov,mp4,m4a,3gp,3g2,mj2","Md5":"bae87992a4b636ba4020f227b155f7d0","AudioStreamSet":[{"Bitrate":48036,"Codec":"aac","SamplingRate":44100}],"VideoStreamSet":[{"Bitrate":478621,"Codec":"h264","Fps":24,"Height":256,"Width":640}]},{"Url":"http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/v.f30.mp4","Definition":30,"Bitrate":1005441,"Height":512,"Width":1280,"Size":2777558,"Duration":21.9582996368408203125,"Container":"mov,mp4,m4a,3gp,3g2,mj2","Md5":"201d0d4bad3d23f289a4dca5c808eaf9","AudioStreamSet":[{"Bitrate":48036,"Codec":"aac","SamplingRate":44100}],"VideoStreamSet":[{"Bitrate":957405,"Codec":"h264","Fps":24,"Height":512,"Width":1280}]}]},"AnimatedGraphicsInfo":null,"SampleSnapshotInfo":{"SampleSnapshotSet":[{"Definition":10,"SampleType":"Percent","Interval":10,"ImageUrlSet":["http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_0.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_2100.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_4200.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_6300.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_8400.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_10500.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_12600.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_14700.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_16800.jpg","http:\/\/1254383113.vod2.myqcloud.com\/2d1ad666vodtransgzp1254383113\/eefbab455285890789701869569\/snapshot\/1559288834_3948091003.100_18900.jpg"],"WaterMarkDefinition":[]}]},"ImageSpriteInfo":null,"SnapshotByTimeOffsetInfo":null,"KeyFrameDescInfo":null,"AdaptiveDynamicStreamingInfo":null}
         * intro : 1111111111111111111111111111111
         * virtual_view_num : 11
         * real_view_num : 12
         * view_num : 23
         * start_time : 2019-06-10 15:00
         * end_time : 2019-06-10 15:00
         * duration : 21
         * is_free : 1
         * status : 1
         * is_deleted : 0
         * updated_at : 2019-06-04 13:41:12
         * created_at : 2019-06-03 15:34:34
         * img_url : http://bj-test-1254383113.file.myqcloud.com/15595472741523.jpg
         * video_img_url : http://bj-test-1254383113.file.myqcloud.com/15595472747400.jpg
         * course_id : 56
         * video_img : http://bj-test-1254383113.file.myqcloud.com/15595472747400.jpg
         * img : http://bj-test-1254383113.file.myqcloud.com/15595472741523.jpg
         * teacher_head_img_id : 15541712872299.jpg
         * teacher_head_img : http://bj-test-1254383113.file.myqcloud.com/15541712872299.jpg
         * teacher_name : 小明
         * teacher_intro : 是的法定方式是打发的说法的说法
         * teacher_label : 但紧接而来的金融危机
         */

        private int id;
        private String plat;
        private String title;
        private int type;
        private int teacher_id;
        private String img_id;
        private String video_img_id;
        private String file_id;
        private String file_url;
        private String file_info;
        private String intro;
        private int virtual_view_num;
        private int real_view_num;
        private int view_num;
        private String start_time;
        private String end_time;
        private int duration;
        private int is_free;
        private int status;
        private int is_deleted;
        private String updated_at;
        private String created_at;
        private String img_url;
        private String video_img_url;
        private int course_id;
        private String video_img;
        private String img;
        private String teacher_head_img_id;
        private String teacher_head_img;
        private String teacher_name;
        private String teacher_intro;
        private String teacher_label;
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPlat() {
            return plat;
        }

        public void setPlat(String plat) {
            this.plat = plat;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(int teacher_id) {
            this.teacher_id = teacher_id;
        }

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getVideo_img_id() {
            return video_img_id;
        }

        public void setVideo_img_id(String video_img_id) {
            this.video_img_id = video_img_id;
        }

        public String getFile_id() {
            return file_id;
        }

        public void setFile_id(String file_id) {
            this.file_id = file_id;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        public String getFile_info() {
            return file_info;
        }

        public void setFile_info(String file_info) {
            this.file_info = file_info;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getVirtual_view_num() {
            return virtual_view_num;
        }

        public void setVirtual_view_num(int virtual_view_num) {
            this.virtual_view_num = virtual_view_num;
        }

        public int getReal_view_num() {
            return real_view_num;
        }

        public void setReal_view_num(int real_view_num) {
            this.real_view_num = real_view_num;
        }

        public int getView_num() {
            return view_num;
        }

        public void setView_num(int view_num) {
            this.view_num = view_num;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getVideo_img_url() {
            return video_img_url;
        }

        public void setVideo_img_url(String video_img_url) {
            this.video_img_url = video_img_url;
        }

        public int getCourse_id() {
            return course_id;
        }

        public void setCourse_id(int course_id) {
            this.course_id = course_id;
        }

        public String getVideo_img() {
            return video_img;
        }

        public void setVideo_img(String video_img) {
            this.video_img = video_img;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTeacher_head_img_id() {
            return teacher_head_img_id;
        }

        public void setTeacher_head_img_id(String teacher_head_img_id) {
            this.teacher_head_img_id = teacher_head_img_id;
        }

        public String getTeacher_head_img() {
            return teacher_head_img;
        }

        public void setTeacher_head_img(String teacher_head_img) {
            this.teacher_head_img = teacher_head_img;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getTeacher_intro() {
            return teacher_intro;
        }

        public void setTeacher_intro(String teacher_intro) {
            this.teacher_intro = teacher_intro;
        }

        public String getTeacher_label() {
            return teacher_label;
        }

        public void setTeacher_label(String teacher_label) {
            this.teacher_label = teacher_label;
        }
    }

    public List<ListBean> getList() {
        return syCourses;
    }


    public int getUnreadNum() {
        return unreadNum;
    }

    public void setUnreadNum(int unreadNum) {
        this.unreadNum = unreadNum;
    }

    public WeekPlanBean getWeekPlan() {
        return weekPlan;
    }

    public void setWeekPlan(WeekPlanBean weekPlan) {
        this.weekPlan = weekPlan;
    }

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

    public List<CoursesBean> getCourses() {
        return courses;
    }

    public void setCourses(List<CoursesBean> courses) {
        this.courses = courses;
    }

    public List<CourseNoticeBean> getCourseNotice() {
        return courseNotice;
    }

    public void setCourseNotice(List<CourseNoticeBean> courseNotice) {
        this.courseNotice = courseNotice;
    }

    public List<ArticleBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleBean> articles) {
        this.articles = articles;
    }

    public static class WeekPlanBean {
        /**
         * week : 第10周
         * time : 03.04-03.10
         * courseNum : 6
         * taskNum : 5
         * examNum : 0
         * thesisNum : 0
         * courseList : [{"type":1,"courseId":"5713","title":"hhhhhh ","thumb":"https://education-1254383113.file.myqcloud.com/4467897693573.jpg","package":"MBA~1测试产品包后台功能测试产品包后台功能","status":0,"handout":"","week":"星期一","time":"03-04 14:00-16:00","hasJoin":0}]
         * taskList : [{"type":2,"examId":"116","title":"水水水水水水水水水水水水水水水水","package":"产品包1","classify":"1","week":"星期四","time":"11-29 17:26-17:26","isRepeat":0,"isStart":0,"isEnd":0,"hasJoin":0,"status":0}]
         * examList : [{"type":3,"examId":"116","title":"水水水水水水水水水水水水水水水水","package":"产品包1","classify":"1","week":"星期四","time":"11-29 17:26-17:26","isRepeat":0,"isStart":0,"isEnd":0,"hasJoin":0,"status":0}]
         * thesisList : [{"type":4,"thesisId":"116","title":"水水水水水水水水水水水水水水水水","package":"产品包1","week":"星期四","fileUrl":"https://education-1254383113.file.myqcloud.com/44678976.pdf","editFileUrl":"https://education-1254383113.file.myqcloud.com/44678976.pdf","time":"11-29 17:26-17:26","hasJoin":0,"status":0}]
         */

        private String week;
        private String time;
        private int courseNum;
        private int taskNum;
        private int examNum;
        private int thesisNum;
        private List<CourseListBean> courseList;
        private List<TaskListBean> taskList;
        private List<ExamListBean> examList;
        private List<ThesisListBean> thesisList;

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

        public int getCourseNum() {
            return courseNum;
        }

        public void setCourseNum(int courseNum) {
            this.courseNum = courseNum;
        }

        public int getTaskNum() {
            return taskNum;
        }

        public void setTaskNum(int taskNum) {
            this.taskNum = taskNum;
        }

        public int getExamNum() {
            return examNum;
        }

        public void setExamNum(int examNum) {
            this.examNum = examNum;
        }

        public int getThesisNum() {
            return thesisNum;
        }

        public void setThesisNum(int thesisNum) {
            this.thesisNum = thesisNum;
        }

        public List<CourseListBean> getCourseList() {
            return courseList;
        }

        public void setCourseList(List<CourseListBean> courseList) {
            this.courseList = courseList;
        }

        public List<TaskListBean> getTaskList() {
            return taskList;
        }

        public void setTaskList(List<TaskListBean> taskList) {
            this.taskList = taskList;
        }

        public List<ExamListBean> getExamList() {
            return examList;
        }

        public void setExamList(List<ExamListBean> examList) {
            this.examList = examList;
        }

        public List<ThesisListBean> getThesisList() {
            return thesisList;
        }

        public void setThesisList(List<ThesisListBean> thesisList) {
            this.thesisList = thesisList;
        }

        public static class CourseListBean {
            /**
             * type : 1
             * courseId : 5713
             * title : hhhhhh
             * thumb : https://education-1254383113.file.myqcloud.com/4467897693573.jpg
             * package : MBA~1测试产品包后台功能测试产品包后台功能
             * status : 0
             * handout :
             * week : 星期一
             * time : 03-04 14:00-16:00
             * hasJoin : 0
             */

            private int type;
            private int playStatus;
            private String courseId;
            private String title;
            private String thumb;
            @SerializedName("package")
            private String packageX;
            private int status;
            private String handout;
            private String week;
            private String time;
            private int hasJoin;

            public int getPlayStatus() {
                return playStatus;
            }

            public void setPlayStatus(int playStatus) {
                this.playStatus = playStatus;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getHandout() {
                return handout;
            }

            public void setHandout(String handout) {
                this.handout = handout;
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

            public int getHasJoin() {
                return hasJoin;
            }

            public void setHasJoin(int hasJoin) {
                this.hasJoin = hasJoin;
            }
        }

        public static class TaskListBean {
            /**
             * type : 2
             * examId : 116
             * title : 水水水水水水水水水水水水水水水水
             * package : 产品包1
             * classify : 1
             * week : 星期四
             * time : 11-29 17:26-17:26
             * isRepeat : 0
             * isStart : 0
             * isEnd : 0
             * hasJoin : 0
             * status : 0
             */

            private int type;
            private String examId;
            private String title;
            @SerializedName("package")
            private String packageX;
            private String classify;
            private String courseId;
            private String week;
            private String time;
            private int isRepeat;
            private int isStart;
            private int isEnd;
            private int hasJoin;
            private int status;
            private int isView;
            private int left;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getIsView() {
                return isView;
            }

            public void setIsView(int isView) {
                this.isView = isView;
            }

            public String getCourseId() {
                return courseId;
            }

            public void setCourseId(String courseId) {
                this.courseId = courseId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getExamId() {
                return examId;
            }

            public void setExamId(String examId) {
                this.examId = examId;
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

            public String getClassify() {
                return classify;
            }

            public void setClassify(String classify) {
                this.classify = classify;
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

            public int getHasJoin() {
                return hasJoin;
            }

            public void setHasJoin(int hasJoin) {
                this.hasJoin = hasJoin;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class ExamListBean {
            /**
             * type : 3
             * examId : 116
             * title : 水水水水水水水水水水水水水水水水
             * package : 产品包1
             * classify : 1
             * week : 星期四
             * time : 11-29 17:26-17:26
             * isRepeat : 0
             * isStart : 0
             * isEnd : 0
             * hasJoin : 0
             * status : 0
             */

            private int type;
            private String examId;
            private String title;
            @SerializedName("package")
            private String packageX;
            private String classify;
            private String week;
            private String time;
            private int isRepeat;
            private int isStart;
            private int isEnd;
            private int hasJoin;
            private int status;
            private int playStatus;

            public int getPlayStatus() {
                return playStatus;
            }

            public void setPlayStatus(int playStatus) {
                this.playStatus = playStatus;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getExamId() {
                return examId;
            }

            public void setExamId(String examId) {
                this.examId = examId;
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

            public String getClassify() {
                return classify;
            }

            public void setClassify(String classify) {
                this.classify = classify;
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

            public int getHasJoin() {
                return hasJoin;
            }

            public void setHasJoin(int hasJoin) {
                this.hasJoin = hasJoin;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class ThesisListBean {
            /**
             * type : 4
             * thesisId : 116
             * title : 水水水水水水水水水水水水水水水水
             * package : 产品包1
             * week : 星期四
             * fileUrl : https://education-1254383113.file.myqcloud.com/44678976.pdf
             * editFileUrl : https://education-1254383113.file.myqcloud.com/44678976.pdf
             * time : 11-29 17:26-17:26
             * hasJoin : 0
             * status : 0
             */

            private int type;
            private String thesisId;
            private String title;
            @SerializedName("package")
            private String packageX;
            private String week;
            private String fileUrl;
            private String editFileUrl;
            private String time;
            private int hasJoin;
            private int status;
            private int isEnd;
            private int isStart;

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getThesisId() {
                return thesisId;
            }

            public void setThesisId(String thesisId) {
                this.thesisId = thesisId;
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

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public String getEditFileUrl() {
                return editFileUrl;
            }

            public void setEditFileUrl(String editFileUrl) {
                this.editFileUrl = editFileUrl;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getHasJoin() {
                return hasJoin;
            }

            public void setHasJoin(int hasJoin) {
                this.hasJoin = hasJoin;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }

    public static class BannersBean {
        /**
         * imgUrl : https://education-1254383113.file.myqcloud.com/92951476854199.png
         * url : http://www.baidu.com
         */

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
        /**
         * name : 考试安排
         * iconUrl : https://education-1254383113.file.myqcloud.com/24781868119455.png
         * type : 2
         */

        private String name;
        private String iconUrl;
        private String type;
        private String link_url;
        private int link_type;

        public int getLink_type() {
            return link_type;
        }

        public void setLink_type(int link_type) {
            this.link_type = link_type;
        }

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

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
        /**
         * courseId : 5733
         * video_type : 1
         * meeting_id :
         * title : 3.5日直播课测试测试预告长度
         * desc :
         * poster : https://education-1254383113.file.myqcloud.com/107821865428536.jpg
         * subject : MBA~1测试产品包后台功能测试产品包后台功能
         * teacher : 孙瑜behanvior teacher hollwood
         * playType : 1
         * status : 0
         * type : 0
         * videoType : 1
         * meetingId :
         * orderStatus : 0
         * startTime : 03月05日 14:46分
         * playStatus : 0
         * headUrls : ["https://education-1254383113.file.myqcloud.com/51165983039252.png","https://education-1254383113.file.myqcloud.com/106691320248623.png"]
         * orderNum : 56077
         * viewNum : 0
         */

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

    public static class CourseNoticeBean implements Parcelable {
        /**
         * courseId : 134
         * title : 讨论课1
         * desc :
         * poster : https://education-1254383113.file.myqcloud.com/83044595662420.jpg
         * startTime : 20:00:00
         * type : 1
         * handout :
         * subject : 管理经济学
         * teacher : 喻天骥
         * date_time : 2018-09-13
         * orderStatus : 0
         * headUrls : ["https://avatars0.githubuse_2","https://education-1254383113.file.myqcloud.com/110535059558360.png","4","https://avatars0.githubuse_5","https://avatars0.githubuse_3","https://avatars0.githubuse_1","43463635607439.jpg","https://education-1254383113.file.myqcloud.com/62155897281084.png"]
         * orderNum : 33
         */

        private String courseId;
        private String title;
        private String desc;
        private String poster;
        private String startTime;
        private String type;
        private String subject;
        private String teacher;
        private int playType;

        public int getPlayType() {
            return playType;
        }

        public void setPlayType(int playType) {
            this.playType = playType;
        }

        private int orderStatus;
        private int orderNum;
        private List<String> headUrls;

        public CourseNoticeBean() {
        }

        public CourseNoticeBean(Parcel in) {
            courseId = in.readString();
            title = in.readString();
            desc = in.readString();
            poster = in.readString();
            startTime = in.readString();
            type = in.readString();
            subject = in.readString();
            teacher = in.readString();
            orderStatus = in.readInt();
            playType = in.readInt();
            orderNum = in.readInt();
            headUrls = in.createStringArrayList();
        }

        public static final Creator<CourseNoticeBean> CREATOR = new Creator<CourseNoticeBean>() {
            @Override
            public CourseNoticeBean createFromParcel(Parcel in) {
                return new CourseNoticeBean(in);
            }

            @Override
            public CourseNoticeBean[] newArray(int size) {
                return new CourseNoticeBean[size];
            }
        };

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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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


        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public List<String> getHeadUrls() {
            return headUrls;
        }

        public void setHeadUrls(List<String> headUrls) {
            this.headUrls = headUrls;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(courseId);
            dest.writeString(title);
            dest.writeString(desc);
            dest.writeString(poster);
            dest.writeString(startTime);
            dest.writeString(type);
            dest.writeString(subject);
            dest.writeString(teacher);
            dest.writeInt(orderStatus);
            dest.writeInt(playType);
            dest.writeInt(orderNum);
            dest.writeStringList(headUrls);
        }
    }

}
