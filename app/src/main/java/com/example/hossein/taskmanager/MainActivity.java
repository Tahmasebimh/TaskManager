package com.example.hossein.taskmanager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hossein.taskmanager.Fragments.AllTaskListFragment;
import com.example.hossein.taskmanager.Fragments.DoneTaskFragment;
import com.example.hossein.taskmanager.Fragments.UndoneTaskFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayoutTaskMode ;
    private ViewPager mViewPagerTask;
    private FloatingActionButton mFloatingActionButtonAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayoutTaskMode = findViewById(R.id.tab_layout_mode_tsak_show);
        mViewPagerTask = findViewById(R.id.main_view_pager);
        mFloatingActionButtonAddTask = findViewById(R.id.fab_add_task);

        mViewPagerTask.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0 : {
                        return new AllTaskListFragment();
                    }case 1 :{
                        return new DoneTaskFragment();
                    }case 2 : {
                        return new UndoneTaskFragment();
                    }default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0 :{
                        return "ALL";
                    }case 1 :{
                        return "DONE";
                    }case 2 :{
                        return "UNDONE";
                    }default:
                        return null;
                }
            }
        });
        mTabLayoutTaskMode.setupWithViewPager(mViewPagerTask);

        mFloatingActionButtonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddEditActivity.newIntent(MainActivity.this , false , null);
                startActivity(intent);
            }
        });
    }
}
