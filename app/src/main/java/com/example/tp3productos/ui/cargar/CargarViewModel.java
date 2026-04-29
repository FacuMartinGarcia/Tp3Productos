package com.example.tp3productos.ui.cargar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3productos.modelo.Producto;
import com.example.tp3productos.modelo.ProductoRepository; // IMPORTANTE

public class CargarViewModel extends ViewModel {
    // traemos la instancia unica del repositorio
    private final ProductoRepository repository = ProductoRepository.getInstance();

    private final MutableLiveData<String> mensaje = new MutableLiveData<>();
    private final MutableLiveData<Boolean> operacionExitosa = new MutableLiveData<>();

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public LiveData<Boolean> getOperacionExitosa() {
        return operacionExitosa;
    }

    public void agregarProducto(int codigo, String descripcion, double precio) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            mensaje.setValue("La descripción no puede estar vacía");
            operacionExitosa.setValue(false);
            return;
        }

        if (precio <= 0) {
            mensaje.setValue("El precio debe ser mayor a 0");
            operacionExitosa.setValue(false);
            return;
        }

        Producto nuevoProducto = new Producto(codigo, descripcion.trim(), precio);

        boolean agregado = repository.agregarProducto(nuevoProducto);

        if (agregado) {
            mensaje.setValue("Producto agregado correctamente");
            operacionExitosa.setValue(true);
        } else {
            mensaje.setValue("Error: Ya existe un producto con ese código ó descripción");
            operacionExitosa.setValue(false);
        }
    }

    public void limpiarMensajes() {
        mensaje.setValue(null);
        operacionExitosa.setValue(null);
    }
}