package com.example.pricecalculator.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.Helper.IngredientDatabaseHelper;
import com.example.pricecalculator.R;

import java.util.List;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{

    Context context;
    List<IngredientTable> ingredientTableList;
    View view;

    IngredientDatabaseHelper helper;

    public IngredientAdapter(Context context, List<IngredientTable> ingredientTableList){
        this.context = context;
        this.ingredientTableList = ingredientTableList;

        helper = IngredientDatabaseHelper.getInstance(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredient_name, ingredient_weight, ingredient_total_price, ingredient_unit_price;
        ImageButton more_iv;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_weight = itemView.findViewById(R.id.ingredient_weight);
            ingredient_total_price = itemView.findViewById(R.id.ingredient_total_price);
            ingredient_unit_price = itemView.findViewById(R.id.ingredient_price_per_unit);

            more_iv = itemView.findViewById(R.id.more_iv);
        }
    }

    // ViewHolder가 생성되는 함수
    // 맨 처음 화면에 보이는 전체 리스트 목록이 딱 10개라면, 위아래 버퍼를 생각해서 13~15개 정도의 뷰 객체가 생성된다.
    // 정확하게 말하자면 뷰 객체를 담고 있는 ViewHolder가 생성되는 것이다.
    // 그래서 onCreateViewHolder함수는 딱 13~15번 정도만 호출되고 더 이상 호출되지 않는다.

    // return되는 곳에서 MyViewHolder의 생성자에 view 객체를 넘겨주는데,
    // 이 view객체는 아까 사진에서 본 한개의 채팅방 목록이 디자인 되어있는 레이아웃이다.
    // 즉 viewHolder는 그 레이아웃을 인자로 받아서 기억하고 있는 것이다.
    // 이제는 계속해서 재사용되는 뷰 홀더(레이아웃)들에 데이터를 바인딩 해주는 작업만 남았다.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rv_ingredient_table_layout, parent, false);

        return new ViewHolder(view);
    }

    // 생성된 뷰홀더에 데이터를 바인딩 해주는 함수
    // 예를 들어 데이터가 스크롤 되어서 맨 위에있던 뷰 홀더(레이아웃) 객체가 맨 아래로 이동한다면,
    // 그 레이아웃은 재사용 하되 데이터는 새롭게 바뀔 것이다.
    // 고맙게도 아래에서 새롭게 보여질 데이터의 인덱스 값이 position이라는 이름으로 사용가능하다.

    // 스크롤을 해서 데이터 바인딩이 새롭게 필요할 때 마다 호출된다.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(ingredientTableList != null && ingredientTableList.size() > 0){
            IngredientTable ingredientTable = ingredientTableList.get(position);
            String ingredient_weight = String.valueOf(ingredientTable.getIngredient_weight()) + String.valueOf(ingredientTable.getIngredient_unit());
            String ingredient_total_price = String.valueOf(ingredientTable.getIngredient_total_price()) + "원";
            String ingredient_unit_price;

            if(ingredientTable.getIngredient_unit_price() % 1 == 0){
                ingredient_unit_price = String.valueOf((int) ingredientTable.getIngredient_unit_price()) + "원/" + String.valueOf(ingredientTable.getIngredient_unit());
            }else{
                double price = Math.ceil(ingredientTable.getIngredient_unit_price() * 100)/100.0;
                ingredient_unit_price = String.valueOf(price) + "원/" + String.valueOf(ingredientTable.getIngredient_unit());
            }
            holder.ingredient_name.setText(String.valueOf(ingredientTable.getIngredient_name()));
            holder.ingredient_weight.setText(ingredient_weight);
            holder.ingredient_total_price.setText(ingredient_total_price);
            holder.ingredient_unit_price.setText(ingredient_unit_price);

            holder.more_iv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    helper.deleteIngredientData(ingredientTable);
                    ingredientTableList.remove(position);
                    notifyDataSetChanged();
                    notifyItemRangeChanged(position, ingredientTableList.size());
                }


//                @Override
//                public void onClick(View view) {
//                    PopupMenu popupMenu = new PopupMenu(context, holder.more_iv);
//                    popupMenu.getMenuInflater().inflate(R.menu.dropdown_update_delete, popupMenu.getMenu());
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem menuItem) {
//                            switch (menuItem.getItemId()){
//                                case R.id.update_id:
//                                    context.startActivity(new Intent(context, UpdateIngredient.class)
//                                    .putExtra("ingredient_table", ingredientTable));
//                                    break;
//                                case R.id.delete_id:
//                                    helper.deleteIngredientData(ingredientTable);
//                                    ingredientTableList.remove(position);
//                                    notifyDataSetChanged();
//                                    notifyItemRangeChanged(position, ingredientTableList.size());
//                                    break;
//                            }
//                            return false;
//                        }
//                    });
//                    popupMenu.show();
//                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return ingredientTableList.size();
    }
}