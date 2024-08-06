package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;

public class SendPrescriptionActivity extends AppCompatActivity {

    private EditText et_to,et_subject,et_message;
    private Button bt_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_prescription);

        et_to = findViewById(R.id.et_to);
        et_subject = findViewById(R.id.et_subject);
        et_message = findViewById(R.id.et_message);
        bt_send = findViewById(R.id.bt_send);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPrescriptionEmail();
            }
        });
    }

    private void sendPrescriptionEmail() {
        String to = "recipient@example.com"; // Replace with the recipient's email address
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
            Toast.makeText(SendPrescriptionActivity.this, "No email app installed", Toast.LENGTH_SHORT).show();
        }
    }
    }
    
      