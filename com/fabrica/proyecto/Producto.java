package com.fabrica.proyecto;
import java.sql.SQLException;
import java.util.Scanner;

public class Producto {
    private String nombre;
    private String precio;
    private String cantidad;
    private String nombreMaterial;
    private Material material = new Material();
    private Scanner scan = new Scanner(System.in);
    private Menu menu = new Menu();
    private ConnectionDB connection = new ConnectionDB();
    private ConsultasDB consultas = new ConsultasDB();

    public void crearProducto() throws NumberFormatException, SQLException{
        System.out.println("Ingrese nombre del producto:");
        nombre = scan.nextLine();
        System.out.println("Ingrese precio del producto:");
        precio = scan.nextLine();
        connection.nuevoProducto(nombre,Float.parseFloat(precio));
        System.out.println("");
        System.out.println("Ingrese materiales para fabricar " + nombre);
        do{
            System.out.println("Nombre de material: ");
            nombreMaterial = scan.nextLine();
            if(connection.validarDato(nombreMaterial)){
                System.out.println("Ingrese cantidad de material");
                cantidad = scan.nextLine();                
                connection.asociarMaterial(consultas.getProductoID(nombre),consultas.getMaterialID(nombreMaterial),Integer.valueOf(cantidad));
            }else{
                System.out.println("El material no existe, desea crearlo: ");
                if(menu.menuSiNo()){
                    material.crearMaterial();
                    connection.asociarMaterial(consultas.getProductoID(nombre),consultas.getMaterialID(nombreMaterial),Integer.valueOf(cantidad));
                }else{
                    System.out.println("Material no agregado");
                }
            }
            System.out.println("Desea ingresar otro material:");
        }while(menu.menuSiNo());
    }    
}