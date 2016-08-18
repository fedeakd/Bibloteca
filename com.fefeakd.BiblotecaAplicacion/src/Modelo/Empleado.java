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
public class Empleado extends Usuario {
    private String nombre;
    private String apellido;
    private int edad; 
    private int documento;
    private float sueldo;
    private int idAdministrador;

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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Empleado(String nombre, String apellido, int edad, int documento, float sueldo, int idAdministrador,
            int idUsuario, String usuario, String clave, String mail, String fechaDeRegistro, ETipoDeRango rango) {
        super(idUsuario, usuario, clave, mail, fechaDeRegistro, rango);
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.documento = documento;
        this.sueldo = sueldo;
        this.idAdministrador = idAdministrador;
    }

    public Empleado() {
        super();
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", documento=" + documento + ", sueldo=" + sueldo + ", idAdministrador=" + idAdministrador + '}';
    }
    
    
}
