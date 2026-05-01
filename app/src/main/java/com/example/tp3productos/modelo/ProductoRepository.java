package com.example.tp3productos.modelo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp3productos.MainActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProductoRepository {

    private static ProductoRepository instance;

    // Mutable live data para notificar cambios en la lista.
    private final MutableLiveData<List<Producto>> productosLiveData = new MutableLiveData<>();


    public LiveData<List<Producto>> getProductosLiveData() {
        return productosLiveData;
    }
    private ProductoRepository() {
        cargarDatosPrueba();
        productosLiveData.setValue(obtenerProductosOrdenados());

    }

    public static ProductoRepository getInstance() {
        if (instance == null) {
            instance = new ProductoRepository();
        }
        return instance;
    }

    private void cargarDatosPrueba() {
        if (!MainActivity.productos.isEmpty()) return; // Evita duplicados si ya se cargaron

        MainActivity.productos.add(new Producto(101, "AZÚCAR LEDESMA 1KG", 1200.50));
        MainActivity.productos.add(new Producto(102, "LECHE ENTERA LA SERENÍSIMA", 1500.00));
        MainActivity.productos.add(new Producto(103, "YERBA MATE PLAYADITO 1KG", 3800.00));
        MainActivity.productos.add(new Producto(104, "ARROZ GALLO ORO 1KG", 2100.00));
        MainActivity.productos.add(new Producto(105, "FIDEOS LUCCHETTI TALLARÍN", 1100.25));
        MainActivity.productos.add(new Producto(106, "ACEITE DE GIRASOL NATURA 1.5L", 2900.00));
        MainActivity.productos.add(new Producto(107, "HARINA BLANCAFLOR LEUDANTE", 1350.00));
        MainActivity.productos.add(new Producto(108, "GALLETITAS BAGLEY SURTIDAS", 1800.00));
        MainActivity.productos.add(new Producto(109, "MERMELADA DE FRUTILLA BC", 2400.00));
        MainActivity.productos.add(new Producto(110, "CAFÉ INSTANTÁNEO ARLISTÁN", 4200.50));
    }

    public boolean agregarProducto(Producto nuevoProducto) {
        String nuevaDesc = nuevoProducto.getDescripcion()
                .trim()
                .toUpperCase();

        for (Producto p : MainActivity.productos) {
            String descExistente = p.getDescripcion() == null ? "" : p.getDescripcion();

            if (p.getCodigo() == nuevoProducto.getCodigo() ||
                    descExistente.equals(nuevaDesc)) {
                return false;
            }
        }

        nuevoProducto.setDescripcion(nuevaDesc);
        MainActivity.productos.add(nuevoProducto);
        productosLiveData.setValue(obtenerProductosOrdenados());
        return true;

    }

    public List<Producto> obtenerProductosOrdenados() {
        List<Producto> copia = new ArrayList<>(MainActivity.productos);
        Collections.sort(copia, (p1, p2) ->
                p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion()));
        return copia;
    }

    public void borrarTodo() {
        MainActivity.productos.clear();
    }
}