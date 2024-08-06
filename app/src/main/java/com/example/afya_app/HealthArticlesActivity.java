package com.example.afya_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] health_details = {
            {"Staying clean","","","","Click for more details"},
            {"Exercise","","","","Click for more details"},
            {"Eating Healthy","","","","Click for more details"},
            {"Sleep early","","","","Click for more details"},
            {"Walking Daily","","","","Click for more details"}
    };

    private int[] images= {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button backButtonHA;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles);

        ListView listView=findViewById(R.id.listViewHA);

        backButtonHA=findViewById(R.id.backButtonHA);

        backButtonHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthArticlesActivity.this, PatientActivity.class));
                finish();
            }
        });

        list=new ArrayList();
        for (int i=0;i<health_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",health_details[i][0]);
            item.put("line2",health_details[i][1]);
            item.put("line3",health_details[i][2]);
            item.put("line4",health_details[i][3]);
            item.put("line5",health_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it=new Intent(HealthArticlesActivity.this,HealthArticleDetailsActivity.class);
                it.putExtra("text1",health_details[position][0]);
                it.putExtra("text2",images[position]);
                startActivity(it);
            }
        });
    }
}