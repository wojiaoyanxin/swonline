package com.sunlands.intl.yingshi.ui.my.bean;

import java.util.List;

/**
 * Created by yanxin on 2019/2/20.
 */

public class MyRewardBean {


    /**
     * hasMore : 0
     * couponList : [{"coupon_name":"aaa","coupon_num":10,"price":20,"created_at":"1970-01-01 08:01:51"}]
     */

    private int hasMore;
    private List<CouponListBean> couponList;

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public List<CouponListBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<CouponListBean> couponList) {
        this.couponList = couponList;
    }

    public static class CouponListBean {
        /**
         * coupon_name : aaa
         * coupon_num : 10
         * price : 20
         * created_at : 1970-01-01 08:01:51
         */

        private String coupon_name;
        private int coupon_num;
        private int price;
        private String created_at;

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public int getCoupon_num() {
            return coupon_num;
        }

        public void setCoupon_num(int coupon_num) {
            this.coupon_num = coupon_num;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
