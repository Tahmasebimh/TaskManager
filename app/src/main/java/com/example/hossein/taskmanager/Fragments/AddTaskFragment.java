package com.example.hossein.taskmanager.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    private static final int REQ_dATE_PiCKER = 0 ;
    private EditText mEditTextTitle;
    private EditText mEditTextDesc;
    private Button mButtonSave;
    private CheckBox mCheckBoxIsDone;
    private Bundle bundle;
    private Button mButtonEdit , mButtonDelete;
    private  TaskLab mTaskLab;
    private Button mButtonDatePicker ;
    private String DIALOG_TAG = "DIALOG_TAG";
    private Task mTask;

    public AddTaskFragment() {
        // Required empty public constructor
    }

    public static AddTaskFragment newInstance(UUID uuid, boolean isEdited) {

        Bundle args = new Bundle();
        args.putBoolean("edited", isEdited);
        args.putSerializable("uuid", uuid);
        AddTaskFragment fragment = new AddTaskFragment();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mTaskLab = TaskLab.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        identifyItem(view);
        bundle = getArguments();

        if (!bundle.getBoolean("edited") || bundle == null) {
            if (mEditTextTitle.getText().toString().equals("")) {
                mTask = new Task();
            }
        }
        if (bundle != null) {
            if (bundle.getBoolean("edited")) {
                mButtonDelete.setVisibility(View.VISIBLE);
                mButtonEdit.setVisibility(View.VISIBLE);
                mButtonSave.setVisibility(View.GONE);

                mTask = mTaskLab.findWithUUID((UUID) bundle.getSerializable("uuid"));
                mEditTextTitle.setText(mTask.getTitle());
                mCheckBoxIsDone.setChecked(mTask.isDone());
                mEditTextDesc.setText(mTask.getDescryption());
                mButtonDatePicker.setText(mTask.getDate().toString());
            }
        }

        if(bundle == null || !bundle.getBoolean("edited")){
            mButtonDelete.setVisibility(View.GONE);
            mButtonEdit.setVisibility(View.GONE);
            mButtonSave.setVisibility(View.VISIBLE);
        }
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (!bundle.getBoolean("edited") || bundle == null) {
                    if (mEditTextTitle.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "You must fill title field", Toast.LENGTH_SHORT).show();
                    } else {
                        //mTask = new Task();

                        mTask.setDone(mCheckBoxIsDone.isChecked());
                        mTask.setDescryption(mEditTextDesc.getText().toString());
                        mTask.setEdited(true);
                        mTask.setTitle(mEditTextTitle.getText().toString());
                        mTaskLab.add(mTask);
                        Objects.requireNonNull(getActivity()).finish();
                    }
                }
            }
        });

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditTextTitle.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "You must fill title field", Toast.LENGTH_SHORT).show();
                } else {
                    Task task = new Task();
                    task.setDone(mCheckBoxIsDone.isChecked());
                    task.setDescryption(mEditTextDesc.getText().toString());
                    task.setEdited(true);
                    task.setTitle(mEditTextTitle.getText().toString());
                    task.setDate(mTask.getDate());
                    mTaskLab.replaceTask(task, (UUID) bundle.getSerializable("uuid"));
                    getActivity().finish();
                }
            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialogFragment alertDialogFragment = AlertDialogFragment
                        .newInstance(((UUID) bundle.getSerializable("uuid")));

                alertDialogFragment.show(getFragmentManager() , "dialog");
            }
        });

        mButtonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
                datePickerFragment.setTargetFragment(AddTaskFragment.this , REQ_dATE_PiCKER);
                datePickerFragment.show(getFragmentManager() , DIALOG_TAG);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQ_dATE_PiCKER){
            Date date1 = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTask.setDate(date1);
            mButtonDatePicker.setText(date1.toString());
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
    }


}
