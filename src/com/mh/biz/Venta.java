/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.biz;

import com.mh.utils.Utils;

/**
 *
 * @author dev
 */
public class Venta {
    private String fecha_hora;
    private String posicion;
    private String nombre;
    private double precio;
    private String tipo;
    private int cantidad;

    public Venta(String fecha_hora, String posicion, String nombre, double precio, String tipo, int cantidad) {
        this.fecha_hora = fecha_hora;
        this.posicion = posicion;
        this.nombre = nombre;
        this.precio = Utils.redondeoDosDecimales(precio);
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return  fecha_hora + " :: " + posicion + " :: " + nombre + " :: " + precio + "€ :: " + tipo + " :: " + cantidad ;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }
    public String getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }
}