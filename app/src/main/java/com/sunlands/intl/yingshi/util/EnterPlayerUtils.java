package com.sunlands.intl.yingshi.util;

import android.content.Context;

import com.blankj.utilcode.util.ToastUtils;
import com.sunlands.comm_core.utils.CommonUtils;
import com.sunlands.intl.yingshi.bean.CourseEnterResponse;
import com.sunlands.intl.yingshi.helper.EnterClassRequestHelper;
import com.sunlands.intl.yingshi.ui.activity.home.smallclass.SmallPlayActivity;

/**
 * Created by yanxin on 2019/3/15.
 * 进入课程工具类
 */

public class EnterPlayerUtils {

    public static void enterClass(Context context, int id) {

        if (!CommonUtils.hasNetWorkConection()) {
            ToastUtils.showShort("请检查网络");
            return;
        }

        new EnterClassRequestHelper().enterClass(context, id + "", new EnterClassRequestHelper.enterClassCallBack() {
            @Override
            public void enterSuccess(CourseEnterResponse courseEnterResponse) {

                if (courseEnterResponse.getStatus() == 2) {
                    //回放在生成
                    ToastUtils.showShort("回放生成中");
                    return;
                }
                if (courseEnterResponse.getStatus() == 0) {
                    //回放在生成
                    ToastUtils.showShort("课程未开始");
                    return;
                }
                if (courseEnterResponse.getVideo_type() == 4) {

                    SmallPlayActivity.show(context, id + "", courseEnterResponse.title);

                }else {

                    com.tencent.liteav.demo.play.enterclass.EnterPlayerUtils.enterClass(context, id );

                }
            }

            @Override
            public void enterFailed(String msg) {
                ToastUtils.showShort(msg);
            }
        });

    }

    /**
     * 进入大咖课
     *
     * @param context
     * @param id      课程id
     */
    public static void enterBilingClass(Context context, int id, int is_free) {

    }
}
