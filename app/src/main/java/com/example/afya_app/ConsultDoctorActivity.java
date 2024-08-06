package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.afya_app.Adapter.Doctor;
import com.example.afya_app.Adapter.DoctorListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ConsultDoctorActivity extends AppCompatActivity {

    private ListView listView;
    private DoctorListAdapter adapter;
    private List<Doctor> allDoctors;
    private List<Doctor> filteredDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_doctor);


        // Initialize ListView
        listView = findViewById(R.id.listview2);

        // Create Doctor objects with provided names and the "administrator" image resource
        allDoctors = new ArrayList<>();
        allDoctors.add(new Doctor("Esther Ndirangu", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Paul Andimu", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Natasha Ndambo", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Nicholas Murithi", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Gloria Wahome", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Lola Kaytranada", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Onika Mayers", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Kendrick Lamar", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Sebastian Williams", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Cate Muuo", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Dinah Mwaura", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Tim Gomez", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Jennifer Aniston", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Dave Baraka", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Stacy Pile", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Larry Mutuku", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Ben Tashid", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Gianna Hadid", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Percy Atieno", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Chloe Mamre", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Bernice Tuta", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Emmanuel Baraza", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Charles Richards", R.drawable.profiledoc));
        allDoctors.add(new Doctor("Betty Joe", R.drawable.profiledoc));
        allDoctors.add(new Doctor("John Doe", R.drawable.profiledoc));

        // Initialize filteredDoctors with allDoctors initially
        filteredDoctors = new ArrayList<>(allDoctors);

        // Initialize and set adapter
        adapter = new DoctorListAdapter(this, filteredDoctors);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked doctor
                Doctor clickedDoctor = filteredDoctors.get(position);

                // Start new activity with details of the clicked doctor
                Intent intent = new Intent(ConsultDoctorActivity.this, ConsultDetailsActivity.class);
                intent.putExtra("doctor_name", clickedDoctor.getName());
                // Add other details if needed
                startActivity(intent);
            }
        });


        // Initialize SearchView
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        SearchView searchView = findViewById(R.id.searchView2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the list when the text changes
                filterDoctors(newText);
                return true;
            }
        });
    }

    // Method to filter doctors based on search query
    private void filterDoctors(String query) {
        query = query.toLowerCase(Locale.getDefault());
        filteredDoctors.clear();
        if (query.length() == 0) {
            filteredDoctors.addAll(allDoctors); // If the query is empty, show all doctors
        } else {
            for (Doctor doctor : allDoctors) {
                if (doctor.getName().toLowerCase(Locale.getDefault()).contains(query)) {
                    filteredDoctors.add(doctor); // Add doctors whose names contain the query
                }
            }
        }
        // Notify the adapter that the dataset has changed
        adapter.notifyDataSetChanged();

    }
}