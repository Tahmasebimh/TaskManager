package com.example.hossein.taskmanager.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hossein.taskmanager.AddEditActivity;
import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;
import com.example.hossein.taskmanager.utils.PictureUtils;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public abstract class SingleFragmentActivity extends Fragment {

    private RecyclerView mRecyclerView ;
    private LinearLayout mLinearLayout;
    private File mFilePhoto ;
    private String TAG_BITMAP_ERROR = ">>bitmap error";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // setHasOptionsMenu(true);
    }

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
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DialogShowTaskDetailFragment dialogShowTaskDetailFragment = DialogShowTaskDetailFragment.newInstance(mTask.getUUID());
                    dialogShowTaskDetailFragment.show(getFragmentManager() , "test");
                    return false;
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
            return new TaskViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {

            taskViewHolder.mTextViewTitle.setText(mTasks.get(i).getTitle());
            taskViewHolder.mTextViewDate.setText(mTasks.get(i).getDate().toString());
            taskViewHolder.setTask(mTasks.get(i));
            mFilePhoto = TaskLab.getInstance(getActivity()).getPhotoFile(mTasks.get(i) , 1);
//            if(mFilePhoto != null || mFilePhoto.exists()){
//
//                Bitmap bitmap = PictureUtils.getScalledBitmap(mFilePhoto.getPath() , 70
//                        ,70);
//                taskViewHolder.mCircleImageView.setImageBitmap(bitmap);
//                Log.i("<<>><<>>" , "in try 11");
            //}else
                if(mTasks.get(i).getImageUri() != null){
                Log.i("<<>><<>>" , "in try 22");
                try {
                    Log.i("<<>><<>>" , "in try");
                    Bitmap bitmap =  PictureUtils.getScalledBitmap(getRealPathFromURI(mTasks.get(i).getImageUri()) , 30
                            ,30);
                    taskViewHolder.mCircleImageView.setImageBitmap(bitmap);
                }catch (Exception e){
                    Log.e(TAG_BITMAP_ERROR , e.getMessage() + "  [[[[[]]]] "+ i);
                    Bitmap bitmap = PictureUtils.getScalledBitmap(mFilePhoto.getPath() , 30
                            ,30);
                    taskViewHolder.mCircleImageView.setImageBitmap(bitmap);
                }
            }else{
                taskViewHolder.mTextViewOnImage.setText(mTasks.get(i).getTitle().substring(0,1));
                Log.i(">>>>><<<<" , "not image" + i);
            }
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }

    public abstract int getLayout();

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj,
                null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
