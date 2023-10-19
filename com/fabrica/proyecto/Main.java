package com.fabrica.proyecto;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        String opcion;
        Boolean salir=false;
        Scanner entrada = new Scanner(System.in);
        Menu menu = new Menu();
        Persona persona = new Persona();
        Producto producto = new Producto();
        Material material = new Material();
        ComprarMaterial comprar = new ComprarMaterial();
        Vender vender = new Vender();

            do{
            menu.principal();
            opcion = entrada.nextLine();
            switch(opcion){
                case "1":
                    comprar.realizar();
                break;
                case "2":
                    vender.realizar();
                break;
                case "3":
                    menu.editar();
                    opcion = entrada.nextLine();
                    switch(opcion){
                        case "1":
                            opcion = entrada.nextLine();
                            switch(opcion){
                                case "1":
                                    menu.editarEstado();
                                    opcion = entrada.nextLine();
                                    switch(opcion){
                                        case"1":
                                        //Proceso de corte
                                        vender.editarEstadoV("Proceso de corte");
                                        case"2":
                                        //Proceso de armado
                                        vender.editarEstadoV("Proceso de armado");
                                        case"3":
                                        //Proceso de acabado
                                        vender.editarEstadoV("Proceso de acabado");
                                        case"4":
                                        //Proceso de entrega
                                        vender.editarEstadoV("Proceso de entrega");
                                    }
                                    break;
                                case "2":

                                    break;
                                case "3":

                                    break;
                                default:
                                    System.out.println("Opcion no valida");
                                    break;
                            }   
                            break;
                        case "2":
                            break;
                        case "3":
                            break; 
                    }
            
                break;
                case "4":
                    menu.crear();
                    opcion = entrada.nextLine();
                    switch (opcion){
                        case "1":
                            producto.crearPoducto();
                        break;
                        case "2":                        
                            material.crearMaterial();
                        break;
                        case "3":
                            persona.crearProveedor();
                        break;
                        case "4":
                            persona.crearCliente();
                        break;
                        case "5":
                        break;
                        default:
                            System.out.println("Valor invalido");
                    break;                                                                
                    }
                    break;
                case "5":
                    menu.buscar();
                    opcion = entrada.nextLine();
                    switch (opcion){
                        case "1":
                            System.out.println("Ingrese No. de orden: ");
                            opcion = entrada.nextLine();
                            System.out.println("Orden No. " + opcion);
                            System.out.println("Estado: Materiales en bodega");
                            break;
                        case "2":
                            System.out.println("Ingrese nombre de proveedor: ");
                            opcion = entrada.nextLine();
                            System.out.println("Nombre: Maderas el Angel");
                            System.out.println("Direccion: Avenida petapa");
                            break;                
                        case "3":
                            System.out.println("Ingrese NIT de cliente: ");
                            opcion = entrada.nextLine();
                            System.out.println("Nombre: Marco Polo");
                            System.out.println("Direccion: Ciudad, Guatemala");
                            break;            
                        case "4":
                            break;
                        default:
                            System.out.println("Valor invalido:");
                            menu.menuSiNo();
                        break;                
                    }

                break;
                case "0":
                    salir = true;
                    System.out.println("Programa cerrado");
                break;
                default:
                    System.out.println("Valor invalido, intente de nuevo");
                    break;
            }
        }while(!salir);
    entrada.close();
    }
}
