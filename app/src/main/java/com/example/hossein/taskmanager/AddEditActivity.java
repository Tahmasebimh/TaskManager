package com.example.hossein.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.hossein.taskmanager.Fragment.AddTaskFragment;

import java.util.UUID;

public class AddEditActivity extends AppCompatActivity {
    private FrameLayout mFrameLayoutAddEdit;
    private static final String TAG_ISEDITED = "isEdited";
    private static final String TAG_UUID = "id";

    public static Intent newIntent(Context context, boolean isEdited, UUID id) {
        Intent intent = new Intent(context, AddEditActivity.class);
        intent.putExtra(TAG_ISEDITED, isEdited);
        intent.putExtra(TAG_UUID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        mFrameLayoutAddEdit = findViewById(R.id.frm_add_edit);
        Intent intent = getIntent();
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (intent.hasExtra(TAG_ISEDITED)) {
            if (!intent.getBooleanExtra(TAG_ISEDITED, false)) {
                if (fragmentManager.findFragmentById(R.id.frm_add_edit) == null) {
                    AddTaskFragment addTaskFragment = AddTaskFragment.newInstance((UUID) intent.getSerializableExtra(TAG_UUID), false);
                    fragmentManager.beginTransaction()
                            .add(R.id.frm_add_edit, addTaskFragment)
                            .commit();
                }
            }else{
                if(fragmentManager.findFragmentById(R.id.frm_add_edit) == null){
                   AddTaskFragment addTaskFragment = AddTaskFragment.newInstance((UUID) intent.getSerializableExtra(TAG_UUID), true);
                    fragmentManager.beginTransaction()
                            .add(R.id.frm_add_edit , addTaskFragment)
                            .commit();
                }
            }
        }
    }
}
