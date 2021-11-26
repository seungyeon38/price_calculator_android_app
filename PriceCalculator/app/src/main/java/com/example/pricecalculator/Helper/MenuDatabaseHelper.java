package com.example.pricecalculator.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.pricecalculator.Databases.DatabaseClient;
import com.example.pricecalculator.Databases.Menu;
import com.example.pricecalculator.ShowMenus;

import java.util.List;

// 비동기처리
public class MenuDatabaseHelper {

    Context context;

    public MenuDatabaseHelper(Context context) {
        this.context = context;
    }

    public static MenuDatabaseHelper getInstance(Context context){
        return new MenuDatabaseHelper(context);
    }

    // Insert Ingredient Data
    public void addMenu(String menu_name, int menu_price){
        class NewMenu extends AsyncTask<Void, Void, Menu>{
            @Override
            protected Menu doInBackground(Void... voids) {
                Menu menu = new Menu();
                menu.setMenu_name(menu_name);
                menu.setMenu_price(menu_price);
//                menuTable.setMenu_selling_price(menu_selling_price);

                DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .insertData(menu);

                return menu;
            }

            @Override
            protected void onPostExecute(Menu menuTable) {
                super.onPostExecute(menuTable);

                if(menuTable != null){
                    Toast.makeText(context, menuTable.getMenu_name() + "가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        }

        NewMenu newMenu = new NewMenu();
        newMenu.execute();
    }

    // Show all data from ingredientTable
    public void getAllMenusData(){
        class AllMenus extends AsyncTask<Void, Void, List<Menu>>{
            @Override
            protected List<Menu> doInBackground(Void... voids) {
                List<Menu> list = DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .selectAll();

                return list;
            }

            @Override
            protected void onPostExecute(List<Menu> menuTables) {
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
    public void updateMenuData(Menu table, String menu_name, int menu_price){
        class UpdateMenuData extends AsyncTask<Void, Void, Menu>{
            @Override
            protected Menu doInBackground(Void... voids) {
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
            protected void onPostExecute(Menu menuTable) {
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
    public void deleteMenuData(Menu menu){
        class DeleteMenuData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getMenuDatabase()
                        .menuDAO()
                        .deleteData(menu);

                return null;
            }
        }
        DeleteMenuData deleteMenuData = new DeleteMenuData();
        deleteMenuData.execute();
    }
}
