package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface AbsDAO
{
    @Query("SELECT crunches FROM Abs")
    List<Integer> selectAllCrunches();

    @Query("SELECT MAX(_id) FROM Abs")
    int selectMostRecentAbsId();

    @Query("SELECT crunches FROM Abs WHERE _id = :_id")
    int selectCrunches(int _id);

    @Query("SELECT legRaises FROM Abs")
    List<Integer> selectAllLegRaises();

    @Query("SELECT legRaises FROM Abs WHERE _id = :_id")
    int selectLegRaises(int _id);

    @Query("SELECT bicycles FROM Abs")
    List<Integer> selectAllBicycles();

    @Query("SELECT bicycles FROM Abs WHERE _id = :_id")
    int selectBicycles(int _id);

    @Insert
    void insertAbs(Abs... abs);
}
