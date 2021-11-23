package com.example.Practice_room_table.Databases;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDAO {

    // For inserting Data
    @Insert
    void insertData(StudentsTable studentsTable);

    // For gettinf all Data
    @Query("SELECT * FROM studentstable")
    List<StudentsTable> selectAll();
}
