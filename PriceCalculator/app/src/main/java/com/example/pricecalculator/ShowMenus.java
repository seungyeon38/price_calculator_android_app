package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.pricecalculator.Adapters.MenuAdapter;
import com.example.pricecalculator.Databases.MenuTable;
import com.example.pricecalculator.Helper.MenuDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityShowMenusBinding;

import java.util.List;

public class ShowMenus extends AppCompatActivity {

    private ActivityShowMenusBinding binding;

    MenuAdapter menuAdapter;
    MenuDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowMenusBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = MenuDatabaseHelper.getInstance(this);
        helper.showAllMenusData();

        // toolbar 뒤로가기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setRecyclerView(List<MenuTable> menuTableList){
        double costPercentage = 0;
        binding.rvMenuTable.setLayoutManager(new LinearLayoutManager(this));
        if(!binding.costPercentage.getText().toString().isEmpty()){
            costPercentage = Double.parseDouble(binding.costPercentage.getText().toString());
        }

        menuAdapter = new MenuAdapter(this, menuTableList, costPercentage);
        binding.rvMenuTable.setAdapter(menuAdapter);
    }

    //toolbar의 back키 눌렀을 때 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        helper.showAllMenusData();
//        helper.getAllIngredientsData();
//        reloadDATABASE(); // addIngredient를 하고나서 돌아올 때 동작
    }


    public void addMenu(View view) {
        startActivity(new Intent(this, AddMenu.class));
    }

    public void applyCostPercentage(View view) {
        helper.showAllMenusData();
    }
}