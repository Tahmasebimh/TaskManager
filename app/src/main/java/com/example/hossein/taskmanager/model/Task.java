package com.example.hossein.taskmanager.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Task implements Serializable {

    private String title , date , descryption ;
    private boolean done = false ;
    private boolean isEdited = false ;
    private UUID mUUID;
    private Date mDate;
    private int accID ;

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
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
}
