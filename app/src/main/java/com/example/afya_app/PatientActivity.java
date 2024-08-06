package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        CardView findDoctor=findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(PatientActivity.this,FindDoctorActivity.class));
            }
        });
        CardView logOut=findViewById(R.id.cardLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this, StartActivity.class));
                finish();
            }
        });
        CardView setReminder=findViewById(R.id.cardSetReminder);
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this, ReminderActivity.class));
            }
        });
        CardView searchMeds =findViewById(R.id.cardSearchMeds);
        searchMeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this, MainActivity.class));
            }
        });
        CardView health =findViewById(R.id.cardHealthArticle);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this, HealthArticlesActivity.class));
            }
        });
        CardView notification =findViewById(R.id.cardConsultDoctor);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientActivity.this, ConsultDoctorActivity.class));
            }
        });
    }
}