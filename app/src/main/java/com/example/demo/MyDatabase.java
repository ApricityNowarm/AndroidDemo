package com.example.demo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class,UserData.class},version = 2,exportSchema = false)
@TypeConverters({Coverters.class})
abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase INSTANCE;
    static synchronized MyDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"MyDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract UserDao getUserDao();
    public abstract DataDao getDataDao();
}
