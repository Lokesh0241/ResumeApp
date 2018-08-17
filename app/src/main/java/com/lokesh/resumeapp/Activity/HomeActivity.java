package com.lokesh.resumeapp.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lokesh.resumeapp.Adapter.ResumeAdapter;
import com.lokesh.resumeapp.Fragment.HomeFragment;
import com.lokesh.resumeapp.Model.CreateBioModel;
import com.lokesh.resumeapp.Model.ResumeModel;
import com.lokesh.resumeapp.Model.StateModel;
import com.lokesh.resumeapp.R;
import com.lokesh.resumeapp.Support.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = CreateBioDataActivity.class.getSimpleName();
    List<CreateBioModel> createBioModels = new ArrayList<>();

    EditText etSearch;
    ImageView ivSearch;
    ImageView ivBack;
    ImageView ivCreateBioData;

    RecyclerView rvResumeList;
    ResumeAdapter resumeAdapter;

    private DatabaseReference mFirebaseDatabase;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        init();
    }

    private void init() {

        FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        addUserChangeListener();

        ivBack = findViewById(R.id.ivBack);
        ivSearch = findViewById(R.id.ivSearch);
        etSearch = findViewById(R.id.etSearch);
        ivCreateBioData = findViewById(R.id.ivCreateBioData);
        ivCreateBioData.setOnClickListener(this);

        ivBack.setOnClickListener(this);

        rvResumeList = findViewById(R.id.rvResumeList);

        rvResumeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    resumeAdapter.getFilter().filter(charSequence.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    createBioModels.clear();
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        CreateBioModel value = dataSnapshot1.getValue(CreateBioModel.class);
                        CreateBioModel fire = new CreateBioModel();
                        dataSnapshot.getKey();
                        if (value != null) {
                            fire.setUserImg(value.getUserImg());
                            fire.setEtName(value.getEtName());
                            fire.setEtMobileStr(value.getEtMobileStr());
                            fire.setEtFatherName(value.getEtFatherName());
                            fire.setEtFatherOccupation(value.getEtFatherOccupation());
                            fire.setEtMotherName(value.getEtMotherName());
                            fire.setEtMotherOcp(value.getEtMotherOcp());
                            fire.setEtDob(value.getEtDob());
                            fire.setEtTob(value.getEtTob());
                            fire.setEtBrother(value.getEtBrother());
                            fire.setEtSister(value.getEtSister());
                            fire.setEtWeight(value.getEtWeight());
                            fire.setEtHeight(value.getEtHeight());
                            fire.setEtGotra(value.getEtGotra());
                            fire.setEtMamaGotra(value.getEtMamaGotra());
                            fire.setEtComplexion(value.getEtComplexion());
                            fire.setEtManglic(value.getEtManglic());
                            fire.setEtSex(value.getEtSex());
                            fire.setEtMarried(value.getEtMarried());
                            fire.setEtEducation(value.getEtEducation());
                            fire.setEtOccuption(value.getEtOccuption());
                            fire.setEtAddress(value.getEtAddress());
                            fire.setEtState(value.getEtState());
                            fire.setEtCity(value.getEtCity());
                            fire.setEtChilds(value.getEtChilds());
                            fire.setEtDescription(value.getEtDescription());
                            createBioModels.add(fire);
                        }
                        resumeAdapter = new ResumeAdapter(HomeActivity.this, createBioModels, rvResumeList);
                        rvResumeList.setAdapter(resumeAdapter);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ivBack:
                intent = new Intent(HomeActivity.this, PinLoginActivity.class);
                startActivity(intent);
                break;
            case R.id.ivCreateBioData:
                intent = new Intent(HomeActivity.this, CreateBioDataActivity.class);
                startActivity(intent);
                break;

        }
    }


    public void showAlertDialogExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // your code
            showAlertDialogExit();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}