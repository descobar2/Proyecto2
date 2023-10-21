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
//funcion para ingresar personas como cliente o proveedor
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
//funcion para ingresar materiales
    public void nuevoMaterial(String nombre, String medida, float precio){
        try{
            String sql = "INSERT INTO Material (NombreMat, Medida, PrecioMat, CantDisp) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nombre);
            ps.setString(2, medida);
            ps.setFloat(3, precio);
            ps.setInt(4,0);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//funcion para ingresar compra
    public void nuevaCompra(String nombre, int cantidad){
        try{
            String sql = "UPDATE Material SET CantDisp = ? WHERE NombreMat = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setString(2,nombre);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//funcion para ingresar nuevo producto
    public void nuevoProdcuto(String nombre, Float precio){
        try {
            String sql = "INSERT INTO Producto (NombrePro, PrecioProd) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nombre);
            ps.setFloat(2, precio);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getID() throws SQLException{
        String sql = "SET @nuevoProductoId = LAST_INSERT_ID()";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);         
    return rs.getInt("ProductoID");
    }
    public void asociarMaterial(int productoId, int materialID, int cantida) throws SQLException{
        String sql = "INSERT INTO ProdcutoMaterial (ProductoID, MaterialID, Cantidad) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productoId);
        ps.setInt(2,materialID);
        ps.setInt(3,cantida);
    }
//funcion para validar si existe un elemento
    public boolean validarDato(String dato){
        try {
            String sql = "SELECT COUNT(*) FROM Material WHERE NombreMat = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, dato);              
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int rowCount = rs.getInt(1);
                        return rowCount > 0;  // Si rowCount es mayor que 0, el valor existe.
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//funcio para validar si existe un material y retornar id
    public int getMaterialId(String nombre){
        try {
            String sql = "SELECT MaterialID FROM Material WHERE NombreMat = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nombre);
            try(ResultSet rs = ps.executeQuery();){
                if(rs.next()){
                    return rs.getInt("MaterialID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
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
    public static ArrayList<Datos> showProductos(Connection con){
        ArrayList<Datos> datos = new ArrayList<Datos>();
        try{
            String sql = "SELECT * FROM FABRICA.Producto;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs =ps.executeQuery(sql);

            while(rs.next()){
                Datos p = new Datos();
                p.setId(rs.getString("ProductoId"));
                p.setNombre(rs.getString("NombrePro"));
                p.setPrecio(rs.getString("PrecioProd"));
                datos.add(p);
            }
            for (Datos p : datos){
                System.out.println(p.getId() + "\t" + p.getNombre()  + "\t" + p.getPrecio());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return datos;
    }
}

