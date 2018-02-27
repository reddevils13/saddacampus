package com.example.win10.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void manutd(View v)
    {
        Intent i=new Intent(this, ManUtd.class);
        startActivity(i);
    }
    public void mancity(View v)
    {
        Intent i=new Intent(this, ManCity.class);
        startActivity(i);
    }
    public void chelsea(View v)
    {
        Intent i=new Intent(this, Chelsea.class);
        startActivity(i);
    }
    public void arsenal(View v)
    {
        Intent i=new Intent(this, Arsenal.class);
        startActivity(i);
    }
    public void spurs(View v)
    {
        Intent i=new Intent(this, Spurs.class);
        startActivity(i);
    }
    public void liverpool(View v)
    {
        Intent i=new Intent(this, Liverpool.class);
        startActivity(i);
    }
}
