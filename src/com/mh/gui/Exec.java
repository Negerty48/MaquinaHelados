/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mh.gui;

import com.mh.biz.*;
import com.mh.exceptions.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author enriquenogal
 */
public class Exec {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MaquinaHelados mh = null;
        try {
            mh = new MaquinaHelados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String opcion;
        do {
            opcion = menu(sc);
            if (opcion.equals("1")) {
                try {
                    mostrarHelados(mh);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (opcion.equals("2")) {
                introDinero(mh, sc);
            } else if (opcion.equals("3")) {
                try {
                    pedirHelado(mh, sc);
                } catch (NotValidPositionException e) {
                    System.out.println("Posición introducida inexistente");
                } catch (QuantityExceededException e) {
                    System.out.println("No quedan helados en esa posición");
                } catch (NotEnoughMoneyException e) {
                    System.out.println("No tiene dinero suficiente para ese helado");
                } catch (Exception e) {
                    System.out.println("Se ha producido un error inesperado. Por favor, contacte con el administrador.");
                }
            }
        } while (!opcion.equalsIgnoreCase("S"));
        try {
            salir(mh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarHelados(MaquinaHelados mh) throws Exception {
        try {
            ArrayList<Helado> helados = mh.getHelados();
            for (Helado helado : helados) {
                System.out.println(helado);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static void introDinero(MaquinaHelados mh, Scanner sc) {
        double moneda = 0;
        String opcion = "";
        System.out.println("---------Introduzca monedas--------");
        mostrarMonedas();
        do {
            System.out.print("Elija una opción: ");
            opcion = sc.nextLine();
            if ((!(opcion.equalsIgnoreCase("A") || opcion.equalsIgnoreCase("B") || opcion.equalsIgnoreCase("C")
                    || opcion.equalsIgnoreCase("D") || opcion.equalsIgnoreCase("E") || opcion.equalsIgnoreCase("F")
                    || opcion.equalsIgnoreCase("S")))) {
                System.out.print("Opción incorrecta. ");
            } else {
                if (!opcion.equalsIgnoreCase("S")) {
                    mh.setMonedero(mh.getMonedero() + traducirDinero(opcion.toUpperCase()));
                    System.out.println("El dinero acumulado es: " + mh.getMonedero() + "€");
                }
            }
        } while (!(opcion.equalsIgnoreCase("S")));
    }

    public static double traducirDinero(String opcion) {
        if (opcion.equals("A")) {
            return 0.05d;
        } else if (opcion.equals("B")) {
            return 0.1d;
        } else if (opcion.equals("C")) {
            return 0.2d;
        } else if (opcion.equals("D")) {
            return 0.5d;
        } else if (opcion.equals("E")) {
            return 1d;
        } else if (opcion.equals("F")) {
            return 2d;
        } else {
            return 0d;
        }
    }

    public static void mostrarMonedas() {
        System.out.println("A.- 0,05€");
        System.out.println("B.- 0,10€");
        System.out.println("C.- 0,20€");
        System.out.println("D.- 0,50€");
        System.out.println("E.- 1,00€");
        System.out.println("F.- 2,00€");
        System.out.println("S.- Salir");
    }

    public static void pedirHelado(MaquinaHelados mh, Scanner sc) throws Exception {
        Helado conseguido = null;
        String posicion = "";
        System.out.println("------------Pedir Helado-------------");
        System.out.print("Introduzca la posición: ");
        posicion = sc.nextLine();
        try {
            conseguido = mh.pedirHelado(posicion);
            if (conseguido != null) {
                System.out.println("Aquí tiene su " + conseguido.getNombre() + " de tipo " + conseguido.getTipo());
                System.out.println("No olvide su cambio: " + mh.getMonedero() + "€");
                mh.setMonedero(0);
            }
        } catch (NotValidPositionException e) {
            throw new NotValidPositionException();
        } catch (QuantityExceededException e) {
            throw new QuantityExceededException();
        } catch (NotEnoughMoneyException e) {
            throw new NotEnoughMoneyException();
        } catch (Exception e) {
            System.out.println("Se ha producido un error inesperado. Por favor, contacte con el administrador.");
        }
    }

    public static void salir(MaquinaHelados mh) throws Exception {
        System.out.println("-----------------------------");
        System.out.println("Apagando máquina de helados.....");
        System.out.println("No olvide recoger su cambio: " + mh.getMonedero() + "€");
        System.out.println("Ingresos totales de la máquina de helados: " + mh.getIngresos() + "€");
        System.out.println("-----------------------------");
    }

    public static String menu(Scanner sc) {
        String opcion = "";
        System.out.println("--------------Menú principal-----------");
        System.out.println("1.- Mostrar helados");
        System.out.println("2.- Introducir monedas");
        System.out.println("3.- Pedir helado");
        System.out.println("S.- Salir y apagar máquina de helados");
        do {
            System.out.println("--------------------------------------");
            System.out.print("Introduzca una opción: ");
            opcion = sc.nextLine();
        } while (!(opcion.equals("1") || opcion.equals("2") || opcion.equals("3") || opcion.equalsIgnoreCase("S")));
        return opcion;
    }
}
