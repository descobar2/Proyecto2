package com.fabrica.proyecto;

import java.util.Scanner;

public class Vender implements Operaciones{
    private Scanner scan = new Scanner(System.in);
    private String nombre;
    private String venta="";
    private String cProducto;
    private String cantidad;
    private String producto;
    private String estado;
    private String id;
    private int idPD;
    private int valor=0;

    @Override
    public void realizar() {
        String pedido="";
        System.out.println("Ingrese nombre de producto");                
        nombre = scan.nextLine();
        System.out.println("Ingrese cantidad");
        cProducto = scan.nextLine();
        
        if(archivo.buscarProducto(nombre)){
            int i=3;            
            producto = archivo.retornarProd(nombre);
            String[] arreglo = producto.split("%");
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
    public void editarEstadoV(String estado){
                
        archivo.modificarEstado(id, estado, "estado");
    }
}
