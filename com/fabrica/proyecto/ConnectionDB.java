package com.fabrica.proyecto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB{
    final String HOST = "proyecto2.ctlvgikixphm.us-east-1.rds.amazonaws.com:3306/Tarea";
    final String DB_URL = String.format("jdbc:mysql://%s", HOST);
    
    try{
    final Connection con = DriverManager.getConnection(DB_URL,"progra","Guate2021+");
    final Statement stmt = con.createStatement();         
    }catch(SQLException e){
        e.printStackTrace();
    }

    public static void instertDB(Connection con, Statement stmt){
        try{ 
            String sql = "INSERT INTO Proveedor (ProveedorID, Nombre, Direccion, Telefono) VALUES (?,?,?,?)";
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
    public static void updateDB(Connection con, Statement stmt){
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
    public static void deleteDB(Connection con, Statement stmt){
        try{
            String sql = "DELETE FROM Tarea.Proveedor WHERE ProveedorID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, 4);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Datos> showTable(Connection con, Statement stmt){
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
