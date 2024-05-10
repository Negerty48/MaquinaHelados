/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mh.dao.interfaces;

import com.mh.biz.Helado;
import java.util.ArrayList;

/**
 *
 * @author dev
 */
public interface HeladoDAO {
    public ArrayList<Helado> getHelados() throws Exception;
    public Helado getHeladoByPos(String posicion) throws Exception;
    public int updateHelado(Helado h) throws Exception;
}