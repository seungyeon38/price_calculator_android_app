package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.pricecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setTitle("원알팔");

        setContentView(view);

    }

    public void showIngredients(View view) {
        startActivity(new Intent(this, ShowIngredients.class));
    }

    public void showMenus(View view) {
        startActivity(new Intent(this, ShowMenus.class));
    }

    public void addMenu(View view) {
        startActivity(new Intent(this, AddMenu.class));
    }
}