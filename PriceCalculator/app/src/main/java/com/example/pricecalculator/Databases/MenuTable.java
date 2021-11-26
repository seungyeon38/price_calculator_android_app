package com.example.pricecalculator.Databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MenuTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int menu_id;

    @ColumnInfo(name = "menu_name")
    private String menu_name;

    @ColumnInfo(name = "menu_price")
    private int menu_price;

//    @ColumnInfo(name = "menu_selling_price")
//    private int menu_selling_price;


    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }

//    public int getMenu_selling_price() {
//        return menu_selling_price;
//    }

//    public void setMenu_selling_price(int menu_selling_price) {
//        this.menu_selling_price = menu_selling_price;
//    }

    @Override
    public String toString() {
        return "MenuTable{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_price=" + menu_price +
                '}';
    }
}
