package com.example.Practice_room_table.Databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {StudentsTable.class}, version = 1)
public abstract class StudentsDatabase extends RoomDatabase {
    public abstract StudentDAO studentDAO();

}
