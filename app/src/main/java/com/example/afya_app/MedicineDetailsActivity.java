package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.afya_app.databinding.ActivityMedicineDetailsBinding;

public class MedicineDetailsActivity extends AppCompatActivity {

    ActivityMedicineDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMedicineDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int desc = intent.getIntExtra("desc", R.string.aceDescription);
            int price = intent.getIntExtra("price", R.string.acePrice);
            int loca = intent.getIntExtra("loca", R.string.aceLocation);
            int image = intent.getIntExtra("image", R.drawable.medicine);

            binding.detailName.setText(name);
            binding.descriptionMed.setText(desc);
            binding.priceMed.setText(price);
            binding.locationMed.setText(loca);
            binding.detailImage.setImageResource(image);
        }
    }
}