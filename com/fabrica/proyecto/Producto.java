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

    public void crearProducto() throws NumberFormatException, SQLException{
        System.out.println("Ingrese nombre del producto:");
        nombre = scan.nextLine();
        System.out.println("Ingrese precio del producto:");
        precio = scan.nextLine();
        connection.nuevoProdcuto(nombre,Float.parseFloat(precio));
        System.out.println("");
        System.out.println("Ingrese materiales para fabricar " + nombre);
        do{
            System.out.println("Nombre de material: ");
            nombreMaterial = scan.nextLine();
            if(connection.validarDato(nombreMaterial)){
                System.out.println("Ingrese cantidad de material");
                cantidad = scan.nextLine();                
                connection.asociarMaterial(connection.getPorductoID(nombre),connection.getMaterialId(nombreMaterial),Integer.valueOf(cantidad));
            }else{
                System.out.println("El material no existe, desea crearlo: ");
                if(menu.menuSiNo()){
                    material.crearMaterial();
                }else{
                    System.out.println("Producto no fue creado");
                }
            }
            System.out.println("Desea ingresar otro material:");
        }while(menu.menuSiNo());
    }    
}