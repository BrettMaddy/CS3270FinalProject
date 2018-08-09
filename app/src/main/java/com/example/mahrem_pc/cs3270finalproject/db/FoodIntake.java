package com.example.mahrem_pc.cs3270finalproject.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FoodIntake
{
    public FoodIntake(int calories, int carbs, int protein, int fat)
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
    private int calories;

    @ColumnInfo(name = "carbs")
    private int carbs;

    @ColumnInfo(name = "protein")
    private int protein;

    @ColumnInfo(name = "fat")
    private int fat;

    public int get_id()
    {
        return _id;
    }

    public int getCalories()
    {
        return calories;
    }

    public void setCalories(int calories)
    {
        this.calories = calories;
    }

    public int getCarbs()
    {
        return carbs;
    }

    public void setCarbs(int carbs)
    {
        this.carbs = carbs;
    }

    public int getProtein()
    {
        return  protein;
    }

    public void setProtein(int protein)
    {
        this.protein = protein;
    }

    public int getFat()
    {
        return fat;
    }

    public void setFat(int fat)
    {
        this.fat = fat;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
