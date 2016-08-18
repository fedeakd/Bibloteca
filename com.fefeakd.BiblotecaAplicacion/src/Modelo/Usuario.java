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
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String clave;
    private String mail;
    private String fechaDeRegistro;
    private ETipoDeRango rango;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(String fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    public ETipoDeRango getRango() {
        return rango;
    }

    public void setRango(ETipoDeRango rango) {
        this.rango = rango;
    }

    public Usuario(int idUsuario ,String usuario, String clave, String mail, String fechaDeRegistro, ETipoDeRango rango) {
        this.idUsuario=idUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.mail = mail;
        this.fechaDeRegistro = fechaDeRegistro;
        this.rango = rango;
    }

    public Usuario() {
    }
    
    
    
}
