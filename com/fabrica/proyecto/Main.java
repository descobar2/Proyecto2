package com.fabrica.proyecto;
import java.sql.SQLException;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws NumberFormatException, SQLException {
        String opcion;
        Boolean salir=false;
        Scanner entrada = new Scanner(System.in);
        Menu menu = new Menu();
        Persona persona = new Persona();
        Producto producto = new Producto();
        Material material = new Material();
        ComprarMaterial comprar = new ComprarMaterial();
        Vender vender = new Vender();
        ConnectionDB connection = new ConnectionDB();
        //ConsultasDB consultas = new ConsultasDB();

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
                    System.out.println("Seleccione nuevo estado");
                    menu.editarEstado();
                    opcion = entrada.nextLine();
                        switch(opcion){
                            case"1":
                                //Estado en bodega
                                System.out.println("Ingrese correlativo: ");
                                opcion = entrada.nextLine();                                            
                                connection.updateEstado(Integer.parseInt(opcion),"bodega");
                                break;
                            case"2":
                                //Proceso de Corte
                                System.out.println("Ingrese correlativo: ");
                                opcion = entrada.nextLine();
                                connection.updateEstado(Integer.parseInt(opcion),"corte");
                                break;
                            case"3":
                                System.out.println("Ingrese correlativo: ");
                                opcion = entrada.nextLine();
                                connection.updateEstado(Integer.parseInt(opcion),"armado");
                                break;
                            case"4":
                                System.out.println("Ingrese correlativo: ");
                                opcion = entrada.nextLine();
                                connection.updateEstado(Integer.parseInt(opcion),"acabado");
                                break;
                            case "5":
                                System.out.println("Ingrese correlativo: ");
                                opcion = entrada.nextLine();
                                connection.updateEstado(Integer.parseInt(opcion),"finalizado");
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                break;
                        }
                    break;
                case "4":
                    menu.crear();
                    opcion = entrada.nextLine();
                    switch (opcion){
                        case "1":
                            producto.crearProducto();
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
            /*    case "5":  
                    menu.buscar();
                    opcion = entrada.nextLine();
                    //System.out.println(consultas.getUltimoID("DocumentoID","Documento")); //String
                    //System.out.println(consultas.getNombreMat(Integer.parseInt(opcion))); //Integer
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
                */
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
