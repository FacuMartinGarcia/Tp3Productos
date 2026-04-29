package com.example.tp3productos.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3productos.modelo.Producto;
import com.example.tp3productos.modelo.ProductoRepository;

import java.util.List;

public class ListarViewModel extends ViewModel {

    private final ProductoRepository repository = ProductoRepository.getInstance();
    private final MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>();

    public ListarViewModel() {
        cargarProductos();   // Carga inicial
    }

    public void cargarProductos() {
        List<Producto> listaActualizada = repository.obtenerProductosOrdenados();
        productosLiveData.setValue(listaActualizada);
    }

    public LiveData<List<Producto>> getProductos() {
        return productosLiveData;
    }

    public void refrescarLista() {
        cargarProductos();
    }
}