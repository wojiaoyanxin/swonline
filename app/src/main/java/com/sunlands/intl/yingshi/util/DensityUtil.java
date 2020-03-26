package com.sunlands.intl.yingshi.util;

import android.content.Context;

/**
 * 像素转化工具类
 */
public class DensityUtil {


    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
