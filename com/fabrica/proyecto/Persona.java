package com.fabrica.proyecto;
import java.util.Scanner;

public class Persona {
    private String nit;
    private String nombre;
    private String direccion;
    private Scanner scan = new Scanner(System.in);

    public void crearCliente(){
        archivo.registroCliente(solicitarDatos());       
    }
    public void crearProveedor(){
        archivo.registroProveedor(solicitarDatos());
    }

    private String solicitarDatos(){
    System.out.println("Ingrese NIT:");
    nit = scan.nextLine();
    System.out.println("Ingrese Nombre:");
    nombre = scan.nextLine();
    System.out.println("Ingrese direccion:");
    direccion = scan.nextLine();

    return nit+"%"+nombre+"%"+direccion;
    }

}
