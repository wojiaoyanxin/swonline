package com.sunlands.intl.yingshi.greendao.helper;

import com.sunlands.intl.yingshi.greendao.MyAllFileDbBeanDao;
import com.sunlands.intl.yingshi.greendao.db.HandoutDbBean;
import com.sunlands.intl.yingshi.greendao.db.LoginInfo;
import com.sunlands.intl.yingshi.greendao.db.MyAllFileDbBean;
import com.sunlands.intl.yingshi.greendao.db.SubjectDbBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBSaveUtils {


    public static void saveDownloadFile(String subjectId, String subjectName,
                                        String courseId, String courseName,
                                        String path, String date, String mFileName, int sid, String url) {


        DbHelper.getInstance().getAllFileDbBeanDao().detachAll();
        LoginInfo loginInfo = (LoginInfo) DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
        if (loginInfo == null) {
            return;
        }
        MyAllFileDbBean myAllFileDbBean = new MyAllFileDbBean();
        myAllFileDbBean.setCourseId(courseId);
        myAllFileDbBean.setCourseName(courseName);
        myAllFileDbBean.setDate(date);
        myAllFileDbBean.setFileName(mFileName);
        myAllFileDbBean.setFilePath(path);
        myAllFileDbBean.setFileUrl(url);
        myAllFileDbBean.setSid(sid);
        myAllFileDbBean.setSubjectName(subjectName);
        myAllFileDbBean.setSubjectId(subjectId);
        myAllFileDbBean.setUserId(loginInfo.getUserId() + "");

        int userId = loginInfo.getUserId();

        QueryBuilder<MyAllFileDbBean> myAllFileDbBeanQueryBuilder = DbHelper.mDaoSession.queryBuilder(MyAllFileDbBean.class);

        List<MyAllFileDbBean> list = myAllFileDbBeanQueryBuilder
                .where(MyAllFileDbBeanDao.Properties.SubjectId.eq(subjectId))
                .where(MyAllFileDbBeanDao.Properties.Sid.eq(sid))
                .where(MyAllFileDbBeanDao.Properties.UserId.eq(userId + ""))
                .where(MyAllFileDbBeanDao.Properties.CourseId.eq(courseId)).list();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                DbHelper.getInstance().getAllFileDbBeanDao().deleteByKey(list.get(i).getId());
            }
            DbHelper.getInstance().getAllFileDbBeanDao().insert(myAllFileDbBean);
        } else {
            DbHelper.getInstance().getAllFileDbBeanDao().insert(myAllFileDbBean);
        }
    }


    public static void removeOldDataToNew() {

        if (DbHelper.getInstance().getHandoutDbBeanDao().count() == 0) {
            return;
        }

        DbHelper.getInstance().getHandoutDbBeanDao().detachAll();
        LoginInfo loginInfo = DbHelper.getInstance().getLoginInfoDao().queryBuilder().unique();
        List<HandoutDbBean> handoutDbBeans = DbHelper.getInstance().getHandoutDbBeanDao().loadAll();

        for (int i = 0; i < handoutDbBeans.size(); i++) {
            HandoutDbBean handoutDbBean = handoutDbBeans.get(i);
            SubjectDbBean unique = DbHelper.getInstance().getSubjectDbBeanDao().queryBuilder().where(com.sunlands.intl.yingshi.greendao.SubjectDbBeanDao.Properties.SubjectId.eq(handoutDbBean.getSubjectId())).build().unique();
            MyAllFileDbBean myAllFileDbBean = new MyAllFileDbBean();
            myAllFileDbBean.setCourseId(handoutDbBean.getCourseId());
            myAllFileDbBean.setCourseName(handoutDbBean.getCourseName());
            myAllFileDbBean.setDate(handoutDbBean.getDate());
            myAllFileDbBean.setFileName(handoutDbBean.getFileName());
            myAllFileDbBean.setFilePath(handoutDbBean.getFilePath());
            myAllFileDbBean.setFileUrl(handoutDbBean.getFileUrl());
            myAllFileDbBean.setSid(handoutDbBean.getSid());
            myAllFileDbBean.setSubjectName(unique.getName());
            myAllFileDbBean.setSubjectId(handoutDbBean.getSubjectId());
            myAllFileDbBean.setUserId(loginInfo.getUserId() + "");
            DbHelper.getInstance().getAllFileDbBeanDao().insert(myAllFileDbBean);
        }
        DbHelper.getInstance().getHandoutDbBeanDao().deleteAll();
    }
}
