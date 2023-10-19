package com.fabrica.proyecto;
import java.util.Scanner;

public class Material {
    private String nombre;
    private String descripcion;
    private String medida;
    private String costo;
    private Scanner scan = new Scanner(System.in);

    public void crearMaterial(){
        archivo.registroMaterial(datosMaterial());
    }
    public String datosMaterial(){
        System.out.println("Ingrese Nombre:");
        nombre = scan.nextLine();
        System.out.println("Ingrese descripcion:");
        descripcion = scan.nextLine();
        System.out.println("Ingrese medida:");
        medida = scan.nextLine();
        System.out.println("Ingrese costo:");
        costo = scan.nextLine();

    return nombre+"%"+descripcion+"%"+medida+"%"+costo;
    }
}
