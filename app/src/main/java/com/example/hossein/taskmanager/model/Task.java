package com.example.hossein.taskmanager.model;

import android.support.annotation.IntDef;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;


@Entity
public class Task  {

    @Id(autoincrement = true)
    private Long id;
    private Long accID ;
    @ToOne(joinProperty = "accID")
    private Account mAccount;

    private String title , descryption ;
    private boolean done = false ;

    @Convert(columnType = String.class , converter = UUIDConverter.class)
    private UUID mUUID;
    private Date mDate;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;
    @Generated(hash = 962504994)
    private transient Long mAccount__resolvedKey;




    public Task(UUID uuid) {
        mUUID = uuid;
        Date date = new Date();
        mDate = date;
    }


    @Generated(hash = 245630661)
    public Task(Long id, Long accID, String title, String descryption, boolean done,
            UUID mUUID, Date mDate) {
        this.id = id;
        this.accID = accID;
        this.title = title;
        this.descryption = descryption;
        this.done = done;
        this.mUUID = mUUID;
        this.mDate = mDate;
    }


    @Generated(hash = 733837707)
    public Task() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getAccID() {
        return this.accID;
    }


    public void setAccID(Long accID) {
        this.accID = accID;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescryption() {
        return this.descryption;
    }


    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }


    public boolean getDone() {
        return this.done;
    }


    public void setDone(boolean done) {
        this.done = done;
    }


    public UUID getMUUID() {
        return this.mUUID;
    }


    public void setMUUID(UUID mUUID) {
        this.mUUID = mUUID;
    }


    public Date getMDate() {
        return this.mDate;
    }


    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1253912853)
    public Account getMAccount() {
        Long __key = this.accID;
        if (mAccount__resolvedKey == null || !mAccount__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AccountDao targetDao = daoSession.getAccountDao();
            Account mAccountNew = targetDao.load(__key);
            synchronized (this) {
                mAccount = mAccountNew;
                mAccount__resolvedKey = __key;
            }
        }
        return mAccount;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1819882386)
    public void setMAccount(Account mAccount) {
        synchronized (this) {
            this.mAccount = mAccount;
            accID = mAccount == null ? null : mAccount.getId();
            mAccount__resolvedKey = accID;
        }
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
    @Generated(hash = 1442741304)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTaskDao() : null;
    }


    public static class UUIDConverter implements PropertyConverter<UUID , String>{

        @Override
        public UUID convertToEntityProperty(String databaseValue) {
            if(databaseValue == null){
                return null;
            }
            return UUID.fromString(databaseValue);
        }

        @Override
        public String convertToDatabaseValue(UUID entityProperty) {
            if(entityProperty == null){
                return null;
            }
            return entityProperty.toString();
        }
    }

}
