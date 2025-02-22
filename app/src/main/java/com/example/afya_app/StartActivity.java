package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button Doctor=findViewById(R.id.Doctor);
        Doctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(StartActivity.this,DoctorLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button Patient=findViewById(R.id.Patient);
        Patient.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}