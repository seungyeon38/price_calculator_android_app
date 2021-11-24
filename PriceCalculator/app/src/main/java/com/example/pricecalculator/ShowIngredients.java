package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pricecalculator.databinding.ActivityShowIngredientsBinding;

public class ShowIngredients extends AppCompatActivity {

    private ActivityShowIngredientsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowIngredientsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    public void addGradient(View view) {
        startActivity(new Intent(this, AddIngredient.class));
    }
}