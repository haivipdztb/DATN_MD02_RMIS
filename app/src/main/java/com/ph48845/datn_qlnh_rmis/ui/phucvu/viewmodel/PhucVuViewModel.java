package com.ph48845.datn_qlnh_rmis.ui.phucvu.viewmodel;



import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.ph48845.datn_qlnh_rmis.data.model.MenuItem;
import com.ph48845.datn_qlnh_rmis.data.model.Table;
import com.ph48845.datn_qlnh_rmis.data.model.Order;
import com.ph48845.datn_qlnh_rmis.data.respository.OrderRepository;


import java.util.List;

public class PhucVuViewModel extends ViewModel {
    public final MutableLiveData<List<MenuItem>> menuItems = new MutableLiveData<>();
    public final MutableLiveData<List<Table>> tables = new MutableLiveData<>();
    public final MutableLiveData<Boolean> orderResult = new MutableLiveData<>();

    private final OrderRepository repository;

    public PhucVuViewModel(OrderRepository repository) {
        this.repository = repository;
        loadTables();
        loadMenu();
    }

    public void loadTables() { repository.getTables(tables); }
    public void loadMenu() { repository.getMenu(menuItems); }
    public void createOrder(Order order) { repository.createOrder(order, orderResult); }
}