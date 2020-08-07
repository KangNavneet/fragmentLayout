package com.navneetkang.uglyapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.prefs.PreferenceChangeEvent;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.TELECOM_SERVICE;

public class settingFragment extends PreferenceFragment {


    CheckBox rememberValues;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    int teamA,teamB,score;
    Intent intent;

    public settingFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void onResume() {
        super.onResume();

        rememberValues=getActivity().findViewById(R.id.rememberValues);


        sharedPreferences=getActivity().getSharedPreferences("mypref",MODE_PRIVATE);
        editor=sharedPreferences.edit();
       intent=getActivity().getIntent();
        teamA=intent.getIntExtra("teamA",0);
        teamB=intent.getIntExtra("teamB",0);
        score=intent.getIntExtra("score",0);

        checkValue();


        Toast.makeText(getActivity().getApplicationContext(),"TEAM A:"+teamA,Toast.LENGTH_LONG).show();
        Toast.makeText(getActivity().getApplicationContext(),"TEAM B:"+teamB,Toast.LENGTH_LONG).show();


        rememberValues.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rememberValues.isChecked())
                {


                    Toast.makeText(getActivity().getApplicationContext(),"Checked",Toast.LENGTH_LONG).show();
                    editor.putString("remValue","checked");
                    editor.putInt("teamA",teamA);
                    editor.putInt("teamB",teamB);
                    editor.putInt("score",score);
                    editor.commit();
                    checkValue();



                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Not Checked",Toast.LENGTH_LONG).show();
                    editor.putString("remValue","notChecked");
                    editor.putInt("teamA",0);
                    editor.putInt("teamB",0);
                    editor.putInt("score",0);
                    editor.commit();
                    checkValue();
                }

            }
        });


    }

    public void checkValue()
    {
        String checkVal=sharedPreferences.getString("remValue",null);
        if(checkVal!=null) {
            if (checkVal.equals("checked")) {
                Log.d("MyMessage", checkVal);

                rememberValues.setChecked(true);
            } else {
                Log.d("MyMessage", checkVal);
                rememberValues.setChecked(false);
            }


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }
}