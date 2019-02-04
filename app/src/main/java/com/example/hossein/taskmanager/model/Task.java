package com.example.hossein.taskmanager.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class Task {

    @Id(autoincrement = true)
    private Long id;

    private String title ;
    private String descryption;
    private boolean done = false ;
    private boolean isEdited = false ;


    @Convert(columnType = String.class , converter = UUIDCinverter.class)
    private UUID mUUID;

    private Date mDate;

    private Long accID ;

    @ToOne(joinProperty = "accID")
    private Account mAccount;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1469429066)
    private transient TaskDao myDao;

    @Generated(hash = 962504994)
    private transient Long mAccount__resolvedKey;

    public Long getAccID() {
        return accID;
    }

    public void setAccID(Long accID) {
        this.accID = accID;
    }

    public Task(UUID uuid) {
        mUUID = uuid;
        Date date = new Date();
        mDate = date;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Task() {
        this(UUID.randomUUID());
    }

    @Generated(hash = 1154391591)
    public Task(Long id, String title, String descryption, boolean done,
            boolean isEdited, UUID mUUID, Date mDate, Long accID) {
        this.id = id;
        this.title = title;
        this.descryption = descryption;
        this.done = done;
        this.isEdited = isEdited;
        this.mUUID = mUUID;
        this.mDate = mDate;
        this.accID = accID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return mDate;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public UUID getUUID() {
        return mUUID;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getDone() {
        return this.done;
    }

    public boolean getIsEdited() {
        return this.isEdited;
    }

    public void setIsEdited(boolean isEdited) {
        this.isEdited = isEdited;
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

    public static class UUIDCinverter implements PropertyConverter<UUID , String>{
        @Override
        public UUID convertToEntityProperty(String databaseValue) {
            return UUID.fromString(databaseValue);
        }

        @Override
        public String convertToDatabaseValue(UUID entityProperty) {
            return entityProperty.toString();
        }
    }

    public String getTaskPhotoName(int counter){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String photoName = "IMG_" + timeStamp + "_" +  this.getTitle() + "_" + counter + ".jpg";
        return photoName;
    }
}
