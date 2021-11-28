package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pricecalculator.Adapters.MenuIngredientAdapter;
import com.example.pricecalculator.ArrayData.MenuIngredient;
import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.Helper.IngredientDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityAddMenuBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddMenu extends AppCompatActivity {

    private ActivityAddMenuBinding binding;
    ArrayList<MenuIngredient> menuIngredientArrayList;
    List<IngredientTable> ingredientList;
    List<String> ingredient_name_list;
    MenuIngredientAdapter menuIngredientAdapter;

    IngredientDatabaseHelper helper;

    Dialog addMenuIngredientDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMenuBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // toolbar 뒤로가기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        binding.rvMenuIngredientTable.setLayoutManager(new LinearLayoutManager(this));
        menuIngredientArrayList = new ArrayList<>();
//        menuIngredientAdapter = new MenuIngredientAdapter(this, menuIngredientArrayList);
//        binding.rvMenuIngredientTable.setAdapter(menuIngredientAdapter);

        helper = IngredientDatabaseHelper.getInstance(this);

        try {
            ingredientList = helper.getAllIngredientsData();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ingredient_name_list = new LinkedList<>();

        for(int i=0; i<ingredientList.size(); i++){
            ingredient_name_list.add(ingredientList.get(i).getIngredient_name());
        }

    }

    public void setRecyclerView(ArrayList<MenuIngredient> menuIngredientArrayList){
        binding.rvMenuIngredientTable.setLayoutManager(new LinearLayoutManager(this));
//        menuIngredientArrayList = new ArrayList<>();
        menuIngredientAdapter = new MenuIngredientAdapter(this, menuIngredientArrayList);
        binding.rvMenuIngredientTable.setAdapter(menuIngredientAdapter);
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
        showDialog();
    }

    public void addMenu(View view) {
        if(!(menuIngredientArrayList.size() > 0) && !binding.menuName.getText().toString().isEmpty()){


        }
//        if(!binding.ingredientName.getText().toString().isEmpty() && !(binding.spUnit.getSelectedItem() == null) && !binding.ingredientWeight.getText().toString().isEmpty() && !binding.ingredientTotalPrice.getText().toString().isEmpty()){
//            double ingredient_unit_price = Double.parseDouble(binding.ingredientTotalPrice.getText().toString())/Double.parseDouble(binding.ingredientWeight.getText().toString());
//
//            helper.addIngredient(binding.ingredientName.getText().toString(),
//                    Integer.parseInt(binding.ingredientWeight.getText().toString()),
//                    binding.spUnit.getSelectedItem().toString(),
//                    Integer.parseInt(binding.ingredientTotalPrice.getText().toString()),
//                    ingredient_unit_price
//            );
//        }
//        finish();
    }

    public void showDialog(){
        if(ingredient_name_list.size() > 0 && ingredient_name_list != null){
            Dialog addMenuIngredientDialog= new Dialog(AddMenu.this);       // Dialog 초기화
//        addMenuIngredientDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
//        addMenuIngredientDialog.setContentView(R.layout.dialog_add_menu_ingredient); // xml 레이아웃 파일과 연결

            View mView = getLayoutInflater().inflate(R.layout.dialog_add_menu_ingredient,null);

            Spinner spIngredients = (Spinner) mView.findViewById(R.id.sp_ingredients);
            EditText menuIngredientWeight = (EditText) mView.findViewById(R.id.menu_ingredient_weight);

            ArrayAdapter<String> ingredientAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ingredient_name_list);

            ingredientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spIngredients.setAdapter(ingredientAdapter);

            addMenuIngredientDialog.setContentView(mView);
            addMenuIngredientDialog.show(); // 다이얼로그 띄우기

            /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */

            // 위젯 연결 방식은 각자 취향대로~
            // '아래 아니오 버튼'처럼 일반적인 방법대로 연결하면 재사용에 용이하고,
            // '아래 네 버튼'처럼 바로 연결하면 일회성으로 사용하기 편함.
            // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

            // 취소 버튼
            Button noBtn = addMenuIngredientDialog.findViewById(R.id.noBtn);
            noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addMenuIngredientDialog.dismiss(); // 다이얼로그 닫기
                }
            });
            // 확인 버튼
            addMenuIngredientDialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!(spIngredients.getSelectedItem() == null) && !menuIngredientWeight.getText().toString().isEmpty()){
                        IngredientTable ingredient = ingredientNameToEntity(spIngredients.getSelectedItem().toString());
                        MenuIngredient newMenuIngredient = new MenuIngredient(spIngredients.getSelectedItem().toString(), ingredient.getIngredient_id(), Double.parseDouble(menuIngredientWeight.getText().toString()), ingredient.getIngredient_unit(), calculateIngredientPrice(ingredient.getIngredient_id(), Double.parseDouble(menuIngredientWeight.getText().toString())));
                        menuIngredientArrayList.add(newMenuIngredient);
                        setRecyclerView(menuIngredientArrayList);
                        addMenuIngredientDialog.dismiss(); // 다이얼로그 닫기
                    }
                    else{
                        // 정보 다 입력하지 않았을 때 오류처리해야함
                        addMenuIngredientDialog.dismiss(); // 다이얼로그 닫기
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "등록된 재료가 없습니다. 먼저 재료를 등록해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private IngredientTable ingredientNameToEntity(String ingredient_name){
        for(int i=0; i<ingredientList.size(); i++){
            if(ingredient_name == ingredientList.get(i).getIngredient_name()){
                return ingredientList.get(i);
            }
        }
        return null;
    }

    private double calculateIngredientPrice(int ingredient_id, double menu_ingredient_weight){
        for(int i=0; i<ingredientList.size(); i++){
            if(ingredient_id == ingredientList.get(i).getIngredient_id()){
                return menu_ingredient_weight * ingredientList.get(i).getIngredient_unit_price();
            }
        }
        return -1;
    }
}