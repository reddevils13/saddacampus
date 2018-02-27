package com.example.win10.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.*;

public class ManUtd extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_utd);
        String[] players={"De Gea","Martial","Sanchez","Pogba","Valencia"};
        ListView utd=(ListView)findViewById(R.id.utd);
        ListAdapter list=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,players);
        utd.setAdapter(list);
    }
}
