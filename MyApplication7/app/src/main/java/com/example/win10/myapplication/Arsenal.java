package com.example.win10.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Arsenal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arsenal);
        String[] players={"Czech","Aubameyang","Mkhitaryan","Lacazette","Koscielny"};
        ListView arsenal=(ListView)findViewById(R.id.arsenal);
        ListAdapter list=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,players);
        arsenal.setAdapter(list);
    }
}
