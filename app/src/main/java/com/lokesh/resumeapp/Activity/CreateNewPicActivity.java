package com.lokesh.resumeapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.lokesh.resumeapp.Database.DataBaseHandler;
import com.lokesh.resumeapp.Database.Database;
import com.lokesh.resumeapp.R;

public class CreateNewPicActivity extends AppCompatActivity implements View.OnClickListener {

    String newPinStr = "";

    String phone;

    EditText etNewPin;
    EditText etConfirmPin;

    RelativeLayout rlNextbtn;

    DataBaseHandler dataBaseHandler=new DataBaseHandler(this);
    Database database=new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_pic);
        init();
    }

    private void init() {
        phone=getIntent().getStringExtra("mobile");

        rlNextbtn = (RelativeLayout) findViewById(R.id.rlNextbtn);
        etNewPin = (EditText) findViewById(R.id.etNewPin);
        etConfirmPin = (EditText) findViewById(R.id.etConfirmPin);

        rlNextbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlNextbtn:
                isValidValues();
                break;
        }

    }


    private boolean isValidValues() {
        if (etNewPin.getText().toString().equals("") || etNewPin.getText().toString().trim().length() < 4) {
            etNewPin.setError("Please enter 6 digit pin");
            etNewPin.requestFocus();
            return false;
        } else if (!etConfirmPin.getText().toString().equals(etNewPin.getText().toString())) {
            etConfirmPin.setError("Pin Not matched");
            etConfirmPin.requestFocus();
            return false;
        } else {
            database.setMobile(phone);
            database.setPin(etNewPin.getText().toString());
            database.setCusActiveStatus("1");
            dataBaseHandler.addContact(database);
            Intent intent = new Intent(CreateNewPicActivity.this, PinLoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

    }
}
