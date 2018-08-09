package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface LegsDAO
{
    @Query("SELECT lunges FROM Legs")
    List<Integer> selectAllLunges();

    @Query("SELECT MAX(_id) FROM Legs")
    int selectMostRecentLegsId();

    @Query("SELECT lunges FROM Legs WHERE _id = :_id")
    int selectLunges(int _id);

    @Query("SELECT calfRaises FROM Legs")
    List<Integer> selectAllCalfRaises();

    @Query("SELECT calfRaises FROM Legs WHERE _id = :_id")
    int selectCalfRaises(int _id);

    @Query("SELECT squatThrusts FROM Legs")
    List<Integer> selectAllSquatThrusts();

    @Query("SELECT squatThrusts FROM Legs WHERE _id = :_id")
    int selectSquatThrusts(int _id);

    @Insert
    void insertLegs(Legs... legs);
}
