package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
//        helper
//        MenuIngredient newMenuIngredient = new MenuIngredient("원두", 10);
//        menuIngredientArrayList.add(newMenuIngredient);
//        setRecyclerView(menuIngredientArrayList);
    }

    public void addMenu(View view) {

    }

    // dialog01을 디자인하는 함수
    public void showDialog(){
        Dialog addMenuIngredientDialog= new Dialog(AddMenu.this);       // Dialog 초기화
//        addMenuIngredientDialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
//        addMenuIngredientDialog.setContentView(R.layout.dialog_add_menu_ingredient); // xml 레이아웃 파일과 연결

        View mView = getLayoutInflater().inflate(R.layout.dialog_add_menu_ingredient,null);

        Spinner spIngredients = (Spinner) mView.findViewById(R.id.sp_ingredients);

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

        // 아니오 버튼
        Button noBtn = addMenuIngredientDialog.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMenuIngredientDialog.dismiss(); // 다이얼로그 닫기
            }
        });
        // 네 버튼
        addMenuIngredientDialog.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 원하는 기능 구현
                addMenuIngredientDialog.dismiss(); // 다이얼로그 닫기
            }
        });
    }
}