package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConsultDetailsActivity extends AppCompatActivity {

    private EditText et_to1,et_subject1,et_message1;
    private Button bt_send1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_details);

        et_to1 = findViewById(R.id.et_to1);
        et_subject1 = findViewById(R.id.et_subject1);
        et_message1 = findViewById(R.id.et_message1);
        bt_send1 = findViewById(R.id.bt_send1);

        bt_send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract text from EditText fields
                String to = et_to1.getText().toString().trim();
                String subject = et_subject1.getText().toString().trim();
                String message = et_message1.getText().toString().trim();

                // Create an Intent to send email
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822"); // Set the MIME type
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                // Verify if there is an activity to handle the intent
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Start the activity (Gmail app)
                    startActivity(Intent.createChooser(intent, "Send Email"));
                } else {
                    // Handle case where no email app is available
                    // For example, show a toast or dialog
                    Toast.makeText(ConsultDetailsActivity.this, "No email app available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}