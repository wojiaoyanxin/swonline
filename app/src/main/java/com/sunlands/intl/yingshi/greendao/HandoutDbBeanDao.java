package com.sunlands.intl.yingshi.greendao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import com.sunlands.intl.yingshi.greendao.db.HandoutDbBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "HANDOUT_DB_BEAN".
*/
public class HandoutDbBeanDao extends AbstractDao<HandoutDbBean, Long> {

    public static final String TABLENAME = "HANDOUT_DB_BEAN";

    /**
     * Properties of entity HandoutDbBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SubjectDbBeanId = new Property(1, Long.class, "subjectDbBeanId", false, "SUBJECT_DB_BEAN_ID");
        public final static Property SubjectId = new Property(2, String.class, "subjectId", false, "SUBJECT_ID");
        public final static Property CourseId = new Property(3, String.class, "courseId", false, "COURSE_ID");
        public final static Property CourseName = new Property(4, String.class, "courseName", false, "COURSE_NAME");
        public final static Property Date = new Property(5, String.class, "date", false, "DATE");
        public final static Property FilePath = new Property(6, String.class, "filePath", false, "FILE_PATH");
        public final static Property FileName = new Property(7, String.class, "fileName", false, "FILE_NAME");
        public final static Property FileUrl = new Property(8, String.class, "fileUrl", false, "FILE_URL");
        public final static Property Sid = new Property(9, int.class, "sid", false, "SID");
        public final static Property UserId = new Property(10, Long.class, "userId", false, "USER_ID");
    }

    private Query<HandoutDbBean> subjectDbBean_MHandoutDbBeansQuery;

    public HandoutDbBeanDao(DaoConfig config) {
        super(config);
    }
    
    public HandoutDbBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HANDOUT_DB_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SUBJECT_DB_BEAN_ID\" INTEGER NOT NULL ," + // 1: subjectDbBeanId
                "\"SUBJECT_ID\" TEXT NOT NULL ," + // 2: subjectId
                "\"COURSE_ID\" TEXT," + // 3: courseId
                "\"COURSE_NAME\" TEXT," + // 4: courseName
                "\"DATE\" TEXT," + // 5: date
                "\"FILE_PATH\" TEXT," + // 6: filePath
                "\"FILE_NAME\" TEXT," + // 7: fileName
                "\"FILE_URL\" TEXT," + // 8: fileUrl
                "\"SID\" INTEGER NOT NULL ," + // 9: sid
                "\"USER_ID\" INTEGER);"); // 10: userId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HANDOUT_DB_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, HandoutDbBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getSubjectDbBeanId());
        stmt.bindString(3, entity.getSubjectId());
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(4, courseId);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(5, courseName);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(6, date);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(7, filePath);
        }
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(8, fileName);
        }
 
        String fileUrl = entity.getFileUrl();
        if (fileUrl != null) {
            stmt.bindString(9, fileUrl);
        }
        stmt.bindLong(10, entity.getSid());
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(11, userId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, HandoutDbBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getSubjectDbBeanId());
        stmt.bindString(3, entity.getSubjectId());
 
        String courseId = entity.getCourseId();
        if (courseId != null) {
            stmt.bindString(4, courseId);
        }
 
        String courseName = entity.getCourseName();
        if (courseName != null) {
            stmt.bindString(5, courseName);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(6, date);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(7, filePath);
        }
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(8, fileName);
        }
 
        String fileUrl = entity.getFileUrl();
        if (fileUrl != null) {
            stmt.bindString(9, fileUrl);
        }
        stmt.bindLong(10, entity.getSid());
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(11, userId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public HandoutDbBean readEntity(Cursor cursor, int offset) {
        HandoutDbBean entity = new HandoutDbBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // subjectDbBeanId
            cursor.getString(offset + 2), // subjectId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // courseId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // courseName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // date
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // filePath
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // fileName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // fileUrl
            cursor.getInt(offset + 9), // sid
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10) // userId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, HandoutDbBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSubjectDbBeanId(cursor.getLong(offset + 1));
        entity.setSubjectId(cursor.getString(offset + 2));
        entity.setCourseId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCourseName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDate(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFilePath(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFileName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setFileUrl(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSid(cursor.getInt(offset + 9));
        entity.setUserId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(HandoutDbBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(HandoutDbBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(HandoutDbBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "mHandoutDbBeans" to-many relationship of SubjectDbBean. */
    public List<HandoutDbBean> _querySubjectDbBean_MHandoutDbBeans(Long subjectDbBeanId) {
        synchronized (this) {
            if (subjectDbBean_MHandoutDbBeansQuery == null) {
                QueryBuilder<HandoutDbBean> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.SubjectDbBeanId.eq(null));
                subjectDbBean_MHandoutDbBeansQuery = queryBuilder.build();
            }
        }
        Query<HandoutDbBean> query = subjectDbBean_MHandoutDbBeansQuery.forCurrentThread();
        query.setParameter(0, subjectDbBeanId);
        return query.list();
    }

}
