package com.sunlands.intl.yingshi.bean;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/4/11 18:52
 * 备注：
 */
public class PostSubmitBean {

    /**
     * post : {"user_id":"187","thread_id":"4615","content":"vh","post_id":"0","re_post_id":"0","created_at":"今天 18:50:25","id":77}
     * user_info : {"id":"262","nickname":"11","mobile":"13070172177","is_virtual":0,"app_user_id":"0","head_img_url":"https://education-1254383113.file.myqcloud.com/103982295236453.jpg"}
     */

    public PostBean post;

    public PostBean getPost() {
        return post;
    }

    public void setPost(PostBean post) {
        this.post = post;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }

    public UserInfoBean user_info;

    public static class PostBean {
        /**
         * user_id : 187
         * thread_id : 4615
         * content : vh
         * post_id : 0
         * re_post_id : 0
         * created_at : 今天 18:50:25
         * id : 77
         */

        public String user_id;

        public String getUser_id() {
            return user_id == null ? "" : user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getThread_id() {
            return thread_id == null ? "" : thread_id;
        }

        public void setThread_id(String thread_id) {
            this.thread_id = thread_id;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPost_id() {
            return post_id == null ? "" : post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getRe_post_id() {
            return re_post_id == null ? "" : re_post_id;
        }

        public void setRe_post_id(String re_post_id) {
            this.re_post_id = re_post_id;
        }

        public String getCreated_at() {
            return created_at == null ? "" : created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String thread_id;
        public String content;
        public String post_id;
        public String re_post_id;
        public String created_at;
        public int id;
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

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname == null ? "" : nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_virtual() {
            return is_virtual;
        }

        public void setIs_virtual(int is_virtual) {
            this.is_virtual = is_virtual;
        }

        public String getApp_user_id() {
            return app_user_id == null ? "" : app_user_id;
        }

        public void setApp_user_id(String app_user_id) {
            this.app_user_id = app_user_id;
        }

        public String getHead_img_url() {
            return head_img_url == null ? "" : head_img_url;
        }

        public void setHead_img_url(String head_img_url) {
            this.head_img_url = head_img_url;
        }

        public String head_img_url;
    }
}
