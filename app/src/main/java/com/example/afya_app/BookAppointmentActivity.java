package com.example.afya_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class BookAppointmentActivity extends AppCompatActivity {

    TextView bookApp;
    EditText fullName,docAddress,contactNumber,doctorFees,patientNumber,patientName,patientID,doctorID;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button buttonAppDate,buttonAppTime,bookButton,backBookButton;
    private int Year, Month, Day;
    private int Hour, Minute;
    private DatabaseReference databaseReference;
    private FirebaseDatabase  firebaseDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        bookApp=findViewById(R.id.bookApp);
        fullName=findViewById(R.id.fullName);
        docAddress=findViewById(R.id.docAddress);
        contactNumber=findViewById(R.id.contactNumber);
        doctorFees=findViewById(R.id.doctorFees);
        buttonAppDate=findViewById(R.id.buttonAppDate);
        buttonAppTime=findViewById(R.id.buttonAppTime);
        bookButton=findViewById(R.id.bookButton);
        backBookButton=findViewById(R.id.backBookButton);
        patientName=findViewById(R.id.patientName);
        patientNumber=findViewById(R.id.patientNumber);
        patientID=findViewById(R.id.patientID);
        doctorID=findViewById(R.id.doctorID);

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getfullName=fullName.getText().toString().trim();
                String getdocAddress=docAddress.getText().toString().trim();
                String getcontactNumber=contactNumber.getText().toString().trim();
                String getdoctorFees=doctorFees.getText().toString().trim();
                String getPatientName = patientName.getText().toString().trim();
                String getPatientNumber = patientNumber.getText().toString().trim();
                String getpatientID= patientID.getText().toString().trim();
                String getdoctorID= doctorID.getText().toString().trim();

                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("fullName",getfullName);
                hashMap.put("docAddress",getdocAddress);
                hashMap.put("contactNumber",getcontactNumber);
                hashMap.put("doctorFees",getdoctorFees);
                hashMap.put("patientName", getPatientName);
                hashMap.put("patientNumber", getPatientNumber);
                hashMap.put("patientID", getpatientID);
                hashMap.put("doctorID", getdoctorID);
                hashMap.put("appointmentDate", Day + "/" + Month + "/" + Year);
                hashMap.put("appointmentTime", Hour + ":" + Minute);

                databaseReference.child("Appointments")
                        .child(getfullName)
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(BookAppointmentActivity.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookAppointmentActivity.this, "Booking Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        fullName.setKeyListener(null);
        docAddress.setKeyListener(null);
        contactNumber.setKeyListener(null);
        doctorFees.setKeyListener(null);
        doctorID.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");
        String ID=it.getStringExtra("text6");

        bookApp.setText(title);
        fullName.setText(fullname);
        docAddress.setText(address);
        contactNumber.setText(contact);
        doctorFees.setText(fees+"/-");
        doctorID.setText(ID);

        //datepicker
        initDatePicker();
        buttonAppDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //timepicker
        initTimePicker();
        buttonAppTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        backBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Year=year;
                Day=dayOfMonth;
                Month=month+1;
                buttonAppDate.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };

        Calendar cal= Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Hour=hourOfDay;
                Minute=minute;
                buttonAppTime.setText(hourOfDay+":"+minute);
            }
        };

        Calendar cal= Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);

    }
}