package com.example.pricecalculator.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MenuDAO {
    // 필요한 query문 쓰면 됨

    @Insert
    long insertData(MenuTable menuTable);

    @Update
    void updateData(MenuTable menuTable);

    @Delete
    void deleteData(MenuTable menuTable);

    @Query("SELECT * FROM MenuTable")
    List<MenuTable> selectAll();

//    @Query("SELECT * FROM MenuTable WHERE id= :menu_id")
//    MenuTable getMenuByMenuId(int menu_id);

//    @Query("SELECT MAX(menu_id) FROM MenuTable")
//    int getLastId();
}
