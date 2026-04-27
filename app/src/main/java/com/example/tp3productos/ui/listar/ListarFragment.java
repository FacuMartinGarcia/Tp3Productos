package com.example.tp3productos.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tp3productos.MainActivity;
import com.example.tp3productos.databinding.FragmentListarBinding;
import com.example.tp3productos.R;
import com.example.tp3productos.databinding.ItemListarBinding;
import com.example.tp3productos.modelo.Producto;
import com.example.tp3productos.ui.ProductoAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private ProductoAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);

        adapter = new ProductoAdapter(new ArrayList<>());
        binding.rvLista.setAdapter(adapter);
        binding.rvLista.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Producto> lista = MainActivity.productos;

        Collections.sort(lista, (a, b) -> a.getDescripcion().compareToIgnoreCase(b.getDescripcion()));

        adapter.setProductos(lista);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}