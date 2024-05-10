/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.biz;

import com.mh.dao.implementations.*;
import com.mh.exceptions.*;
import com.mh.utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author enriquenogal
 */
public class MaquinaHelados {
    private double monedero = 0;
    private double ingresos = 0;

    public ArrayList<Helado> getHelados() throws Exception {
        ArrayList<Helado> al = new ArrayList<>();
        try (HeladoDAOImpl h = new HeladoDAOImpl();){
            al = h.getHelados();
        } catch (Exception e) {
            throw e;
        }
        return al;
    }

    public ArrayList<Venta> getVentas() throws Exception {
        ArrayList<Venta> al = new ArrayList<>();
        try (VentaDAOImpl v = new VentaDAOImpl();) {
            al = v.getVentas();
        } catch (Exception e) {
            throw e;
        }
        return al;
    }
    
    public Helado pedirHelado(String posicion) throws Exception {
        Helado conseguido = null;
        Venta vt = null;
        try (HeladoDAOImpl h = new HeladoDAOImpl();
                VentaDAOImpl v = new VentaDAOImpl()){
            conseguido = h.getHeladoByPos(posicion);
            if (conseguido == null) {
                throw new NotValidPositionException();
            } else if (this.getMonedero() <= conseguido.getPrecio()) {
                throw new NotEnoughMoneyException();
            } else if (conseguido.getCantidad() < 1) {
                throw new QuantityExceededException();
            } else {
                this.setIngresos(this.getIngresos() + conseguido.getPrecio());
                this.setMonedero(this.getMonedero() - conseguido.getPrecio());
                conseguido.setCantidad(conseguido.getCantidad() - 1);
                h.updateHelado(conseguido);
                vt = new Venta(LocalDate.now().toString(), conseguido.getPosicion(), conseguido.getNombre(), conseguido.getPrecio(), conseguido.getTipo(), conseguido.getCantidad());
                v.insertarVenta(vt);
            }
        } catch (Exception e) {
            throw e;
        }
        return conseguido;
    }

    public double getMonedero() {
        return monedero;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setMonedero(double monedero) {
        this.monedero = Utils.redondeoDosDecimales(monedero);
    }

    public void setIngresos(double ingresos) {
        this.ingresos = Utils.redondeoDosDecimales(ingresos);
    }
}