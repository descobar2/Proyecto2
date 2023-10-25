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
    public int getPorductoID(String nombre) throws SQLException{
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
    public void asociarMaterial(int productoId, int materialID, int cantida) throws SQLException{
        String sql = "INSERT INTO ProductoMaterial (ProductoID, MaterialID, Cantidad) VALUES (?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, productoId);
        ps.setInt(2,materialID);
        ps.setInt(3,cantida);
        ps.executeUpdate();
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
//funcion para validar si existe un material y retornar id
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
//funcion para crear orden de compra.
    public void nuevaOrden(int proveedorID, int clienteID, int tipoID, String estado){
        try {
            String sql = "INSERT INTO Documento (ProveedorID, ClienteID, tipoID, estado) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,proveedorID);
            ps.setInt(2, clienteID);
            ps.setInt(3, tipoID);
            ps.setString(4, estado);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getPersonaID(String nombre, int tipo) throws SQLException{
         try {
            String sql = "SELECT PersonaID FROM Persona WHERE Nombre = ? AND TipoID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,nombre);
            ps.setInt(2,tipo);
            try(ResultSet rs = ps.executeQuery();){
                if(rs.next()){
                    return rs.getInt("PersonaID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public ArrayList<ListaMateriales> listaMaterial(int productoID, int cantidad){      
        ArrayList<ListaMateriales> datos = new ArrayList<ListaMateriales>();
        ArrayList<ListaMateriales> pedido = new ArrayList<ListaMateriales>();       
        int contador = 0;
        try {
            String sql = "SELECT MaterialID, Cantidad FROM ProductoMaterial WHERE ProductoID = ?";                    
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs =ps.executeQuery(sql);
            while(rs.next()){
                ListaMateriales p = new ListaMateriales();
                p.setMaterialID(rs.getInt("ProductoId"));
                p.setMaterialID(rs.getInt("MaterialID"));
                p.setCantidad(rs.getInt("PrecioProd"));
                datos.add(p);
            }
            System.out.print("Validadando");
            for (ListaMateriales p : datos){
                if((p.getCantidad()*cantidad)<(getDisponible(p.getMaterialID()))){
                    System.out.print(".");
                    ListaMateriales lm = new ListaMateriales();
                    lm.setMaterialID(p.getMaterialID());
                    lm.setMaterialID(p.getMaterialID());
                    lm.setCantidad(((getDisponible(p.getMaterialID()))-(p.getCantidad()*cantidad)));
                    pedido.add(lm);
                    contador++;
                }else{
                    System.out.println("Materiales en bodega");
                    //funcion para descontar materiales
                }
                if (contador>0){
                    Menu menu = new Menu();
                    System.out.println("Materiales no disponibles, desea generar pedido");
                    if(menu.menuSiNo()){
                        //funcion crear pedido
                    }
                }                
                System.out.println(p.getId() + "\t" + p.getMaterialID()  + "\t" + p.getCantidad());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;
    }
    public int getDisponible(int materialID){
            try {
                String sql = "SELECT CantDisp FROM Material WHERE MaterialID = ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,materialID);
                    try(ResultSet rs = ps.executeQuery();){
                        if(rs.next()){
                            return rs.getInt("CantDisp");
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return -1;
    }
//----------------------------------------------------------------------    
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
}

