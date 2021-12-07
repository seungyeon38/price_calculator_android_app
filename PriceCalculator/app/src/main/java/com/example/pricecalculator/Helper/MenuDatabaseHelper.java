package com.example.pricecalculator.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pricecalculator.Databases.DatabaseClient;
import com.example.pricecalculator.Databases.MenuIngredientTable;
import com.example.pricecalculator.Databases.MenuTable;
import com.example.pricecalculator.ShowMenus;

import java.util.List;
import java.util.concurrent.ExecutionException;

// 비동기처리
public class MenuDatabaseHelper {

    Context context;

    public MenuDatabaseHelper(Context context) {
        this.context = context;
    }

    public static MenuDatabaseHelper getInstance(Context context){
        return new MenuDatabaseHelper(context);
    }

//    public int getLastId(){
//        final int[] menu_id = new int[1];
//
//        class GetMenuTableLastId extends AsyncTask<Void, Void, Integer>{
//            @Override
//            protected Integer doInBackground(Void... voids) {
//                menu_id[0] = DatabaseClient.getInstance(context)
//                        .getMenuDatabase()
//                        .menuDAO()
//                        .getLastId();
//
//                return menu_id[0];
//            }
//        }
//
//        GetMenuTableLastId getMenuTableLastId = new GetMenuTableLastId();
//        getMenuTableLastId.execute();
//
//        return menu_id[0];
//    }

    // Insert Ingredient Data
    public long addMenu(String menu_name, double menu_price) throws ExecutionException, InterruptedException {
        System.out.print("addMenu");
        System.out.print(menu_name + " " + menu_price);
        final long[] menu_id = new long[1];
        class NewMenu extends AsyncTask<Void, Void, Long>{
            @Override
            protected Long doInBackground(Void... voids) {
                MenuTable menuTable = new MenuTable();
                menuTable.setMenu_name(menu_name);
                menuTable.setMenu_price(menu_price);
//                menuTable.setMenu_selling_price(menu_selling_price);

                menu_id[0] = DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .insertData(menuTable);

                return menu_id[0];
            }

//            @Override
//            protected void onPostExecute(MenuTable menuTable) {
//                super.onPostExecute(menuTable);
//
//                if(menuTable != null){
//                    Toast.makeText(context, menuTable.getMenu_name() + "가 추가되었습니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
        }

        NewMenu newMenu = new NewMenu();
        long menuId = newMenu.execute().get();

        return menuId;
    }

    // Show all data from ingredientTable
    public void showAllMenusData(){
        class AllMenus extends AsyncTask<Void, Void, List<MenuTable>>{
            @Override
            protected List<MenuTable> doInBackground(Void... voids) {
                List<MenuTable> list = DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .selectAll();

                return list;
            }

            @Override
            protected void onPostExecute(List<MenuTable> menuTables) {
                super.onPostExecute(menuTables);
                if(menuTables != null && menuTables.size() > 0){
                    ((ShowMenus)context).setRecyclerView(menuTables);
                }
            }
        }
        AllMenus allMenus = new AllMenus();
        allMenus.execute();
    }

    // Update data
    public void updateMenuData(MenuTable table, String menu_name, int menu_price){
        class UpdateMenuData extends AsyncTask<Void, Void, MenuTable>{
            @Override
            protected MenuTable doInBackground(Void... voids) {
                table.setMenu_name(menu_name);
                table.setMenu_price(menu_price);
//                table.setMenu_selling_price(menu_selling_price);

                DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .updateData(table);

                return null;
            }

            @Override
            protected void onPostExecute(MenuTable menuTable) {
                super.onPostExecute(menuTable);
                if(table != null){
                    Toast.makeText(context, table.getMenu_name() + "의 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        UpdateMenuData updateMenuData = new UpdateMenuData();
        updateMenuData.execute();
    }

    // Delete data
    public void deleteMenuData(MenuTable menuTable){
        class DeleteMenuData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .deleteData(menuTable);

                return null;
            }
        }
        DeleteMenuData deleteMenuData = new DeleteMenuData();
        deleteMenuData.execute();
    }

    // Delete data
    public void deleteMenuDataById(int menu_id){
        class DeleteMenuData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .deleteMenuDataById(menu_id);

                return null;
            }
        }
        DeleteMenuData deleteMenuData = new DeleteMenuData();
        deleteMenuData.execute();
    }

//    public MenuTable getMenuTableByMenuId(int menu_id) throws ExecutionException, InterruptedException {
//        class Menu extends AsyncTask<Void, Void, MenuTable>{
//            @Override
//            protected MenuTable doInBackground(Void... voids) {
//                MenuTable menu = DatabaseClient.getInstance(context)
//                        .getMenuDatabase()
//                        .menuDAO()
//                        .getMenuByMenuId(menu_id);
//
//                return menu;
//            }
//        }
//
//        Menu menu = new Menu();
//        return menu.execute().get();
//    }



}
