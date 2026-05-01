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

    public void agregarProducto(String sCodigo, String descripcion, String sPrecio) {

        if (sCodigo == null || sCodigo.trim().isEmpty() ||
                descripcion == null || descripcion.trim().isEmpty() ||
                sPrecio == null || sPrecio.trim().isEmpty()) {
            mensaje.setValue("Por favor, complete todos los campos");
            operacionExitosa.setValue(false);
            return;
        }

        int codigo;
        double precio;

        try {
            codigo = Integer.parseInt(sCodigo.trim());
        } catch (NumberFormatException e) {
            mensaje.setValue("El código debe ser un número entero válido");
            operacionExitosa.setValue(false);
            return;
        }


        try {
            precio = Double.parseDouble(sPrecio.trim());
        } catch (NumberFormatException e) {
            mensaje.setValue("El precio debe ser un número válido");
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
            mensaje.setValue("Producto agregado! Volviendo al listado...");
            operacionExitosa.setValue(true);
        } else {
            mensaje.setValue("Error: Ya existe un producto con ese código o descripción");
            operacionExitosa.setValue(false);
        }
    }

    public void limpiarMensajes() {
        mensaje.setValue(null);
        operacionExitosa.setValue(null);
    }
}