package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Abs
{
    public Abs(int crunches, int legRaises, int bicycles)
    {
        setCrunches(crunches);
        setLegRaises(legRaises);
        setBicycles(bicycles);
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    public int getCrunches() {
        return crunches;
    }

    public void setCrunches(int crunches) {
        this.crunches = crunches;
    }

    public int getLegRaises() {
        return legRaises;
    }

    public void setLegRaises(int legRaises) {
        this.legRaises = legRaises;
    }

    public int getBicycles() {
        return bicycles;
    }

    public void setBicycles(int bicycles) {
        this.bicycles = bicycles;
    }

    @ColumnInfo(name = "cruncehs")

    private int crunches;

    @ColumnInfo(name = "legRaises")
    private int legRaises;

    @ColumnInfo(name = "bicycles")
    private int bicycles;

}
