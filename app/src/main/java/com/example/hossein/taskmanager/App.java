package com.example.hossein.taskmanager;

import android.app.Application;

import com.example.hossein.taskmanager.model.DaoMaster;
import com.example.hossein.taskmanager.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private static DaoSession mDaoSession;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        MyDevHelper myDevHelper = new MyDevHelper(this , "taskmanager-db");
        Database database = myDevHelper.getWritableDb();

        mDaoSession = new DaoMaster(database).newSession();
        app = this ;
    }

    public static App getApp(){
        return app;
    }

    public  DaoSession getDaoSession(){
        return mDaoSession;
    }
}
