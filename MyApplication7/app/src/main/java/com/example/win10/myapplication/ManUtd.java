package com.example.win10.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.*;

public class ManUtd extends Activity
{
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_utd);
        recyclerView=findViewById(R.id.recycler);
        player a[]=new player[11];
        player player = new player("De Gea", R.drawable.degea);
        a[0]=player;
        player player2=new player("Valencia",R.drawable.valencia);
        a[1]=player2;
        player player3=new player("Lindelof",R.drawable.lindelof);
        a[2]=player3;
        player player4=new player("Bailly",R.drawable.bailly);
        a[3]=player4;
        player player5=new player("Shaw",R.drawable.luke_shaw);
        a[4]=player5;
        player player6=new player("Pogba",R.drawable.pogba);
        a[5]=player6;
        player player7=new player("Matic",R.drawable.matic);
        a[6]=player7;
        player player8=new player("Mctominay",R.drawable.mctominay);
        a[7]=player8;
        player player9=new player("Martial",R.drawable.martial);
        a[8]=player9;
        player player10=new player("Lukaku",R.drawable.lukaku);
        a[9]=player10;
        player player11=new player("Mata",R.drawable.mata);
        a[10]=player11;
        manutd_adapter manutd_adapter=new manutd_adapter(a);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(manutd_adapter);
        recyclerView.setLayoutManager(linearLayoutManager);




    }
}
