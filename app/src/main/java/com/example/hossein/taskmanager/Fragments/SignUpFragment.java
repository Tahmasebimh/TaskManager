package com.example.hossein.taskmanager.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.Account;
import com.example.hossein.taskmanager.model.AccountLab;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {


    public SignUpFragment() {
        // Required empty public constructor
    }

    EditText mEditTextUserName ;
    EditText mEditTextPassword ;
    Button mButtonSingUp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up , container, false);

        identifyWidget(view);

        mButtonSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mEditTextUserName.getText().toString().equals("") && !mEditTextPassword.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "inja", Toast.LENGTH_SHORT).show();
                    if(mEditTextPassword.getText().toString().length() >= 5){
                        String userName = mEditTextUserName.getText().toString();
                        String password = mEditTextPassword.getText().toString();
                        AccountLab accountLab = AccountLab.getInstance(getActivity());
                        if(accountLab.isUserNameInDatabase(userName)){
                            Toast.makeText(getActivity(), "this user name is used by other", Toast.LENGTH_SHORT).show();
                        }else{
                            Account account = new Account(userName , password);
                            accountLab.addAccount(account);
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.frm_layout_verify_user , new SignInFragment())
                                    .commit();
                        }

                    }else{
                        Toast.makeText(getActivity(), "Password is too unSecure", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    private void identifyWidget(View view) {
        mEditTextUserName = view.findViewById(R.id.edt_username_signin);
        mEditTextPassword = view.findViewById(R.id.edt_password_signin);
        mButtonSingUp = view.findViewById(R.id.btn_signup);
    }

}
