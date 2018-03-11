package com.example.win10.signin;

import android.app.Application;
import com.firebase.client.Firebase;
/**
 * Created by win10 on 3/10/2018.
 */

public class FireApp extends Application
{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
