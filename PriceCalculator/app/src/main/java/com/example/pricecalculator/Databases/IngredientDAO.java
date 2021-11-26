package com.example.pricecalculator.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IngredientDAO {

    // For inserting ingredient data
    @Insert
    void insertData(Ingredient ingredient);

    // For getting ingredient data
    @Query("SELECT * FROM Ingredient")
    List<Ingredient> selectAll();

    @Update
    void updateData(Ingredient ingredient);

    @Delete
    void deleteData(Ingredient ingredient);
}
