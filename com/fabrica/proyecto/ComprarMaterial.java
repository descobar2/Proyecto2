package com.fabrica.proyecto;

import java.util.Scanner;

public class ComprarMaterial implements Operaciones{
    private String nombre;
    private String cantidad;
    private Scanner scan = new Scanner(System.in);
    ConnectionDB connection = new ConnectionDB();

    @Override
    public void realizar() {
        System.out.println("Ingrese nombre de materail:");
        nombre = scan.nextLine();
        if(connection.validarDato(nombre)){
            System.out.println("Ingrese cantida:");
            cantidad = scan.nextLine();
            connection.nuevaCompra(nombre,Integer.parseInt(cantidad));          
        }else{
            System.out.println("No es posible realizar accion. Material no existe.");
        }
    }   
}
