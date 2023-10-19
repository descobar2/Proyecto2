package com.fabrica.proyecto;

import java.util.Scanner;

public class ComprarMaterial implements Operaciones{
    private String id;
    private String nombre;
    private String cantidad;
    private Scanner scan = new Scanner(System.in);

    @Override
    public void realizar() {
        System.out.println("Ingrese producto de compra:");
        nombre = scan.nextLine();
        if(archivo.buscarMaterial(nombre)){
            if(archivo.buscarInventario(nombre)){
                System.out.println("Ingrese cantida:");
                cantidad = scan.nextLine();
                id = archivo.retoranaIdM(nombre);
                archivo.modificarInventario(id, cantidad,"suma"); //funcion para modificar linea en inventario
            }else{
                System.out.println("Ingrese cantidad:");
                cantidad = scan.nextLine();
                id = archivo.retoranaIdM(nombre);
                archivo.registroInventario(id + "%" + cantidad);
            }            
        }else{
            System.out.println("No es posible realizar accion. Material no existe.");
        }
    }   
}
