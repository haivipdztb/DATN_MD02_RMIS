package com.ph48845.datn_qlnh_rmis.ui.phucvu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ph48845.datn_qlnh_rmis.data.model.MenuItem;
import com.ph48845.datn_qlnh_rmis.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    public interface OnQuantityChangedListener {
        void onQuantityChanged(MenuItem item, int quantity);
    }

    private List<MenuItem> menuItems;
    private final OnQuantityChangedListener listener;

    public MenuAdapter(List<MenuItem> items, OnQuantityChangedListener listener) {
        this.menuItems = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        holder.tvName.setText(item.getName());
        holder.tvPrice.setText(item.getPrice() + " VNĐ");
        holder.tvQuantity.setText(String.valueOf(item.getQuantity()));

        holder.btnAdd.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyItemChanged(position);
            listener.onQuantityChanged(item, item.getQuantity());
        });
        holder.btnMinus.setOnClickListener(v -> {
            if (item.getQuantity() > 0) {
                item.setQuantity(item.getQuantity() - 1);
                notifyItemChanged(position);
                listener.onQuantityChanged(item, item.getQuantity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvQuantity;
        Button btnAdd, btnMinus;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvMenuName);
            tvPrice = itemView.findViewById(R.id.tvMenuPrice);
            tvQuantity = itemView.findViewById(R.id.tvMenuQuantity);
            btnAdd = itemView.findViewById(R.id.btnMenuAdd);
            btnMinus = itemView.findViewById(R.id.btnMenuMinus);
        }
    }
}