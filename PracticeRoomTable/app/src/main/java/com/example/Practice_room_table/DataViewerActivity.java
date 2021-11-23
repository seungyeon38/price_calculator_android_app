package com.example.Practice_room_table;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.Practice_room_table.Adapters.StudentAdapter;
import com.example.Practice_room_table.Databases.StudentsTable;
import com.example.Practice_room_table.Helper.DatabaseHelper;
import com.example.Practice_room_table.databinding.ActivityDataViewerBinding;

import java.util.List;

public class DataViewerActivity extends AppCompatActivity {

    private ActivityDataViewerBinding binding;
    StudentAdapter studentAdapter;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataViewerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        helper = DatabaseHelper.getInstance(this);

        helper.getAllStudentsData();

//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        studentAdapter = new StudentAdapter(this);
//        binding.recyclerView.setAdapter(studentAdapter);
    }

    public void setRecyclerView(List<StudentsTable> studentsTableList){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdapter = new StudentAdapter(this, studentsTableList);
        binding.recyclerView.setAdapter(studentAdapter);
    }
}