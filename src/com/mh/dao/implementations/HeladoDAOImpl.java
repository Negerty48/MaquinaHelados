/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.dao.implementations;

import java.sql.*;
import com.mh.biz.Helado;
import com.mh.dao.interfaces.HeladoDAO;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public class HeladoDAOImpl implements HeladoDAO, AutoCloseable{
    Connection con = null;
        
    static {
        try {
            Class.forName(com.mh.utils.Configuration.DRIVER);
        } catch (Exception e) {
            System.exit(1);
        }
    }    
    
    public HeladoDAOImpl() throws Exception {
        con = DriverManager.getConnection(com.mh.utils.Configuration.URL);
    }
    
    @Override
    public ArrayList<Helado> getHelados() throws Exception {
        ArrayList<Helado> al = new ArrayList<Helado>();
        Helado h;
        ResultSet rs = null;
        String sql = "select posicion, nombre, precio, tipo, cantidad from helado";
        try (PreparedStatement stm = con.prepareStatement(sql);) {
            rs = stm.executeQuery();
            while(rs.next()) {
                h = new Helado(rs.getString("posicion"), rs.getString("nombre"),
                        Double.parseDouble(rs.getString("precio")), rs.getString("tipo"),
                        Integer.parseInt(rs.getString("cantidad")));
                al.add(h);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return al;
    }

    @Override
    public Helado getHeladoByPos(String posicion) throws Exception {
        Helado h = null;
        ResultSet rs = null;
        String sql = "select posicion, nombre, precio, tipo, cantidad from helado where posicion = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, posicion);
            rs = stm.executeQuery();            
            while(rs.next()) {
                h = new Helado(rs.getString("posicion"), rs.getString("nombre"),
                        Double.parseDouble(rs.getString("precio")), rs.getString("tipo"), 
                        Integer.parseInt(rs.getString("cantidad")));
            }            
        } catch (Exception e) {
            throw e;
        } finally {
            rs.close();
        }
        return h;
    }

    @Override
    public int updateHelado(Helado h) throws Exception {
        int r = 0;
        String sql = "update helado set nombre = ?, tipo = ?, precio = ?, cantidad = ? where posicion = ?";
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, h.getNombre());
            stm.setString(2, h.getTipo());
            stm.setDouble(3, h.getPrecio());
            stm.setInt(4, h.getCantidad());
            stm.setString(5, h.getPosicion());
            r = stm.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
        return r;
    }

    @Override
    public void close() throws Exception {
        con.close();
    }    
}