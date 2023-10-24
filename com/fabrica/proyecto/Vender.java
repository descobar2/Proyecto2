package com.fabrica.proyecto;

import java.util.Scanner;

public class Vender implements Operaciones{
    private Scanner scan = new Scanner(System.in);
    private ConnectionDB connection = new ConnectionDB();
    private Menu menu = new Menu();
    private Persona persona = new Persona();
    private String cliente;
    private String cantidad;
    private String producto;

    @Override
    public void realizar() {
        String pedido="";
        System.out.println("Ingrese nombre de producto");                
        producto = scan.nextLine();       
        if(connection.validarDato(producto)){
            System.out.println("Ingrese cantidad");
            cantidad = scan.nextLine();
            //validar inventario, si hay existensi continue, si no crear pedido.            

            if(connection.validarDato(cliente)){                
                connection.nuevaOrden(0,connection.getPersonaID(cliente,1),1,"Espera");
            }else{
                System.out.println("Cliente no existe, desea crearlo: ");
                if(menu.menuSiNo()){
                    persona.crearCliente();
                    //funcion para crear orden
                }
            }


            while(i<arreglo.length){
                id = arreglo[i];
                i++;
                cantidad = arreglo[i];
                valor = Integer.parseInt(cantidad) * Integer.parseInt(cProducto);
                i++;
                //Hacer llamada a funcion para validar inventario
                if(archivo.retornarDispI(id)>valor){
                    archivo.modificarInventario(id, "" + valor, "resta");
                    estado = "Materiales en bodega";
                }else{
                    pedido = pedido + "%" + id + "%" + cantidad;
                }
            }
            if(pedido.length()>0){
                estado = "Esperando materiales";
                idPD = archivo.regitroPedido(pedido,estado);
                System.out.println("Se genero pedido: "+ "PD" + idPD);
                System.out.println("Estado: " + estado);
                venta = "PD" + idPD + "%";
            }else{
                System.out.println("Estado: " + estado);
            }
            venta = venta + nombre+ "%" + cProducto;
            archivo.registroVenta(venta,estado);
        }else{
            System.out.println("No es posible realizar accion. Producto no existe");
        }
    }
    public void nuevoPedido(String estado){
        
    }
}
