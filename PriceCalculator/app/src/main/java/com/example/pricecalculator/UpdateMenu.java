package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pricecalculator.Adapters.MenuIngredientAdapter;
import com.example.pricecalculator.ArrayData.MenuIngredient;
import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.Databases.MenuIngredientTable;
import com.example.pricecalculator.Databases.MenuTable;
import com.example.pricecalculator.Helper.IngredientDatabaseHelper;
import com.example.pricecalculator.Helper.MenuDatabaseHelper;
import com.example.pricecalculator.Helper.MenuIngredientDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityUpdateMenuBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class UpdateMenu extends AppCompatActivity {

    private ActivityUpdateMenuBinding binding;

    MenuDatabaseHelper menuDatabaseHelper;
    IngredientDatabaseHelper ingredientDatabaseHelper;
    MenuIngredientDatabaseHelper menuIngredientDatabaseHelper;

    MenuIngredientAdapter menuIngredientAdapter;

    MenuTable menuTable;

    List<IngredientTable> ingredientList;
    List<String> ingredient_name_list;
    List<MenuIngredientTable> menuIngredientList;

    ArrayList<MenuIngredient> menuIngredientArrayList;
//    private String menu_ingredient_name;
//    private int ingredient_id;
//    private int menu_ingredient_weight;
//    private String menu_ingredient_unit;
//    private double menu_ingredient_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        menuDatabaseHelper = MenuDatabaseHelper.getInstance(this);
        ingredientDatabaseHelper = IngredientDatabaseHelper.getInstance(this);
        menuIngredientDatabaseHelper = MenuIngredientDatabaseHelper.getInstance(this);

        menuIngredientArrayList = new ArrayList<>();

        try {
            ingredientList = ingredientDatabaseHelper.getAllIngredientsData();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ingredient_name_list = new LinkedList<>();

        for(int i=0; i<ingredientList.size(); i++){
            ingredient_name_list.add(ingredientList.get(i).getIngredient_name());
        }

        if(getIntent() != null) {
            menuTable = (MenuTable) getIntent().getSerializableExtra("menu_table");
            try {
                menuIngredientList = menuIngredientDatabaseHelper.getMenuIngredientsByMenuId(menuTable.getId());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            binding.menuName.setText(menuTable.getMenu_name());

            for(int i=0; i<menuIngredientList.size(); i++){
                IngredientTable ingredientTable = ingredientIdToObject(menuIngredientList.get(i).getIngredient_id());
                MenuIngredient menuIngredient = new MenuIngredient(ingredientTable.getIngredient_name(), menuIngredientList.get(i).getIngredient_id(), menuIngredientList.get(i).getMenu_ingredient_weight(), ingredientTable.getIngredient_unit(), menuIngredientList.get(i).getMenu_ingredient_weight()*ingredientTable.getIngredient_unit_price());
                menuIngredientArrayList.add(menuIngredient);
                setRecyclerView(menuIngredientArrayList);
            }
        }
    }

    //toolbar??? back??? ????????? ??? ??????
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
//                startActivity(new Intent(this, MainActivity.class));
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setRecyclerView(ArrayList<MenuIngredient> menuIngredientArrayList){
        binding.rvMenuIngredientTable.setLayoutManager(new LinearLayoutManager(this));
        menuIngredientAdapter = new MenuIngredientAdapter(this, menuIngredientArrayList);
        binding.rvMenuIngredientTable.setAdapter(menuIngredientAdapter);
    }


    public void updateMenu(View view) {
        if(menuIngredientArrayList.size() > 0 && !binding.menuName.getText().toString().isEmpty()) {
            menuDatabaseHelper.updateMenuData(menuTable, binding.menuName.getText().toString(), calculateMenuPrice(menuIngredientArrayList));
            menuIngredientDatabaseHelper.deleteMenuIngredientDataByMenuID(menuTable.getId());
            for(int i=0; i<menuIngredientArrayList.size(); i++){
                menuIngredientDatabaseHelper.addMenuIngredient(menuTable.getId(), menuIngredientArrayList.get(i).getIngredient_id(), menuIngredientArrayList.get(i).getMenu_ingredient_weight());
            }
//            finish();
        }
        finish();
//        startActivity(new Intent(this, ShowMenus.class));
    }

    public void addMenuIngredient(View view) {
        showDialog();
    }


    public void showDialog(){
        if(ingredient_name_list.size() > 0 && ingredient_name_list != null){
            Dialog addMenuIngredientDialog= new Dialog(UpdateMenu.this);       // Dialog ?????????
//        addMenuIngredientDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // ????????? ??????
//        addMenuIngredientDialog.setContentView(R.layout.dialog_add_menu_ingredient); // xml ???????????? ????????? ??????

            View mView = getLayoutInflater().inflate(R.layout.dialog_add_menu_ingredient,null);

            Spinner spIngredients = (Spinner) mView.findViewById(R.id.sp_ingredients);
            EditText menuIngredientWeight = (EditText) mView.findViewById(R.id.menu_ingredient_weight);
            TextView menuIngredientUnit = (TextView) mView.findViewById(R.id.menu_ingredient_unit);

            ArrayAdapter<String> ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ingredient_name_list);


            ingredientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spIngredients.setAdapter(ingredientAdapter);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(addMenuIngredientDialog.getWindow().getAttributes());
            lp.width = (int)(getResources().getDisplayMetrics().widthPixels * 0.90);
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            addMenuIngredientDialog.setContentView(mView);
            addMenuIngredientDialog.show(); // ??????????????? ?????????

            Window window = addMenuIngredientDialog.getWindow();
            window.setAttributes(lp);

            spIngredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    menuIngredientUnit.setText(ingredientNameToObject(ingredient_name_list.get(position)).getIngredient_unit());
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            /* ??? ?????? ?????? ????????? ???????????? ????????? ???????????? ??????. */

            // ?????? ?????? ????????? ?????? ????????????~
            // '?????? ????????? ??????'?????? ???????????? ???????????? ???????????? ???????????? ????????????,
            // '?????? ??? ??????'?????? ?????? ???????????? ??????????????? ???????????? ??????.
            // *????????? ???: findViewById()??? ??? ?????? -> ?????? ????????? ??????????????? ????????? ????????? ??????.

            // ?????? ??????
            Button noBtn = addMenuIngredientDialog.findViewById(R.id.noBtn);
            noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addMenuIngredientDialog.dismiss(); // ??????????????? ??????
                }
            });
            // ?????? ??????
            addMenuIngredientDialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!(spIngredients.getSelectedItem() == null) && !menuIngredientWeight.getText().toString().isEmpty()){
                        IngredientTable ingredient = ingredientNameToObject(spIngredients.getSelectedItem().toString());
                        MenuIngredient newMenuIngredient = new MenuIngredient(spIngredients.getSelectedItem().toString(), ingredient.getId(), Integer.parseInt(menuIngredientWeight.getText().toString()), ingredient.getIngredient_unit(), calculateIngredientPrice(ingredient.getId(), Integer.parseInt(menuIngredientWeight.getText().toString())));
                        menuIngredientArrayList.add(newMenuIngredient);
                        setRecyclerView(menuIngredientArrayList);
                        addMenuIngredientDialog.dismiss(); // ??????????????? ??????
                    }
                    else{
                        // ?????? ??? ???????????? ????????? ??? ?????????????????????
                        addMenuIngredientDialog.dismiss(); // ??????????????? ??????
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "????????? ????????? ????????????. ?????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show();
        }
    }

    private IngredientTable ingredientNameToObject(String ingredient_name){
        for(int i=0; i<ingredientList.size(); i++){
            if(ingredient_name == ingredientList.get(i).getIngredient_name()){
                return ingredientList.get(i);
            }
        }
        return null;
    }

    private IngredientTable ingredientIdToObject(int ingredient_id){
        for(int i=0; i<ingredientList.size(); i++){
            if(ingredientList.get(i).getId() == ingredient_id){
                return ingredientList.get(i);
            }
        }
        return null;
    }

    private double calculateIngredientPrice(int ingredient_id, int menu_ingredient_weight){
        for(int i=0; i<ingredientList.size(); i++){
            if(ingredient_id == ingredientList.get(i).getId()){
                return menu_ingredient_weight * ingredientList.get(i).getIngredient_unit_price();
            }
        }
        return -1;
    }

    private int calculateMenuPrice(ArrayList<MenuIngredient> menuIngredientList){
        double total_price = 0;

        for(int i=0; i<menuIngredientList.size(); i++){
            total_price += menuIngredientList.get(i).getMenu_ingredient_price();
        }

        return (int)Math.ceil(total_price);
    }
}