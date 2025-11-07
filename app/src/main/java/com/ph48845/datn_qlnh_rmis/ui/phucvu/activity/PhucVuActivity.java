package com.ph48845.datn_qlnh_rmis.ui.phucvu.activity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.ph48845.datn_qlnh_rmis.R;
import com.ph48845.datn_qlnh_rmis.data.model.MenuItem;
import com.ph48845.datn_qlnh_rmis.data.model.Table;
import com.ph48845.datn_qlnh_rmis.data.model.Order;

import com.ph48845.datn_qlnh_rmis.data.respository.OrderRepository;
import com.ph48845.datn_qlnh_rmis.ui.phucvu.adapter.MenuAdapter;
import com.ph48845.datn_qlnh_rmis.ui.phucvu.viewmodel.PhucVuViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhucVuActivity extends AppCompatActivity {
    private PhucVuViewModel viewModel;
    private MenuAdapter menuAdapter;
    private RecyclerView recyclerMenu;
    private Spinner spinnerTable;
    private Button btnCreateOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerMenu = findViewById(R.id.recycler_menu);
        spinnerTable = findViewById(R.id.spinner_table);
        btnCreateOrder = findViewById(R.id.btn_create_order);

        // Tạo repository và viewmodel
        OrderRepository repo = new OrderRepository(/* truyền ApiService vào */);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @Override
            public <T extends androidx.lifecycle.ViewModel> T create(Class<T> modelClass) {
                return (T) new PhucVuViewModel(repo);
            }
        }).get(PhucVuViewModel.class);

        // Quan sát dữ liệu bàn
        viewModel.tables.observe(this, tables -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
            for (Table t : tables) adapter.add(t.getName());
            spinnerTable.setAdapter(adapter);
        });

        // Adapter menu món ăn
        viewModel.menuItems.observe(this, items -> {
            menuAdapter = new MenuAdapter(items, (item, qty) -> {});
            recyclerMenu.setAdapter(menuAdapter);
        });

        btnCreateOrder.setOnClickListener(v -> {
            int i = spinnerTable.getSelectedItemPosition();
            Table table = viewModel.tables.getValue().get(i);
            List<MenuItem> selected = new ArrayList<>();
            for (MenuItem m : menuAdapter.getMenuItems()) if (m.getQuantity() > 0) selected.add(m);
            if (selected.isEmpty()) {
                Toast.makeText(this, "Phải chọn ít nhất 1 món!", Toast.LENGTH_SHORT).show(); return;
            }
            Order order = new Order(table.getId(), table.getName(), selected);
            viewModel.createOrder(order);
        });

        viewModel.orderResult.observe(this, result -> {
            if (result != null && result)
                Toast.makeText(this, "Gửi hóa đơn thành công!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Lỗi gửi hóa đơn!", Toast.LENGTH_SHORT).show();
        });
    }
}