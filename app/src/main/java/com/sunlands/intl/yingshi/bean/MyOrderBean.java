package com.sunlands.intl.yingshi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/5/6 13:43
 * 备注：
 */
public class MyOrderBean implements Serializable {

    /**
     * hasMore : 0
     * paginationList : [{"type":2,"name":"【0基础投资】教你从\u201c没钱理财\u201d到","courseId":1,"thumb":"https://education-1254383113.file.myqcloud.com/small/12.jpg","amount":"0","orderNo":"20191202100016","serviceEndDate":"","serviceBeginDate":"","createdAt":"2019-12-02 12:56:42","status":"待支付","itemId":1825096}]
     */

    private int hasMore;
    private List<ListBean> paginationList;
    private List<ListBean> list;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<ListBean> getPaginationList() {
        return paginationList == null ? list : paginationList;
    }

    public void setPaginationList(List<ListBean> paginationList) {
        this.paginationList = paginationList;
    }

    public static class ListBean implements Serializable {
        /**
         * type : 2
         * name : 【0基础投资】教你从“没钱理财”到
         * courseId : 1
         * thumb : https://education-1254383113.file.myqcloud.com/small/12.jpg
         * amount : 0
         * orderNo : 20191202100016
         * serviceEndDate :
         * serviceBeginDate :
         * createdAt : 2019-12-02 12:56:42
         * status : 待支付
         * itemId : 1825096
         */

        private int type;
        private String name;
        private int courseId;
        private String thumb;
        private String amount;
        private String orderNo;
        private String serviceEndDate;
        private String serviceBeginDate;
        private String createdAt;
        private String status;
        private int itemId;
        private int leftTime;

        public int getLeftTime() {
            return leftTime;
        }

        public void setLeftTime(int leftTime) {
            this.leftTime = leftTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getServiceEndDate() {
            return serviceEndDate;
        }

        public void setServiceEndDate(String serviceEndDate) {
            this.serviceEndDate = serviceEndDate;
        }

        public String getServiceBeginDate() {
            return serviceBeginDate;
        }

        public void setServiceBeginDate(String serviceBeginDate) {
            this.serviceBeginDate = serviceBeginDate;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }
    }
}
