package com.example.pricecalculator.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pricecalculator.Databases.DatabaseClient;
import com.example.pricecalculator.Databases.Ingredient;
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
        class NewIngredient extends AsyncTask<Void, Void, Ingredient>{
            @Override
            protected Ingredient doInBackground(Void... voids) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIngredient_name(ingredient_name);
                ingredient.setIngredient_weight(ingredient_weight);
                ingredient.setIngredient_unit(ingredient_unit);
                ingredient.setIngredient_total_price(ingredient_total_price);
                ingredient.setIngredient_unit_price(ingredient_unit_price);

                DatabaseClient.getInstance(context)
                        .getIngredientDatabase()
                        .ingredientDAO()
                        .insertData(ingredient);

                return ingredient;
            }

            @Override
            protected void onPostExecute(Ingredient ingredientTable) {
                super.onPostExecute(ingredientTable);
                if(ingredientTable != null){
                    Toast.makeText(context, ingredientTable.getIngredient_name() + "가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        NewIngredient newIngredient = new NewIngredient();
        newIngredient.execute();
    }

    // Show all data from ingredientTable
    public void getAllIngredientsData(){
        class AllIngredients extends AsyncTask<Void, Void, List<Ingredient>>{
            @Override
            protected List<Ingredient> doInBackground(Void... voids) {
                List<Ingredient> list = DatabaseClient.getInstance(context)
                        .getIngredientDatabase()
                        .ingredientDAO()
                        .selectAll();

                return list;
            }

            @Override
            protected void onPostExecute(List<Ingredient> ingredientTables) {
                super.onPostExecute(ingredientTables);
                if(ingredientTables != null && ingredientTables.size() > 0){
                    ((ShowIngredients)context).setRecyclerView(ingredientTables);
                }
            }
        }

        AllIngredients allIngredients = new AllIngredients();
        allIngredients.execute();
    }

    // Update data
    public void updateIngredientData(Ingredient table, String ingredient_name, int ingredient_weight, String ingredient_unit, int ingredient_total_price, double ingredient_unit_price){
        class UpdateIngredientData extends AsyncTask<Void, Void, Ingredient>{
            @Override
            protected Ingredient doInBackground(Void... voids) {
                table.setIngredient_name(ingredient_name);
                table.setIngredient_weight(ingredient_weight);
                table.setIngredient_unit(ingredient_unit);
                table.setIngredient_total_price(ingredient_total_price);
                table.setIngredient_unit_price(ingredient_unit_price);

                DatabaseClient.getInstance(context)
                        .getIngredientDatabase()
                        .ingredientDAO()
                        .updateData(table);

                return null;
            }

            @Override
            protected void onPostExecute(Ingredient ingredientTable) {
                super.onPostExecute(ingredientTable);
                if(table != null){
                    Toast.makeText(context, table.getIngredient_name() + "의 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        UpdateIngredientData updateIngredientData = new UpdateIngredientData();
        updateIngredientData.execute();
    }
    // Delete data
    public void deleteIngredientData(Ingredient ingredient){
        class DeleteIngredientData extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getIngredientDatabase()
                        .ingredientDAO()
                        .deleteData(ingredient);

                return null;
            }
        }
        DeleteIngredientData deleteIngredientData = new DeleteIngredientData();
        deleteIngredientData.execute();
    }
}
