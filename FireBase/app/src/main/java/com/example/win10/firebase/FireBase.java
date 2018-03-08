package com.example.win10.firebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by win10 on 3/9/2018.
 */

public class FireBase extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
