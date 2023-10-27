package com.fabrica.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConsultasDB {
    final String HOST = "proyecto2.ctlvgikixphm.us-east-1.rds.amazonaws.com:3306/FABRICA";
    final String DB_URL = String.format("jdbc:mysql://%s", HOST);
    private Connection con;
    Statement stmt;

    public ConsultasDB(){
        try{
        con = DriverManager.getConnection(DB_URL,"progra","Guate2021+");
        stmt = con.createStatement();         
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
//Consultas tabla Material
    public int getBusquedaMatDisp(String nombre){
        int disponible=0;
        String dato = getDato("Material","CantDisp","NombreMat",nombre);
        disponible = Integer.parseInt(dato);
        return disponible;
    }
    public int getBusquedaMatDisp(int materialID){
        int disponible=0;
        String sMaterialID = "" + materialID;
        String dato = getDato("Material","CantDisp","materialID",sMaterialID);
        disponible = Integer.parseInt(dato);
        return disponible;
    }
    public String getNombreMat(int materialID){
        String sMaterialID = "" + materialID;
        String dato = getDato("Material","NombreMat","materialID",sMaterialID);          
        return dato;
    }
    public int getMaterialID(String nombre){
        String dato = getDato("Material","materialID","NombreMat",nombre);          
        return Integer.parseInt(dato);
    }
//Consutlas tabla persona
    public String getPersona(String nombre){
        String dato = getDato("Persona","Nit","Nombre",nombre);
        return dato;
    }
    public int gerPersonaN(String nombre){
        String dato = getDato("Persona","PersonaID","Nombre",nombre);
        return Integer.parseInt(dato);
    }
    public String getPersona(int personaID){
        String sPersonaID = "" + personaID;
        String dato = getDato("Persona","Nombre","personaID",sPersonaID);
        return dato;
    }
    public int getPersonaTipo(String cliente){
        String dato = getDato("Persona","TipoID","Nombre",cliente);
        return Integer.parseInt(dato);
    }  
//Consultas tabla Producto
    public int getProductoID(String nombre){
        String dato = getDato("Producto","ProductoID","NombrePro",nombre);
        return Integer.parseInt(dato);
    }
//Consultr tabla Documento
    public String getDocID(int clienteID){
        String sClienteID = "" + clienteID;
        String dato = getDato("Documento","DocumentoID","ClienteID",sClienteID);
        return dato;
    }
//Consultas tabla ProductoMaterial
    public ArrayList<ListaMateriales> listaMaterial(int productoID) throws SQLException{      
        ArrayList<ListaMateriales> materiales = new ArrayList<ListaMateriales>();
        String sql = "SELECT MaterialID, Cantidad FROM ProductoMaterial WHERE ProductoID = ?";                    
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,productoID);
        ResultSet rs =ps.executeQuery();
        while(rs.next()){
            ListaMateriales lm = new ListaMateriales();
            lm.setMaterialID(rs.getInt("MaterialID"));
            lm.setCantidad(rs.getInt("Cantidad"));
            materiales.add(lm);
        }       
        return materiales;
    }
//Consultas tabla * campo 
    public String getDato(String tabla, String campoObtener, String campoBuscar, String busqueda){
        String respuesta="-1";
        try{
            String sql = "SELECT "+ campoObtener +" FROM "+ tabla +" WHERE "+ campoBuscar +" = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, busqueda);
            ResultSet rs =ps.executeQuery();
            if (rs.next()) {
                respuesta = rs.getString(campoObtener);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return respuesta;
    }
//Consultar ulitmo ID generado
    public int getUltimoID(String tabalID, String tabla) {
        try {
            String sql = "SELECT MAX(" + tabalID + ") AS UltimoID FROM " + tabla;
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("UltimoID"); // Obtiene el valor del último ID
            } else {
                return -1; // Si no hay resultados
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Si hay un error
    }
// Consultar inventario
    public void showInventario() throws SQLException{
        ArrayList<ListaMateriales> inventario = new ArrayList<ListaMateriales>();
        String sql = "SELECT * FROM Material"; 
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
              
        while(rs.next()){
            ListaMateriales inv = new ListaMateriales();
            inv.setNombreMat(rs.getString("NombreMat"));
            inv.setCantidad(rs.getInt("CantDisp"));
            inventario.add(inv);
        }
        System.out.println("Inventario");
        for (ListaMateriales inv : inventario){
            System.out.println(inv.getNombreMat()+"\t"+inv.getCantidad());
        }
    }
}
