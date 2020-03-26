package com.sunlands.intl.yingshi.greendao.helper;

import android.database.sqlite.SQLiteDatabase;

import com.sunlands.intl.yingshi.greendao.DaoMaster;
import com.sunlands.intl.yingshi.greendao.DaoSession;
import com.sunlands.intl.yingshi.greendao.HandoutDbBeanDao;
import com.sunlands.intl.yingshi.greendao.LoginInfoDao;
import com.sunlands.intl.yingshi.greendao.MyAllFileDbBeanDao;
import com.sunlands.intl.yingshi.greendao.MyDownLoadInfoDao;
import com.sunlands.intl.yingshi.greendao.PaperDbBeanDao;
import com.sunlands.intl.yingshi.greendao.SubjectDbBeanDao;
import com.sunlands.intl.yingshi.util.DLog;

/**
 * 文件名: DbHelper
 * 描    述: [该类的简要描述]
 * 创建人: duzzi
 * 创建时间: 2018/9/17
 */
public class DbHelper {
    private static DbHelper sDbHelper;

    private DbHelper() {
        mDaoSession = getDaoSession();
    }

    public static DbHelper getInstance() {
        if (sDbHelper == null) {
            synchronized (DbHelper.class) {
                if (sDbHelper == null) {
                    sDbHelper = new DbHelper();
                }
            }
        }
        return sDbHelper;
    }

    public PaperDbBeanDao getPaperDbBeanDao() {
        return mDaoSession.getPaperDbBeanDao();
    }

    public LoginInfoDao getLoginInfoDao() {
        return mDaoSession.getLoginInfoDao();
    }

    public SubjectDbBeanDao getSubjectDbBeanDao() {
        return mDaoSession.getSubjectDbBeanDao();
    }

    public HandoutDbBeanDao getHandoutDbBeanDao() {
        return mDaoSession.getHandoutDbBeanDao();
    }

    public MyAllFileDbBeanDao getAllFileDbBeanDao() {
        return mDaoSession.getMyAllFileDbBeanDao();
    }

    public MyDownLoadInfoDao getMyDownLoadInfoDao() {
        return mDaoSession.getMyDownLoadInfoDao();
    }


    public static SQLiteDatabase db;
    public static DaoMaster mDaoMaster;
    public static DaoSession mDaoSession;
    private static DaoMaster.OpenHelper mHelper;

    /**
     * 设置greenDao
     */
    public static void setDatabase() {
        DLog.d("setDatabase start");
        mHelper = new MySQLiteOpenHelper(com.blankj.utilcode.util.Utils.getApp(), "sunlands.db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        DLog.d("setDatabase end");
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
