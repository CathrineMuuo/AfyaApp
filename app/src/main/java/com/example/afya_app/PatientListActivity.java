package com.example.afya_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afya_app.Adapter.PatientListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PatientListAdapter adapter; // Changed adapter name
    private List<String> patientNames;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        patientNames = new ArrayList<>();
        adapter = new PatientListAdapter(this, patientNames); // Changed adapter name
        recyclerView.setAdapter(adapter);

        // Call a method to fetch patient names from Firestore and populate the list
        fetchPatientNamesFromFirestore();
    }

    private void fetchPatientNamesFromFirestore() {
        db.collection("patients")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String name = document.getString("name");
                                patientNames.add(name);
                            }
                            adapter.notifyDataSetChanged();
                        } else{
                            // If an error occurred while fetching data from Firestore
                            // Log the error for debugging purposes
                            Log.e("FirestoreError", "Error getting documents: ", task.getException());

                            // You can also provide feedback to the user, such as showing a Toast message
                            Toast.makeText(PatientListActivity.this, "Error fetching patient names", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
