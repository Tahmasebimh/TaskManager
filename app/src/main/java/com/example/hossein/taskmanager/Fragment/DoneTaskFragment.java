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
public class DoneTaskFragment extends Fragment {


    public DoneTaskFragment() {
        // Required empty public constructor
    }

    private RecyclerView mRecyclerViewDoneTask ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_done_task, container, false);

        mRecyclerViewDoneTask = view.findViewById(R.id.done_task_recycler_view);
        mRecyclerViewDoneTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI(){
        ArrayList<Task> taskArrayList = TaskDataBase.getDoneTaskList();
        DoneAdapter doneAdapter = new DoneAdapter(taskArrayList);
        mRecyclerViewDoneTask.setAdapter(doneAdapter);
    }

    public class DoneListHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle ;
        private TextView mTextViewOnImage ;
        private TextView mTextViewDate;
        private Task mTask;

        public DoneListHolder(@NonNull View itemView) {

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

        public void setTask(Task task){
            mTask = task;
        }
    }

    public class DoneAdapter extends RecyclerView.Adapter<DoneListHolder>{

        private ArrayList<Task> mTasks;

        public DoneAdapter(ArrayList<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public DoneListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.model_task_recycler_view , viewGroup , false);
            DoneListHolder doneListHolder = new DoneListHolder(view);

            return doneListHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull DoneListHolder doneListHolder, int i) {
               doneListHolder.mTextViewTitle.setText(mTasks.get(i).getTitle());
               doneListHolder.mTextViewDate.setText(mTasks.get(i).getDate());
               doneListHolder.mTextViewOnImage.setText(mTasks.get(i).getTitle().substring(0,1));
                doneListHolder.setTask(mTasks.get(i));
        }

        @Override
        public  int getItemCount() {
            return mTasks.size();
        }
    }

}
