package com.example.Practice_room_table.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    // For inserting Data
    @Insert
    void insertData(StudentsTable studentsTable);

    // For getting all Data
    @Query("SELECT * FROM studentstable")
    List<StudentsTable> selectAll();

    @Update
    void updateData(StudentsTable studentsTable);

    @Delete
    void deleteData(StudentsTable studentsTable);

}
