package com.example.win10.room;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateUser extends AppCompatActivity {

    EditText firstname;
    EditText lastname;
    EditText email;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        email=findViewById(R.id.mail);
        button=findViewById(R.id.button);

       final AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"database").allowMainThreadQueries().build();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user=new User(firstname.getText().toString(),lastname.getText().toString(),email.getText().toString());
                db.userDao().insertAll(user);
                startActivity(new Intent(CreateUser.this,MainActivity.class));
            }
        });
    }
}
