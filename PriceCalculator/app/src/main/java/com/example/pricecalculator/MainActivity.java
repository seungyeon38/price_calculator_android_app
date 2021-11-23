package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;

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
        setContentView(view);

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setTitle("원알팔");
    }

    public void showGradients(View view) {
    }

    public void showMenus(View view) {
    }

    public void addMenu(View view) {
    }
}