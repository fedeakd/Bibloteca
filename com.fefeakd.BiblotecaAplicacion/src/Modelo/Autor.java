/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Autor {
    private int id ;
    private String nombre;
    private String apellido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Autor(int id,  String nombre, String apellido) {
        this.id=id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public Autor(){
    }
    
    
    @Override
    public String toString(){
        return nombre+" "+apellido;
    }
    
    
    
}
