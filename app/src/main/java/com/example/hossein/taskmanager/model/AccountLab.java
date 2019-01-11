package com.example.hossein.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;

import com.example.hossein.taskmanager.database.AccountCursorWrapper;
import com.example.hossein.taskmanager.database.TaskDBShema;
import com.example.hossein.taskmanager.database.TaskManagerBaseHelper;

public class AccountLab {

    private static  AccountLab ourInstance ;
    private SQLiteDatabase mSQLiteDatabase;
    private Context mContext;

    public static AccountLab getInstance(Context context) {

        if(ourInstance == null){
            ourInstance = new AccountLab(context);
        }
        return ourInstance;
    }

    private AccountLab(Context context) {
        mContext = context.getApplicationContext();
        mSQLiteDatabase = new TaskManagerBaseHelper(mContext).getWritableDatabase();
    }

    public void addAccount ( Account account){

        ContentValues contentValues = getContentValue(account);
        mSQLiteDatabase.insert(TaskDBShema.AccountTable.NAME , null , contentValues);
    }

    public boolean isAccountInDatabase (Account account){
        String whereClause = TaskDBShema.AccountTable.Cols.USERNAME + " = ? " + " AND " +TaskDBShema.AccountTable.Cols.PASSWORD + " = ? ";
        String[] whereArgs = new String[]{account.getUsername() , account.getPassword()};

        AccountCursorWrapper accountCursorWrapper = queryAccount(whereClause , whereArgs);

        try {
            if (accountCursorWrapper.getCount() == 0) {
                return false;
            }
            accountCursorWrapper.moveToFirst();
            return true;
        }finally {
            accountCursorWrapper.close();
        }

    }

    public boolean isUserNameInDatabase (String userName){
        String whereClause = TaskDBShema.AccountTable.Cols.USERNAME + " = ? ";
        String[] whereArgs = new String[]{userName};

        AccountCursorWrapper accountCursorWrapper = queryAccount(whereClause , whereArgs);

        try {
            if (accountCursorWrapper.getCount() == 0) {
                return false;
            }
            accountCursorWrapper.moveToFirst();
            return true;
        }finally {
            accountCursorWrapper.close();
        }

    }
    private AccountCursorWrapper queryAccount(String whereClause, String[] whereArgs) {
        Cursor cursor = mSQLiteDatabase.query(TaskDBShema.AccountTable.NAME , null
        , whereClause , whereArgs , null , null , null , null );

        return new AccountCursorWrapper(cursor);
    }

    private ContentValues getContentValue(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDBShema.AccountTable.Cols.USERNAME , account.getUsername());
        contentValues.put(TaskDBShema.AccountTable.Cols.PASSWORD , account.getPassword());

        return contentValues;
    }
}
