package com.example.hossein.taskmanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossein.taskmanager.Fragments.DialogShowTaskDetailFragment;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchActivity extends AppCompatActivity {

    private EditText mEditTextSerach ;
    private RecyclerView mRecyclerViewSerachResult ;
    private List<Task> mTaskList;
    private searchTaskAdapter mSearchTaskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mEditTextSerach = findViewById(R.id.edt_search_model);
        mRecyclerViewSerachResult = findViewById(R.id.recy_search_result);

        mRecyclerViewSerachResult.setLayoutManager(new LinearLayoutManager(this));
        mEditTextSerach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTaskList = TaskLab.getInstance(SearchActivity.this).searchInDataBase(s.toString());
                Log.i("><><><><" , mTaskList.size() + "");
                if(mSearchTaskAdapter == null){
                    mSearchTaskAdapter = new searchTaskAdapter(mTaskList);
                    mRecyclerViewSerachResult.setAdapter(mSearchTaskAdapter);
                }else{
                    mSearchTaskAdapter.setTaskList(mTaskList);
                    mSearchTaskAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private class SearchTaskViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView mCircleImageView;
        private TextView mTextViewTitle ;
        private TextView mTextViewOnImage ;
        private TextView mTextViewDate;
        private Task mTask;
        private View mView;

        public SearchTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mCircleImageView = itemView.findViewById(R.id.circleImageView);
            mTextViewTitle = itemView.findViewById(R.id.tv_title);
            mTextViewOnImage = itemView.findViewById(R.id.img_text_view);
            mTextViewDate = itemView.findViewById(R.id.tv_date);
            mView = itemView.findViewById(R.id.model_root);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = AddEditActivity.newIntent(SearchActivity.this , true , mTask.getUUID());
                    startActivity(intent);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DialogShowTaskDetailFragment dialogShowTaskDetailFragment = DialogShowTaskDetailFragment.newInstance(mTask.getUUID());
                    dialogShowTaskDetailFragment.show(getSupportFragmentManager() , "test");
                    return false;
                }
            });
        }

        public void setTask(Task task) {
            mTask = task;
        }

        public void bind (Task task){
            mTextViewTitle.setText(task.getTitle());
            mTextViewDate.setText(task.getDate().toString());
            mTextViewOnImage.setText(task.getTitle().substring(0,1));
        }
    }

    private class searchTaskAdapter extends RecyclerView.Adapter<SearchTaskViewHolder>{

        private List<Task> mTaskList;

        public searchTaskAdapter(List<Task> taskList) {
            mTaskList = taskList;
        }

        public void setTaskList(List<Task> taskList) {
            mTaskList = taskList;
        }

        @NonNull
        @Override
        public SearchTaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.model_task_recycler_view , viewGroup, false);
            SearchTaskViewHolder searchTaskViewHolder = new SearchTaskViewHolder(view);
            return searchTaskViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SearchTaskViewHolder searchTaskViewHolder, int i) {
            Task task = mTaskList.get(i);
            searchTaskViewHolder.setTask(task);
            searchTaskViewHolder.bind(task);
        }

        @Override
        public int getItemCount() {
            return mTaskList.size();
        }
    }
}
