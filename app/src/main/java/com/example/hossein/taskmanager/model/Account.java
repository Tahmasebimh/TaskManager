package com.example.hossein.taskmanager.model;

import android.content.Intent;
import android.os.Bundle;

public class Account {
    private String username ;
    private String password ;
    private int databaseID ;


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        databaseID = 0 ;
    }

    public Account(String username, String password, int databaseID) {
        this.username = username;
        this.password = password;
        this.databaseID = databaseID;
    }

    public void setDatabaseID(int databaseID) {
        this.databaseID = databaseID;
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
}
