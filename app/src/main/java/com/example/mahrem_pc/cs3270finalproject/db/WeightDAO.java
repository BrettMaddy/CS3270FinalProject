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
    @Query("SELECT * FROM Weight")
    List<Weight> selectAllWeight();

    @Query("SELECT MAX(_id) FROM Weight")
    int selectMostRecentWeightId();

    @Query("SELECT weight FROM Weight WHERE _id = :id")
    double selectWeight(int id);

    @Insert
    void insertWeight(Weight weight);

    @Update
    void updateWeight(Weight weight);

    @Delete
    void deleteWeight(Weight weight);
}
