package com.example.afya_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.afya_app.R;

import java.util.List;

public class DoctorListAdapter extends ArrayAdapter<Doctor> {
    private LayoutInflater inflater;

    public DoctorListAdapter(Context context, List<Doctor> doctors) {
        super(context, 0, doctors);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.list_doctor, parent, false);
        }

        Doctor doctor = getItem(position);

        // Bind data to your custom layout here
        if (doctor != null) {
            ImageView imageView = view.findViewById(R.id.listImage);
            TextView textView = view.findViewById(R.id.list_Text);

            // Set doctor's name
            textView.setText(doctor.getName());

            // Assuming you have a single image for all doctors
            // If you have different images for each doctor, you'll need to handle that
            imageView.setImageResource(doctor.getImageResource());
        }

        return view;
    }
}
