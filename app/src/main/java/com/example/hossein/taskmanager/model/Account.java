package com.example.hossein.taskmanager.model;

import android.content.Intent;
import android.os.Bundle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class Account {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String username ;

    private String password ;


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, int databaseID) {
        this.username = username;
        this.password = password;
        this.id = Long.valueOf(databaseID);
    }

    @Generated(hash = 1711645833)
    public Account(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
