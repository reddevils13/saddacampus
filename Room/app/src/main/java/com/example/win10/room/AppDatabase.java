package com.example.win10.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by win10 on 5/8/2018.
 */

@Database(entities = {User.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase
{

        public abstract UserDao userDao();

}
