package com.example.pricecalculator.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pricecalculator.R;

//public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
//
//}

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{

    Context context;
    View view;

    public IngredientAdapter(Context context){
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView){
            super(itemView);
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

    }

    @Override
    public int getItemCount() {
        return 8;
    }
}