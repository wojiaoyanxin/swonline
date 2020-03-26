package com.sunlands.intl.yingshi.greendao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.sunlands.intl.yingshi.greendao.DaoMaster;
import com.sunlands.intl.yingshi.greendao.HandoutDbBeanDao;
import com.sunlands.intl.yingshi.greendao.LoginInfoDao;
import com.sunlands.intl.yingshi.greendao.PaperDbBeanDao;
import com.sunlands.intl.yingshi.greendao.SubjectDbBeanDao;

import org.greenrobot.greendao.database.Database;

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                    
                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }
                    
                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                },LoginInfoDao.class, PaperDbBeanDao.class, SubjectDbBeanDao.class, HandoutDbBeanDao.class);
    }
}