package com.example.win10.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ManCity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_city);

        String[] players={"Ederson","Laporte","KDB","Aguero","Silva"};
        ListView city=(ListView)findViewById(R.id.city);
        ListAdapter list=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,players);
        city.setAdapter(list);
    }
}
