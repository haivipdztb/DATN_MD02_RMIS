package com.ph48845.datn_qlnh_rmis.ui.phucvu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.ph48845.datn_qlnh_rmis.R;
import com.ph48845.datn_qlnh_rmis.ui.phucvu.adapter.MenuAdapter;
import com.ph48845.datn_qlnh_rmis.ui.phucvu.viewmodel.PhucVuViewModel;

public class MenuFragment extends Fragment {
    private MenuAdapter menuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerMenu = view.findViewById(R.id.recycler_menu);
        PhucVuViewModel viewModel = new ViewModelProvider(requireActivity()).get(PhucVuViewModel.class);
        viewModel.menuItems.observe(getViewLifecycleOwner(), items -> {
            if (menuAdapter == null) {
                menuAdapter = new MenuAdapter(items, (item, qty) -> {});
                recyclerMenu.setAdapter(menuAdapter);
            }
            menuAdapter.notifyDataSetChanged();
        });
    }
}