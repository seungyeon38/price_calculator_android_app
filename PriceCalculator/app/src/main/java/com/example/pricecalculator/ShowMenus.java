package com.example.pricecalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.pricecalculator.Adapters.MenuAdapter;
import com.example.pricecalculator.Databases.MenuTable;
import com.example.pricecalculator.Helper.MenuDatabaseHelper;
import com.example.pricecalculator.databinding.ActivityShowMenusBinding;

import java.util.List;



public class ShowMenus extends AppCompatActivity {
//    public static Activity _Show_Menu_Activity;

    private ActivityShowMenusBinding binding;

    MenuAdapter menuAdapter;
    MenuDatabaseHelper helper;

    SharedPreferences sf;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowMenusBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        _Show_Menu_Activity = ShowMenus.this;

        // toolbar 뒤로가기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //저장된 값을 불러오기 위해 같은 네임파일을 찾음.
        sf = getSharedPreferences("selling_percentage_sf",MODE_PRIVATE);
        editor = sf.edit();
        //selling_percentage라는 key에 저장된 값이 있는지 확인. 아무값도 들어있지 않으면 "0"를 반환
        String text = sf.getString("selling_percentage","0");
        if(!text.equals("0")){
            binding.sellingPercentage.setText(text);
        }

        helper = MenuDatabaseHelper.getInstance(this);
        helper.showAllMenusData();
    }

    public void setRecyclerView(List<MenuTable> menuTableList){
        int sellingPercentage = 0;
        binding.rvMenuTable.setLayoutManager(new LinearLayoutManager(this));

//        if(!binding.costPercentage.getText().toString().isEmpty()){
//            costPercentage = Double.parseDouble(binding.costPercentage.getText().toString());
//        }
        if(sf != null && !sf.getString("selling_percentage","0").equals("0")){
            sellingPercentage = Integer.parseInt(sf.getString("selling_percentage","0"));
        }
        menuAdapter = new MenuAdapter(this, menuTableList, sellingPercentage);
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
        editor.putString("selling_percentage", binding.sellingPercentage.getText().toString());
        editor.commit();
        helper.showAllMenusData();
    }
}