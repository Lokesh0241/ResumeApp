package com.lokesh.resumeapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.lokesh.resumeapp.R;

public class WelComeActivity extends AppCompatActivity implements View.OnClickListener {

    CardView cvViewList;
    CardView cvCreateBiodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        init();
    }

    private void init() {

        cvViewList = (CardView)findViewById(R.id.cvViewList);
        cvCreateBiodata = (CardView)findViewById(R.id.cvCreateBiodata);

        cvViewList.setOnClickListener(this);
        cvCreateBiodata.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.cvViewList:
                intent = new Intent(WelComeActivity.this, HomeActivity.class);
                intent.putExtra("list","");
                startActivity(intent);
                break;
            case R.id.cvCreateBiodata:
                intent = new Intent(WelComeActivity.this, CreateBioDataActivity.class);
                startActivity(intent);
                break;
        }

    }
}
