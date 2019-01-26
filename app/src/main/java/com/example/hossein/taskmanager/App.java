package com.example.hossein.taskmanager;

import android.app.Application;

import com.example.hossein.taskmanager.model.DaoMaster;
import com.example.hossein.taskmanager.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private static App ourInstance;

    public static App getInstance() {
        return ourInstance;
    }



    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        MyDevHelper myDevOpenHelper = new MyDevHelper(this, "DatabaseName");
        Database db = myDevOpenHelper.getWritableDb();

        mDaoSession = new DaoMaster(db).newSession();
        ourInstance = this ;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
