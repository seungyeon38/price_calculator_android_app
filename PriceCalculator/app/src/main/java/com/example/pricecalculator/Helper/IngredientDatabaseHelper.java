package com.example.pricecalculator.Helper;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pricecalculator.Databases.DatabaseClient;
import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.ShowIngredients;

import java.util.List;

public class IngredientDatabaseHelper {

    Context context;

    public IngredientDatabaseHelper(Context context) {
        this.context = context;
    }

    public static IngredientDatabaseHelper getInstance(Context context){
        return new IngredientDatabaseHelper(context);
    }


    // Insert Ingredient Data
    public void addIngredient(String ingredient_name, int ingredient_weight, String ingredient_unit, int ingredient_total_price, double ingredient_unit_price){
        class NewIngredient extends AsyncTask<Void, Void, IngredientTable>{
            @Override
            protected IngredientTable doInBackground(Void... voids) {
                IngredientTable ingredientTable = new IngredientTable();
                ingredientTable.setIngredient_name(ingredient_name);
                ingredientTable.setIngredient_weight(ingredient_weight);
                ingredientTable.setIngredient_unit(ingredient_unit);
                ingredientTable.setIngredient_total_price(ingredient_total_price);
                ingredientTable.setIngredient_unit_price(ingredient_unit_price);

                DatabaseClient.getInstance(context)
                        .getIngredientDatabase()
                        .ingredientDAO()
                        .insertData(ingredientTable);
                return ingredientTable;
            }

            @Override
            protected void onPostExecute(IngredientTable ingredientTables) {
                super.onPostExecute(ingredientTables);
                if(ingredientTables != null){
                    Toast.makeText(context, ingredientTables.getIngredient_name() + "\n" + ingredientTables.getIngredient_unit_price(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        NewIngredient newIngredient = new NewIngredient();
        newIngredient.execute();
    }

    // Show all data from ingredientTable
    public void getAllIngredientsData(){
        class AllIngredients extends AsyncTask<Void, Void, List<IngredientTable>>{
            @Override
            protected List<IngredientTable> doInBackground(Void... voids) {
                List<IngredientTable> list = DatabaseClient.getInstance(context)
                        .getIngredientDatabase()
                        .ingredientDAO()
                        .selectAll();

                return list;
            }

            @Override
            protected void onPostExecute(List<IngredientTable> ingredientTables) {
                super.onPostExecute(ingredientTables);
                if(ingredientTables != null && ingredientTables.size() > 0){
                    ((ShowIngredients)context).setRecyclerView(ingredientTables);
                }
            }
        }

        AllIngredients allIngredients = new AllIngredients();
        allIngredients.execute();
    }
}
