/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.dao.interfaces;

import com.mh.biz.Venta;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public interface VentaDAO {
    public ArrayList<Venta> getVentas() throws Exception;
    public int insertarVenta(Venta v) throws Exception;
}