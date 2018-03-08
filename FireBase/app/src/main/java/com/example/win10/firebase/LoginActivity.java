package com.example.win10.firebase;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity
{
    Firebase mRef;
    private Button btn;
    EditText text;



    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        text=(EditText)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button2);
        RelativeLayout relative=(RelativeLayout)findViewById(R.id.relative);
        mRef=new Firebase("https://fir-739b2.firebaseio.com/Users");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value=text.getText().toString();
                Firebase child=mRef.push();
                child.setValue(value);
                Toast toast = Toast.makeText(getApplicationContext(), "Your text has been added", Toast.LENGTH_SHORT);
                toast.show();

            }
        });



    }
}
