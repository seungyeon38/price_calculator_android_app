package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.pricecalculator.Helper.MenuDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityAddIngredientBinding;
import com.example.pricecalculator.databinding.ActivityAddMenuBinding;

public class AddMenu extends AppCompatActivity {

    private ActivityAddMenuBinding binding;
    MenuDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = MenuDatabaseHelper.getInstance(this);

        // toolbar 뒤로가기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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


    public void addMenuIngredient(View view) {
    }

    public void addMenu(View view) {
    }
}