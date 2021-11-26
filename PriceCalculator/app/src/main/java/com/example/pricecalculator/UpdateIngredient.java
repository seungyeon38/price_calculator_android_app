package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.Helper.IngredientDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityUpdateIngredientBinding;

public class UpdateIngredient extends AppCompatActivity {

    private ActivityUpdateIngredientBinding binding;

    IngredientTable ingredientTable;
    IngredientDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateIngredientBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = IngredientDatabaseHelper.getInstance(this);

        // toolbar 뒤로가기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter unit_adapter = ArrayAdapter.createFromResource(this, R.array.unit_array, android.R.layout.simple_spinner_dropdown_item);

        unit_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spUnit.setAdapter(unit_adapter);


        if(getIntent() != null){
            ingredientTable = (IngredientTable) getIntent().getSerializableExtra("ingredient_table");
            binding.ingredientName.setText(ingredientTable.getIngredient_name());
            binding.ingredientWeight.setText(Integer.toString(ingredientTable.getIngredient_weight()));
            binding.ingredientTotalPrice.setText(Integer.toString(ingredientTable.getIngredient_total_price()));
            binding.spUnit.setSelection(getIndex(binding.spUnit, ingredientTable.getIngredient_unit()));
        }
    }

    // private method of your class
    // string에 해당하는 것의 spinner에서의 position을 return해줌
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }
        return 0;
    }

    //toolbar의 back키 눌렀을 때 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateIngredient(View view) {
        if(!binding.ingredientName.getText().toString().isEmpty() && !(binding.spUnit.getSelectedItem() == null) && !binding.ingredientWeight.getText().toString().isEmpty() && !binding.ingredientTotalPrice.getText().toString().isEmpty()){
            double ingredient_unit_price = Double.parseDouble(binding.ingredientTotalPrice.getText().toString())/Double.parseDouble(binding.ingredientWeight.getText().toString());

            helper.updateIngredientData(ingredientTable,
                    binding.ingredientName.getText().toString(),
                    Integer.parseInt(binding.ingredientWeight.getText().toString()),
                    binding.spUnit.getSelectedItem().toString(),
                    Integer.parseInt(binding.ingredientTotalPrice.getText().toString()),
                    Math.ceil(ingredient_unit_price * 100)/100.0
            );
        }
        finish();
    }
}