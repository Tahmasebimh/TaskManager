package com.example.hossein.taskmanager.model;

import android.content.Intent;
import android.os.Bundle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

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

    @Generated(hash = 1711645833)
    public Account(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 882125521)
    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
