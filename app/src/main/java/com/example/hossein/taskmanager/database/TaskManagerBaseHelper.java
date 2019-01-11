package com.example.hossein.taskmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class TaskManagerBaseHelper extends SQLiteOpenHelper {

    public TaskManagerBaseHelper(@Nullable Context context) {
        super(context, TaskDBShema.NAME, null, TaskDBShema.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL( " create table " + TaskDBShema.AccountTable.NAME + " ( " +
                TaskDBShema.AccountTable.Cols.ID + " INTEGER primary key AUTOINCREMENT , " +
                TaskDBShema.AccountTable.Cols.USERNAME + " , " +
                TaskDBShema.AccountTable.Cols.PASSWORD + " ) "
        );

        sqLiteDatabase.execSQL("create table " + TaskDBShema.TaskTable.NAME + " ( " +
                TaskDBShema.TaskTable.Cols.ID + " INTEGER primary key autoincrement , " +
                TaskDBShema.TaskTable.Cols.TITLE + " , " +
                TaskDBShema.TaskTable.Cols.DESCRYPTION + " , " +
                TaskDBShema.TaskTable.Cols.DATE + " ,  " +
                TaskDBShema.TaskTable.Cols.ISDONE + " , " +
                TaskDBShema.TaskTable.Cols.UUID + " , " +
                TaskDBShema.TaskTable.Cols.ACCOUNTID + " REFERENCES " +
                TaskDBShema.TaskTable.NAME + " ( " + TaskDBShema.TaskTable.Cols.ID + " ) " +
                " ON delete cascade " + " ) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
