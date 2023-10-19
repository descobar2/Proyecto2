package com.fabrica.proyecto;
import java.util.Scanner;

public class Producto {

    private String nombre;
    private String precio;
    private String cantidad;
    private String nombreMaterial;
    private Material material = new Material();
    private Scanner scan = new Scanner(System.in);
    private Menu menu = new Menu();


    public void crearPoducto(){
        archivo.registroProducto(datosProducto());
    }
    public String datosProducto(){
        String listaMarterial="";
        String id;
        System.out.println("Ingrese nombre del producto:");
        nombre = scan.nextLine();
        System.out.println("Ingrese precio del producto:");
        precio = scan.nextLine();
        System.out.println("Ingrese materiales para fabricar " + nombre);
        do{
            System.out.println("Nombre de material: ");
            nombreMaterial = scan.nextLine();

            if(archivo.buscarMaterial(nombreMaterial)){
                System.out.println("Ingrese cantidad de material");
                cantidad = scan.nextLine();
                id = archivo.retoranaIdM(nombreMaterial);
                listaMarterial = listaMarterial + id + "%" + cantidad + "%";
                //System.out.println("Lista material: " + listaMarterial);
            }else{
                System.out.println("El material no existe, desea crearlo: ");
                if(menu.menuSiNo()){
                    material.crearMaterial();
                    System.out.println("Ingrese cantidad de material");
                    cantidad = scan.nextLine();
                    id = archivo.retoranaIdM(nombreMaterial);
                    listaMarterial = listaMarterial + id + "%" + cantidad + "%";
                    //System.out.println("Lista material: " + listaMarterial);
                }else{
                    System.out.println("Producto no fue creado");
                }
            }
            System.out.println("Desea ingresar otro material:");
        }while(menu.menuSiNo());
    return nombre+"%"+precio+"%"+listaMarterial;
    }    
}