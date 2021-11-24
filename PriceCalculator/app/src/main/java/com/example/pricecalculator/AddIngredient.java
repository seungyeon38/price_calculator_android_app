package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pricecalculator.databinding.ActivityAddIngredientBinding;


public class AddIngredient extends AppCompatActivity {

    private ActivityAddIngredientBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddIngredientBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);






        ArrayAdapter unit_adapter = ArrayAdapter.createFromResource(this, R.array.unit_array, android.R.layout.simple_spinner_dropdown_item);

        unit_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spUnit.setAdapter(unit_adapter);

        binding.spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // 이 오버라이드 메소드에서 position 몇 번째 값이 클릭됐는지 알 수 있다.
            // getItemAtPosition(position)를 통해서 해당 값을 받아올 수 있다.
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long i) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addIngredient(View view) {
    }
}