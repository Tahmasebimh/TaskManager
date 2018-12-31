package com.example.hossein.taskmanager.Fragment;


import android.os.Bundle;
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

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    private EditText mEditTextTitle;
    private EditText mEditTextDesc;
    private Button mButtonSave;
    private CheckBox mCheckBoxIsDone;
    private Bundle bundle;
    private Button mButtonEdit , mButtonDelete;
    private  TaskLab mTaskLab;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mTaskLab = TaskLab.getInstance();
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        identifyItem(view);
        bundle = getArguments();
        if (bundle != null) {
            if (bundle.getBoolean("edited")) {
                mButtonDelete.setVisibility(View.VISIBLE);
                mButtonEdit.setVisibility(View.VISIBLE);
                mButtonSave.setVisibility(View.GONE);

                Task task = mTaskLab.findWithUUID((UUID) bundle.getSerializable("uuid"));
                mEditTextTitle.setText(task.getTitle());
                mCheckBoxIsDone.setChecked(task.isDone());
                mEditTextDesc.setText(task.getDescryption());
            }
        }

        if(bundle == null || !bundle.getBoolean("edited")){
            mButtonDelete.setVisibility(View.GONE);
            mButtonEdit.setVisibility(View.GONE);
            mButtonSave.setVisibility(View.VISIBLE);
        }
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bundle.getBoolean("edited") || bundle == null) {
                    if (mEditTextTitle.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "You must fill title field", Toast.LENGTH_SHORT).show();
                    } else {
                        Task task = new Task();
                        task.setDone(mCheckBoxIsDone.isChecked());
                        task.setDescryption(mEditTextDesc.getText().toString());
                        task.setEdited(true);
                        task.setTitle(mEditTextTitle.getText().toString());
                        mTaskLab.add(task);
                        getActivity().finish();
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
                    mTaskLab.replaceTask(task, (UUID) bundle.getSerializable("uuid"));
                    getActivity().finish();
                }
            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          //      Task task = TaskDataBase.findWithUUID((UUID) bundle.getSerializable("uuid"));
          //      TaskDataBase.remove(task);
          //      getActivity().finish();
                AlertDialogFragment alertDialogFragment = AlertDialogFragment.newInstance(mTaskLab.findWithUUID((UUID) bundle.getSerializable("uuid")));
                alertDialogFragment.show(getFragmentManager() , "dialog");
            }
        });
        return view;
    }

    private void identifyItem(View view) {
        mEditTextTitle = view.findViewById(R.id.tv_add_title);
        mEditTextDesc = view.findViewById(R.id.tv_add_desc);
        mCheckBoxIsDone = view.findViewById(R.id.chkbox_add_isdone);
        mButtonSave = view.findViewById(R.id.btn_add_save);
        mButtonEdit = view.findViewById(R.id.btn_add_edit);
        mButtonDelete = view.findViewById(R.id.btn_add_delete);
    }


}
