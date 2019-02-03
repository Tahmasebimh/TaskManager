package com.example.hossein.taskmanager;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hossein.taskmanager.Fragments.AddTaskFragment;
import com.example.hossein.taskmanager.model.DaoMaster;
import com.example.hossein.taskmanager.model.DaoSession;

import org.greenrobot.greendao.database.Database;

import java.util.UUID;

public class AddEditActivity extends AppCompatActivity {
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

    public static class App extends Application {

        public static App app;
        private DaoSession daoSession;


        @Override
        public void onCreate() {
            super.onCreate();

            MyDevHelper myDevOpenHelper = new MyDevHelper(this, "DatabaseName");
            Database db = myDevOpenHelper.getWritableDb();

            daoSession = new DaoMaster(db).newSession();
            app = this ;
        }

        public static App getApp() {
            return app;
        }

        public DaoSession getDaoSession() {
            return daoSession;
        }
    }
}
