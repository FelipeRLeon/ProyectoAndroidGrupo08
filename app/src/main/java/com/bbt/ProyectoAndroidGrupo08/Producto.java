package com.bbt.ProyectoAndroidGrupo08;

public class Producto {
    private String ref, nom;
    private int precio;

    public Producto(){
        this.ref = "";
        this.nom = "";
        this.precio = 0;
    }

    public Producto(String ref, String nom, int precio){
        this.ref = ref;
        this.nom = nom;
        this.precio = precio;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
