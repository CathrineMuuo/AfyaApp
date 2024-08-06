package com.example.afya_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afya_app.AppointmentDetailsActivity;
import com.example.afya_app.Model.Appointment;
import com.example.afya_app.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointments;
    private Context context;


    public AppointmentAdapter(List<Appointment> appointments, Context context) {
        this.appointments = appointments;
        this.context = context;
    }


    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointments.get(position);
        holder.bind(appointment);

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDate;
        private TextView txtTime;
        private TextView txtPatientName;
        private TextView txtpatientNumber;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtPatientName = itemView.findViewById(R.id.txtPatientName);
            txtpatientNumber = itemView.findViewById(R.id.txtContactNumber);
        }

        public void bind(Appointment appointment) {
            txtDate.setText(appointment.getAppointmentDate());
            txtTime.setText(appointment.getAppointmentTime());
            txtPatientName.setText(appointment.getPatientName());
            txtpatientNumber.setText(appointment.getPatientNumber());
        }
    }
}
