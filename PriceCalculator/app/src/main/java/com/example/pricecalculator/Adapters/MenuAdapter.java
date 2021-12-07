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
import com.example.pricecalculator.Helper.MenuIngredientDatabaseHelper;
import com.example.pricecalculator.R;
import com.example.pricecalculator.UpdateMenu;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    Context context;
    List<MenuTable> menuTableList;
    double costPercentage;
    View view;

    MenuDatabaseHelper menu_helper;
    MenuIngredientDatabaseHelper menu_ingredient_helper;

    public MenuAdapter(Context context, List<MenuTable> menuTableList, int cost_percentage) {
        this.context = context;
        this.menuTableList = menuTableList;
        this.costPercentage = cost_percentage;

        menu_helper = MenuDatabaseHelper.getInstance(context);
        menu_ingredient_helper = MenuIngredientDatabaseHelper.getInstance(context);
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
//            String menu_price = String.valueOf(menuTable.getMenu_price()) + "원";
            int menu_price;
            String menu_price_str;
            String menu_selling_price = "";

//            if(menuTable.getMenu_price() % 1 == 0){
//                menu_price = (int)menuTable.getMenu_price();
//            }else{
//                menu_price = (int)Math.ceil(menuTable.getMenu_price());
//            }

            menu_price = (int)Math.ceil(menuTable.getMenu_price());
            menu_price_str = String.valueOf(menu_price) + "원";

            if(costPercentage != 0){
                double cost_percentage = menu_price/costPercentage * 100;

                menu_selling_price = String.valueOf((int)Math.ceil(cost_percentage)) + "원";
            }

            holder.menu_name.setText(menuTable.getMenu_name());
            holder.menu_price.setText(menu_price_str);
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
                                    context.startActivity(new Intent(context, UpdateMenu.class)
                                            .putExtra("menu_table", menuTable));
                                    break;
                                case R.id.delete_id:
                                    menu_helper.deleteMenuData(menuTable);
                                    menu_ingredient_helper.deleteMenuIngredientDataByMenuID(menuTable.getId());
                                    menuTableList.remove(position);
                                    notifyDataSetChanged();
                                    notifyItemRangeChanged(position, menuTableList.size());

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
