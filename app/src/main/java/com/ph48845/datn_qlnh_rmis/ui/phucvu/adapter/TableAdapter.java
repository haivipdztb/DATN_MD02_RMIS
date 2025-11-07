package com.ph48845.datn_qlnh_rmis.ui.phucvu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ph48845.datn_qlnh_rmis.data.model.Table;
import com.ph48845.datn_qlnh_rmis.R;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {

    private List<Table> tables;
    private final OnTableClickListener listener;

    public interface OnTableClickListener {
        void onTableClick(Table table);
    }

    public TableAdapter(List<Table> tables, OnTableClickListener listener) {
        this.tables = tables;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TableViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        Table table = tables.get(position);
        holder.tvTableName.setText("Bàn " + table.getName());
        holder.tvTableStatus.setText(table.getStatus());

        holder.itemView.setOnClickListener(v -> listener.onTableClick(table));
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder {
        TextView tvTableName, tvTableStatus;

        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTableName = itemView.findViewById(R.id.tvTableName);
            tvTableStatus = itemView.findViewById(R.id.tvTableStatus);
        }
    }
}