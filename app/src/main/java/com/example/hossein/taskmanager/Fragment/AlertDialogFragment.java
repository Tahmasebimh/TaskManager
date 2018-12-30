package com.example.hossein.taskmanager.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskDataBase;

public class AlertDialogFragment extends DialogFragment {

    public static AlertDialogFragment newInstance(Task task) {
        
        Bundle args = new Bundle();
        args.putSerializable("task" , task);

        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Task task = (Task) getArguments().getSerializable("task");
        String text = "This text is white. <font color=\"#E72A02\">This text is blue.</font>";

        return new AlertDialog.Builder(getActivity())
                .setTitle("Really want to delete " + task.getTitle()
                        + " Task" )
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(getActivity(), "ok clicked", Toast.LENGTH_SHORT).show();
                                TaskDataBase.remove(task);
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
