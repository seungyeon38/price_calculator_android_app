package com.example.pricecalculator.Databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SimpleSQLiteQuery;

@Database(entities = {MenuIngredientTable.class}, version = 1)
public abstract class MenuIngredientDatabase extends RoomDatabase{
    public abstract MenuIngredientDAO menuIngredientDAO();


}
