package com.sunlands.intl.yingshi.util;

import android.content.Context;
import android.content.Intent;

import com.sunlands.intl.yingshi.greendao.MyAllFileDbBeanDao;
import com.sunlands.intl.yingshi.constant.Constants;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.greendao.db.MyAllFileDbBean;
import com.sunlands.intl.yingshi.greendao.helper.DbHelper;
import com.sunlands.intl.yingshi.helper.LoginInOutHelper;
import com.sunlands.intl.yingshi.ui.activity.ExamTransitionActivity;
import com.sunlands.intl.yingshi.ui.my.handout.view.MaterialActivity;
import com.sunlands.intl.yingshi.ui.my.handout.view.DownLoadActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import static com.sunlands.intl.yingshi.constant.Constants.Key.KEY_FILE_NAME;
import static com.sunlands.intl.yingshi.constant.Constants.Key.KEY_FILE_PATH;

/**
 * 反射类com.sunlands.intl.yingshi.util.ReflectMoudel
 */

public class ReflectMoudel {

    /**
     * 进入做题页面
     *
     * @param context
     * @param mCourseId 课程id
     * @param quiz      试卷id
     */
    public static void toHomeWork(Context context, String mCourseId,
                                  String quiz) {
        ExamTransitionActivity.show(context, quiz, mCourseId);
    }

    /**
     * 判断讲义是否下载
     *
     * @return
     */

    public static boolean isDownLoad(String handoutName, String subjectId, String courseId) {

        LoginInfo loginInfo = DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
        if (loginInfo == null) {
            return false;
        }

        int userId = loginInfo.getUserId();

        QueryBuilder<MyAllFileDbBean> myAllFileDbBeanQueryBuilder = DbHelper.mDaoSession.queryBuilder(MyAllFileDbBean.class);

        List<MyAllFileDbBean> list = myAllFileDbBeanQueryBuilder
                .where(MyAllFileDbBeanDao.Properties.SubjectId.eq(subjectId))
                .where(MyAllFileDbBeanDao.Properties.Sid.eq(0))
                .where(MyAllFileDbBeanDao.Properties.FileName.eq(handoutName))
                .where(MyAllFileDbBeanDao.Properties.UserId.eq(userId + ""))
                .where(MyAllFileDbBeanDao.Properties.CourseId.eq(courseId)).list();

        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 退出登录
     *
     * @param context
     */

    public static void LoginOut(Context context) {
        LoginInOutHelper.loginOut(context);
    }

    /**
     * 进入课程资料页面
     *
     * @param context
     * @param url
     * @param title
     */

    public static void toMaterailActivity(Context context, String url, String title) {

        Intent intent = new Intent(context, MaterialActivity.class);

        intent.putExtra(KEY_FILE_PATH, url);
        intent.putExtra(KEY_FILE_NAME, title);

        context.startActivity(intent);


    }

    /**
     * 进入课程资料下载页面
     *
     * @param context
     * @param courseId
     */

    public static void toDownLoadActivity(Context context, String courseId) {
        Intent intent = new Intent(context, DownLoadActivity.class);
        intent.putExtra(Constants.Key.KEY_COURSE_ID, courseId);
        context.startActivity(intent);
    }


}
