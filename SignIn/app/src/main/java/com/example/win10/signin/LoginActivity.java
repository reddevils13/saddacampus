package com.example.win10.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText email;
    EditText password;
    Button button;
    TextView register;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        button=(Button)findViewById(R.id.button);
        register=(TextView)findViewById((R.id.register));
        button.setOnClickListener( this);
        progressDialog =new ProgressDialog(this);
        register.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));

        }
    }

    @Override
    public void onClick(View view) {
        if(view==button)
        {
            userLogin();
        }
        if(view==register)
        {
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    private void userLogin()
    {
        String emailid=email.getText().toString().trim();
        String passwrd=password.getText().toString().trim();
        if (TextUtils.isEmpty(emailid) || TextUtils.isEmpty(passwrd)) {
            Toast.makeText(LoginActivity.this, "No empty fields", Toast.LENGTH_SHORT).show();

        }
        progressDialog.setMessage("Signing In..");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(emailid,passwrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(LoginActivity.this, ProfileActivity.class));

                }
                else
                    Toast.makeText(LoginActivity.this,"No such User exists",Toast.LENGTH_LONG).show();

            }
        });


        }
}
