/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.dao.implementations;

import com.mh.biz.Venta;
import com.mh.dao.interfaces.VentaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public class VentaDAOImpl implements VentaDAO, AutoCloseable{
    Connection con = null;

    static {
        try {
            Class.forName(com.mh.utils.Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public VentaDAOImpl() throws Exception{
        con = DriverManager.getConnection(com.mh.utils.Configuration.URL);
    }

    @Override
    public ArrayList<Venta> getVentas() throws Exception {
        ArrayList<Venta>al = new ArrayList<Venta>();
        Venta v;
        ResultSet rs = null;
        String sql = "select fecha_hora, posicion, nombre, precio, tipo, cantidad from venta";
        try(PreparedStatement stm = con.prepareStatement(sql);){
            rs = stm.executeQuery();
            while(rs.next()) {
                v = new Venta(rs.getString("fecha_hora"),rs.getString("posicion"), rs.getString("nombre"),
                        Double.parseDouble(rs.getString("precio")), rs.getString("tipo"),
                        Integer.parseInt(rs.getString("cantidad")));
                al.add(v);
            }
        }catch (Exception e){
            throw e;
        }finally {
            if (rs != null){
                rs.close();
            }
        }
        return al;
    }

    @Override
    public int insertarVenta(Venta v) throws Exception {
        int r = 0;
        String posicion = v.getPosicion();
        String nombre = v.getNombre();
        double precio = v.getPrecio();
        String tipo = v.getTipo();
        String sql = "insert into venta values (datetime('now'),? ,? ,? ,? ,1)";
        try (PreparedStatement stm = con.prepareStatement(sql);){            
            stm.setString(1, posicion);
            stm.setString(2, nombre);
            stm.setDouble(3, precio);
            stm.setString(4, tipo);
            r = stm.executeUpdate();
        }catch (Exception e){
            throw e;
        }
        return r;
    }

    @Override
    public void close() throws Exception {
      con.close();
    }    
}