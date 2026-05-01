package com.example.tp3productos.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tp3productos.databinding.FragmentListarBinding;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ListarViewModel listarViewModel;
    private ProductoAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);

        // Configuración del RecyclerView
        adapter = new ProductoAdapter(new ArrayList<>());
        binding.rvLista.setAdapter(adapter);
        binding.rvLista.setLayoutManager(new LinearLayoutManager(requireContext()));

        listarViewModel = new ViewModelProvider(this).get(ListarViewModel.class);

        // Observamos cambios en la lista
        listarViewModel.getProductos().observe(getViewLifecycleOwner(), productos -> {
            if (productos != null) {
                adapter.setProductos(productos);
            }
        });

        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}