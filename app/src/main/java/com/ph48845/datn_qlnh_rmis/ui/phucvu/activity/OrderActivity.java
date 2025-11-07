package com.ph48845.datn_qlnh_rmis.ui.phucvu.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.ph48845.datn_qlnh_rmis.data.model.MenuItem;
import com.ph48845.datn_qlnh_rmis.data.model.Table;
import com.ph48845.datn_qlnh_rmis.ui.phucvu.adapter.MenuAdapter;
import com.ph48845.datn_qlnh_rmis.ui.phucvu.viewmodel.PhucVuViewModel;
import com.ph48845.datn_qlnh_rmis.R;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    private PhucVuViewModel viewModel;
    private RecyclerView recyclerMenu;
    private Spinner spinnerTable;
    private Button btnCreateOrder;
    private MenuAdapter menuAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerMenu = findViewById(R.id.recycler_menu);
        spinnerTable = findViewById(R.id.spinner_table);
        btnCreateOrder = findViewById(R.id.btn_create_order);

        viewModel = new ViewModelProvider(this).get(PhucVuViewModel.class);

        // TODO: Khởi tạo trình hiển thị bàn (Spinner), danh sách món (RecyclerView)
        // Lấy dữ liệu bàn, thực đơn từ ViewModel/UseCase

        viewModel.menuItems.observe(this, menuItems -> {
            if (menuAdapter == null) {
                menuAdapter = new MenuAdapter(menuItems, (item, quantity) -> {
                    // Xử lý khi số lượng món thay đổi
                });
                recyclerMenu.setAdapter(menuAdapter);
            } else {
                menuAdapter.notifyDataSetChanged();
            }
        });

        btnCreateOrder.setOnClickListener(v -> {
            // Lấy bàn đang chọn từ spinnerTable
            // Lấy các món số lượng > 0
            List<MenuItem> selectedItems = menuAdapter.getMenuItems();
            // TODO: Gọi CreateOrderUseCase để tạo đơn mới (gửi về bếp)
            Toast.makeText(this, "Đã gửi đơn hàng thành công!", Toast.LENGTH_SHORT).show();
        });
    }
}