package com.example.win10.firebase;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockApplication;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivity extends AppCompatActivity
{
    Firebase mRef;
    private Button btn;
    EditText text;
    ListView listView;
    ArrayList<String> username=new ArrayList<String>();



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
                if(!value.equals("")){
                Firebase child=mRef.push();
                    child.setValue(value);}
                Toast toast = Toast.makeText(getApplicationContext(), "Your text has been added", Toast.LENGTH_SHORT);
                toast.show();


            }
        }
        );
        listView=(ListView)findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, (List<String>) username);
        listView.setAdapter(arrayAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value= dataSnapshot.getValue(String.class);
                username.add(value);
                arrayAdapter.setNotifyOnChange(true);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {




            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });






    }
}
