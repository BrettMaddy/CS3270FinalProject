package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TricepsAndChest
{
    public TricepsAndChest(int pushups, int dips, int shoulderPress)
    {
        setPushups(pushups);
        setDips(dips);
        setShouldPress(shoulderPress);
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @ColumnInfo(name = "pushups")
    private int pushups;

    @ColumnInfo(name = "dips")
    private int dips;

    @ColumnInfo(name = "shouldPress")
    private int shoulderPress;

    public int getPushups() {
        return pushups;
    }

    public void setPushups(int pushups) {
        this.pushups = pushups;
    }

    public int getDips() {
        return dips;
    }

    public void setDips(int dips) {
        this.dips = dips;
    }

    public int getShoulderPress() {
        return shoulderPress;
    }

    public void setShouldPress(int shoulderPress) {
        this.shoulderPress = shoulderPress;
    }
}
