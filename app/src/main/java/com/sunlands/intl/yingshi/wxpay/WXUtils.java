package com.sunlands.intl.yingshi.wxpay;

import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;

/**
 * Created by todoen on 2018/11/7.
 */

public class WXUtils {

    public static void toXiaoChengxu() {

        try {
            //    app 吊起小程序
            WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
            req.userName = "gh_9eed60d3c778";
            // 填小程序原始id
            req.path = "pages/guide/guide";
            // 拉起小程序页面的可带参路径，不填默认拉起小程序首页
            req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE;
            // 可选打开 开发版，体验版和正式版
         //   mWxApi.sendReq(req);
        } catch (Exception e) {
          //  ToastUtil.showCenter("检查到您手机没有安装微信，请安装后使用该功能");
        }

    }
}
