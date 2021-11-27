package com.example.pricecalculator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricecalculator.ArrayData.MenuIngredient;
import com.example.pricecalculator.R;

import java.util.ArrayList;
import java.util.List;

public class MenuIngredientAdapter extends RecyclerView.Adapter<MenuIngredientAdapter.ViewHolder> {
    Context context;
    View view;

    private ArrayList<MenuIngredient> menuIngredientArrayList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView menu_ingredient_name, menu_ingredient_weight;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            this.menu_ingredient_name = itemView.findViewById(R.id.menu_ingredient_name);
            this.menu_ingredient_weight = itemView.findViewById(R.id.menu_ingredient_weight);
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
            holder.menu_ingredient_name.setText(menuIngredientArrayList.get(position).getMenu_ingredient_name());
            holder.menu_ingredient_weight.setText(String.valueOf(menuIngredientArrayList.get(position).getMenu_ingredient_weight()));
        }
    }

    @Override
    public int getItemCount() {
        return menuIngredientArrayList.size();
//        return (null != menuIngredientArrayList ? menuIngredientArrayList.size() : 0);
    }
}
