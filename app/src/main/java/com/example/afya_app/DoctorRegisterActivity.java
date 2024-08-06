package com.example.afya_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DoctorRegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailDoctor, passwordDoctor,doctorName,phoneDoctor;
    private Button buttonDoctor;
    private TextView signInDoctor;
    FirebaseFirestore firestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        emailDoctor=findViewById(R.id.emailDoctor);
        passwordDoctor=findViewById(R.id.passwordDoctor);
        buttonDoctor=findViewById(R.id.buttonDoctor);
        signInDoctor=findViewById(R.id.signInDoctor);
        doctorName=findViewById(R.id.doctorName);
        phoneDoctor=findViewById(R.id.phoneDoctor);

        buttonDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailDoctor.getText().toString().trim();
                String password = passwordDoctor.getText().toString().trim();
                String name = doctorName.getText().toString().trim();
                String phone = phoneDoctor.getText().toString().trim();
                if (email.isEmpty()) {
                    emailDoctor.setError("Email cannot be empty");
                }
                if (password.isEmpty()) {
                    passwordDoctor.setError("Password cannot be empty");
                }
                if (name.isEmpty()) {
                    doctorName.setError("Name cannot be empty");
                }
                if (phone.isEmpty()) {
                    phoneDoctor.setError("Phone cannot be empty");
                } else {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DoctorRegisterActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                userID = auth.getCurrentUser().getUid();
                                DocumentReference documentReference = firestore.collection("doctors").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name);
                                user.put("email", email);
                                user.put("phone", phone);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "onSuccess: User profile is created for" + userID);
                                    }
                                });
                                startActivity(new Intent(DoctorRegisterActivity.this, DoctorLoginActivity.class));
                                finish();
                            } else {
                                Toast.makeText(DoctorRegisterActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signInDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorRegisterActivity.this,DoctorLoginActivity.class));
            }
        });
    }
}