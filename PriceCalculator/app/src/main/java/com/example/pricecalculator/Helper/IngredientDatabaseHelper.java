package com.example.pricecalculator.Helper;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pricecalculator.Databases.DatabaseClient;
import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.ShowIngredients;
import com.example.pricecalculator.UpdateIngredient;

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
                    Toast.makeText(context, ingredientTables.getIngredient_name() + "가 추가되었습니다.", Toast.LENGTH_SHORT).show();
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

    // Update data
    public void updateData(IngredientTable table, String ingredient_name, int ingredient_weight, String ingredient_unit, int ingredient_total_price, double ingredient_unit_price){
        class UpdateIngredientData extends AsyncTask<Void, Void, IngredientTable>{
            @Override
            protected IngredientTable doInBackground(Void... voids) {
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
            protected void onPostExecute(IngredientTable ingredientTable) {
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
    public void deleteData(IngredientTable ingredientTable){
        class DeleteIngredientData extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getIngredientDatabase()
                        .ingredientDAO()
                        .deleteData(ingredientTable);

                return null;
            }
        }
        DeleteIngredientData deleteIngredientData = new DeleteIngredientData();
        deleteIngredientData.execute();
    }
}
