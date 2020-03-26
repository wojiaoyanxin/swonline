package com.sunlands.intl.yingshi.util;

import android.icu.math.BigDecimal;

import com.sunlands.intl.yingshi.constant.Constants;

/**
 * 文件名: StringUtils
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/8/3
 */
public class StringUtils {
    public static boolean isVerifyCodeValid(CharSequence charSequence) {
        return !isEmpty(charSequence) && charSequence.length() >= Constants.VERIFY_CODE_LENGTH;
    }

    public static boolean isImgVerifyCodeValid(CharSequence charSequence) {
        return !isEmpty(charSequence);
    }

    public static boolean isPasswordValid(CharSequence charSequence) {
        DLog.d(charSequence + ": " + charSequence.length());
        return !isEmpty(charSequence) && charSequence.length() >= Constants.MIN_PASSWORD_LENGTH
                && charSequence.length() <= Constants.MAX_PASSWORD_LENGTH;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    //@RequiresApi(api = Build.VERSION_CODES.N)
    public static String toNumber(int number) {
        String str = "";
        if (number <= 0) {
            str = "0.00万";
        } else {
            double d = (double) number;
            //将数字转换成万
            double num = d / 10000;
            BigDecimal b = null;
            double f1 = 0.0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                b = new BigDecimal(num);
                 f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            //四舍五入保留小数点后2位
            str = f1 + "万";
        }
        return str;
    }

    public static  String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return "文件";
        }

    }
}
