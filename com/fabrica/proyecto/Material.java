package com.fabrica.proyecto;
import java.util.Scanner;

public class Material {
    private String nombre;
    private String medida;
    private String precio;
    private Scanner scan = new Scanner(System.in);
    private ConnectionDB connection = new ConnectionDB();

    public void crearMaterial(){
        System.out.println("Ingrese Nombre:");
        nombre = scan.nextLine();
        System.out.println("Ingrese medida:");
        medida = scan.nextLine();
        System.out.println("Ingrese costo:");
        precio = scan.nextLine();

        connection.nuevoMaterial(nombre,medida,Float.parseFloat(precio));
    }
}
