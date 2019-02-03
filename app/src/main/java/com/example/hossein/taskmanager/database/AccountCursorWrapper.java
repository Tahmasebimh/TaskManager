//package com.example.hossein.taskmanager.database;
//
//import android.database.Cursor;
//import android.database.CursorWrapper;
//
//import com.example.hossein.taskmanager.model.Account;
//
//public class AccountCursorWrapper extends CursorWrapper {
//
//    public AccountCursorWrapper(Cursor cursor) {
//        super(cursor);
//    }
//
//    public Account getAccount (){
//        String userName = getString(getColumnIndex(TaskDBShema.AccountTable.Cols.USERNAME));
//        String password = getString(getColumnIndex(TaskDBShema.AccountTable.Cols.PASSWORD));
//        Long ACCID = getInt(getColumnIndex(TaskDBShema.AccountTable.Cols.ID));
//
//        Account account = new Account(userName , password , ACCID);
//        return account;
//    }
//}
