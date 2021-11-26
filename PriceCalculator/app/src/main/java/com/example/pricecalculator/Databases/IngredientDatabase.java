package com.example.pricecalculator.Databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ingredient.class}, version=1)
public abstract class IngredientDatabase extends RoomDatabase {
    public abstract IngredientDAO ingredientDAO();

}
