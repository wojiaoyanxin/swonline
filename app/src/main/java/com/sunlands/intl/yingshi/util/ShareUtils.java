package com.sunlands.intl.yingshi.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.blankj.utilcode.util.ImageUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;

import static com.sunlands.intl.yingshi.App.sLPApplication;

/**
 * Created by yanxin on 2019/3/11.
 */

public class ShareUtils {

//    分享到对话:
//    SendMessageToWX.Req.WXSceneSession
//    分享到朋友圈:
//    SendMessageToWX.Req.WXSceneTimeline ;
//    分享到收藏:
//    SendMessageToWX.Req.WXSceneFavorite

    //网址分享
    public static void shareUrl(Context context, String title,
                                String description,
                                String transaction, int scene,
                                String url, int id) {

        //初始化一个WXWebpageObject，填写url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        Bitmap thumbBmp = BitmapFactory.decodeResource(context.getResources(), id);
        msg.thumbData = ImageUtils.bitmap2Bytes(thumbBmp, Bitmap.CompressFormat.PNG);
        msg.description = description;
        msg.title = title;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = transaction;
        req.message = msg;
        req.scene = scene;
        //调用api接口，发送数据到微信
        sLPApplication.api.sendReq(req);
    }

    // 文字分享
    public static void shareText(String text, String transaction, int scene) {

//初始化一个 WXTextObject 对象，填写分享的文本内容
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;
//用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = text;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = transaction;
        req.message = msg;
        req.scene = scene;
        //调用api接口，发送数据到微信
        sLPApplication.api.sendReq(req);
    }
    // 图片分享

}
