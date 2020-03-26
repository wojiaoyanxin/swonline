package com.sunlands.intl.yingshi.greendao.db;

import com.sunlands.intl.yingshi.greendao.DaoSession;
import com.sunlands.intl.yingshi.greendao.HandoutDbBeanDao;
import com.sunlands.intl.yingshi.greendao.SubjectDbBeanDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * 文件名: SubjectDbBean
 * 描    述: [产品包数据模型]
 * 创建人: duzzi
 * 创建时间: 2018/10/9
 */
@Entity
public class SubjectDbBean {
    @Id(autoincrement = true)
    private Long id;
    private String subjectId;
    private String name;

    @ToMany(referencedJoinProperty = "subjectDbBeanId")//只能是long？
//    @ToMany(joinProperties = {@JoinProperty(name = "subjectId", referencedName = "subjectDbBeanSubjectId")})
    private List<HandoutDbBean> mHandoutDbBeans;


    private Long userId;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1726681121)
    private transient SubjectDbBeanDao myDao;

    @Generated(hash = 1238904654)
    public SubjectDbBean(Long id, String subjectId, String name, Long userId) {
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.userId = userId;
    }

    @Generated(hash = 1076825061)
    public SubjectDbBean() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SubjectDbBean{" +
                "id=" + id +
                ", subjectId='" + subjectId + '\'' +
                ", name='" + name + '\'' +
                ", mHandoutDbBeans=" + mHandoutDbBeans +
                '}';
    }


    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HandoutDbBean> getHandoutDbBeans() {
        return mHandoutDbBeans;
    }

    public void setHandoutDbBeans(List<HandoutDbBean> handoutDbBeans) {
        mHandoutDbBeans = handoutDbBeans;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 274845429)
    public List<HandoutDbBean> getMHandoutDbBeans() {
        if (mHandoutDbBeans == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HandoutDbBeanDao targetDao = daoSession.getHandoutDbBeanDao();
            List<HandoutDbBean> mHandoutDbBeansNew = targetDao._querySubjectDbBean_MHandoutDbBeans(id);
            synchronized (this) {
                if (mHandoutDbBeans == null) {
                    mHandoutDbBeans = mHandoutDbBeansNew;
                }
            }
        }
        return mHandoutDbBeans;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 56199794)
    public synchronized void resetMHandoutDbBeans() {
        mHandoutDbBeans = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1564471493)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSubjectDbBeanDao() : null;
    }




}
