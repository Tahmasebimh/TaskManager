package com.example.hossein.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.hossein.taskmanager.App;
import com.example.hossein.taskmanager.database.TaskDBShema;
import com.example.hossein.taskmanager.database.TaskManagerBaseHelper;

import java.util.ArrayList;
import java.util.UUID;

public class TaskLab {

    private SQLiteDatabase mSQLiteDatabase;
    private static TaskLab ourInstance ;
    private ArrayList<Task> mTasks;
    private Context mContext;
    private DaoSession mDaoSession;
    private TaskDao mTaskDao;
    private AccountDao mAccountDao;

    public static TaskLab getInstance(Context context) {
        if(ourInstance == null){
            ourInstance = new TaskLab(context);
        }
        return ourInstance;
    }

    private TaskLab(Context context) {

        mContext = context.getApplicationContext();
        mTasks = new ArrayList<>();
        mSQLiteDatabase = new TaskManagerBaseHelper(mContext).getWritableDatabase();
        mDaoSession = App.getApp().getDaoSession();
        mTaskDao = mDaoSession.getTaskDao();
        mAccountDao = mDaoSession.getAccountDao();
    }

    public void add(Task task) {
        mTasks.add(task);
//        ContentValues contentValues = getContentValue(task);
//        mSQLiteDatabase.insert(TaskDBShema.TaskTable.NAME , null , contentValues);
        mTaskDao.insert(task);
    }

    private ContentValues getContentValue(Task task) {
        //setLoginUserId();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TaskDBShema.TaskTable.Cols.TITLE , task.getTitle());
        contentValues.put(TaskDBShema.TaskTable.Cols.DATE , task.getDate().getTime());
        contentValues.put(TaskDBShema.TaskTable.Cols.DESCRYPTION , task.getDescryption());
        contentValues.put(TaskDBShema.TaskTable.Cols.ISDONE , task.isDone() ? 1 : 0);
        contentValues.put(TaskDBShema.TaskTable.Cols.UUID , task.getUUID().toString());
        contentValues.put(TaskDBShema.TaskTable.Cols.ACCOUNTID , LoginedUser.getInstance().getId() + "");

        return contentValues;
    }

    private void setLoginUserId() {

    }

    public void remove(Task task) {
//        mSQLiteDatabase.delete(TaskDBShema.TaskTable.NAME , TaskDBShema.TaskTable.Cols.UUID + " = ? " ,
//        new String[]{task.getUUID().toString()});
        mTaskDao.delete(task);
        getTasks();
    }

    public void removeAllTask (){
        getTasks();
    }

    public ArrayList<Task> getTasks() {
        setLoginUserId();
        mTasks = new ArrayList<>();
//        //ArrayList<Task> taskArrayList = new ArrayList<>();
//        TaskCursorWrapper taskCursorWrapper = queryTask(TaskDBShema.TaskTable.Cols.ACCOUNTID + " = ? " ,
//                new String[]{LoginedUser.getInstance().getId() + ""});
//        try {
//            if(taskCursorWrapper.getCount() == 0){
//                return mTasks;
//            }
//            taskCursorWrapper.moveToFirst();
//
//            while (!taskCursorWrapper.isAfterLast()){
//                mTasks.add(taskCursorWrapper.getTask());
//                taskCursorWrapper.moveToNext();
//            }
//        }finally {
//            taskCursorWrapper.close();
//        }
        return mTasks;
    }

    public ArrayList<Task> getDoneTaskList() {

        ArrayList<Task> doneTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.isDone()) {
                doneTasks.add(task);
            }
        }
        Log.i("****doneTask" , doneTasks.size()  + "");
        return doneTasks;
    }

    public ArrayList<Task> getUnDoneTaskList() {
        ArrayList<Task> unDoneTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (!task.isDone()) {
                unDoneTasks.add(task);
            }
        }
        Log.i("****undoneTask" , unDoneTasks.size()  + "");
        return unDoneTasks;
    }

    public Task findWithUUID(UUID uuid) {

        for (Task task : mTasks) {
            if (task.getUUID().equals(uuid)) {

                return task;
            }
        }
        return null;
    }

    public void replaceTask(Task task, UUID uuid) {
        mSQLiteDatabase.update(TaskDBShema.TaskTable.NAME , getContentValue(task) , TaskDBShema.TaskTable.Cols.UUID + " = ? " ,
                new String[]{uuid.toString()});
        getTasks();
    }

//    private TaskCursorWrapper queryTask (String whereClause, String[] whereArgs){
//        Cursor cursor = mSQLiteDatabase.query(TaskDBShema.TaskTable.NAME , null , whereClause , whereArgs,
//                null , null , null, null );
//
//    return new TaskCursorWrapper(cursor);
//    }

}
