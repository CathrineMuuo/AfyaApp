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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText emailSignUp, passwordSignUp, nameSign,phoneSign;
    private Button signUpButton;
    private TextView signInText;
    FirebaseFirestore firestore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        emailSignUp=findViewById(R.id.emailSignUp);
        passwordSignUp=findViewById(R.id.passwordSignUp);
        signUpButton=findViewById(R.id.signUpButton);
        signInText=findViewById(R.id.signInText);
        nameSign=findViewById(R.id.nameSign);
        phoneSign=findViewById(R.id.phoneSign);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=  emailSignUp.getText().toString().trim();
                String password= passwordSignUp.getText().toString().trim();
                String name=nameSign.getText().toString().trim();
                String phone=phoneSign.getText().toString().trim();
                if (email.isEmpty()){
                    emailSignUp.setError("Email cannot be empty");
                }
                if (password.isEmpty()){
                    passwordSignUp.setError("Password cannot be empty");
                }if (name.isEmpty()){
                    nameSign.setError("Name cannot be empty");
                }if (phone.isEmpty()){
                    phoneSign.setError("Phone cannot be empty");
                }else{
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                                userID=auth.getCurrentUser().getUid();
                                DocumentReference documentReference= firestore.collection("patients").document(userID);
                                Map<String,Object> user=new HashMap<>();
                                user.put("name",name);
                                user.put("email",email);
                                user.put("phone",phone);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG,"onSuccess: User profile is created for"+ userID);
                                    }
                                });
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}