package com.example.pricecalculator.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricecalculator.ArrayData.MenuIngredient;
import com.example.pricecalculator.R;
import com.example.pricecalculator.UpdateIngredient;

import java.util.ArrayList;
import java.util.List;

public class MenuIngredientAdapter extends RecyclerView.Adapter<MenuIngredientAdapter.ViewHolder> {
    Context context;
    View view;

    private ArrayList<MenuIngredient> menuIngredientArrayList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView menu_ingredient_name, menu_ingredient_weight, menu_ingredient_unit, menu_ingredient_price;
        ImageButton menu_ingredient_delete_btn;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            menu_ingredient_name = itemView.findViewById(R.id.menu_ingredient_name);
            menu_ingredient_weight = itemView.findViewById(R.id.menu_ingredient_weight);
            menu_ingredient_unit = itemView.findViewById(R.id.menu_ingredient_unit);
            menu_ingredient_price = itemView.findViewById(R.id.menu_ingredient_price);

            menu_ingredient_delete_btn = itemView.findViewById(R.id.delete_menu_ingredient);
        }
    }

    public MenuIngredientAdapter(Context context, ArrayList<MenuIngredient> list){
        this.context = context;
        this.menuIngredientArrayList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rv_menu_ingredient_table_layout, parent, false);

        return new MenuIngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (menuIngredientArrayList != null && menuIngredientArrayList.size() > 0) {
            String menu_ingredient_weight;
            String menu_ingredient_price;

//            if(menuIngredientArrayList.get(position).getMenu_ingredient_weight() % 1 == 0){
//                menu_ingredient_weight = String.valueOf((int) menuIngredientArrayList.get(position).getMenu_ingredient_weight()) + menuIngredientArrayList.get(position).getMenu_ingredient_unit();
//            }else{
//                double weight = Math.ceil(menuIngredientArrayList.get(position).getMenu_ingredient_weight() * 100)/100.0;
//                menu_ingredient_weight = String.valueOf(weight) + menuIngredientArrayList.get(position).getMenu_ingredient_unit();
//            }

            menu_ingredient_weight = String.valueOf(menuIngredientArrayList.get(position).getMenu_ingredient_weight()) + menuIngredientArrayList.get(position).getMenu_ingredient_unit();

            if(menuIngredientArrayList.get(position).getMenu_ingredient_price() % 1 == 0){
                menu_ingredient_price = String.valueOf((int) menuIngredientArrayList.get(position).getMenu_ingredient_price()) + "원";
            }else{
                double price = Math.ceil(menuIngredientArrayList.get(position).getMenu_ingredient_price() * 100)/100.0;
                menu_ingredient_price = String.valueOf(price) + "원";
            }
            holder.menu_ingredient_name.setText(menuIngredientArrayList.get(position).getMenu_ingredient_name());
            holder.menu_ingredient_weight.setText(menu_ingredient_weight);
            holder.menu_ingredient_price.setText(menu_ingredient_price);
            holder.menu_ingredient_delete_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    menuIngredientArrayList.remove(position);
                    notifyDataSetChanged();
                    notifyItemRangeChanged(position, menuIngredientArrayList.size());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return menuIngredientArrayList.size();
//        return (null != menuIngredientArrayList ? menuIngredientArrayList.size() : 0);
    }
}
