package com.example.pricecalculator.Databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;


@Entity(primaryKeys = {"menu_id", "ingredient_id"},
        foreignKeys = {
        @ForeignKey(entity = MenuTable.class,
                    parentColumns = "menu_id",
                    childColumns = "menu_id"),
        @ForeignKey(entity = IngredientTable.class,
                    parentColumns = "ingredient_id",
                    childColumns = "ingredient_id")})
public class MenuIngredientTable implements Serializable {
    @ColumnInfo(name = "menu_id")
    private int menu_id;

    @ColumnInfo(name = "ingredient_id")
    private int ingredient_id;

    @ColumnInfo(name = "menu_ingredient_weight")
    private int menu_ingredient_weight;


//    @ColumnInfo(name = "ingredient_unit_price")
//    private int ingredient_unit_price;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getMenu_ingredient_weight() {
        return menu_ingredient_weight;
    }

    public void setMenu_ingredient_weight(int menu_ingredient_weight) {
        this.menu_ingredient_weight = menu_ingredient_weight;
    }

    @Override
    public String toString() {
        return "MenuIngredientTable{" +
                "menu_id=" + menu_id +
                ", ingredient_id=" + ingredient_id +
                ", menu_ingredient_weight=" + menu_ingredient_weight +
                '}';
    }
}

