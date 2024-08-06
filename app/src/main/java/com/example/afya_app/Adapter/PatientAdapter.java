package com.example.afya_app.Adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afya_app.Model.Patient;
import com.example.afya_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private List<Patient> patientsList;
    private Context context;

    public PatientAdapter(List<Patient> patientsList,Context context) {
        this.patientsList = patientsList;
        this.context=context;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient = patientsList.get(position);
        holder.nameTextView.setText(patient.getName());
        holder.emailTextView.setText(patient.getEmail());

        // Set OnClickListener for confirm button
        holder.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle confirm button click
                confirmAppointment(patient);
            }
        });

        // Set OnClickListener for reschedule button
        holder.rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle reschedule button click
                rescheduleAppointment(patient);
            }
        });
    }

    private void rescheduleAppointment(Patient patient) {
            // Construct the email body with rescheduling information
            String emailBody = "Dear " + patient.getName() + ",\n\n";
            emailBody += "This is to notify you that the appointment you had booked has been rescheduled.\n";
            emailBody += "New appointment details:\n";
        // Add rescheduling information like date, time, etc. here
        emailBody += "Appointment Date:\n";
        emailBody += "Appointment Time:\n";


        // Create an Intent to send the email
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{patient.getEmail()});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Appointment Rescheduled");
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

            // Start the activity with the email client chooser dialog
        if (emailIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(Intent.createChooser(emailIntent, "Send Email"));
        } else {
            // Handle case where no email app is available
            Toast.makeText(context, "No email app installed", Toast.LENGTH_SHORT).show();
        }
        }

    private void confirmAppointment(Patient patient) {
        Toast.makeText(context, "Appointment confirmed for " + patient.getName(), Toast.LENGTH_SHORT).show();

        // Save appointment details to Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> appointment = new HashMap<>();
        appointment.put("patientName", patient.getName());
        appointment.put("email", patient.getEmail());
        // Add more appointment details if needed

        db.collection("appointments")
                .add(appointment)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Appointment details saved with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding appointment details", e);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return patientsList.size();
    }

    static class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView;
        Button leftButton,rightButton;
        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txtPatientName);
            emailTextView = itemView.findViewById(R.id.txtEmail);
            leftButton = itemView.findViewById(R.id.leftButton);
            rightButton = itemView.findViewById(R.id.rightButton);
        }
    }
}
