package com.example.pricecalculator.Databases;

import android.content.Context;

import androidx.room.Room;

import com.example.pricecalculator.Adapters.IngredientAdapter;

public class DatabaseClient {

    Context context;
    static DatabaseClient client;
    IngredientDatabase ingredientDatabase;

    public DatabaseClient(Context context){
        this.context = context;

        ingredientDatabase = Room.databaseBuilder(context, IngredientDatabase.class, "IngredientDatabase").build();
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
}
