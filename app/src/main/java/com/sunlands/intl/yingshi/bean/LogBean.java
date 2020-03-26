package com.sunlands.intl.yingshi.bean;

import java.util.List;

/**
 * Created by yanxin on 2019/4/30.
 */

public class LogBean {


    /**
     * code : 0
     * msg : success
     * data : {}
     * onlineNum : 2
     * recentList : [{"uid":"15011282874","name":"李小明","avatar":"https://education-1254383113.file.myqcloud.com/9422926843523.jpg","type":1,"forbid":0},{"uid":"15012791707","name":"陈晓霞","avatar":"https://education-1254383113.file.myqcloud.com/36910755282208.png","type":1,"forbid":0}]
     */

    private int onlineNum;
    private List<RecentListBean> recentList;

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
    }

    public List<RecentListBean> getRecentList() {
        return recentList;
    }

    public void setRecentList(List<RecentListBean> recentList) {
        this.recentList = recentList;
    }

    public static class DataBean {



    }

    public static class RecentListBean {
        /**
         * uid : 15011282874
         * name : 李小明
         * avatar : https://education-1254383113.file.myqcloud.com/9422926843523.jpg
         * type : 1
         * forbid : 0
         */

        private String uid;
        private String name;
        private String avatar;
        private int type;
        private int forbid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getForbid() {
            return forbid;
        }

        public void setForbid(int forbid) {
            this.forbid = forbid;
        }
    }
}
