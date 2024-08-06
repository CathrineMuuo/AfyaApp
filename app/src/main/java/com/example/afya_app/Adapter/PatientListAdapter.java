package com.example.afya_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.afya_app.R;

import java.util.List;

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientViewHolder> {
    private List<String> patientNames;
    private Context context;

    public PatientListAdapter(Context context, List<String> patientNames) {
        this.context = context;
        this.patientNames = patientNames;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_list, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        String patientName = patientNames.get(position);
        holder.bind(patientName);
    }

    @Override
    public int getItemCount() {
        return patientNames.size();
    }

    public class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView patientText;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            patientText = itemView.findViewById(R.id.patient_Text);
        }

        public void bind(String patientName) {
            patientText.setText(patientName);
        }
    }
}
