package com.example.tp3productos.modelo;

import java.util.ArrayList;

public class Producto {

    private int codigo;
    private String descripcion;
    private double precio;


    private static ArrayList<Producto> listaProductos = new ArrayList<>();


    public Producto() {
    }


    public Producto(int codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }


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

    public static void agregarProducto(Producto p) {
        listaProductos.add(p);
    }

    public static void listarProductos() {
        for (Producto p : listaProductos) {
            System.out.println("Código: " + p.getCodigo());
            System.out.println("Descripción: " + p.getDescripcion());
            System.out.println("Precio: $" + p.getPrecio());
            System.out.println("-------------------------");
        }
    }
}