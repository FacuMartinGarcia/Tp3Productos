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

    /**
     * Carga la lista ordenada desde el Repository
     */
    public void cargarProductos() {
        List<Producto> listaActualizada = repository.obtenerProductosOrdenados();
        productosLiveData.setValue(listaActualizada);
    }

    /**
     * Devuelve LiveData para que el Fragment observe cambios
     */
    public LiveData<List<Producto>> getProductos() {
        return productosLiveData;
    }

    /**
     * Método para forzar la actualización (útil cuando se agrega un producto desde otro fragment)
     */
    public void refrescarLista() {
        cargarProductos();
    }
}