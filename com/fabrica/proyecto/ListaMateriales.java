package com.fabrica.proyecto;

public class ListaMateriales {
    private int materialID;
    private String nombreMat;
    private String medida;
    private Float precioMat;
    private int cantidad;
    
//Setters
    public void setMaterialID(int materialID){
        this.materialID = materialID;
    }
    public void setNombreMat(String nombreMat){
        this.nombreMat = nombreMat;
    }
    public void setMedida(String medida){
        this.medida = medida;
    }
    public void setPrecioMat(Float precioMat){
        this.precioMat = precioMat;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
//Getters
    public int getMaterialID(){
        return materialID;
    }
    public String getNombreMat(){
        return nombreMat;
    }
    public String getMedida(){
        return medida;
    }
    public Float getPrecioMat(){
        return precioMat;
    }
    public int getCantidad(){
        return cantidad;
    }
}
