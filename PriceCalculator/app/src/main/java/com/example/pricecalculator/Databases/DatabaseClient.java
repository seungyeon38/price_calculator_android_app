package com.example.pricecalculator.Databases;

import android.content.Context;

import androidx.room.Room;

import com.example.pricecalculator.Adapters.IngredientAdapter;

public class DatabaseClient {

    Context context;
    static DatabaseClient client;
    IngredientDatabase ingredientDatabase;
    MenuDatabase menuDatabase;
//    MenuIngredientDatabase menuIngredientDatabase;

    public DatabaseClient(Context context){
        this.context = context;

        ingredientDatabase = Room.databaseBuilder(context, IngredientDatabase.class, "IngredientDatabase").build();
        menuDatabase = Room.databaseBuilder(context, MenuDatabase.class, "MenuDatabase").build();
//        menuIngredientDatabase = Room.databaseBuilder(context, MenuIngredientDatabase.class, "MenuIngredientDatabase").build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if(client == null){
            client = new DatabaseClient(context);
        }
        return client;
    }

    public IngredientDatabase getIngredientDatabase(){
        return ingredientDatabase;
    }

    public MenuDatabase getMenuDatabase(){ return menuDatabase; }

//    public MenuIngredientDatabase getMenuIngredientDatabase(){ return menuIngredientDatabase; }
}
