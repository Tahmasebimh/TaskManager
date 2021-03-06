package com.example.hossein.taskmanager.Fragments;


import android.support.v4.app.Fragment;

import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoneTaskFragment extends SingleFragmentActivity {


    @Override
    public ArrayList<Task> getTaskArrayList() {
        TaskLab taskLab = TaskLab.getInstance(getActivity());
        return taskLab.getDoneTaskList();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_all_task_list;
    }
}
