package com.example.hossein.taskmanager.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.hossein.taskmanager.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerFragment extends DialogFragment {


    private static final  String ARG_DARE = "argdate";
    private Date mDate;
    private DatePicker mDatePicker;
    public static final String EXTRA_DATE = "dateextra";

    public DatePickerFragment() {
        // Required empty public constructor
    }

    public static DatePickerFragment newInstance(Date date) {
        
        Bundle args = new Bundle();
        args.putSerializable(ARG_DARE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mDate = (Date) getArguments().getSerializable(ARG_DARE);
        }else {
            mDate = new Date();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_date_picker , null);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int mounth = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker = view.findViewById(R.id.date_picker);
        mDatePicker.init(year , mounth , day , null);

        AlertDialog alertDialog =  new AlertDialog.Builder(getActivity())
                .setTitle("Pick Your Date : ")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int year = mDatePicker.getYear();
                        int mounth = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();

                        Date date = new GregorianCalendar(year , mounth , day).getTime();

                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_DATE, date);
                        getTargetFragment().onActivityResult(getTargetRequestCode() ,
                                Activity.RESULT_OK ,
                                intent);
                    }
                })
                .setView(view)
                .create();

        return alertDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_picker, container, false);
    }

}
