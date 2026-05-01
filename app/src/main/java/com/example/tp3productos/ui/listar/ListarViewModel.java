package com.example.tp3productos.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3productos.modelo.Producto;
import com.example.tp3productos.modelo.ProductoRepository;

import java.util.List;

public class ListarViewModel extends ViewModel {

    private final ProductoRepository repository = ProductoRepository.getInstance();

    public LiveData<List<Producto>> getProductos() {
        return repository.getProductosLiveData();
    }
}