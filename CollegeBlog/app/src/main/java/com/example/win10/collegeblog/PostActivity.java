package com.example.win10.collegeblog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    ImageButton imageButton;
    TextView text;
    EditText title;
    EditText desc;
    Button save;

    private ProgressDialog progressDialog;

    Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private final int GALLERY_INTENT=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        imageButton=(ImageButton)findViewById(R.id.addimage);
        text=(TextView)findViewById(R.id.text);
        title=(EditText)findViewById(R.id.title);
        desc=(EditText)findViewById(R.id.desc);
        save=(Button)findViewById(R.id.save);
        storageReference= FirebaseStorage.getInstance().getReference();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("New Post");

        progressDialog=new ProgressDialog(this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("Image/..");
                startActivityForResult(galleryIntent,GALLERY_INTENT);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startPosting();
            }
        });

    }

    private void startPosting()
    {
        progressDialog.setTitle("Uploading...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        final String titleval=title.getText().toString().trim();
        final String descval=desc.getText().toString().trim();
        if(!TextUtils.isEmpty(titleval)&&!TextUtils.isEmpty(descval)&&imageUri!=null)
        {

            StorageReference filepath=storageReference.child("Blog_Image").child(imageUri.getLastPathSegment());

            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloaduri=taskSnapshot.getDownloadUrl();


                    DatabaseReference newfile=databaseReference.push();
                    newfile.child("Title").setValue(titleval);
                    newfile.child("Description").setValue(descval);
                    newfile.child("ImageUri").setValue(downloaduri.toString());
                    Toast.makeText(PostActivity.this,"Upload done",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    startActivity(new Intent(PostActivity.this,MainActivity.class));
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_INTENT&&resultCode==RESULT_OK)
        {
            imageUri=data.getData();

            imageButton.setImageURI(imageUri);
        }

    }
}
