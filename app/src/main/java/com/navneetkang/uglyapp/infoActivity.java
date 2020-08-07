package com.navneetkang.uglyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class infoActivity extends AppCompatActivity {
TextView infoText;
Button infoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        infoText=findViewById(R.id.infoText);
        infoBtn=findViewById(R.id.infoButton);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoText.setText("INFORMATION\nDeveloped By : NAVNEET KANG\nHave a nice day");

            }
        });

        Toast.makeText(this,"Developed By : Navneet Kang",Toast.LENGTH_LONG).show();


    }
}