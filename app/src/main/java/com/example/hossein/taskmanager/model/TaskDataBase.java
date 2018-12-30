package com.example.hossein.taskmanager.model;

import com.example.hossein.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.UUID;

public class TaskDataBase {
    public static ArrayList<Task> mTasks = new ArrayList<>();
    public static ArrayList<Task> doneTasks ;
    public static ArrayList<Task> unDoneTasks ;

    public static void add(Task task){
        mTasks.add(task);
    }

    public static void remove(Task task){
        mTasks.remove(task);
    }

    public Task findTask(UUID uuid){

        for(Task task : mTasks){
            if(task.getUUID().equals(uuid)){
                return task;
            }
        }
        return null;
    }

    public static ArrayList<Task> getTasks(){
        return mTasks;
    }

    public static ArrayList<Task> getDoneTaskList(){
        doneTasks = new ArrayList<>();
        for(Task task : mTasks){
            if(task.isDone()){
                doneTasks.add(task);
            }
        }
        return doneTasks;
    }

    public static ArrayList<Task> getUnDoneTaskList(){
        unDoneTasks = new ArrayList<>();
        for(Task task : mTasks){
            if(!task.isDone()){
                unDoneTasks.add(task);
            }
        }
        return unDoneTasks;
    }

    public static Task findWithUUID(UUID uuid){

        for (Task task : mTasks){
            if(task.getUUID().equals(uuid)){

                return task;
            }
        }
        return null;
    }

    public static void replaceTask (Task task , UUID uuid){
        Task task1 = findWithUUID(uuid);
        mTasks.get(mTasks.indexOf(task1)).setTitle(task.getTitle());
        mTasks.get(mTasks.indexOf(task1)).setEdited(task.isEdited());
        mTasks.get(mTasks.indexOf(task1)).setDone(task.isDone());
        mTasks.get(mTasks.indexOf(task1)).setDescryption(task.getDescryption());
    }

}
