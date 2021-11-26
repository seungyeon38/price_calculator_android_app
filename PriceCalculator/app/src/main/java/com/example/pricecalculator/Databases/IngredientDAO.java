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
    void insertData(IngredientTable ingredientTable);

    // For getting ingredient data
    @Query("SELECT * FROM IngredientTable")
    List<IngredientTable> selectAll();

    @Update
    void updateData(IngredientTable ingredientTable);

    @Delete
    void deleteData(IngredientTable ingredientTable);
}
