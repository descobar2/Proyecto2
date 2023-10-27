package com.fabrica.proyecto;

import java.util.Scanner;

public class Menu {
    private Scanner entrada = new Scanner(System.in);
    private String opcion;
    
    public void principal(){
        //Detalle de menu de inicio
        System.out.println("\nFabrica de Muebles");
        System.out.println("1. Realizar una compra");
        System.out.println("2. Realizar una venta");
        System.out.println("3. Editar estado");
        System.out.println("4. Crear");
        System.out.println("5. Mostrar inventario");
        System.out.println("0. Salir");
    }
    public void crear(){
        System.out.println("\nMenu Crear");
        System.out.println("1. Nuevo producto");
        System.out.println("2. Nuevo material");
        System.out.println("3. Nuevo proveedor");   
        System.out.println("4. Nuevo cliente");
        System.out.println("5. Regresar");
    }
    public void venta(){
        System.out.println("\nRealizar Venta");
        System.out.println("Ingrese nombre de producto:");
        opcion = entrada.nextLine();
        System.out.println("Ingrese cantidad");
        opcion = entrada.nextLine();
        System.out.println("Ingrese nombre del cliente");
        opcion = entrada.nextLine();
        System.out.println("Se ingreso orden de fabricacion No. 10:");
        System.out.println("Estado: Materiales en bodega");
    }
    public void compra(){
        System.out.println("\nRealizar Compra de material");
        System.out.println("Ingrese nombre de proveedor:");
        opcion = entrada.nextLine();
        System.out.println("Ingrese nombre del material:");
        opcion = entrada.nextLine();
        System.out.println("Ingrese cantidad:");
        opcion = entrada.nextLine();
        System.out.println("Compra ingresada exitosamente.");
    }
    public void editar(){
        System.out.println("\nEditar Estado");
        System.out.println("1. Orden de venta");
        System.out.println("2. Pedido");
        System.out.println("3. Regresar");           
    }
    public void editarEstado(){
        System.out.println("1. En bodega");
        System.out.println("2. Proceso de corte");
        System.out.println("3. Proceso de armado");
        System.out.println("4. Proceso de acabados");
        System.out.println("5. Finalizado");
    }        
    public void buscar(){
        System.out.println("\nMenu Buscar");
        System.out.println("1. Orden de fabricacion");
        System.out.println("2. Proveedor");
        System.out.println("3. Cliente");   
        System.out.println("4. Regresar");        
    }
    public boolean menuSiNo(){
        boolean continuar=true;
        do{
            System.out.println("1. Si");
            System.out.println("2. No");
            opcion = entrada.nextLine();

            switch (opcion){
                case "1":
                    return true;
                case "2":
                    return false;
                default:
                    System.out.println("Valor invalido intente de nuevo: ");
                break;
            }
        }while(continuar);
    return continuar;
    }
}
