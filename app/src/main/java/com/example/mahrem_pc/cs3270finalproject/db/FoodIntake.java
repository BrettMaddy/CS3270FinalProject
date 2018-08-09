package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FoodIntake
{
    public FoodIntake(double calories, double carbs, double protein, double fat)
    {
        setCalories(calories);
        setCarbs(carbs);
        setProtein(protein);
        setFat(fat);
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int _id;

    @ColumnInfo(name = "calories")
    private double calories;

    @ColumnInfo(name = "carbs")
    private double carbs;

    @ColumnInfo(name = "protein")
    private double protein;

    @ColumnInfo(name = "fat")
    private double fat;

    public int get_id()
    {
        return _id;
    }

    public double getCalories()
    {
        return calories;
    }

    public void setCalories(double calories)
    {
        this.calories = calories;
    }

    public double getCarbs()
    {
        return carbs;
    }

    public void setCarbs(double carbs)
    {
        this.carbs = carbs;
    }

    public double getProtein()
    {
        return  protein;
    }

    public void setProtein(double protein)
    {
        this.protein = protein;
    }

    public double getFat()
    {
        return fat;
    }

    public void setFat(double fat)
    {
        this.fat = fat;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
