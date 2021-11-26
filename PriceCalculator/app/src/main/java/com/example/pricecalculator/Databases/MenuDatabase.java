package com.example.pricecalculator.Databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MenuTable.class}, version = 1)
public abstract class MenuDatabase extends RoomDatabase {
    public abstract MenuDAO menuDAO();
}
