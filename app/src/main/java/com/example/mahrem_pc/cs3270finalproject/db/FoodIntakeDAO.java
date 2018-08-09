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
    @Query("SELECT calories FROM FoodIntake")
    List<Double> selectAllCalories();

    @Query("SELECT MAX(_id) FROM FoodIntake")
    int selectMostRecentFoodIntakeId();

    @Query("SELECT calories FROM FoodIntake WHERE _id = :id")
    double selectCalories(int id);

    @Query("SELECT carbs FROM FoodIntake")
    List<Double> selectAllCarbs();

    @Query("SELECT carbs FROM FoodIntake WHERE _id = :id")
    double selectCarbs(int id);

    @Query("SELECT protein FROM FoodIntake")
    List<Double> selectAllProtein();

    @Query("SELECT protein FROM FoodIntake WHERE _id = :id")
    double selectProtein(int id);

    @Query("SELECT fat FROM FoodIntake")
    List<Double> selectAllFat();

    @Query("SELECT fat FROM FoodIntake WHERE _id = :id")
    double selectFat(int id);

    @Insert
    void insertFoodIntake(FoodIntake... foodIntakes);

}
