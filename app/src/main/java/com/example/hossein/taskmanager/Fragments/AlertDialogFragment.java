package com.example.hossein.taskmanager.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;

import java.util.UUID;

public class AlertDialogFragment extends DialogFragment {

    private TaskLab mTaskLab;

    public static AlertDialogFragment newInstance(UUID uuid) {
        
        Bundle args = new Bundle();
        args.putSerializable("task" , uuid);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mTaskLab = TaskLab.getInstance(getActivity());

         final Task task = mTaskLab.findWithUUID((UUID) getArguments().getSerializable("task"));


        return new AlertDialog.Builder(getActivity())
                .setTitle("Really want to delete " + task.getTitle()
                        + " Task" )
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getActivity(), task.getTitle() + " Removed", Toast.LENGTH_SHORT).show();
                                mTaskLab.remove(task);
                                getActivity().finish();
                            }
                        }
                )
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }
                        }
                )
                .create();
    }
}
