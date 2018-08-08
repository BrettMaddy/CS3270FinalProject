package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface WeightDAO
{
    @Query("SELECT * FROM Weight WHERE date = :date")
    List<Weight> getWeightDiff(String date);

    @Insert
    void insertWeight(Weight weight);

    @Update
    void updateWeight(Weight weight);

    @Delete
    void deleteWeight(Weight weight);
}
