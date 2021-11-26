package com.example.pricecalculator.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MenuDAO {
    // 필요한 query문 쓰면 됨

    @Insert
    void insertData(Menu menu);

    @Update
    void updateData(Menu menu);

    @Delete
    void deleteData(Menu menu);

    @Query("SELECT * FROM Menu")
    List<Menu> selectAll();

}
