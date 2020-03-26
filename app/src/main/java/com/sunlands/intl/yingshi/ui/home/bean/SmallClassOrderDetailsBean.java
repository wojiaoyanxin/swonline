package com.sunlands.intl.yingshi.ui.home.bean;

/**
 * @author yxin
 * @date 2019-11-30 - 15:14
 * @des
 */
public class SmallClassOrderDetailsBean {


    /**
     * type : 1
     * name : 测试壁垒项目-1
     * amount : 0.01
     * orderNo : 20190429000040
     * createdAt : 2019-04-29 14:59:41
     * status : 已支付
     * leftTime : 0
     */

    private int type;
    private String name;
    private String amount;
    private String orderNo;
    private String createdAt;
    private String status;
    private int leftTime;
    private String courseId;
    private String thumb;

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

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
