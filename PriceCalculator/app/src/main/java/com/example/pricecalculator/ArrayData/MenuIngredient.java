package com.example.pricecalculator.ArrayData;

public class MenuIngredient {
    private String menu_ingredient_name;
    private int ingredient_id;
    private double menu_ingredient_weight;
    private String menu_ingredient_unit;
    private double menu_ingredient_price;

    public MenuIngredient(String menu_ingredient_name, int ingredient_id, double menu_ingredient_weight, String menu_ingredient_unit, double menu_ingredient_price) {
        this.menu_ingredient_name = menu_ingredient_name;
        this.ingredient_id = ingredient_id;
        this.menu_ingredient_weight = menu_ingredient_weight;
        this.menu_ingredient_unit = menu_ingredient_unit;
        this.menu_ingredient_price = menu_ingredient_price;
    }

    public String getMenu_ingredient_name() {
        return menu_ingredient_name;
    }

    public void setMenu_ingredient_name(String menu_ingredient_name) {
        this.menu_ingredient_name = menu_ingredient_name;
    }

    public double getMenu_ingredient_weight() {
        return menu_ingredient_weight;
    }

    public void setMenu_ingredient_weight(int menu_ingredient_weight) {
        this.menu_ingredient_weight = menu_ingredient_weight;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public double getMenu_ingredient_price() {
        return menu_ingredient_price;
    }

    public void setMenu_ingredient_price(int menu_ingredient_price) {
        this.menu_ingredient_price = menu_ingredient_price;
    }

    public String getMenu_ingredient_unit() {
        return menu_ingredient_unit;
    }

    public void setMenu_ingredient_unit(String menu_ingredient_unit) {
        this.menu_ingredient_unit = menu_ingredient_unit;
    }
}
