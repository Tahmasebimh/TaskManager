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
public class UndoneTaskFragment extends Fragment {


    public UndoneTaskFragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerViewUndoneTask ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_undone_task, container, false);

        mRecyclerViewUndoneTask = view.findViewById(R.id.undone_task_recycler_view);
        mRecyclerViewUndoneTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI(){
        UndoneTaskAdapter undoneTaskAdapter = new UndoneTaskAdapter(TaskDataBase.getUnDoneTaskList());
        mRecyclerViewUndoneTask.setAdapter(undoneTaskAdapter);
    }

    public class UndoneTaskListHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle ;
        private TextView mTextViewOnImage ;
        private TextView mTextViewDate;
        private Task mTask;

        public UndoneTaskListHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewTitle = itemView.findViewById(R.id.tv_title);
            mTextViewOnImage = itemView.findViewById(R.id.img_text_view);
            mTextViewDate = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = AddEditActivity.newIntent(getActivity() , true , mTask.getUUID());
                    startActivity(intent);
                }
            });

        }
        public void setTask (Task task){
            mTask = task;
        }
    }

    public class UndoneTaskAdapter extends RecyclerView.Adapter<UndoneTaskListHolder>{

        private ArrayList<Task> unDoneTask ;

        public UndoneTaskAdapter(ArrayList<Task> tasks) {
            unDoneTask = tasks;
        }

        @NonNull
        @Override
        public UndoneTaskListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.model_task_recycler_view , viewGroup , false);
            UndoneTaskListHolder undoneTaskListHolder = new UndoneTaskListHolder(view);

            return undoneTaskListHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UndoneTaskListHolder undoneTaskListHolder, int i) {

            undoneTaskListHolder.mTextViewTitle.setText(unDoneTask.get(i).getTitle());
            undoneTaskListHolder.mTextViewDate.setText(unDoneTask.get(i).getDate());
            undoneTaskListHolder.mTextViewOnImage.setText(unDoneTask.get(i).getTitle().substring(0 , 1));
            undoneTaskListHolder.setTask(unDoneTask.get(i));
        }

        @Override
        public int getItemCount() {
            return unDoneTask.size();
        }
    }

}

