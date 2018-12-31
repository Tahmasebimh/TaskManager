package com.example.hossein.taskmanager.Fragment;


import android.support.v4.app.Fragment;

import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTaskListFragment extends TaskList {


    @Override
    public ArrayList<Task> getTaskArrayList() {
        TaskLab taskLab = TaskLab.getInstance();
        return taskLab.getTasks();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_all_task_list;
    }
}
