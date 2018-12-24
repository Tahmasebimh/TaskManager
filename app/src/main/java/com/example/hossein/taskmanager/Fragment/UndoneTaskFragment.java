package com.example.hossein.taskmanager.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hossein.taskmanager.AddEditActivity;
import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskDataBase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UndoneTaskFragment extends TaskList {


    @Override
    public ArrayList<Task> getTaskArrayList() {
        return TaskDataBase.getUnDoneTaskList();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_all_task_list;
    }
}

