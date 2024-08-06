package com.example.afya_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 = {

            {"Esther Ndirangu", "Hospital Address: Peru", "1", "+2540779238014", "2500"},
            {"Paul Andimu", "Hospital Address: Berlin", "2", "+2540779238014", "3000"},
            {"Natasha Ndambo", "Hospital Address: London", "3", "+2540779238014", "5000"},
            {"Nicholas Murithi", "Hospital Address: Paris", "4", "+2540779238014", "6000"},
            {"Gloria Wahome", "Hospital Address: Tokyo", "5", "+2540779238014", "6000"}
    };
    private String[][] doctor_details2 = {
            {"Lola Kaytranada", "Hospital Address: Peru", "6", "+254798845329", "2000"},
            {"Onika Mayers", "Hospital Address: Berlin", "7", "+254772134567", "2500"},
            {"Kendrick Lamar", "Hospital Address: London", "8", "+254770976543", "3000"},
            {"Sebastian Williams", "Hospital Address: Paris", "9", "+254789456732", "5000"},
            {"Cate Muuo", "Hospital Address: Tokyo", "10", "+254111929213", "5500"}
    };
    private String[][] doctor_details3 = {
            {"Dinah Mwaura", "Hospital Address: Peru", "11", "+254712345789", "2000"},
            {"Tim Gomez", "Hospital Address: Berlin", "12", "+254778567435", "3000"},
            {"Jennifer Aniston", "Hospital Address: London", "13", "+254772008562", "4000"},
            {"Dave Baraka", "Hospital Address: Paris", "14", "+254765321453", "5500"},
            {"Stacy Pile", "Hospital Address: Tokyo", "15", "+254770675458", "6000"}
    };
    private String[][] doctor_details4 = {
            {"Larry Mutuku", "Hospital Address: Peru", "16", "+254779238014", "1500"},
            {"Ben Tashid", "Hospital Address: Berlin", "17", "+2547235674348", "3500"},
            {"Gianna Hadid", "Hospital Address: London", "18", "+254745673450", "4000"},
            {"Percy Atieno", "Hospital Address: Paris", "19", "+254709763452", "5000"},
            {"Chloe Mamre", "Hospital Address: Tokyo", "20", "+254789347564", "5500"}
    };
    private String[][] doctor_details5 = {
            {"Bernice Tuta", "Hospital Address: Peru", "21", "+254767345671", "1500"},
            {"Emmanuel Baraza", "Hospital Address: Berlin", "22", "+254734567891", "2500"},
            {"Charles Richards", "Hospital Address:London", "23", "+254709876543", "3000"},
            {"Betty Joe", "Hospital Address: Paris", "24", "+254787654322", "3500"},
            {"John Doe", "Hospital Address: Tokyo", "25", "+254789546324", "4000"}
    };
    TextView textView2;
    Button backButtonDD;
    HashMap<String, String> item;
    String[][] doctor_details = {};
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        textView2 = findViewById(R.id.textView2);
        backButtonDD = findViewById(R.id.backButtonDD);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        textView2.setText(title);

        if (title.compareTo("Family Doctors") == 0)
            doctor_details = doctor_details1;
        else if (title.compareTo("Surgeons") == 0)
            doctor_details = doctor_details2;
        else if (title.compareTo("Dentists") == 0)
            doctor_details = doctor_details3;
        else if (title.compareTo("Nutritionists") == 0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        backButtonDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees:" + doctor_details[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        ListView lst=findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[position][0]);
                it.putExtra("text3",doctor_details[position][1]);
                it.putExtra("text4",doctor_details[position][3]);
                it.putExtra("text5",doctor_details[position][4]);
                it.putExtra("text6",doctor_details[position][2]);
                startActivity(it);
            }
        });
    }
}