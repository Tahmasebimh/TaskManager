package com.example.hossein.taskmanager.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.hossein.taskmanager.model.Task;

import java.util.Date;
import java.util.UUID;

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask(){
        String title = getString(getColumnIndex(TaskDBShema.TaskTable.Cols.TITLE));
        String descryption = getString(getColumnIndex(TaskDBShema.TaskTable.Cols.DESCRYPTION));
        long date = getLong(getColumnIndex(TaskDBShema.TaskTable.Cols.DATE));
        String uuid = getString(getColumnIndex(TaskDBShema.TaskTable.Cols.UUID));
        int isDone = getInt(getColumnIndex(TaskDBShema.TaskTable.Cols.ISDONE));
        int accID = getInt(getColumnIndex(TaskDBShema.TaskTable.Cols.ACCOUNTID));

        Task task = new Task(UUID.fromString(uuid));
        task.setTitle(title);
        task.setDescryption(descryption);
        task.setDone(isDone == 1);
        task.setMDate(new Date(date));
        task.setAccID((long) accID);

        return task;
    }
}
