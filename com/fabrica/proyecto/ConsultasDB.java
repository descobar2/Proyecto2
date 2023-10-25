package com.fabrica.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public String getPersona(String cliente){
            String dato = getDato("Persona","Nit","Nombre",cliente);
        return dato;
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
    public int getPorductoID(String nombre){
        try {
        String sql = "SELECT ProductoID FROM Producto WHERE NombrePro = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,nombre);
        try(ResultSet rs = ps.executeQuery();){
            if(rs.next()){
                return rs.getInt("ProductoID");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1;
    }

//Consultas tabla ProductoMaterial

    public String getDato(String tabla, String campoObtener, String campoBuscar, String busqueda){
        String respuesta="Null";
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
}
