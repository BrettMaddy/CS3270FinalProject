package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface TricepsAndChestDAO
{
    @Query("SELECT MAX(_id) FROM TricepsAndChest")
    int selectMostRecentTricepsAndChestId();

    @Query("SELECT pushups FROM TricepsAndChest")
    List<Integer> selectAllPushups();

    @Query("SELECT pushups FROM TricepsAndChest WHERE _id = :_id")
    int selectPushups(int _id);

    @Query("SELECT dips FROM TricepsAndChest")
    List<Integer> selectAllDips();

    @Query("SELECT dips FROM TricepsAndChest WHERE _id = :_id")
    int selectDips(int _id);

    @Query("SELECT shoulderPress from TricepsAndChest")
    List<Integer> selectAllShoulderPress();

    @Query("SELECT shoulderPress FROM TricepsAndChest WHERE _id = :_id")
    int selectShoulderPress(int _id);

    @Insert
    void insertTricepsAndChest(TricepsAndChest... tricepsAndChests);
}
