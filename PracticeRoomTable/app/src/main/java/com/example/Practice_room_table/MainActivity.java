package com.example.Practice_room_table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Practice_room_table.Helper.DatabaseHelper;
import com.example.Practice_room_table.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = DatabaseHelper.getInstance(this);
    }

    public void submitData(View view){
        if(!binding.nameTv.getText().toString().isEmpty() && !binding.standardTv.getText().toString().isEmpty()){
            helper.addNewStudent(binding.nameTv.getText().toString(), binding.standardTv.getText().toString());
        }
//        else{
////            Toast.makeText(this, "정보를 모두 입력해주세요.");
//            Toast.makeText(this, "정보를 모두 입력해주세요.", Toast.LENGTH_LONG);
//        }
    }

    public void showData(View view){
        startActivity(new Intent(this, DataViewerActivity.class));
    }
}