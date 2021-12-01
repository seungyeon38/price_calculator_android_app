package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.pricecalculator.Helper.IngredientDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityAddIngredientBinding;


public class AddIngredient extends AppCompatActivity {

    private ActivityAddIngredientBinding binding;

    IngredientDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddIngredientBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = IngredientDatabaseHelper.getInstance(this);

        // toolbar 뒤로가기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // 일단 핵심기능부터 구현하고나서 생각해보기
//        // textwatcher
//        TextWatcher textWatcher = new TextWatcher() {
//            // text가 변경되기 바로 이전에 동작
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//            // text가 변경되는 동시에 동작
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//            // text가 변경된 이후에 동작
//            @Override
//            public void afterTextChanged(Editable editable) {
//                if(editable != null && !editable.toString().equals("")) {
//                    binding.pricePerUnit.setText(editable);
//                }
//            }
//        };

//        binding.ingredientWeight.addTextChangedListener(textWatcher);
//        binding.ingredientTotalPrice.addTextChangedListener(textWatcher);


        ArrayAdapter unit_adapter = ArrayAdapter.createFromResource(this, R.array.unit_array, android.R.layout.simple_spinner_dropdown_item);

        unit_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spUnit.setAdapter(unit_adapter);

//        binding.spUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            // 이 오버라이드 메소드에서 position 몇 번째 값이 클릭됐는지 알 수 있다.
//            // getItemAtPosition(position)를 통해서 해당 값을 받아올 수 있다.
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long i) {
//
//            }
//
//            @Override
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
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

    public void addIngredient(View view) {
        if(!binding.ingredientName.getText().toString().isEmpty() && !(binding.spUnit.getSelectedItem() == null) && !binding.ingredientWeight.getText().toString().isEmpty() && !binding.ingredientTotalPrice.getText().toString().isEmpty()){
            double ingredient_unit_price = Double.parseDouble(binding.ingredientTotalPrice.getText().toString())/Double.parseDouble(binding.ingredientWeight.getText().toString());

            helper.addIngredient(binding.ingredientName.getText().toString(),
                Integer.parseInt(binding.ingredientWeight.getText().toString()),
                binding.spUnit.getSelectedItem().toString(),
                Integer.parseInt(binding.ingredientTotalPrice.getText().toString()),
                ingredient_unit_price
            );
        }
        finish();
    }
}