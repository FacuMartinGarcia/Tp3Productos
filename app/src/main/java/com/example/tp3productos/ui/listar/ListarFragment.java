package com.example.tp3productos.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.tp3productos.databinding.FragmentListarBinding;
import com.example.tp3productos.ui.ProductoAdapter;
import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel listarViewModel;
    private ProductoAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);

        adapter = new ProductoAdapter(new ArrayList<>());
        binding.rvLista.setAdapter(adapter);
        binding.rvLista.setLayoutManager(new LinearLayoutManager(getContext()));

        listarViewModel = new ViewModelProvider(this).get(ListarViewModel.class);

        listarViewModel.getProductos().observe(getViewLifecycleOwner(), productos -> {
            adapter.setProductos(productos);
            adapter.notifyDataSetChanged(); // Refrescae el recyclerView
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        listarViewModel.cargarProductos();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}