package com.example.pricecalculator.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Transaction;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

@Dao
public interface MenuIngredientDAO {
    // 필요한 query문 쓰면 됨
    // 메뉴 추가 시
    @Insert
    void insertData(MenuIngredientTable menuIngredientTable);

    // 메뉴의 한 재료만 삭제시
    @Delete
    void deleteData(MenuIngredientTable menuIngredientTable);

    // 메뉴 삭제시
    @Query("DELETE FROM MenuIngredientTable WHERE menu_id= :menu_id")
    void deleteDataByMenuId(int menu_id);

//    @Transaction
//    @Query("SELECT * FROM MenuTable")
//    public List<MenuWithIngredients> getMenuWithIngredients();

    @Query("SELECT * FROM MenuIngredientTable WHERE menu_id= :menu_id")
    public List<MenuIngredientTable> getMenuWithIngredients(int menu_id);


//    @RawQuery()
//    void insertMIData(int menu_id, int ingredient_id, int weight);

    @RawQuery()
    boolean insertMIData(SupportSQLiteQuery query);

}



