package com.sunlands.intl.yingshi.ui.activity.home.leakfilling.bean;

import java.util.List;

/**
 * Created by yanxin on 2019/4/8.
 */

public class LeakThesisBean {

    /**
     * recent : [{"rid":"84","title":"国台办批台湾高中历史课纲","thesis_time":"2018-11-16","time":"11-16 11:31","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1}]
     * more : [{"rid":"84","title":"国台办批台湾高中历史课纲","thesis_time":"2018-11-16","time":"11-16 11:31","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"85","title":"商业法律环境论文","thesis_time":"2018-11-16","time":"11-16 11:37","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"100","title":"商业法律环境论文","thesis_time":"2019-02-11","time":"2019-02-11 15:43","hasJoined":0,"hasBegin":1,"hasEnd":0,"canRepeat":1},{"rid":"101","title":"酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷","thesis_time":"2019-02-19","time":"02-19 18:01","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"103","title":"酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷","thesis_time":"2019-02-25","time":"02-25 15:11","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"123","title":"酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷","thesis_time":"2019-02-25","time":"02-25 18:18","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"106","title":"On Influence of Cultural Differences on Associative Meanings and Translation","thesis_time":"2019-02-27","time":"02-27 11:32","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"124","title":"酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷酷","thesis_time":"2019-02-27","time":"02-27 18:19","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"127","title":"a","thesis_time":"2019-03-06","time":"03-06 15:09","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1},{"rid":"114","title":"精打细算","thesis_time":"2019-03-07","time":"03-07 14:23","hasJoined":0,"hasBegin":1,"hasEnd":1,"canRepeat":1}]
     * hasMore : 1
     */

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }
    private int hasMore;


    private List<RecentBean> recent;
    private List<MoreBean> more;



    public List<RecentBean> getRecent() {
        return recent;
    }

    public void setRecent(List<RecentBean> recent) {
        this.recent = recent;
    }

    public List<MoreBean> getMore() {
        return more;
    }

    public void setMore(List<MoreBean> more) {
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
