package com.example.afya_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PrescribeMedicationsActivity extends AppCompatActivity {

    private EditText medNameEditText;
    private EditText medDoctorIDEditText;
    private EditText medPatientIDEditText;
    private EditText dosageFormEditText;
    private Button saveMed;
    private Button nextButton;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescribe_medications);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        medNameEditText = findViewById(R.id.medName);
        medDoctorIDEditText = findViewById(R.id.medDoctorID);
        medPatientIDEditText = findViewById(R.id.medPatientID);
        dosageFormEditText = findViewById(R.id.dosageForm);
        saveMed = findViewById(R.id.saveMed);
        nextButton = findViewById(R.id.nextButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPrescriptionEmail();
            }
        });

        saveMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrescription();
            }
        });
    }

    private void sendPrescriptionEmail() {
        String to = "cathrine4999@gmail.com"; // Replace with the recipient's email address
        String subject = "Prescription";
        String message = "I have prescribed the medicine below. Here is how you take it and for how long./nDosage:/n Duration: ";

        // Create an Intent to send the email
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + to));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        // Start the activity with the email client chooser dialog
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        } else {
            // Handle case where no email app is available
            // For example, display a toast message
            Toast.makeText(PrescribeMedicationsActivity.this, "No email app installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePrescription() {
        String medName = medNameEditText.getText().toString();
        String doctorID = medDoctorIDEditText.getText().toString();
        String patientID = medPatientIDEditText.getText().toString();
        String dosageForm = dosageFormEditText.getText().toString();

        // Create a new prescription object
        Map<String, Object> prescription = new HashMap<>();
        prescription.put("medName", medName);
        prescription.put("doctorID", doctorID);
        prescription.put("patientID", patientID);
        prescription.put("dosageForm", dosageForm);

        db.collection("prescriptions")
                .add(prescription)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Prescription saved successfully
                        Toast.makeText(PrescribeMedicationsActivity.this, "Prescription saved successfully", Toast.LENGTH_SHORT).show();
                        // You can show a success message or perform any other action
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Failed to save prescription
                        Toast.makeText(PrescribeMedicationsActivity.this, "Failed to save prescription", Toast.LENGTH_SHORT).show();
                        // You can show an error message or perform any other action
                    }
                });
    }
}