package com.lokesh.resumeapp.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lokesh.resumeapp.Model.CreateBioModel;
import com.lokesh.resumeapp.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersonDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    public List<CreateBioModel> createBioModels = new ArrayList<>();

    ImageView ivBack;
    ImageView ivUser;

    TextView tvName;
    TextView tvAddress;
    TextView tvMobile;
    TextView tvEmail;
    TextView tvDob;
    TextView tvTob;
    TextView tvPob;
    TextView tvHeight;
    TextView tvWeight;
    TextView tvFather;
    TextView tvFatherOcp;
    TextView tvMother;
    TextView tvMotherOcp;
    TextView tvBother;
    TextView tvSister;
    TextView tvChilds;
    TextView tvGotra;
    TextView tvMamaGotra;
    TextView tvMarried;
    TextView tvEducation;
    TextView tvSex;
    TextView tvOccuption;
    TextView tvParentAddress;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        init();
    }

    private void init() {

        ivBack = findViewById(R.id.ivBack);
        ivUser = findViewById(R.id.ivUser);
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvMobile = findViewById(R.id.tvMobile);
        tvEmail = findViewById(R.id.tvEmail);
        tvDob = findViewById(R.id.tvDob);
        tvTob = findViewById(R.id.tvTob);
        tvPob = findViewById(R.id.tvPob);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvFather = findViewById(R.id.tvFather);
        tvFatherOcp = findViewById(R.id.tvFatherOcp);
        tvMother = findViewById(R.id.tvMother);
        tvMotherOcp = findViewById(R.id.tvMotherOcp);
        tvBother = findViewById(R.id.tvBother);
        tvSister = findViewById(R.id.tvSister);
        tvGotra = findViewById(R.id.tvGotra);
        tvMamaGotra = findViewById(R.id.tvMamaGotra);
        tvMarried = findViewById(R.id.tvMarried);
        tvEducation = findViewById(R.id.tvEducation);
        tvSex = findViewById(R.id.tvSex);
        tvOccuption = findViewById(R.id.tvOccuption);
        tvParentAddress = findViewById(R.id.tvParentAddress);
        tvChilds = findViewById(R.id.tvChilds);
        tvDescription = findViewById(R.id.tvDescription);

        tvFather.setOnClickListener(this);
        tvMother.setOnClickListener(this);
        ivBack.setOnClickListener(this);

        try {
            createBioModels = (List<CreateBioModel>) getIntent().getSerializableExtra("listUser");
            for (int i = 0; i < createBioModels.size(); i++) {
                String User = createBioModels.get(i).getUserImg();
                String Name = createBioModels.get(i).getEtName();
                String Address = createBioModels.get(i).getEtAddress();
                String Mobile = createBioModels.get(i).getEtMobileStr();
                String Dob = createBioModels.get(i).getEtDob();
                String Tob = createBioModels.get(i).getEtTob();
                String Pob = createBioModels.get(i).getEtAddress();
                String Height = createBioModels.get(i).getEtHeight();
                String Weight = createBioModels.get(i).getEtWeight();
                String Father = createBioModels.get(i).getEtFatherName();
                String FatherOcp = createBioModels.get(i).getEtFatherOccupation();
                String Mother = createBioModels.get(i).getEtMotherName();
                String MotherOcp = createBioModels.get(i).getEtMotherOcp();
                String Bother = createBioModels.get(i).getEtBrother();
                String Sister = createBioModels.get(i).getEtSister();
                String Gotra = createBioModels.get(i).getEtGotra();
                String MamaGotra = createBioModels.get(i).getEtMamaGotra();
                String Married = createBioModels.get(i).getEtMarried();
                String Education = createBioModels.get(i).getEtEducation();
                String Sex = createBioModels.get(i).getEtSex();
                String Occuption = createBioModels.get(i).getEtOccuption();
                String ParentAddress = createBioModels.get(i).getEtAddress();
                String state = createBioModels.get(i).getEtState();
                String city = createBioModels.get(i).getEtCity();
                String childs = createBioModels.get(i).getEtChilds();
                String description = createBioModels.get(i).getEtDescription();

                ivUser.setImageBitmap(StringToBitMap(User));
                tvName.setText(Name);
                tvAddress.setText(Address + " " + city + " " + state);
                tvMobile.setText(Mobile);
                tvDob.setText(Dob);
                tvTob.setText(Tob);
                tvPob.setText(Pob);
                tvHeight.setText(Height);
                tvWeight.setText(Weight);
                tvFather.setText(Father);
                tvFatherOcp.setText(FatherOcp);
                tvMother.setText(Mother);
                tvMotherOcp.setText(MotherOcp);
                tvBother.setText(Bother);
                tvSister.setText(Sister);
                tvGotra.setText(Gotra);
                tvMamaGotra.setText(MamaGotra);
                tvMarried.setText(Married);
                tvEducation.setText(Education);
                tvSex.setText(Sex);
                tvOccuption.setText(Occuption);
                tvParentAddress.setText(ParentAddress);
                tvChilds.setText(childs);
                tvDescription.setText(description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case  R.id.tvFather:
                Toast.makeText(this, "FATHER", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvMother:
                Toast.makeText(this, "MOTHER", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void setContentView(){
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }

    }
}
