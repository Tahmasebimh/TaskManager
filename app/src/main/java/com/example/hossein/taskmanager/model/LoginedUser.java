package com.example.hossein.taskmanager.model;

public class LoginedUser {

    private static final LoginedUser ourInstance = new LoginedUser();
    private String userName ;
    private String password ;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static LoginedUser getInstance() {
        return ourInstance;
    }

    private LoginedUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
