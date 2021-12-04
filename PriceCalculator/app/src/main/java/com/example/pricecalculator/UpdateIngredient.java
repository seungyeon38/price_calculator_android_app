//package com.example.pricecalculator;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import com.example.pricecalculator.Databases.IngredientTable;
//import com.example.pricecalculator.Databases.MenuIngredientTable;
//import com.example.pricecalculator.Databases.MenuTable;
//import com.example.pricecalculator.Helper.IngredientDatabaseHelper;
//import com.example.pricecalculator.Helper.MenuDatabaseHelper;
//import com.example.pricecalculator.Helper.MenuIngredientDatabaseHelper;
//import com.example.pricecalculator.databinding.ActivityUpdateIngredientBinding;
//
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//
//public class UpdateIngredient extends AppCompatActivity {
//
//    private ActivityUpdateIngredientBinding binding;
//
//    IngredientTable ingredientTable;
//    IngredientDatabaseHelper ingredientDatabaseHelper;
//    MenuDatabaseHelper menuDatabaseHelper;
//    MenuIngredientDatabaseHelper menuIngredientDatabaseHelper;
//
//    List<MenuIngredientTable> menuIngredientTableList;
//    List<IngredientTable> ingredientTableList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityUpdateIngredientBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
//        setContentView(view);
//
//        ingredientDatabaseHelper = IngredientDatabaseHelper.getInstance(this);
//
//        // toolbar 뒤로가기
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//        ArrayAdapter unit_adapter = ArrayAdapter.createFromResource(this, R.array.unit_array, android.R.layout.simple_spinner_dropdown_item);
//
//        unit_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spUnit.setAdapter(unit_adapter);
//
//
//        if (getIntent() != null) {
//            ingredientTable = (IngredientTable) getIntent().getSerializableExtra("ingredient_table");
//            binding.ingredientName.setText(ingredientTable.getIngredient_name());
//            binding.ingredientWeight.setText(Integer.toString(ingredientTable.getIngredient_weight()));
//            binding.ingredientTotalPrice.setText(Integer.toString(ingredientTable.getIngredient_total_price()));
//            binding.spUnit.setSelection(getIndex(binding.spUnit, ingredientTable.getIngredient_unit()));
//        }
//    }
//
//    // private method of your class
//    // string에 해당하는 것의 spinner에서의 position을 return해줌
//    private int getIndex(Spinner spinner, String myString) {
//        for (int i = 0; i < spinner.getCount(); i++) {
//            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
//                return i;
//            }
//        }
//        return 0;
//    }
//
//    //toolbar의 back키 눌렀을 때 동작
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home: {
//                finish();
//                return true;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void updateIngredient(View view) throws ExecutionException, InterruptedException {
//        if (!binding.ingredientName.getText().toString().isEmpty() && !(binding.spUnit.getSelectedItem() == null) && !binding.ingredientWeight.getText().toString().isEmpty() && !binding.ingredientTotalPrice.getText().toString().isEmpty()) {
//            double ingredient_unit_price = Double.parseDouble(binding.ingredientTotalPrice.getText().toString()) / Double.parseDouble(binding.ingredientWeight.getText().toString());
//
//            ingredientDatabaseHelper.updateIngredientData(ingredientTable,
//                    binding.ingredientName.getText().toString(),
//                    Integer.parseInt(binding.ingredientWeight.getText().toString()),
//                    binding.spUnit.getSelectedItem().toString(),
//                    Integer.parseInt(binding.ingredientTotalPrice.getText().toString()),
//                    Math.ceil(ingredient_unit_price * 100) / 100.0
//            );
//
//            ingredientTableList = ingredientDatabaseHelper.
//
//            menuIngredientTableList = menuIngredientDatabaseHelper.getMenuIngredientsByIngredientId(ingredientTable.getId());
//
//            List<MenuIngredientTable> menuIngredientTables;
//
//            for(int i=0; i<menuIngredientTableList.size(); i++){
//                menuIngredientTables = menuIngredientDatabaseHelper.getMenuIngredientsByMenuId(menuIngredientTableList.get(i).getMenu_id());
//
//
//            }
//
//
//            //            menuDatabaseHelper.updateMenuData(menuTable, binding.menuName.getText().toString(), calculateMenuPrice(menuIngredientArrayList));
////             ingredientAndMenuIngredientsList = [];
//
////            try {
////                Log.i("tag", "ingredientTable.getId()");
////                Log.i("tag", String.valueOf(ingredientTable.getId()));
////
////                ingredientAndMenuIngredientsList = menuIngredientDatabaseHelper.getIngredientsInfoByIngredientId(ingredientTable.getId());
////
////                for (int i = 0; i < ingredientAndMenuIngredientsList.size(); i++) {
////                    Log.i("tag", ingredientAndMenuIngredientsList.get(i).getIngredientTable().toString());
////                    Log.i("tag", ingredientAndMenuIngredientsList.get(i).getMenuIngredientTable().toString());
////                }
////
////            } catch (ExecutionException e) {
////                e.printStackTrace();
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//            finish();
//        }
//    }
//
//    private IngredientTable ingredientIdToEntity(int ingredient_id){
//        for(int i=0; i<ingredientList.size(); i++){
//            if(ingredientList.get(i).getId() == ingredient_id){
//                return ingredientList.get(i);
//            }
//        }
//        return null;
//    }
//}