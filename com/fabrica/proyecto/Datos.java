package com.fabrica.proyecto;

public class Datos {
    private String proveedorId;
    private String nombre;
    private String direccion;
    private String telefono;

    public void setId(String proveedorId){
        this.proveedorId = proveedorId;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public String getId(){
        return proveedorId;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getTelefono(){
        return telefono;
    }
}
