package com.sunlands.intl.yingshi.ui.home.bean;

/**
 * @author yxin
 * @date 2019-11-30 - 15:14
 * @des
 */
public class SmallClassOrderBean {


    /**
     * orderNumber : 2019512351232
     * payUrl : http://trade.sunlands.com/trade/web/trade/payDirect.action?orderNumber=20191121101360&termType=mobile
     */

    private String orderNumber;
    private String payUrl;
    private int orderStatus;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public int getOrderStatus() {

        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
