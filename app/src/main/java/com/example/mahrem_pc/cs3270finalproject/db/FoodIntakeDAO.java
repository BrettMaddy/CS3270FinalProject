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
    List<Integer> selectAllCalories();

    @Query("SELECT MAX(_id) FROM FoodIntake")
    int selectMostRecentFoodIntakeId();

    @Query("SELECT calories FROM FoodIntake WHERE _id = :id")
    int selectCalories(int id);

    @Query("SELECT carbs FROM FoodIntake")
    List<Integer> selectAllCarbs();

    @Query("SELECT carbs FROM FoodIntake WHERE _id = :id")
    int selectCarbs(int id);

    @Query("SELECT protein FROM FoodIntake")
    List<Integer> selectAllProtein();

    @Query("SELECT protein FROM FoodIntake WHERE _id = :id")
    int selectProtein(int id);

    @Query("SELECT fat FROM FoodIntake")
    List<Integer> selectAllFat();

    @Query("SELECT fat FROM FoodIntake WHERE _id = :id")
    int selectFat(int id);

    @Insert
    void insertFoodIntake(FoodIntake... foodIntakes);

}
