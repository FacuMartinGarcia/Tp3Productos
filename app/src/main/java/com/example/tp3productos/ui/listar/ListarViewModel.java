package com.example.tp3productos.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3productos.modelo.Producto;
import com.example.tp3productos.modelo.ProductoRepository;

import java.util.List;

public class ListarViewModel extends ViewModel {

    // traemos la instancia unica del repositorio
    private final ProductoRepository repository = ProductoRepository.getInstance();

    private final MutableLiveData<List<Producto>> mProductos = new MutableLiveData<>();

    public ListarViewModel() {
        cargarProductos();
    }

    public void cargarProductos() {
        List<Producto> listaActualizada = repository.obtenerProductosOrdenados();
        mProductos.setValue(listaActualizada);
    }

    public LiveData<List<Producto>> getProductos() {
        return mProductos;
    }
}