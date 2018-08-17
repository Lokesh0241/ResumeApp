package com.lokesh.resumeapp.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.lokesh.resumeapp.Adapter.ResumeAdapter;
import com.lokesh.resumeapp.Model.ResumeModel;
import com.lokesh.resumeapp.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements View.OnClickListener {

    View rootView;


    ImageView ivMenu;



    public static HomeFragment fragment;

    public static HomeFragment newInstance() {
        fragment = new HomeFragment();

        return fragment;
    }

    public static HomeFragment getInstance() {

        if (fragment == null)
            return new HomeFragment();

        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return rootView;
    }

    private void init() {
        ivMenu = (ImageView) rootView.findViewById(R.id.ivMenu);


        //setRvResumeList();
    }

    @Override
    public void onClick(View view) {

    }


    public void setRvResumeList() {



    }
}
