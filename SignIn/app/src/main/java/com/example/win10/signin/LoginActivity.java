package com.example.win10.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.auth.GoogleAuthProvider;

import static android.widget.Toast.LENGTH_LONG;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG = "Login Activity";
    EditText email;
    EditText password;
    Button button;
    TextView register;
    FirebaseAuth firebaseAuth;
    GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN=1;
    private SignInButton signInButton;
    ProgressDialog progressDialog;
    FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        button=(Button)findViewById(R.id.button);
        register=(TextView)findViewById((R.id.register));
        authStateListener=new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if(firebaseAuth.getCurrentUser()!=null)
                    startActivity(new Intent(LoginActivity.this,ProfileActivity.class));

            }
        };

        button.setOnClickListener( this);
        signInButton=(SignInButton)findViewById(R.id.googlebutton);

        signInButton.setOnClickListener(this);
        progressDialog =new ProgressDialog(this);
        register.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));

        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(LoginActivity.this,"Error has occurred", LENGTH_LONG).show();

                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
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
        if(view==signInButton)
            signIn();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
            } catch (ApiException e) {
                e.printStackTrace();
            }
            firebaseAuthWithGoogle(account);

        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.w(TAG, "signInWithCredential:success",task.getException());
                            Toast.makeText(LoginActivity.this,"Authentication Failed",LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
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
