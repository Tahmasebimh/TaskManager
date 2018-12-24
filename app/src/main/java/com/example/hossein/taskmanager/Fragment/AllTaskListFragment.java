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

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllTaskListFragment extends Fragment {

    private RecyclerView mRecyclerViewAllTask ;

    public AllTaskListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_task_list, container, false);

        mRecyclerViewAllTask = view.findViewById(R.id.all_task_recycler_view);
        mRecyclerViewAllTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view ;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public void updateUI(){

        ArrayList<Task> taskArrayList = TaskDataBase.getTasks();
        AllTaskAdapter allTaskAdapter = new AllTaskAdapter(taskArrayList);
        mRecyclerViewAllTask.setAdapter(allTaskAdapter);
    }

    public class AllTaskHOlder extends RecyclerView.ViewHolder {

        private CircleImageView mCircleImageView;
        private TextView mTextViewTitle ;
        private TextView mTextViewOnImage ;
        private TextView mTextViewDate;
        private Task mTask;


        public AllTaskHOlder(@NonNull View itemView) {
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
        public void setTask (Task task){
            mTask = task;
        }


    }

    public class AllTaskAdapter extends RecyclerView.Adapter<AllTaskHOlder>{

        private ArrayList<Task> mTasks;

        public AllTaskAdapter(ArrayList<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public AllTaskHOlder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.model_task_recycler_view , viewGroup , false);
            return new AllTaskHOlder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AllTaskHOlder allTaskHOlder, int i) {

                allTaskHOlder.mTextViewTitle.setText(mTasks.get(i).getTitle());
                allTaskHOlder.mTextViewDate.setText(mTasks.get(i).getDate());
                String s = String.valueOf(allTaskHOlder.mTextViewTitle.getText().toString().charAt(0));
                allTaskHOlder.mTextViewOnImage.setText(s);
                allTaskHOlder.setTask(mTasks.get(i));
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

}
