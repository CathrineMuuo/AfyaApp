package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        CardView patientList=findViewById(R.id.cardpatientList);
        patientList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(DoctorActivity.this,PatientListActivity.class));
            }
        });

        CardView cardviewApp=findViewById(R.id.cardviewApp);
        cardviewApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this, ViewAppointmentActivity.class));
            }
        });

        CardView consultPatient=findViewById(R.id.cardConsultPatient);
        consultPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this, ConsultPatientActivity.class));
            }
        });

        CardView setMeds=findViewById(R.id.cardsetMeds);
        setMeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this, PrescribeMedicationsActivity.class));
            }
        });

        CardView editProfile=findViewById(R.id.cardEditProfile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this, UserProfileActivity.class));
             }
        });

        CardView logOut=findViewById(R.id.cardLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorActivity.this, StartActivity.class));
                finish();
            }
        });
    }
}