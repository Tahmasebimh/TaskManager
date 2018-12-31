package com.example.hossein.taskmanager.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hossein.taskmanager.AddEditActivity;
import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Task;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public abstract class TaskList extends Fragment {

    private RecyclerView mRecyclerView ;
    private LinearLayout mLinearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayout() , container , false);
        mRecyclerView = view.findViewById(R.id.all_task_recycler_view);
        mLinearLayout = view.findViewById(R.id.no_task_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI(){
        ArrayList<Task> taskArrayList = getTaskArrayList();
        TaskAdapter taskAdapter = new TaskAdapter(taskArrayList);
        mRecyclerView.setAdapter(taskAdapter);
        if(getTaskArrayList().size() == 0){
            mLinearLayout.setVisibility(View.VISIBLE);
        }else {
            mLinearLayout.setVisibility(View.GONE);
        }
    }

    public abstract ArrayList<Task> getTaskArrayList();

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView mCircleImageView;
        private TextView mTextViewTitle ;
        private TextView mTextViewOnImage ;
        private TextView mTextViewDate;
        private Task mTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mCircleImageView = itemView.findViewById(R.id.circleImageView);
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

    public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>{

        private ArrayList<Task> mTasks;

        public TaskAdapter(ArrayList<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.model_task_recycler_view , viewGroup , false);
            TaskViewHolder taskViewHolder = new TaskViewHolder(view);
            return taskViewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {

            taskViewHolder.mTextViewTitle.setText(mTasks.get(i).getTitle());
            taskViewHolder.mTextViewDate.setText(mTasks.get(i).getDate());
            taskViewHolder.mTextViewOnImage.setText(mTasks.get(i).getTitle().substring(0,1));
            taskViewHolder.setTask(mTasks.get(i));
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

    public abstract int getLayout();
}
