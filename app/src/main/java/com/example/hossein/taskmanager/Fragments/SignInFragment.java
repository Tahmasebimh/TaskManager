package com.example.hossein.taskmanager.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hossein.taskmanager.MainActivity;
import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Account;
import com.example.hossein.taskmanager.model.AccountLab;
import com.example.hossein.taskmanager.model.LoginedUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    private TextView mTextViewGotoSignUp ;
    private EditText mEditTextUserName ;
    private EditText mEditTextPassword ;
    private Button mButtonSingIn ;

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        identifyWidget(view);

        mTextViewGotoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frm_layout_verify_user , new SignUpFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        mButtonSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mEditTextUserName.getText().toString();
                String password = mEditTextPassword.getText().toString();
                Account account = new Account(userName , password);
                AccountLab accountLab = AccountLab.getInstance(getActivity());
                if(accountLab.isAccountInDatabase(account)){
                    Intent intent = new Intent(getActivity() , MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Wrong UserName Or Password!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void identifyWidget(View view) {
        mTextViewGotoSignUp = view.findViewById(R.id.tv_goto_singup);
        mEditTextUserName = view.findViewById(R.id.edt_username_signin);
        mEditTextPassword = view.findViewById(R.id.edt_password_signin);
        mButtonSingIn = view.findViewById(R.id.btn_login);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
