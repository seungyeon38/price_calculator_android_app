package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.pricecalculator.Adapters.IngredientAdapter;
import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.Helper.IngredientDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityShowIngredientsBinding;

import java.util.List;

public class ShowIngredients extends AppCompatActivity {

    private ActivityShowIngredientsBinding binding;

//    RecyclerView recyclerView;
    IngredientAdapter ingredientAdapter;
    IngredientDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowIngredientsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = IngredientDatabaseHelper.getInstance(this);
        helper.getAllIngredientsData();
        // toolbar 뒤로가기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setRecyclerView(List<IngredientTable> ingredientTableList){
        binding.rvGradientTable.setLayoutManager(new LinearLayoutManager(this));
        ingredientAdapter = new IngredientAdapter(this, ingredientTableList);
        binding.rvGradientTable.setAdapter(ingredientAdapter);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        helper.getAllIngredientsData();
//        helper.getAllIngredientsData();
//        reloadDATABASE(); // addIngredient를 하고나서 돌아올 때 동작
    }

    public void addGradient(View view) {
        startActivity(new Intent(this, AddIngredient.class));
    }
}