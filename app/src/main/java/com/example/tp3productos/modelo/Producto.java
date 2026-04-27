package com.example.tp3productos.modelo;

import java.util.ArrayList;

public class Producto {

    private int codigo;
    private String descripcion;
    private double precio;

    private static final ArrayList<Producto> listaProductos = new ArrayList<>();

    // ==================== CONSTRUCTORES ====================

    public Producto() {
    }

    public Producto(int codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // ==================== GETTERS Y SETTERS ====================

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // ==================== MÉTODOS ESTÁTICOS (LÓGICA DE DATOS) ====================

    /**
     * Agrega un producto a la lista si el código no está repetido.
     * @return true si se agregó correctamente, false si el código ya existe
     */
    public static boolean agregarProducto(Producto nuevoProducto) {
        // Validación: código no repetido
        for (Producto p : listaProductos) {
            if (p.getCodigo() == nuevoProducto.getCodigo()) {
                return false; // Código duplicado
            }
        }

        listaProductos.add(nuevoProducto);
        return true;
    }

    /**
     * Devuelve una copia de la lista ordenada alfabéticamente por descripción
     */
    public static ArrayList<Producto> getListaProductos() {
        // Ordenamos la lista por descripción
        listaProductos.sort((p1, p2) ->
                p1.getDescripcion().compareToIgnoreCase(p2.getDescripcion()));

        // Devolvemos una copia para evitar que modifiquen la lista original desde afuera
        return new ArrayList<>(listaProductos);
    }

    /**
     * Limpia toda la lista
     */
    public static void limpiarLista() {
        listaProductos.clear();
    }
}