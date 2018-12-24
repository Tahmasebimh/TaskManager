package com.example.hossein.taskmanager.model;

import java.util.Date;
import java.util.UUID;

public class Task {

    private String title , date , descryption ;
    private boolean done = false ;
    private boolean isEdited = false ;
    private UUID mUUID;

    public Task() {
        Date date1 = new Date();
        this.date = date1.toString();
        this.mUUID = UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
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
