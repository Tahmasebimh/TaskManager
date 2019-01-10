package com.example.hossein.taskmanager;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hossein.taskmanager.Fragments.SignInFragment;

public class VerifyUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_user);

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.frm_layout_verify_user, new SignInFragment())
                .addToBackStack(null)
                .commit();
    }
}
