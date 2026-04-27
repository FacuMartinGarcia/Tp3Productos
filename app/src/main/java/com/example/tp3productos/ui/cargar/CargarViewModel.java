package com.example.tp3productos.ui.cargar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3productos.modelo.Producto;

public class CargarViewModel extends ViewModel {
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();
    private final MutableLiveData<Boolean> operacionExitosa = new MutableLiveData<>();

    public LiveData<String> getMensaje() {
        return mensaje;
    }

    public LiveData<Boolean> getOperacionExitosa() {
        return operacionExitosa;
    }

    /**
     * Intenta agregar un nuevo producto después de validar los datos
     */
    public void agregarProducto(int codigo, String descripcion, double precio) {

        // Validación 1: Campos vacíos
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

        // Crear el producto
        Producto nuevoProducto = new Producto(codigo, descripcion.trim(), precio);

        // Validación 2: Código no repetido (usando el método del modelo)
        boolean agregado = Producto.agregarProducto(nuevoProducto);

        if (agregado) {
            mensaje.setValue("Producto agregado correctamente");
            operacionExitosa.setValue(true);
        } else {
            mensaje.setValue("Error: Ya existe un producto con ese código");
            operacionExitosa.setValue(false);
        }
    }

    /**
     * Limpia los mensajes después de mostrarlos
     */
    public void limpiarMensajes() {
        mensaje.setValue(null);
        operacionExitosa.setValue(null);
    }
}