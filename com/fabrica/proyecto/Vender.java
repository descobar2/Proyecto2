package com.fabrica.proyecto;

import java.sql.SQLException;
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
            //validar inventario, si hay existensia continua, si no crear pedido del material que hace falta.
            //Se asocia pedio a orden de compra.
            if(connection.validarDato(cliente)){                
                try {
                    nuevaOrden("Bodega");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("Cliente no existe, desea crearlo: ");
                if(menu.menuSiNo()){
                    persona.crearCliente();
                    try {
                        nuevaOrden("Bodega");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
            System.out.println("No es posible realizar accion. Producto no existe");
        }
    }
    public void nuevoPedido(String estado){
        
    }
    public void nuevaOrden(String estado) throws SQLException{
        connection.nuevaOrden(0,connection.getPersonaID(cliente,1),1,estado);
    }
}
