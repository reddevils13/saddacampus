package com.example.win10.room;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    FloatingActionButton fab;
    RecyclerView.Adapter adapter;
    ArrayList<User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CreateUser.class));
            }
        });

        users=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);

        AppDatabase db=Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"database").allowMainThreadQueries().build();

        List<User> users=db.userDao().getAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new UserAdapter( users);
        recyclerView.setAdapter(adapter);




    }


}
