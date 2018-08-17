package com.lokesh.resumeapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lokesh.resumeapp.Database.DataBaseHandler;
import com.lokesh.resumeapp.Database.Database;
import com.lokesh.resumeapp.R;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    String TAG = this.getClass().getSimpleName();
    Database database=new Database();
    DataBaseHandler dataBaseHandler=new DataBaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    List<Database> contacts = dataBaseHandler.getActiveCus();
                    if (contacts.size() > 0) {
                        for(Database cn : contacts){
                            Intent intent = new Intent(SplashActivity.this, PinLoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        };
        timerThread.start();
    }
}
