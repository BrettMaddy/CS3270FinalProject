package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Legs
{
    public Legs(int lunges, int calfRaises, int squatThrusts)
    {
        setLunges(lunges);
        setCalfRaises(calfRaises);
        setSquatThrusts(squatThrusts);
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    public int getLunges() {
        return lunges;
    }

    public void setLunges(int lunges) {
        this.lunges = lunges;
    }

    public int getCalfRaises() {
        return calfRaises;
    }

    public void setCalfRaises(int calfRaises) {
        this.calfRaises = calfRaises;
    }

    public int getSquatThrusts() {
        return squatThrusts;
    }

    public void setSquatThrusts(int squatThrusts) {
        this.squatThrusts = squatThrusts;
    }

    @ColumnInfo(name = "lunges")
    private int lunges;

    @ColumnInfo(name = "calfRaises")
    private int calfRaises;

    @ColumnInfo(name = "squatThrusts")
    private int squatThrusts;
}
