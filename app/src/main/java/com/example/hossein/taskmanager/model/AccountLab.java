package com.example.hossein.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.hossein.taskmanager.App;
import com.example.hossein.taskmanager.database.TaskDBShema;
import com.example.hossein.taskmanager.database.TaskManagerBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AccountLab {

    private static  AccountLab ourInstance ;
    private SQLiteDatabase mSQLiteDatabase;
    private Context mContext;
    private List<Account> mAccountList;
    DaoSession mDaoSession;
    AccountDao mAccountDao;

    public static AccountLab getInstance(Context context) {

        if(ourInstance == null){
            ourInstance = new AccountLab(context);
        }
        return ourInstance;
    }

    private AccountLab(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new TaskManagerBaseHelper(mContext).getWritableDatabase();
        mDaoSession = App.getApp().getDaoSession();
        mAccountDao = mDaoSession.getAccountDao();
    }

    public void addAccount ( Account account){
        mAccountDao.insert(account);
    }

    public boolean isAccountInDatabase (Account account){

        mAccountList = new ArrayList<>();
        mAccountList = mAccountDao.loadAll();
        for(Account account1 : mAccountList){
            if (account1.getUsername().equals(account.getUsername()) && account1.getPassword().equals(account.getPassword())){
                LoginedUser.getInstance().setId(account1.getId());
                LoginedUser.getInstance().setUserName(account1.getUsername());
                LoginedUser.getInstance().setPassword(account1.getPassword());
                return true;
            }
        }
        return false;
    }

    public boolean isUserNameInDatabase (String userName){


        return false;
    }


    private ContentValues getContentValue(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDBShema.AccountTable.Cols.USERNAME , account.getUsername());
        contentValues.put(TaskDBShema.AccountTable.Cols.PASSWORD , account.getPassword());

        return contentValues;
    }
}
