package com.example.pricecalculator.Databases;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MenuWithIngredients {
    @Embedded
    public MenuTable menuTable;
    @Relation(
            parentColumn = "menu_id",
            entityColumn = "ingredient_id",
            associateBy = @Junction(MenuIngredientTable.class)
    )
    public List<IngredientTable> ingredientTableList;
}
