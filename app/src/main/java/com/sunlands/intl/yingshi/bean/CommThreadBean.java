package com.sunlands.intl.yingshi.bean;

import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/4/10 17:36
 * 备注：
 */
public class CommThreadBean {

    /**
     * thread : {"id":4610,"user_id":187,"title":"就来了","content":"噢噢噢哦哦","to_user_id":0,"to_group_id":"0","crawled_from":"","is_crawled":0,"real_view_num":0,"virtual_view_num":0,"published_at":"今天 17:33:40","is_fixed_time":0,"fixed_time_at":"2019-04-10 17:33:40","weight":0,"view_num":0,"shared_num":0,"liked_num":0,"channel_id":0,"is_stuck":0,"status":1,"stuck_at":"2019-04-10 17:33:40","is_public":0,"img_num":2,"is_deleted":0,"updated_at":"2019-04-10 17:33:40","created_at":"2019-04-10 17:33:40"}
     * extend : {"comment_num":"0","is_liked":"0","is_collect":"0"}
     * image : ["http://bj-test-1254383113.file.myqcloud.com/123227374073611.png","http://bj-test-1254383113.file.myqcloud.com/55607408972270.png"]
     * user_info : {"id":"262","nickname":"11","mobile":"13070172177","is_virtual":0,"app_user_id":"0","head_img_url":"https://education-1254383113.file.myqcloud.com/103982295236453.jpg"}
     */

    public ThreadBean thread;
    public ExtendBean extend;
    public UserInfoBean user_info;
    public List<String> image;

    public static class ThreadBean {
        /**
         * id : 4610
         * user_id : 187
         * title : 就来了
         * content : 噢噢噢哦哦
         * to_user_id : 0
         * to_group_id : 0
         * crawled_from :
         * is_crawled : 0
         * real_view_num : 0
         * virtual_view_num : 0
         * published_at : 今天 17:33:40
         * is_fixed_time : 0
         * fixed_time_at : 2019-04-10 17:33:40
         * weight : 0
         * view_num : 0
         * shared_num : 0
         * liked_num : 0
         * channel_id : 0
         * is_stuck : 0
         * status : 1
         * stuck_at : 2019-04-10 17:33:40
         * is_public : 0
         * img_num : 2
         * is_deleted : 0
         * updated_at : 2019-04-10 17:33:40
         * created_at : 2019-04-10 17:33:40
         */

        public int id;
        public int user_id;
        public String title;
        public String content;
        public int to_user_id;
        public String to_group_id;
        public String crawled_from;
        public int is_crawled;
        public int real_view_num;
        public int virtual_view_num;
        public String published_at;
        public int is_fixed_time;
        public String fixed_time_at;
        public int weight;
        public int view_num;
        public int shared_num;
        public int liked_num;
        public int channel_id;
        public int is_stuck;
        public int status;
        public String stuck_at;
        public int is_public;
        public int img_num;
        public int is_deleted;
        public String updated_at;
        public String created_at;
    }

    public static class ExtendBean {
        /**
         * comment_num : 0
         * is_liked : 0
         * is_collect : 0
         */

        public String comment_num;
        public String is_liked;
        public String is_collect;
    }

    public static class UserInfoBean {
        /**
         * id : 262
         * nickname : 11
         * mobile : 13070172177
         * is_virtual : 0
         * app_user_id : 0
         * head_img_url : https://education-1254383113.file.myqcloud.com/103982295236453.jpg
         */

        public String id;
        public String nickname;
        public String mobile;
        public int is_virtual;
        public String app_user_id;
        public String head_img_url;
    }
}
