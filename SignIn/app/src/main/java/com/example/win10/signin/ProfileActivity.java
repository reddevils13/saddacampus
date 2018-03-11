package com.example.win10.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth firebaseAuth;
    Button button;
    public EditText mobile;
    EditText name;
    Button save;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        name=(EditText)this.findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.mobile);
        save= (Button) findViewById(R.id.save);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user==null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }


        button=(Button)findViewById(R.id.logout);
        button.setOnClickListener( this);
        save.setOnClickListener(this);
    }
    private void saveUserInformation()
    {
        String getname=name.getText().toString();
        String getmobile=mobile.getText().toString();
        UserInformation userInformation=new UserInformation(getname,getmobile);
        FirebaseUser user=firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(ProfileActivity.this,"Info is Saved",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View view)
    {
        if(view==button)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(view==save)
        {
            saveUserInformation();
        }

    }
}
