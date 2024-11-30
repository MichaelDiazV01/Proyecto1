package com.steepdiaz.proyecto1;

public class Usuario {

    private String numero, nombre;

    public Usuario(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
        
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    @Override
    public String toString() {
        return "Usuario{" + "numero=" + numero + ", nombre=" + nombre  + '}';
    }
}