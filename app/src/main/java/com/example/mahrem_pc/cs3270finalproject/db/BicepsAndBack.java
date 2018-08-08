package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BicepsAndBack
{
    public BicepsAndBack(int curls, int pullups, int chinups)
    {
        setCurls(curls);
        setPullups(pullups);
        setChinups(chinups);
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @ColumnInfo(name = "curls")
    private int curls;

    @ColumnInfo(name = "pullups")
    private int pullups;

    @ColumnInfo(name = "chinups")
    private int chinups;

    public int get_id() { return _id; }

    public int getCurls() {
        return curls;
    }

    public void setCurls(int curls) {
        this.curls = curls;
    }

    public int getPullups() {
        return pullups;
    }

    public void setPullups(int pullups) {
        this.pullups = pullups;
    }

    public int getChinups() {
        return chinups;
    }

    public void setChinups(int chinups) {
        this.chinups = chinups;
    }


}
