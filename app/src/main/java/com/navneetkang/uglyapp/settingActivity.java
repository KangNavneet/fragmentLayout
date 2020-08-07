package com.navneetkang.uglyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class settingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new settingFragment())
                .commit();


    }


}