package com.example.win10.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView manutd = (TextView) findViewById(R.id.textView3);
        if (manutd != null) {
            manutd.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView mancity = (TextView) findViewById(R.id.textView2);
        if (mancity != null) {
            mancity.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView chelsea = (TextView) findViewById(R.id.textView4);
        if (chelsea != null) {
            chelsea.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView arsenal = (TextView) findViewById(R.id.textView7);
        if (arsenal != null) {
            arsenal.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView spurs = (TextView) findViewById(R.id.textView5);
        if (spurs != null) {
            spurs.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView liverpool=(TextView)findViewById(R.id.textView6);
        liverpool.setMovementMethod(LinkMovementMethod.getInstance());

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
