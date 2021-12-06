package com.example.pricecalculator.Databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(indices = {@Index(value = {"menu_name"}, unique = true)})
public class MenuTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "menu_name")
    private String menu_name;

    @ColumnInfo(name = "menu_price")
    private double menu_price;

//    @ColumnInfo(name = "menu_selling_percentage")
//    private double menu_selling_percentage;
//
//    @ColumnInfo(name = "menu_selling_price")
//    private int menu_selling_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public double getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(double menu_price) {
        this.menu_price = menu_price;
    }

//    public double getMenu_selling_percentage() {
//        return menu_selling_percentage;
//    }
//
//    public void setMenu_selling_percentage(double menu_selling_percentage) {
//        this.menu_selling_percentage = menu_selling_percentage;
//    }
//
//    public int getMenu_selling_price() {
//        return menu_selling_price;
//    }
//
//    public void setMenu_selling_price(int menu_selling_price) {
//        this.menu_selling_price = menu_selling_price;
//    }

    @Override
    public String toString() {
        return "MenuTable{" +
                "menu_id=" + id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_price=" + menu_price +
                '}';
    }
}
