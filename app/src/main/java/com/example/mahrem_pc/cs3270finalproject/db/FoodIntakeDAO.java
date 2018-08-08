package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FoodIntakeDAO
{
    @Query("SELECT * FROM FoodIntake")
    List<FoodIntake> getAllFoodIntake();

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

    /*@Update
    void updateFoodIntake(FoodIntake... foodIntakes);

    @Update
    void updateCalories(Double calories);

    @Update
    void updateCarbs(Double carbs);

    @Update
    void updateProtein(Double protein);

    @Update
    void updateFat(Double fat);

    @Update
    void updateDate(String date);

    @Delete
    void deleteFoodIntake(FoodIntake foodIntake);*/

}
