package com.lokesh.resumeapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.chaos.view.PinView;
import com.lokesh.resumeapp.R;

import java.security.SecureRandom;

public class OtpActivity extends AppCompatActivity implements View.OnClickListener {

    int range = 9;  // to generate a single number with this range, by default its 0..9
    int length = 4; // by default length is 4

    RelativeLayout rlNextbtn;
    PinView pinView;
    BroadcastReceiver receiveSMS;

    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        init();
    }

    private void init() {
        rlNextbtn = (RelativeLayout) findViewById(R.id.rlNextbtn);
        pinView = (PinView) findViewById(R.id.pinView);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("YOUR_PREF_NAME", MODE_PRIVATE);
        SharedPreferences.Editor edt = pref.edit();
        otp = pref.getString("OTP", "");
        pinView.setText(otp);

        //pinView.setText("" + receiveSMS);

        rlNextbtn.setOnClickListener(this);

        receiveSMS = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    String smsBody = intent.getStringExtra("sms");
                    String pin = smsBody.replace(getResources().getString(R.string.your_extra_text), "").trim();
                    pinView.setText(pin);
                    pinView.setText("" + receiveSMS);
                    // if (validatePin(pin));
                    // go through the app
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rlNextbtn:
                intent = new Intent(OtpActivity.this, PinLoginActivity.class);
                startActivity(intent);
                break;
        }


    }


    public int generateRandomNumber() {
        int randomNumber;
        SecureRandom secureRandom = new SecureRandom();
        String s = "";
        for (int i = 0; i < length; i++) {
            int number = secureRandom.nextInt(range);
            if (number == 0 && i == 0) { // to prevent the Zero to be the first number as then it will reduce the length of generated pin to three or even more if the second or third number came as zeros
                i = -1;
                continue;
            }
            s = s + number;
        }

        randomNumber = Integer.parseInt(s);

        return randomNumber;
    }


}
