package com.example.hossein.taskmanager.Fragments;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hossein.taskmanager.AddEditActivity;
import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;
import com.example.hossein.taskmanager.utils.PictureUtils;

import java.io.File;
import java.text.BreakIterator;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogShowTaskDetailFragment extends DialogFragment {

    private EditText mEditTextTitle;
    private EditText mEditTextDesc;
    private CheckBox mCheckBoxIsDone;
    private Button mButtonSave;
    private Button mButtonEdit;
    private ImageView mImageView;
    private Button mButtonDelete;
    private Bundle bundle;
    private Task mTask;
    private TaskLab mTaskLab;
    private Button mButtonDatePicker;
    private File mFilePhoto;

    public static DialogShowTaskDetailFragment newInstance(@NonNull UUID uuid) {

        Bundle args = new Bundle();
        args.putSerializable("uuid" , uuid);
        
        DialogShowTaskDetailFragment fragment = new DialogShowTaskDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public DialogShowTaskDetailFragment() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task , container , false);
        identifyItem(view);
        mButtonSave.setVisibility(View.GONE);

        setView();
        return view;
    }

    private void setView() {
        bundle = getArguments();
        mTaskLab = TaskLab.getInstance(getActivity());

        if (bundle != null) {
                mButtonDelete.setVisibility(View.GONE);
                mButtonEdit.setVisibility(View.GONE);
                mButtonSave.setVisibility(View.GONE);

                mEditTextTitle.setFocusable(false);
                mEditTextDesc.setFocusable(false);
                mButtonDatePicker.setEnabled(false);
                mCheckBoxIsDone.setEnabled(false);

                mTask = mTaskLab.findWithUUID((UUID) bundle.getSerializable("uuid"));
                mEditTextTitle.setText(mTask.getTitle());
                mCheckBoxIsDone.setChecked(mTask.isDone());
                mEditTextDesc.setText(mTask.getDescryption());
                mButtonDatePicker.setText(mTask.getDate().toString());
                mFilePhoto = TaskLab.getInstance(getActivity()).getPhotoFile(mTask, 1);
                if(mFilePhoto == null || !mFilePhoto.exists()){
                //mImageViewTaskImage.setImageDrawable(null);

                Log.i(">>>>><<<<" , "not image");
                 }else{
                Log.i(">>>>><<<<" , "set image");
                Bitmap bitmap = PictureUtils.getScalledBitmap(mFilePhoto.getPath() , getActivity());
                mImageView.setImageBitmap(bitmap);
                }
        }
    }

    private void identifyItem(View view) {
        mEditTextTitle = view.findViewById(R.id.tv_add_title);
        mEditTextDesc = view.findViewById(R.id.tv_add_desc);
        mCheckBoxIsDone = view.findViewById(R.id.chkbox_add_isdone);
        mButtonSave = view.findViewById(R.id.btn_add_save);
        mButtonEdit = view.findViewById(R.id.btn_add_edit);
        mButtonDelete = view.findViewById(R.id.btn_add_delete);
        mButtonDatePicker = view.findViewById(R.id.btn_date_picker);
        mImageView = view.findViewById(R.id.iv_task_image);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_task , null);
        identifyItem(view);
        setView();

        AlertDialog alertDialog =  new AlertDialog.Builder(getActivity())
                .setTitle("Edit this task : \n ")
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = AddEditActivity.newIntent(getActivity() , true , mTask.getUUID());
                        startActivity(intent);
                    }
                })
                .create();

        return alertDialog;
    }
}
