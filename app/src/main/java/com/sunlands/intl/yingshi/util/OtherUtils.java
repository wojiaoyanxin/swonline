package com.sunlands.intl.yingshi.util;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.R;
import com.xueh.bg.drawable.DrawableCreator;

/**
 * 当前包名: com.sunlands.intl.yingshi.helper
 * 创 建 人: xueh
 * 创建日期: 2019/3/12 13:49
 * 备注：
 */
public class OtherUtils {
    public static void setClickableView(View view, int CornersRadius) {
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(CornersRadius))
                .setSolidColor(CommonUtils.getColor(R.color.cl_D2AA77))
                .setStrokeColor(CommonUtils.getColor(R.color.cl_D2AA77))
                .build();
        view.setBackground(drawable);
        view.setClickable(true);
    }

    public static void setNoClickableView(View view, int CornersRadius) {
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(CornersRadius))
                .setSolidColor(CommonUtils.getColor(R.color.cl_cccccc))
                .setStrokeColor(CommonUtils.getColor(R.color.cl_cccccc))
                .build();
        view.setBackground(drawable);
        view.setClickable(false);
    }

    public static void setClickableState(View view, int CornersRadius, int color) {
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(CornersRadius))
                .setSolidColor(CommonUtils.getColor(color))
                .setStrokeColor(CommonUtils.getColor(color))
                .build();
        view.setBackground(drawable);
    }

    public static void setTvClickableState(TextView view, int CornersRadius, int bgColor, int tvColor) {
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(CornersRadius))
                .setSolidColor(CommonUtils.getColor(bgColor))
                .setStrokeColor(CommonUtils.getColor(bgColor))
                .build();
        view.setBackground(drawable);
        view.setTextColor(CommonUtils.getColor(tvColor));
    }

    public static void setTvNoClickableState(TextView view, int CornersRadius, int bgColor, int tvColor) {
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(CornersRadius))
                .setSolidColor(CommonUtils.getColor(bgColor))
                .setStrokeColor(CommonUtils.getColor(bgColor))
                .build();
        view.setBackground(drawable);
        view.setTextColor(CommonUtils.getColor(tvColor));
    }

    public static void setRoundLineBg(TextView view, int radius, @ColorRes int color) {
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(radius))
                .setStrokeColor(CommonUtils.getColor(color))
                .setStrokeWidth(CommonUtils.dip2px(1))
                .build();
        view.setBackground(drawable);
        view.setTextColor(CommonUtils.getColor(color));
    }

    public static void setRoundBg(View view, int radius, @ColorRes int color) {
        Drawable drawable = new DrawableCreator.Builder().setCornersRadius(CommonUtils.dip2px(radius))
                .setSolidColor(CommonUtils.getColor(color))
                .build();
        view.setBackground(drawable);
    }

    public static void jumpToWeixin() {
        /**
         * 跳转到微信
         */
        copy("SUPER-HYM");
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setComponent(cmp);
            Utils.getApp().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            ToastUtils.showShort("检查到您手机没有安装微信");
        }
    }

    /**
     * 复制功能
     */
    private static void copy(String data) {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) Utils.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(null, data);
        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
    }

}
