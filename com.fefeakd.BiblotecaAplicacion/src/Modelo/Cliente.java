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
public class Cliente extends  Usuario  {
    private String nombre;
    private String ciudad;
    private String apellido;
    private String documento;
    private String edad;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Cliente( String nombre, String apellido, String usuario, 
            String clave,String documento,String mail, String edad, String ciudad, String fechaDeRegistro, ETipoDeRango rango) {
        super(-1,usuario,clave,mail,fechaDeRegistro,rango);
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.apellido = apellido;
        this.documento = documento;
        this.edad = edad;
    }

    public Cliente() {
        super();
    }

    /*            
    formRegistro.getTextContraseña().getText(),
    formRegistro.getTextDni().getText(),
    formRegistro.getTextMail().getText(),
    formRegistro.getTextEdad().getText(),
    formRegistro.getTextCiudad().getText()*/

}
