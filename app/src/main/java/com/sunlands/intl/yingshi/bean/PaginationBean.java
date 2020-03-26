package com.sunlands.intl.yingshi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/3/6 14:37
 * 备注：
 */
public class PaginationBean {

    /**
     * start : 0
     * limit : 20
     * count : 2
     * list : [{"post":{"id":179,"user_id":3,"thread_id":79,"post_id":0,"re_post_id":0,"content":"jsjsjsjsj","is_deleted":0,"updated_at":"2019-03-06 15:41:12","created_at":"2019-03-06 15:41:12"},"user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":"https://education-1254383113.file.myqcloud.com/81983737020204.jpg"},"comment_list":[{"post":{"id":"184","user_id":"3","thread_id":"79","post_id":"179","re_post_id":"182","content":"魔龙","is_deleted":"0","updated_at":"2019-03-06 17:27:54","created_at":"2019-03-06 17:27:54"},"user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":""},"re_user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":"https://education-1254383113.file.myqcloud.com/81983737020204.jpg"}},{"post":{"id":"183","user_id":"3","thread_id":"79","post_id":"179","re_post_id":"181","content":"啦咯啦咯啦咯","is_deleted":"0","updated_at":"2019-03-06 17:27:36","created_at":"2019-03-06 17:27:36"},"user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":""},"re_user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":"https://education-1254383113.file.myqcloud.com/81983737020204.jpg"}}],"comment_num":"4","re_user_info":{}}]
     */

    public String start;
    public String limit;

    public String getStart() {
        return start == null ? "" : start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLimit() {
        return limit == null ? "" : limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getCount() {
        return count == null ? "" : count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PaginationBean{" +
                "start='" + start + '\'' +
                ", limit='" + limit + '\'' +
                ", count='" + count + '\'' +
                ", list=" + list +
                '}';
    }

    public String count;
    public List<ListBean> list;

    public static class ListBean {
        /**
         * post : {"id":179,"user_id":3,"thread_id":79,"post_id":0,"re_post_id":0,"content":"jsjsjsjsj","is_deleted":0,"updated_at":"2019-03-06 15:41:12","created_at":"2019-03-06 15:41:12"}
         * user_info : {"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":"https://education-1254383113.file.myqcloud.com/81983737020204.jpg"}
         * comment_list : [{"post":{"id":"184","user_id":"3","thread_id":"79","post_id":"179","re_post_id":"182","content":"魔龙","is_deleted":"0","updated_at":"2019-03-06 17:27:54","created_at":"2019-03-06 17:27:54"},"user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":""},"re_user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":"https://education-1254383113.file.myqcloud.com/81983737020204.jpg"}},{"post":{"id":"183","user_id":"3","thread_id":"79","post_id":"179","re_post_id":"181","content":"啦咯啦咯啦咯","is_deleted":"0","updated_at":"2019-03-06 17:27:36","created_at":"2019-03-06 17:27:36"},"user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":""},"re_user_info":{"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":"https://education-1254383113.file.myqcloud.com/81983737020204.jpg"}}]
         * comment_num : 4
         * re_user_info : {}
         */

        public PostBean post;
        public UserInfoBean user_info;

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

        public String getComment_num() {
            return comment_num == null ? "" : comment_num;
        }

        public void setComment_num(String comment_num) {
            this.comment_num = comment_num;
        }

        public ReUserInfoBean getRe_user_info() {
            return re_user_info;
        }

        public void setRe_user_info(ReUserInfoBean re_user_info) {
            this.re_user_info = re_user_info;
        }

        public List<CommentListBean> getComment_list() {
            if (comment_list == null) {
                return new ArrayList<>();
            }
            return comment_list;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "post=" + post +
                    ", user_info=" + user_info +
                    ", comment_num='" + comment_num + '\'' +
                    ", re_user_info=" + re_user_info +
                    ", comment_list=" + comment_list +
                    '}';
        }

        public void setComment_list(List<CommentListBean> comment_list) {
            this.comment_list = comment_list;
        }

        public String comment_num;
        public ReUserInfoBean re_user_info;
        public List<CommentListBean> comment_list;

        public static class PostBean {
            /**
             * id : 179
             * user_id : 3
             * thread_id : 79
             * post_id : 0
             * re_post_id : 0
             * content : jsjsjsjsj
             * is_deleted : 0
             * updated_at : 2019-03-06 15:41:12
             * created_at : 2019-03-06 15:41:12
             */

            public int id;
            public int user_id;
            public int thread_id;
            public int post_id;
            public int re_post_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getThread_id() {
                return thread_id;
            }

            public void setThread_id(int thread_id) {
                this.thread_id = thread_id;
            }

            public int getPost_id() {
                return post_id;
            }

            public void setPost_id(int post_id) {
                this.post_id = post_id;
            }

            public int getRe_post_id() {
                return re_post_id;
            }

            public void setRe_post_id(int re_post_id) {
                this.re_post_id = re_post_id;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getIs_deleted() {
                return is_deleted;
            }

            public void setIs_deleted(int is_deleted) {
                this.is_deleted = is_deleted;
            }

            public String getUpdated_at() {
                return updated_at == null ? "" : updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getCreated_at() {
                return created_at == null ? "" : created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String content;

            @Override
            public String toString() {
                return "PostBean{" +
                        "id=" + id +
                        ", user_id=" + user_id +
                        ", thread_id=" + thread_id +
                        ", post_id=" + post_id +
                        ", re_post_id=" + re_post_id +
                        ", content='" + content + '\'' +
                        ", is_deleted=" + is_deleted +
                        ", updated_at='" + updated_at + '\'' +
                        ", created_at='" + created_at + '\'' +
                        '}';
            }

            public int is_deleted;
            public String updated_at;
            public String created_at;
        }

        public static class UserInfoBean {
            /**
             * id : 122
             * nickname : 黄艳娜
             * mobile : 15010271269
             * head_img_url : https://education-1254383113.file.myqcloud.com/81983737020204.jpg
             */

            public String id;
            public String nickname;

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

            public String getHead_img_url() {
                return head_img_url == null ? "" : head_img_url;
            }

            @Override
            public String toString() {
                return "UserInfoBean{" +
                        "id='" + id + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", head_img_url='" + head_img_url + '\'' +
                        '}';
            }

            public void setHead_img_url(String head_img_url) {
                this.head_img_url = head_img_url;
            }

            public String mobile;
            public String head_img_url;
        }

        public static class ReUserInfoBean {

            /**
             * id : 974
             * nickname : 魏洋
             * mobile : 17730613137
             * head_img_url : https://education-1254383113.file.myqcloud.com/12683076074934.png
             */

            public String id;

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

            public String getHead_img_url() {
                return head_img_url == null ? "" : head_img_url;
            }

            public void setHead_img_url(String head_img_url) {
                this.head_img_url = head_img_url;
            }

            public String nickname;
            public String mobile;
            public String head_img_url;

            @Override
            public String toString() {
                return "ReUserInfoBean{" +
                        "id='" + id + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", head_img_url='" + head_img_url + '\'' +
                        '}';
            }

        }

        public static class CommentListBean {
            /**
             * post : {"id":"184","user_id":"3","thread_id":"79","post_id":"179","re_post_id":"182","content":"魔龙","is_deleted":"0","updated_at":"2019-03-06 17:27:54","created_at":"2019-03-06 17:27:54"}
             * user_info : {"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":""}
             * re_user_info : {"id":"122","nickname":"黄艳娜","mobile":"15010271269","head_img_url":"https://education-1254383113.file.myqcloud.com/81983737020204.jpg"}
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

            public ReUserInfoBean getRe_user_info() {
                return re_user_info;
            }

            public void setRe_user_info(ReUserInfoBean re_user_info) {
                this.re_user_info = re_user_info;
            }

            @Override
            public String toString() {
                return "CommentListBean{" +
                        "post=" + post +
                        ", user_info=" + user_info +
                        ", re_user_info=" + re_user_info +
                        '}';
            }

            public UserInfoBean user_info;
            public ReUserInfoBean re_user_info;

        }

    }
}
