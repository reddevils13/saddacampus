package com.example.win10.signin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.R.*;
import static android.widget.Toast.LENGTH_LONG;
import static java.security.AccessController.getContext;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth firebaseAuth;
    Button button;
    public EditText mobile;
    EditText name;
    Button image;
    Button camera;
    StorageReference storage;
    private final int gallery=2;
    private final int cameraRequest=1;
    private ProgressDialog progressDialog;

    ListView listView;
    Button save;

    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        name=(EditText)this.findViewById(R.id.name);
        mobile=(EditText)findViewById(R.id.mobile);
        progressDialog=new ProgressDialog(this);
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
        image=(Button)findViewById(R.id.saveimage);
        camera=(Button)findViewById(R.id.camera);
        storage=FirebaseStorage.getInstance().getReference();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/=");
                startActivityForResult(intent,gallery);

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,cameraRequest);


            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        if(requestCode==gallery&&resultCode==RESULT_OK)
        {

            Uri uri=data.getData();
            StorageReference filepath=storage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileActivity.this,"Upload done..", LENGTH_LONG).show();

                }
            });
        }
        if(requestCode==cameraRequest&&requestCode==RESULT_OK)
        {
            Uri uri=data.getData();
            StorageReference filepath=storage.child("Photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Toast.makeText(ProfileActivity.this,"Upload done..", LENGTH_LONG).show();

                }
            });
        }
    }

    private void saveUserInformation()
    {
        String getname=name.getText().toString();
        String getmobile=mobile.getText().toString();
        UserInformation userInformation=new UserInformation(getname,getmobile);
        FirebaseUser user=firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(ProfileActivity.this,"Info is Saved", LENGTH_LONG).show();
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
