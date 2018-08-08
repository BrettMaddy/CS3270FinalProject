package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {FoodIntake.class, Weight.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, AppDatabase.class, "fitness-database").build();
        }

        return instance;
    }

    public abstract FoodIntakeDAO foodIntakeDAO();
    public abstract WeightDAO weightDAO();
}
