package com.sunlands.intl.yingshi.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前包名: com.sunlands.intl.yingshi.bean
 * 创 建 人: xueh
 * 创建日期: 2019/4/10 14:04
 * 备注：
 */
public class ChannelListBean {
    public List<ListBean> getList() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<ListBean> list;
    public static class ListBean {
        /**
         * id : 8
         * title : 测试2
         * img_id : 36362332663966.png
         * status : 1
         * is_deleted : 0
         * updated_at : 2019-03-14 17:50:48
         * created_at : 2019-03-14 17:50:48
         * img_url : http://bj-test-1254383113.file.myqcloud.com/36362332663966.png
         */

        public int id;
        public String title;
        public String img_id;
        public int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg_id() {
            return img_id == null ? "" : img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
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

        public String getImg_url() {
            return img_url == null ? "" : img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public int is_deleted;
        public String updated_at;
        public String created_at;
        public String img_url;
    }

}
