package com.fabrica.proyecto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vender implements Operaciones{
    private Scanner scan = new Scanner(System.in);
    private ConnectionDB connection = new ConnectionDB();
    private ConsultasDB consultas = new ConsultasDB();
    private String cliente;
    private String cantidadP;
    private String producto;
    private int materialID;
    private int cantidadM;
    private int inventario;
    private int total;
    private int contador=0;

    @Override
    public void realizar() {
        System.out.println("Ingrese nombre de producto");                
        producto = scan.nextLine();       
        if(consultas.getProductoID(producto)!=(-1)){
            System.out.println("Ingrese cantidad");
            cantidadP = scan.nextLine();
            System.out.println("Ingrese nombre de cliente");
            cliente = scan.nextLine();
            try {
                if(connection.getPersonaID(cliente,1)!=(-1)){                
                    //validar inventario, si hay existencia continua, si no crear pedido del material que hace falta.
                    //Se asocia pedio a orden de compra.
                
                    ArrayList<ListaMateriales> materiales = consultas.listaMaterial(consultas.getProductoID(producto));
                    for(ListaMateriales listaMateriales : materiales){
                        materialID = listaMateriales.getMaterialID();
                        cantidadM = listaMateriales.getCantidad();
                        inventario = consultas.getBusquedaMatDisp(materialID);
                        total = cantidadM*Integer.parseInt(cantidadP);
                        if(inventario>total){
                            //Rebajar de inventario
                            connection.updateInventario(materialID,total);
                        }else{
                            //Agregar a lista de pedido
                            ArrayList<ListaMateriales> pedido = new ArrayList<ListaMateriales>();
                            ListaMateriales lm = new ListaMateriales();
                            lm.setMaterialID(materialID);
                            lm.setCantidad(cantidadM*Integer.parseInt(cantidadP));   
                            pedido.add(lm);                         
                            contador++;
                        }
                    }
                    if(contador!=0){
                        nuevoPedido("Espera");
                        System.out.print("Se genero pedido No.\t");
                        System.out.println(consultas.getUltimoID("DocumentoID","Documento"));
                    }else{
                        nuevaOrden("Bodega");
                        System.out.print("Se genero orden No.\t");
                        System.out.println(consultas.getUltimoID("DocumentoID","Documento"));
                    }
                }else{
                    System.out.println("Cliente no existe");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("No es posible realizar accion. Producto no existe");
        }
    }
    public void nuevoPedido(String estado) throws SQLException{
        connection.nuevaOrden(connection.getPersonaID(cliente,1),consultas.getProductoID(producto),Integer.parseInt(cantidadP),3,estado);
    }
    public void nuevaOrden(String estado) throws SQLException{
        connection.nuevaOrden(connection.getPersonaID(cliente,1),consultas.getProductoID(producto),Integer.parseInt(cantidadP),4,estado);
    }
}
