package com.example.hossein.taskmanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.hossein.taskmanager.App;
import com.example.hossein.taskmanager.database.TaskManagerBaseHelper;

import java.io.File;
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
        mTaskDao.insert(task);
        getTasks();
    }

    public void remove(Task task) {
        mTaskDao.delete(task);
        getTasks();
    }

    public void removeAllTask (){
        mTaskDao.queryBuilder().where(TaskDao.Properties.AccID.eq(LoginedUser.getInstance().getId())).buildDelete().executeDeleteWithoutDetachingEntities();
        mDaoSession.clear();
        getTasks();
    }

    public ArrayList<Task> getTasks() {
        mTasks = new ArrayList<>();
        ArrayList<Task> taskArrayList = (ArrayList<Task>) mTaskDao.loadAll();

        for(Task task : taskArrayList){
            if(task.getAccID().equals(LoginedUser.getInstance().getId())){
                mTasks.add(task);
            }
        }
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
        Task task1  = mTaskDao.queryBuilder().where(TaskDao.Properties.MUUID.eq(uuid.toString())).unique();
        task1.setAccID(task.getAccID());
        task1.setMDate(task.getMDate());
        task1.setTitle(task.getTitle());
        task1.setDescryption(task.getDescryption());
        task1.setDone(task.getDone());
        mTaskDao.update(task1);
        getTasks();
    }

    public File getPhotoFile(Task task , int imageCounter){
        File fileDir = mContext.getFilesDir();
        return new File(fileDir , task.getTaskPhotoName(imageCounter));
    }

}
