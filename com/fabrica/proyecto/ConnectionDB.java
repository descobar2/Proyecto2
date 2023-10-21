package com.fabrica.proyecto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB{
    final String HOST = "proyecto2.ctlvgikixphm.us-east-1.rds.amazonaws.com:3306/FABRICA";
    final String DB_URL = String.format("jdbc:mysql://%s", HOST);
    private Connection con;
    Statement stmt;

    public ConnectionDB(){
        try{
        con = DriverManager.getConnection(DB_URL,"progra","Guate2021+");
        stmt = con.createStatement();         
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
//funcion para ingresar personas
    public void nuevaPersona(int tipo, String nombre, String nit){
        try{
            String sql = "INSERT INTO Persona (TipoID, Nombre, Nit) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,tipo);
            ps.setString(2, nombre);
            ps.setString(3, nit);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void instertDB(Connection con){
        try{ 
            String sql = "INSERT INTO Proveedor (Nombre, Direccion, Telefono) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,4);
            ps.setString(2, "Allan");
            ps.setString(3, "Guatemala");
            ps.setString(4, "24282428");
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }        
    }
    public void updateDB(Connection con){
        try{
            String sql = "UPDATE Proveedor SET Nombre = ? WHERE ProveedorID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"Marvin");
            ps.setInt(2, 4);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteDB(Connection con){
        try{
            String sql = "DELETE FROM Tarea.Proveedor WHERE ProveedorID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 4);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Datos> showTable(Connection con){
        ArrayList<Datos> datos = new ArrayList<Datos>();
        try{
            String sql = "SELECT * FROM Tarea.Proveedor;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs =ps.executeQuery(sql);

            while(rs.next()){
                Datos p = new Datos();
                p.setId(rs.getString("ProveedorID"));
                p.setNombre(rs.getString("Nombre"));
                p.setDireccion(rs.getString("Direccion"));
                p.setTelefono(rs.getString("Telefono"));
                datos.add(p);
            }
            for (Datos p : datos){
                System.out.println(p.getId() + "\t" + p.getNombre()  + "\t" + p.getDireccion() + "\t" + p.getTelefono());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return datos;
    }
}

