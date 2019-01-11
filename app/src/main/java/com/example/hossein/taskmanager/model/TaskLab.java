package com.example.hossein.taskmanager.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.hossein.taskmanager.database.TaskManagerBaseHelper;

import java.util.ArrayList;
import java.util.UUID;

public class TaskLab {

    private SQLiteDatabase mSQLiteDatabase;
    private static TaskLab ourInstance ;
    private ArrayList<Task> mTasks;
    private Context mContext;

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
    }

    public void add(Task task) {
        mTasks.add(task);
    }

    public void remove(Task task) {
        mTasks.remove(task);
    }

    public ArrayList<Task> getTasks() {
        return mTasks;
    }

    public ArrayList<Task> getDoneTaskList() {
        ArrayList<Task> doneTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (task.isDone()) {
                doneTasks.add(task);
            }
        }
        return doneTasks;
    }

    public ArrayList<Task> getUnDoneTaskList() {
        ArrayList<Task> unDoneTasks = new ArrayList<>();
        for (Task task : mTasks) {
            if (!task.isDone()) {
                unDoneTasks.add(task);
            }
        }
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
        Task task1 = findWithUUID(uuid);
        mTasks.get(mTasks.indexOf(task1)).setTitle(task.getTitle());
        mTasks.get(mTasks.indexOf(task1)).setEdited(task.isEdited());
        mTasks.get(mTasks.indexOf(task1)).setDone(task.isDone());
        mTasks.get(mTasks.indexOf(task1)).setDescryption(task.getDescryption());
    }

}
