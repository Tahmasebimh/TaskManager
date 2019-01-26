package com.example.hossein.taskmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hossein.taskmanager.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class MyDevHelper extends DaoMaster.DevOpenHelper {
    public MyDevHelper(Context context, String name) {
        super(context, name);
    }

    public MyDevHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
