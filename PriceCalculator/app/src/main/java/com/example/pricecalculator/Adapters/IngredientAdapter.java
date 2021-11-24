package com.example.pricecalculator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricecalculator.Databases.IngredientDatabase;
import com.example.pricecalculator.Databases.IngredientTable;
import com.example.pricecalculator.R;

import java.util.List;

//public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
//
//}

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{

    Context context;
    List<IngredientTable> ingredientTableList;
    View view;

    public IngredientAdapter(Context context, List<IngredientTable> ingredientTableList){
        this.context = context;
        this.ingredientTableList = ingredientTableList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView ingredient_name, ingredient_weight, ingredient_unit, ingredient_total_price, ingredient_unit_price;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            ingredient_name = itemView.findViewById(R.id.ingredient_name);
            ingredient_weight = itemView.findViewById(R.id.ingredient_weight);
            ingredient_total_price = itemView.findViewById(R.id.ingredient_total_price);
            ingredient_unit_price = itemView.findViewById(R.id.ingredient_price_per_unit);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rv_ingredient_table_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(ingredientTableList != null && ingredientTableList.size() > 0){
            IngredientTable ingredientTable = ingredientTableList.get(position);
            String ingredient_weight = String.valueOf(ingredientTable.getIngredient_weight()) + String.valueOf(ingredientTable.getIngredient_unit());
            String ingredient_total_price = String.valueOf(ingredientTable.getIngredient_total_price()) + "원";
            String ingredient_unit_price = String.valueOf(ingredientTable.getIngredient_unit_price()) + "원/" + String.valueOf(ingredientTable.getIngredient_unit());

            holder.ingredient_name.setText(String.valueOf(ingredientTable.getIngredient_name()));
            holder.ingredient_weight.setText(ingredient_weight);
            holder.ingredient_total_price.setText(ingredient_total_price);
            holder.ingredient_unit_price.setText(ingredient_unit_price);
        }
    }

    @Override
    public int getItemCount() {
        return ingredientTableList.size();
    }
}