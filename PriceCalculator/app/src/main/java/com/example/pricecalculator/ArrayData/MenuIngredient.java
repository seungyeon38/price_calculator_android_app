package com.example.pricecalculator.ArrayData;

public class MenuIngredient {
    private String menu_ingredient_name;
    private int ingredient_id;
    private int menu_ingredient_weight;

    public MenuIngredient(String menu_ingredient_name, int ingredient_id, int menu_ingredient_weight) {
        this.menu_ingredient_name = menu_ingredient_name;
        this.ingredient_id = ingredient_id;
        this.menu_ingredient_weight = menu_ingredient_weight;
    }

    public String getMenu_ingredient_name() {
        return menu_ingredient_name;
    }

    public void setMenu_ingredient_name(String menu_ingredient_name) {
        this.menu_ingredient_name = menu_ingredient_name;
    }

    public int getMenu_ingredient_weight() {
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
}
