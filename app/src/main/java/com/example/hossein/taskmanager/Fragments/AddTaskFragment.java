package com.example.hossein.taskmanager.Fragments;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hossein.taskmanager.R;
import com.example.hossein.taskmanager.model.LoginedUser;
import com.example.hossein.taskmanager.model.Task;
import com.example.hossein.taskmanager.model.TaskLab;
import com.example.hossein.taskmanager.utils.PictureUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTaskFragment extends Fragment {

    private static final int REQ_dATE_PiCKER = 0 ;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private int PICK_IMAGE = 2;

    private EditText mEditTextTitle;
    private EditText mEditTextDesc;
    private Button mButtonSave;
    private ImageView mImageView;
    private ImageView mImageViewTaskImage;
    private Uri mUri;

    private CheckBox mCheckBoxIsDone;
    private Bundle bundle;
    private Button mButtonEdit , mButtonDelete;
    private  TaskLab mTaskLab;
    private Button mButtonDatePicker ;
    private String DIALOG_TAG = "DIALOG_TAG";
    private Task mTask;
    private File mFilePhoto ;
    private final CharSequence[] items = { "Take Photo", "Choose from Library"};
    private String TAG_ERROR_URI = ">>>>.uri error <<<<";

    public AddTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().getBoolean("edited")) {
            mTask = TaskLab.getInstance(getActivity()).findWithUUID((UUID) getArguments().getSerializable("uuid"));
            if(mTask.getImageUri() != null){
                mUri = mTask.getImageUri();
                mFilePhoto = new File(mUri.getPath());
            }
        }else{
            mTask = new Task();
        }
        mFilePhoto = TaskLab.getInstance(getActivity()).getPhotoFile(mTask , 1);
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mTaskLab = TaskLab.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        identifyItem(view);
        bundle = getArguments();

        if (!bundle.getBoolean("edited") || bundle == null) {
            if (mEditTextTitle.getText().toString().equals("")) {
              //  mTask = new Task();
            }
        }
        if (bundle != null) {
            if (bundle.getBoolean("edited")) {
                if(mUri !=  null){
                    try {
                    Bitmap bitmap =  PictureUtils.getScalledBitmap(getRealPathFromURI(mUri) , 70
                            ,70);
                    mImageViewTaskImage.setImageBitmap(bitmap);
                    }catch (Exception e){
                    Log.e(TAG_ERROR_URI , e.getMessage());
                }}

                mButtonDelete.setVisibility(View.VISIBLE);
                mButtonEdit.setVisibility(View.VISIBLE);
                mImageView.setVisibility(View.VISIBLE);
                mButtonSave.setVisibility(View.GONE);


                mEditTextTitle.setText(mTask.getTitle());
                mCheckBoxIsDone.setChecked(mTask.isDone());
                mEditTextDesc.setText(mTask.getDescryption());
                mButtonDatePicker.setText(mTask.getDate().toString());
            }
        }

        if(bundle == null || !bundle.getBoolean("edited")){
            mButtonDelete.setVisibility(View.GONE);
            mButtonEdit.setVisibility(View.GONE);
            mImageView.setVisibility(View.GONE);
            mButtonSave.setVisibility(View.VISIBLE);
        }

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                if (!bundle.getBoolean("edited") || bundle == null) {
                    if (mEditTextTitle.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "You must fill title field", Toast.LENGTH_SHORT).show();
                    } else {
                        //mTask = new Task();
                        mTask.setDone(mCheckBoxIsDone.isChecked());
                        mTask.setDescryption(mEditTextDesc.getText().toString());
                        mTask.setEdited(true);
                        mTask.setTitle(mEditTextTitle.getText().toString());
                        mTask.setAccID(LoginedUser.getInstance().getId());
                        mTask.setImageUri(mUri);
                        mTaskLab.add(mTask);
                        Objects.requireNonNull(getActivity()).finish();
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
                    task.setDate(mTask.getDate());
                    task.setAccID(LoginedUser.getInstance().getId());
                    task.setImageUri(mUri);
                    mTaskLab.replaceTask(task, (UUID) bundle.getSerializable("uuid"));
                    getActivity().finish();
                }
            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialogFragment alertDialogFragment = AlertDialogFragment
                        .newInstance(((UUID) bundle.getSerializable("uuid")));

                alertDialogFragment.show(getFragmentManager() , "dialog");
            }
        });

        mButtonDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mTask.getDate());
                datePickerFragment.setTargetFragment(AddTaskFragment.this , REQ_dATE_PiCKER);
                datePickerFragment.show(getFragmentManager() , DIALOG_TAG);
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShareIntent();
            }
        });

        mImageViewTaskImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Choose your action : ");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0 :{
                                dispatchTakePictureIntent();
                                break;
                            }case 1:{
                                startGalleryIntent();
                                break;
                            }default:
                                dialog.dismiss();
                        }
                    }
                });
                dialog.show();
            }
        });
        updatePhotoView();
        return view;
    }

    private void startGalleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //intent.setType("image/*");
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    private void startShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT , mTask.getTitle());
        String body = "Desc : " + mTask.getDescryption()
                + "\n Date is : " + mTask.getDate().toString()
                + "\n Done Condition : " + mTask.getDone();

        shareIntent.putExtra(Intent.EXTRA_TEXT , body);
        startActivity(Intent.createChooser(shareIntent , " Share via : "));
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {

            Uri uri = getPhotoUri();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT , uri);
            List<ResolveInfo> activities = getActivity().
                    getPackageManager().queryIntentActivities(takePictureIntent ,
                    PackageManager.MATCH_DEFAULT_ONLY);

            for(ResolveInfo resolveInfo : activities){
                getActivity().grantUriPermission(resolveInfo.activityInfo.packageName ,
                        uri ,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private Uri getPhotoUri() {
        return FileProvider.getUriForFile(getActivity() ,
                        "com.example.hossein.taskmanager.fileprovider" ,
                        mFilePhoto);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQ_dATE_PiCKER){
            Date date1 = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTask.setDate(date1);
            mButtonDatePicker.setText(date1.toString());
        }else if(requestCode == REQUEST_IMAGE_CAPTURE){
            mUri = getPhotoUri();
            getActivity().revokeUriPermission(mUri , Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            updatePhotoView();
        }else if(requestCode == PICK_IMAGE){
            mUri = data.getData();
            Log.e(">>>>>uir<<<<" , mUri.toString());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), mUri);
                mImageViewTaskImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
           // updatePhotoView();
        }
    }

    private void identifyItem(View view) {
        mEditTextTitle = view.findViewById(R.id.tv_add_title);
        mEditTextDesc = view.findViewById(R.id.tv_add_desc);
        mCheckBoxIsDone = view.findViewById(R.id.chkbox_add_isdone);
        mButtonSave = view.findViewById(R.id.btn_add_save);
        mButtonEdit = view.findViewById(R.id.btn_add_edit);
        mButtonDelete = view.findViewById(R.id.btn_add_delete);
        mButtonDatePicker = view.findViewById(R.id.btn_date_picker);
        mImageView = view.findViewById(R.id.iv_share_icon);
        mImageViewTaskImage = view.findViewById(R.id.iv_task_image);
    }


    private void updatePhotoView(){
        if(mFilePhoto == null || !mFilePhoto.exists()){
            //mImageViewTaskImage.setImageDrawable(null);
            Log.i(">>>>><<<<" , "not image");
        }else{

            Log.i(">>>>><<<<" , "set image");
            Bitmap bitmap = PictureUtils.getScalledBitmap(mFilePhoto.getPath() , 70 , 70);
            mImageViewTaskImage.setImageBitmap(bitmap);
        }
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj,
                null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
