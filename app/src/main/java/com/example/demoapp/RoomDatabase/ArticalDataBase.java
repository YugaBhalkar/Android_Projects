package com.example.demoapp.RoomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Artical.class}, exportSchema = false, version = 1)
public abstract class ArticalDataBase extends RoomDatabase {

    private static final String DB_NAME = "demoapp_db";
    private static ArticalDataBase instance;

    public static synchronized ArticalDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ArticalDataBase.class, DB_NAME)
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract ArticalDao articalDao();
}
