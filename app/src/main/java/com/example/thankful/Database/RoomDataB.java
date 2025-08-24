package com.example.thankful.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.thankful.Model.Notes;

@Database(entities = {Notes.class}, version = 1, exportSchema = false)
public abstract class RoomDataB extends RoomDatabase {

    private static RoomDataB instance;

    // Abstract method to get DAO
    public abstract MainDAO MainDAO();

    // Singleton pattern to get database instance
    public static synchronized RoomDataB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            RoomDataB.class,
//                            "notes_database"
                            "notes_database"
                    )
                    .fallbackToDestructiveMigration() // resets DB if schema changes
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
