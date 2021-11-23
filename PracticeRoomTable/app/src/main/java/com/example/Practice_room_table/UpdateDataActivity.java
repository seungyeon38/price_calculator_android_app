package com.example.Practice_room_table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Practice_room_table.Databases.StudentsTable;
import com.example.Practice_room_table.Helper.DatabaseHelper;
import com.example.Practice_room_table.databinding.ActivityMainBinding;
import com.example.Practice_room_table.databinding.ActivityUpdateDataBinding;

public class UpdateDataActivity extends AppCompatActivity {

    private ActivityUpdateDataBinding binding;

    StudentsTable studentsTable;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateDataBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = DatabaseHelper.getInstance(this);

        if(getIntent() != null){
            studentsTable = (StudentsTable)getIntent().getSerializableExtra("stu_table");

            binding.rollNoTv.setText("Roll No: " + studentsTable.getId());
            binding.nameTv.setText(studentsTable.getStu_name());
            binding.standardTv.setText(studentsTable.getStu_standard());
        }
    }

    public void updateData(View view) {
        if(!binding.nameTv.getText().toString().isEmpty() && !binding.standardTv.getText().toString().isEmpty())
            helper.updateData(studentsTable, binding.nameTv.getText().toString(), binding.standardTv.getText().toString());
    }

    public void showData(View view) {
        startActivity(new Intent(this, DataViewerActivity.class));
    }
}