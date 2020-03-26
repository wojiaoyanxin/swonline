package com.sunlands.intl.yingshi.wxpay;

import com.sunlands.intl.yingshi.App;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.Random;

/**
 * Created by yanxin on 2019/4/1.
 */

public class WxPay {

    private static PayReq req;
    private static void registerWX() {
        req = new PayReq();
        req.appId = WXConstants.APP_ID;
        req.partnerId = WXConstants.MCH_ID;
        req.nonceStr = getNonceStr();
        req.timeStamp = genTimeStamp();
        req.packageValue = "Sign=WXPay";
    }

    //生成随机号，防重发
    private static String getNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    //生成时间戳
    private static String genTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    public static void pay(String sign, String prepayId, String orderno) {
        registerWX();
        req.prepayId = prepayId;
        req.sign = "1111";
        App.sLPApplication.api.sendReq(req);
    }

}
