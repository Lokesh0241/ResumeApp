package com.lokesh.resumeapp.Support;

import android.app.Application;

import com.lokesh.resumeapp.Model.CreateBioModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell on 6/17/2017.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public List<CreateBioModel> bioModelList = new ArrayList<>();

    public static MyApplication getmInstance() {
        return mInstance;
    }

    public static void setmInstance(MyApplication mInstance) {
        MyApplication.mInstance = mInstance;
    }


    public List<CreateBioModel> getBioModelList() {
        return bioModelList;
    }

    public void setBioModelList(List<CreateBioModel> bioModelList) {
        this.bioModelList = bioModelList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
