package com.ph48845.datn_qlnh_rmis.data.respository;


import androidx.lifecycle.MutableLiveData;

import com.ph48845.datn_qlnh_rmis.data.model.Order;
import com.ph48845.datn_qlnh_rmis.data.model.Table;
import com.ph48845.datn_qlnh_rmis.data.remote.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {
    private final ApiService apiService;

    public OrderRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getTables(MutableLiveData<List<Table>> liveData) {
        apiService.getTables().enqueue(new Callback<List<Table>>() {
            @Override
            public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                if (response.isSuccessful() && response.body() != null)
                    liveData.setValue(response.body());
                else
                    liveData.setValue(null);
            }
            @Override
            public void onFailure(Call<List<Table>> call, Throwable t) {
                liveData.setValue(null);
            }
        });
    }

    public void createOrder(Order order, MutableLiveData<Boolean> result) {
        apiService.createOrder(order).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                result.postValue(response.isSuccessful());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.postValue(false);
            }
        });
    }
}