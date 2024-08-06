 package com.example.afya_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

 public class UserProfileActivity extends AppCompatActivity {

     private TextView NameTextView;
     private TextView EmailTextView;
     private TextView PhoneTextView;
     private Button editProfile;
     private FirebaseAuth mAuth;
     private FirebaseFirestore db;
     String userId;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_user_profile);

         // Initialize TextViews
         NameTextView = findViewById(R.id.profileName);
         EmailTextView = findViewById(R.id.profileEmail);
         PhoneTextView = findViewById(R.id.profilePhone);
         editProfile = findViewById(R.id.editButton);


         // Initialize Firebase components
         mAuth = FirebaseAuth.getInstance();
         db = FirebaseFirestore.getInstance();

         // Get currently logged-in user
         userId = mAuth.getCurrentUser().getUid();

         DocumentReference documentReference = db.collection("doctors").document(userId);
         documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
             @Override
             public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                 EmailTextView.setText(value.getString("email"));
                 NameTextView.setText(value.getString("name"));
                 PhoneTextView.setText(value.getString("phone"));
             }
         });

         editProfile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(UserProfileActivity.this, EditProfileActivity.class);
                 intent.putExtra("name",NameTextView.getText().toString());
                 intent.putExtra("email",EmailTextView.getText().toString());
                 intent.putExtra("phone",PhoneTextView.getText().toString());
                 startActivity(intent);
                 finish();
             }
         });
     }
 }
