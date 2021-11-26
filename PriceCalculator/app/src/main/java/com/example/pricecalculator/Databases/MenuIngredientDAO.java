package com.example.pricecalculator.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface MenuIngredientDAO {
    // 필요한 query문 쓰면 됨
    // 메뉴 추가 시
    @Insert
    void insertData(MenuIngredient menuIngredient);

    // 메뉴의 한 재료만 삭제시
    @Delete
    void deleteData(MenuIngredient menuIngredient);

    // 메뉴 삭제시
    @Query("DELETE FROM MenuIngredient WHERE menu_id= :menu_id")
    void deleteDataByMenuId(int menu_id);

//    @Query("SELECT * FROM ")
}
