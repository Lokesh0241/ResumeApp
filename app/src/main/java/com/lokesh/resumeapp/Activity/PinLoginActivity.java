package com.lokesh.resumeapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lokesh.resumeapp.Database.DataBaseHandler;
import com.lokesh.resumeapp.Database.Database;
import com.lokesh.resumeapp.R;

import java.util.List;

public class PinLoginActivity extends AppCompatActivity implements View.OnClickListener{

    RelativeLayout rlNextbtn;
    TextView tvCreateNewPin;
    EditText etPin;
    DataBaseHandler dataBaseHandler=new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_login);
        init();
    }

    private void init() {
        rlNextbtn = (RelativeLayout)findViewById(R.id.rlNextbtn);
        tvCreateNewPin = (TextView)findViewById(R.id.tvCreateNewPin);
        etPin = (EditText)findViewById(R.id.etPin);

        rlNextbtn.setOnClickListener(this);
        tvCreateNewPin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.rlNextbtn:
                if (etPin.getText().toString().equals("") || etPin.getText().toString().trim().length()<4){
                    etPin.setError("please enter correct pin");
                }else{
                    List<Database> contacts = dataBaseHandler.getActiveCus();
                    if (contacts.size() > 0) {
                        for (Database cn : contacts) {
                            String pin = cn.getPin();
                            if (pin.equals(etPin.getText().toString())){
                                intent = new Intent(PinLoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                etPin.setError("pin is invalid");
                            }
                        }
                    }
                }

                break;
            case R.id.tvCreateNewPin:
                intent = new Intent(PinLoginActivity.this,CreateNewPicActivity.class);
                startActivity(intent);
                break;

        }


    }
}
