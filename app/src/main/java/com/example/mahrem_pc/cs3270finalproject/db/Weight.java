package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Weight
{
    public Weight(double weight)
    {
        setWeight(weight);
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @ColumnInfo(name = "weight")
    private double weight;

    public int get_id() { return _id; }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
