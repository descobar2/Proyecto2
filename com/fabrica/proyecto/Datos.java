package com.fabrica.proyecto;

public class Datos {
    private String productoId;
    private String nombre;
    private String precio;

    public void setId(String productoId){
        this.productoId = productoId;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPrecio(String precio){
        this.precio = precio;
    }
    public String getId(){
        return productoId;
    }
    public String getNombre(){
        return nombre;
    }
    public String getPrecio(){
        return precio;
    }
}
