package com.example.pricecalculator.Databases;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MenuWithIngredients {
    @Embedded
    public Menu menu;
    @Relation(
            parentColumn = "menu_id",
            entityColumn = "ingredient_id",
            associateBy = @Junction(MenuIngredient.class)
    )
    public List<Ingredient> ingredientList;
}
