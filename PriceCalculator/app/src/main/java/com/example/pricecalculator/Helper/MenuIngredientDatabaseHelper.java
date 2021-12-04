package com.example.pricecalculator.Helper;

import android.content.Context;
import android.os.AsyncTask;

import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.pricecalculator.Databases.DatabaseClient;
import com.example.pricecalculator.Databases.MenuIngredientTable;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MenuIngredientDatabaseHelper {

    Context context;


    public MenuIngredientDatabaseHelper(Context context) {
        this.context = context;
    }

    public static MenuIngredientDatabaseHelper getInstance(Context context){
        return new MenuIngredientDatabaseHelper(context);
    }

    // Insert MenuIngredient Data
    public void addMenuIngredient(int menu_id, int ingredient_id, int menu_ingredient_weight){
        class NewMenuIngredient extends AsyncTask<Void, Void, MenuIngredientTable> {
            @Override
            protected MenuIngredientTable doInBackground(Void... voids) {
                MenuIngredientTable menuIngredientTable = new MenuIngredientTable();
                menuIngredientTable.setMenu_id(menu_id);
                menuIngredientTable.setIngredient_id(ingredient_id);
                menuIngredientTable.setMenu_ingredient_weight(menu_ingredient_weight);

//                DatabaseClient.getInstance(context)
//                        .getMenuIngredientDatabase()
//                        .menuIngredientDAO()
//                        .insertMIData(menu_id, ingredient_id, menu_ingredient_weight);

                DatabaseClient.getInstance(context)
                        .getMenuIngredientDatabase()
                        .menuIngredientDAO()
                        .insertMIData(new SimpleSQLiteQuery("INSERT INTO MenuIngredientTable VALUES " + "(" + menu_id + "," + ingredient_id + "," + menu_ingredient_weight + ")"));

                return menuIngredientTable;
            }

//            @Override
//            protected void onPostExecute(MenuIngredientTable menuIngredientTable) {
//                super.onPostExecute(menuIngredientTable);
//                if(menuIngredientTable != null){
//                    Toast.makeText(context, menuIngredientTable.getMenu_id() + ","  + menuIngredientTable.getIngredient_id() + "가 추가되었습니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
        }
        NewMenuIngredient newMenuIngredient = new NewMenuIngredient();
        newMenuIngredient.execute();
    }

    // Delete data
    public void deleteMenuIngredientDataByMenuID(int menu_id){
        class DeleteMenuIngredientData extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getMenuIngredientDatabase()
                        .menuIngredientDAO()
                        .deleteDataByMenuId(menu_id);

                return null;
            }
        }
        DeleteMenuIngredientData deleteMenuIngredientData = new DeleteMenuIngredientData();
        deleteMenuIngredientData.execute();
    }

    public List<MenuIngredientTable> getMenuIngredientsByMenuId(int menu_id) throws ExecutionException, InterruptedException {
        class MenuIngredients extends AsyncTask<Void, Void, List<MenuIngredientTable>>{
            @Override
            protected List<MenuIngredientTable> doInBackground(Void... voids) {
                List<MenuIngredientTable> list = DatabaseClient.getInstance(context)
                        .getMenuIngredientDatabase()
                        .menuIngredientDAO()
                        .getMenuIngredientsByMenuId(menu_id);

                return list;
            }
        }

        MenuIngredients allMenuIngredients = new MenuIngredients();
        return allMenuIngredients.execute().get();
    }

    public List<MenuIngredientTable> getMenuIngredientsByIngredientId(int ingredient_id) throws ExecutionException, InterruptedException {
        class MenuIngredients extends AsyncTask<Void, Void, List<MenuIngredientTable>>{
            @Override
            protected List<MenuIngredientTable> doInBackground(Void... voids) {
                List<MenuIngredientTable> list = DatabaseClient.getInstance(context)
                        .getMenuIngredientDatabase()
                        .menuIngredientDAO()
                        .getMenuIngredientsByIngredientId(ingredient_id);

                return list;
            }
        }

        MenuIngredients menuIngredients = new MenuIngredients();
        return menuIngredients.execute().get();
    }

//    public List<IngredientAndMenuIngredient> getIngredientsInfoByIngredientId(int ingredient_id) throws ExecutionException, InterruptedException {
//        class MenuIngredients extends AsyncTask<Void, Void, List<IngredientAndMenuIngredient>>{
//            @Override
//            protected List<IngredientAndMenuIngredient> doInBackground(Void... voids) {
//                List<IngredientAndMenuIngredient> list = DatabaseClient.getInstance(context)
//                        .getMenuIngredientDatabase()
//                        .menuIngredientDAO()
//                        .getIngredientsInfoByIngredientId(ingredient_id);
//
//                return list;
//            }
//        }
//
//        MenuIngredients menuIngredients = new MenuIngredients();
//        Log.i("tag", "menuIngredients.execute().get().toString()");
//        Log.i("tag", menuIngredients.execute().get().toString());
//        return menuIngredients.execute().get();
//    }
//
//    public List<IngredientAndMenuIngredient> getAllIngredientsInfo() throws ExecutionException, InterruptedException {
//        class MenuIngredients extends AsyncTask<Void, Void, List<IngredientAndMenuIngredient>>{
//            @Override
//            protected List<IngredientAndMenuIngredient> doInBackground(Void... voids) {
//                List<IngredientAndMenuIngredient> list = DatabaseClient.getInstance(context)
//                        .getMenuIngredientDatabase()
//                        .menuIngredientDAO()
//                        .getAllIngredientsInfo();
//
//                return list;
//            }
//        }
//
//        MenuIngredients menuIngredients = new MenuIngredients();
//        return menuIngredients.execute().get();
//    }
}
