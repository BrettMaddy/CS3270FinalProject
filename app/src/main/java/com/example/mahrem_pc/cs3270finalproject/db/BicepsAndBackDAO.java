package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface BicepsAndBackDAO
{
    @Query("SELECT * FROM BicepsAndBack")
    List<FoodIntake> getAllBicepsAndBack();

    @Query("SELECT calories FROM FoodIntake WHERE date = :date")
    List<Double> getAllCalories(String date);

    @Query("SELECT carbs FROM FoodIntake WHERE date = :date")
    List<Double> getAllCarbs(String date);

    @Query("SELECT protein FROM FoodIntake WHERE date = :date")
    List<Double> getAllProtein(String date);

    @Query("SELECT fat FROM FoodIntake WHERE date = :date")
    List<Double> getAllFat(String date);

    @Insert
    void insertFoodIntake(FoodIntake... foodIntakes);
}
