package com.fabrica.proyecto;
import java.util.Scanner;

public class Persona {
    private String nit;
    private String nombre;
    private Scanner scan = new Scanner(System.in);
    private ConnectionDB connection = new ConnectionDB();

    public void crearCliente(){      
        connection.nuevaPersona(1,solicitarNombre(),solicitarNit());
    }
    public void crearProveedor(){
        connection.nuevaPersona(2,solicitarNombre(),solicitarNit());
    }
    private String solicitarNombre(){
    System.out.println("Ingrese Nombre:");
    nombre = scan.nextLine();
    return nombre;
    }
    private String solicitarNit(){
    System.out.println("Ingrese Nit:");
    nit = scan.nextLine();
    return nit;
    }
}
