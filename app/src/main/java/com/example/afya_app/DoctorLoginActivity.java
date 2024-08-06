package com.example.afya_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorLoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailDoctor, passwordDoctor;
    private Button loginButton;
    private TextView signupDoctor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login2);

        auth=FirebaseAuth.getInstance();
        emailDoctor=findViewById(R.id.doctorLogin);
        passwordDoctor=findViewById(R.id.doctorPass);
        loginButton=findViewById(R.id.loginButton);
        signupDoctor=findViewById(R.id.signupDoctor);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=  emailDoctor.getText().toString();
                String password= passwordDoctor.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(!password.isEmpty()){
                        auth.signInWithEmailAndPassword(email,password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(DoctorLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(DoctorLoginActivity.this,DoctorActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(DoctorLoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else {
                        passwordDoctor.setError("Password cannot be empty");
                    }
                } else if (email.isEmpty()) {
                    emailDoctor.setError("Email cannot be empty");
                }else {
                    startActivity(new Intent(DoctorLoginActivity.this,DoctorActivity.class));
                    finish();
                }
            }
        });

        signupDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorLoginActivity.this,DoctorRegisterActivity.class));
                finish();
            }
        });
    }
}