package com.sunlands.intl.yingshi.ui.my.bean;

import java.util.List;

/**
 * Created by yanxin on 2019/2/19.
 */

public class PunchCardBean {


    /**
     * points : 9
     * cType : 1
     * total_clock : 4
     * current_clock : 4
     * has_clock : 1
     * sharePic :
     * shareText :
     * shareId :
     * rules : 1.完成每日打卡可获得相应学习币，每累计7日循环一次，即当累计打卡到第1、8、15、22日等时，可领取第一天的奖励，以此类推
     2..本活动与Apple Inc.公司无关
     * prizeList : [{"day":1,"name":"+1学习币","type":1,"num":1},{"day":2,"name":"+1学习币","type":1,"num":1},{"day":3,"name":"+3学习币","type":1,"num":3},{"day":4,"name":"+4学习币","type":1,"num":4},{"day":5,"name":"+5学习币","type":1,"num":5},{"day":6,"name":"+6学习币","type":1,"num":6},{"day":7,"name":"+7学习币","type":1,"num":7}]
     * mallList : [{"goods_id":"3","goods_name":"优惠券1","goods_type":"1","coupon_num":"10","price":"1"}]
     */

    private int points;
    private int cType;
    private int total_clock;
    private int current_clock;
    private int has_clock;
    private String sharePic;
    private String shareText;
    private String shareId;
    private String rules;
    private List<PrizeListBean> prizeList;
    private List<MallListBean> mallList;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCType() {
        return cType;
    }

    public void setCType(int cType) {
        this.cType = cType;
    }

    public int getTotal_clock() {
        return total_clock;
    }

    public void setTotal_clock(int total_clock) {
        this.total_clock = total_clock;
    }

    public int getCurrent_clock() {
        return current_clock;
    }

    public void setCurrent_clock(int current_clock) {
        this.current_clock = current_clock;
    }

    public int getHas_clock() {
        return has_clock;
    }

    public void setHas_clock(int has_clock) {
        this.has_clock = has_clock;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public List<PrizeListBean> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<PrizeListBean> prizeList) {
        this.prizeList = prizeList;
    }

    public List<MallListBean> getMallList() {
        return mallList;
    }

    public void setMallList(List<MallListBean> mallList) {
        this.mallList = mallList;
    }

    public static class PrizeListBean {
        /**
         * day : 1
         * name : +1学习币
         * type : 1
         * num : 1
         */

        private int day;
        private String name;
        private int type;
        private int num;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

    public static class MallListBean {
        /**
         * goods_id : 3
         * goods_name : 优惠券1
         * goods_type : 1
         * coupon_num : 10
         * price : 1
         */

        private String goods_id;
        private String goods_name;
        private String goods_type;
        private String coupon_num;
        private String price;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(String goods_type) {
            this.goods_type = goods_type;
        }

        public String getCoupon_num() {
            return coupon_num;
        }

        public void setCoupon_num(String coupon_num) {
            this.coupon_num = coupon_num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
