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

import com.example.pricecalculator.Databases.MenuTable;
import com.example.pricecalculator.Helper.MenuDatabaseHelper;
import com.example.pricecalculator.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    Context context;
    List<MenuTable> menuTableList;
    double costPercentage;
    View view;

    MenuDatabaseHelper helper;

    public MenuAdapter(Context context, List<MenuTable> menuTableList, double cost_percentage) {
        this.context = context;
        this.menuTableList = menuTableList;
        this.costPercentage = cost_percentage;

        helper = MenuDatabaseHelper.getInstance(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView menu_name, menu_price, menu_selling_price;
        ImageButton more_iv;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            menu_name = itemView.findViewById(R.id.menu_name);
            menu_price = itemView.findViewById(R.id.menu_price);
            menu_selling_price = itemView.findViewById(R.id.menu_selling_price);

            more_iv = itemView.findViewById(R.id.more_iv);
        }
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rv_menu_table_layout, parent, false);

        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(menuTableList != null && menuTableList.size() > 0){
            MenuTable menuTable = menuTableList.get(position);

            String menu_price = String.valueOf(menuTable.getMenu_price()) + "원";
            String menu_selling_price = "";

            if(costPercentage != 0){
                menu_selling_price = String.valueOf(menuTable.getMenu_price()/costPercentage * 100) + "원";
            }

            holder.menu_name.setText(menuTable.getMenu_name());
            holder.menu_price.setText(menu_price);
            holder.menu_selling_price.setText(menu_selling_price);

            holder.more_iv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, holder.more_iv);
                    popupMenu.getMenuInflater().inflate(R.menu.dropdown_update_delete, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.update_id:
//                                    context.startActivity(new Intent(context, UpdateMenu.class)
//                                            .putExtra("menu_table", menuTable));
                                    break;
                                case R.id.delete_id:

                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return menuTableList.size();
    }
}
