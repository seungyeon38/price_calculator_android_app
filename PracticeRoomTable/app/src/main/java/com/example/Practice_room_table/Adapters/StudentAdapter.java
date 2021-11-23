package com.example.Practice_room_table.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Practice_room_table.Databases.StudentsTable;
import com.example.Practice_room_table.R;

import java.util.List;

// RecyclerView Adapter 구현
// RecyclerView에서는 반드시 개발자가 어댑터를 직접 구현해야 한다.

// RecyclerView.ViewHolder를 상속받아서 Adapter 내에서 ViewHolder를 위한 class를 구현함.
// 이렇게 작성한 ViewHolder는 Adapter의 onCreateViewHolder()와 onBindViewHolder() 메서드를 통해 각각 생성 및 바인딩(데이터 표시)되어 화면에 표시됩니다
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    Context context;
    List<StudentsTable> studentsTableList;
    View view;

    // 아이템 뷰를 저장하는 ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView roll_no_tv, name_tv, standard_tv;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            roll_no_tv = itemView.findViewById(R.id.roll_no_tv);
            name_tv = itemView.findViewById(R.id.name_tv);
            standard_tv = itemView.findViewById(R.id.standard_tv);
        }
    }

//    해당 뷰홀더에서는 아이템에 표시될 텍스트뷰(textView1)에 대한 참조를 가지도록 만들었습니다.
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView textView1 ;
//
//        ViewHolder(View itemView) {
//            super(itemView) ;
//
//            // 뷰 객체에 대한 참조. (hold strong reference)
//            textView1 = itemView.findViewById(R.id.text1) ;
//        }
//    }

    // 생성자에서 context와 studentsTableList를 전달받음.
    public StudentAdapter(Context context, List<StudentsTable> studentsTableList){
        this.context = context;
        this.studentsTableList = studentsTableList;
    }

    // viewType 형태의 아이템 뷰를 위한 ViewHolder 객체 생성
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rv_layout, parent, false);
        return new ViewHolder(view);
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(studentsTableList != null && studentsTableList.size() > 0){
            StudentsTable studentsTable = studentsTableList.get(position);
            holder.roll_no_tv.setText(String.valueOf(studentsTable.getId()));
            holder.name_tv.setText(String.valueOf(studentsTable.getStu_name()));
            holder.standard_tv.setText(String.valueOf(studentsTable.getStu_standard()));
        }
    }

    // 전체 아이템 갯수 리턴.
    @Override
    public int getItemCount() {
        return studentsTableList.size();
    }

}
