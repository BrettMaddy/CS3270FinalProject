package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Weight
{
    public Weight(double weight, String date)
    {
        setWeight(weight);
        setDate(date);
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @ColumnInfo(name = "weight")
    private double weight;

    @ColumnInfo(name = "date")
    private String date;

    public int get_id() { return _id; }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
