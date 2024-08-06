package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import com.example.afya_app.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ActivityMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;
    ArrayList<ListData> filteredList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageList = { R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine,
                R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine,
                R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine,
                R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine,
                R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine,
                R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine, R.drawable.medicine
        };

        int[] descriptionList = {R.string.aceDescription, R.string.amoDescription,R.string.atoDescription,R.string.aziDescription,
        R.string.cipDescription,R.string.escDescription,R.string.ibuDescription,R.string.lisDescription,
        R.string.metDescription,R.string.omeDescription,R.string.paroDescription, R.string.predDescription,
        R.string.simDescription,R.string.tadaDescription,R.string.tramDescription,R.string.warDescription,
        R.string.amloDescription,R.string.clonDescription,R.string.fluoDescription,R.string.loraDescription,
        R.string.oxycDescription,R.string.pregaDescription,R.string.silDescription,R.string.tamDescription,
        R.string.zolDescription,R.string.bupDescription,R.string.doxyDescription,R.string.gabaDescription,
        R.string.losartanDescription,R.string.pantoDescription,R.string.propDescription, R.string.carveDescription,
                R.string.esomeDescription,R.string.hydroDescription,R.string.meloDescription,R.string.methoDescription};


        int[] priceList = {R.string.acePrice, R.string.amoPrice, R.string.atoPrice, R.string.aziPrice,
                R.string.cipPrice, R.string.escPrice, R.string.ibuPrice, R.string.lisPrice,
                R.string.metPrice, R.string.omePrice, R.string.paroPrice, R.string.predPrice,
                R.string.simPrice, R.string.tadaPrice, R.string.tramPrice, R.string.warPrice,
                R.string.amloPrice, R.string.clonPrice, R.string.fluoPrice, R.string.loraPrice,
                R.string.oxycPrice, R.string.pregaPrice, R.string.silPrice, R.string.tamPrice,
                R.string.zolPrice, R.string.bupPrice, R.string.doxyPrice, R.string.gabaPrice,
                R.string.losartanPrice, R.string.pantoPrice, R.string.propPrice, R.string.carvePrice,
                R.string.esomePrice, R.string.hydroPrice, R.string.meloPrice, R.string.methoPrice};

        int[] locationList = {R.string.aceLocation, R.string.amoLocation, R.string.atoLocation, R.string.aziLocation,
                R.string.cipLocation, R.string.escLocation, R.string.ibuLocation, R.string.lisLocation,
                R.string.metLocation, R.string.omeLocation, R.string.paroLocation, R.string.predLocation,
                R.string.simLocation, R.string.tadaLocation, R.string.tramLocation, R.string.warLocation,
                R.string.amloLocation, R.string.clonLocation, R.string.fluoLocation, R.string.loraLocation,
                R.string.oxycLocation, R.string.pregaLocation, R.string.silLocation, R.string.tamLocation,
                R.string.zolLocation, R.string.bupLocation, R.string.doxyLocation, R.string.gabaLocation,
                R.string.losartanLocation, R.string.pantoLocation, R.string.propLocation, R.string.carveLocation,
                R.string.esomeLocation, R.string.hydroLocation, R.string.meloLocation, R.string.methoLocation};

        String[] nameList = {"Acetaminophen", "amoxicillin", "Atorvastatin",
                "Azithromycin", "Ciprofloxacin", "Escitalopram", "Ibuprofen",
                "Lisinopril", "Metformin", "Omeprazole", "Paroxetine",
                "Prednisone", "Simvastatin", "Tadalafil", "Tramadol",
                "Warfarin", "Amlodipine", "Clonazepam", "Fluoxetine",
                "Lorazepam", "Oxycodone", "Pregabalin", "Sildenafil",
                "Tamsulosin", "Zolpidem", "Bupropion", "Doxycycline",
                "Gabapentin", "Losartan", "Pantoprazole", "Propranolol",
                "Carvedilol", "Esomeprazole", "Hydroxyzine", "Meloxicam", "Methotrexate"};

        for (int i = 0; i < nameList.length; i++) {
            listData = new ListData(nameList[i], descriptionList[i], priceList[i], locationList[i],imageList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(MainActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this, MedicineDetailsActivity.class);
                intent.putExtra("name",nameList[position]);
                intent.putExtra("desc",descriptionList[position]);
                intent.putExtra("price",priceList[position]);
                intent.putExtra("loca",locationList[position]);
                intent.putExtra("image",imageList[position]);
                startActivity(intent);
            }
        });

        filteredList.addAll(dataArrayList); // Initially, filtered list is the same as the data list

        listAdapter = new ListAdapter(MainActivity.this, filteredList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MedicineDetailsActivity.class);
                intent.putExtra("name", filteredList.get(position).getName());
                intent.putExtra("desc", filteredList.get(position).getDesc());
                intent.putExtra("price", filteredList.get(position).getPrice());
                intent.putExtra("loca", filteredList.get(position).getLoca());
                intent.putExtra("image", filteredList.get(position).getImage());
                startActivity(intent);
            }
        });

        binding.searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase(Locale.getDefault());
        filteredList.clear();
        if (newText.length() == 0) {
            filteredList.addAll(dataArrayList);
        } else {
            for (ListData data : dataArrayList) {
                if (data.getName().toLowerCase(Locale.getDefault()).contains(newText)) {
                    filteredList.add(data);
                }
            }
        }
        listAdapter.notifyDataSetChanged();
        return true;
    }
}