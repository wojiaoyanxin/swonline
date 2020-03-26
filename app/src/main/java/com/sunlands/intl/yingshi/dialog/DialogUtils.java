package com.sunlands.intl.yingshi.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * Created by yanxin on 2019/5/9.
 * 弹窗工具类
 */

public class DialogUtils {


    /**
     * 点击确定的回调接口
     */
    public interface onClick {

        void sure();
    }

    public static void exit(Context mContext, onClick onClick) {

        //实例化对话框的内部类Builder(构建器)对象
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("直播结束,点击退出");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClick != null) {
                    onClick.sure();
                }
            }
        });
        if (((Activity) mContext).isDestroyed()) {
            return;
        }
        builder.create().show();
    }


    /**
     * 直播结束,退出方法
     *
     * @param mContext 上下文
     * @param onClick  回到函数
     */

    public static void go(Context mContext, String text, onClick onClick) {

        //实例化对话框的内部类Builder(构建器)对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        if (TextUtils.isEmpty(text)) {
            builder.setTitle("提示");
            builder.setMessage("已购买该课程");
        } else {
            builder.setTitle(text);
            builder.setMessage("点击观看课程");
        }
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClick != null) {
                    onClick.sure();
                }
            }
        });
        builder.create().show();
    }

    /**
     * 删除文件
     *
     * @param mContext 上下文
     * @param onClick  回到函数
     */


    public static void deleteFile(Context mContext, onClick onClick) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("确定删除吗?");
        builder.setCancelable(false);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClick != null) {
                    onClick.sure();
                }
            }
        });

        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        if (!builder.create().isShowing()) {
            builder.create().show();
        }

    }


    /**
     * 下载失败
     *
     * @param mContext 上下文
     * @param onClick  回到函数
     */

    public static void reDownLoad(Context mContext, onClick onClick) {

        //实例化对话框的内部类Builder(构建器)对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage("下载失败,点击重新下载");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (onClick != null) {
                    onClick.sure();
                }
            }
        });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

}
