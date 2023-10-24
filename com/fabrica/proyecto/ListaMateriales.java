package com.fabrica.proyecto;

public class ListaMateriales {
    private int productoId;
    private int materialID;
    private int cantidad;
    private String nombreMat;
    
//Setters
    public void setId(int productoId){
        this.productoId = productoId;
    }
    public void setMaterialID(int materialID){
        this.materialID = materialID;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setNombreMat(String nombreMat){
        this.nombreMat = nombreMat;
    }

//Getters
    public int getId(){
        return productoId;
    }
    public int getMaterialID(){
        return materialID;
    }
    public int getCantidad(){
        return cantidad;
    }
    public String getNombreMat(){
        return nombreMat;
    }
}
