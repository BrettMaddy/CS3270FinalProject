package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface BicepsAndBackDAO
{
    @Query("SELECT curls FROM BicepsAndBack")
    List<Integer> selectAllCurls();

    @Query("SELECT MAX(_id) FROM BicepsAndBack")
    int selectMostRecentBicepsAndBackId();

    @Query("SELECT curls FROM BicepsAndBack WHERE _id = :_id")
    int selectCurls(int _id);

    @Query("SELECT pullups FROM BicepsAndBack")
    List<Integer> selectAllPullups();

    @Query("SELECT pullups FROM BicepsAndBack WHERE _id = :_id")
    int selectPullups(int _id);

    @Query("SELECT chinups FROM BicepsAndBack")
    List<Integer> selectAllChinups();

    @Query("SELECT chinups FROM BicepsAndBack WHERE _id = :_id")
    int selectChinups(int _id);

    @Insert
    void insertBicepsAndBack(BicepsAndBack... bicepsAndBacks);
}
