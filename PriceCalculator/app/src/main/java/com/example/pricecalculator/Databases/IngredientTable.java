package com.example.pricecalculator.Databases;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Entity means table
@Entity
public class IngredientTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ingredient_id;

    @ColumnInfo(name = "ingredient_name")
    private String ingredient_name;

    @ColumnInfo(name = "ingredient_weight")
    private int ingredient_weight;

    @ColumnInfo(name = "ingredient_unit")
    private String ingredient_unit;

    @ColumnInfo(name = "ingredient_total_price")
    private int ingredient_total_price;

    @ColumnInfo(name = "ingredient_unit_price")
    private double ingredient_unit_price;

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public int getIngredient_weight() {
        return ingredient_weight;
    }

    public void setIngredient_weight(int ingredient_weight) {
        this.ingredient_weight = ingredient_weight;
    }

    public String getIngredient_unit() {
        return ingredient_unit;
    }

    public void setIngredient_unit(String ingredient_unit) {
        this.ingredient_unit = ingredient_unit;
    }

    public int getIngredient_total_price() {
        return ingredient_total_price;
    }

    public void setIngredient_total_price(int ingredient_total_price) {
        this.ingredient_total_price = ingredient_total_price;
    }

    public double getIngredient_unit_price() {
        return ingredient_unit_price;
    }

    public void setIngredient_unit_price(double ingredient_unit_price) {
        this.ingredient_unit_price = ingredient_unit_price;
    }

    @Override
    public String toString() {
        return "IngredientTable{" +
                "ingredient_id=" + ingredient_id +
                ", ingredient_name='" + ingredient_name + '\'' +
                ", ingredient_weight=" + ingredient_weight +
                ", ingredient_unit='" + ingredient_unit + '\'' +
                ", ingredient_total_price='" + ingredient_total_price + '\'' +
                ", ingredient_unit_price='" + ingredient_unit_price + '\'' +
                '}';
    }
}
