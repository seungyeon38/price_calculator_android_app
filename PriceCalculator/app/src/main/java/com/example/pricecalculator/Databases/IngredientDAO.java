package com.example.pricecalculator.Databases;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDAO {

    // For inserting ingredient data
    @Insert
    void insertData(IngredientTable ingredientTable);

    // For getting ingredient data
    @Query("SELECT * FROM ingredienttable")
    List<IngredientTable> selectAll();

}
