package com.example.win10.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.w3c.dom.Text;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Main Activity";
    EditText email;
    EditText password;
    Button button;
    private ProgressDialog progressBar;
    FirebaseAuth firebaseAuth;
    TextView text;

    FirebaseUser user;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = new ProgressDialog(this);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.button);
        text=(TextView)findViewById(R.id.text);

        button.setOnClickListener(this);
        text.setOnClickListener(this);

        if(firebaseAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(this,ProfileActivity.class));
        }





    }



    @Override
    public void onClick(View view) {
        if (view == button) {
            registerUser();
        }
        if(view==text);
        {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }


    }




    private void registerUser() {
        String emailid = email.getText().toString().trim();
        String passwrd = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailid) || TextUtils.isEmpty(passwrd)) {
            Toast.makeText(MainActivity.this, "No empty fields", Toast.LENGTH_SHORT).show();

        }
        progressBar.setMessage("Registering..");
        progressBar.show();



        firebaseAuth.createUserWithEmailAndPassword(emailid, passwrd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    finish();
                    if(user!=null)
                    {
                        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this,"Verified", LENGTH_LONG).show();

                                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                                }
                                else
                                    Toast.makeText(MainActivity.this,"Not verified", LENGTH_LONG).show();
                            }
                        });
                    }




                } else {
                    Toast.makeText(MainActivity.this, "UNsuccesful", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }



}


