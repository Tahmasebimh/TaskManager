package com.example.hossein.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.hossein.taskmanager.App;
import com.example.hossein.taskmanager.database.AccountCursorWrapper;
import com.example.hossein.taskmanager.database.TaskDBShema;
import com.example.hossein.taskmanager.database.TaskManagerBaseHelper;

import java.util.List;

public class AccountLab {

    private static  AccountLab ourInstance ;
    private SQLiteDatabase mSQLiteDatabase;
    private AccountDao mAccountDao;
    private DaoSession mDaoSession = App.getInstance().getDaoSession();
    private Context mContext;
    private List<Account> accounts;

    public static AccountLab getInstance(Context context) {


        if(ourInstance == null){
            ourInstance = new AccountLab(context);
        }


        return ourInstance;
    }

    private AccountLab(Context context) {
        mContext = context.getApplicationContext();
    //    mSQLiteDatabase = new TaskManagerBaseHelper(mContext).getWritableDatabase();
    }

    public void addAccount ( Account account){
        mAccountDao = mDaoSession.getAccountDao();
        mAccountDao.insert(account);
    }

    public boolean isAccountInDatabase(Account account) {
        mAccountDao = mDaoSession.getAccountDao();
        accounts = mAccountDao.loadAll();
        for (Account account1 : accounts){
            if(account1.getUsername().equals(account.getUsername()) && account1.getPassword().equals(account.getPassword())){
                LoginedUser.getInstance().setId(account1.getId());
                return true;
            }
        }
        return false;
    }
}
