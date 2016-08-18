/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.*;

/**
 *
 * @author Usuario
 */
public class ControladorLogin {

    private Login login;
    private Conexion con;

    public ControladorLogin(Login login) {
        this.con = new Conexion();
        this.login = login;

    }

    public boolean ComprobarLogin() {
        String usuario = login.getTextUsuario().getText();
        String clave = login.getTextContraseña().getText();

        return ComprobarUsuarioYClave(usuario, clave);
    }

    public Usuario DarUsuario(String usuario, String clave) {
        try {
            ResultSet resultado = this.con.getCmd().executeQuery(String.format("SELECT u.clave, u.usuario,u.idusuario ,u.mail ,r.nombre,u.fecha "
                    + " FROM Usuarios AS u  "
                    + "INNER JOIN  Rango AS r ON r.idRango=u.idRango "
                    + "WHERE usuario='%s' AND clave='%s' ", usuario, clave));

            while (resultado.next()) {
                System.out.println(resultado.getString("nombre"));
                ETipoDeRango rango = ETipoDeRango.valueOf(resultado.getString("nombre"));
                
                Usuario u = new Usuario();
                u.setFechaDeRegistro(resultado.getString("fecha"));
                u.setRango(rango);
                u.setMail(resultado.getString("mail"));
                u.setUsuario(resultado.getString("usuario"));
                u.setClave(resultado.getString("clave"));
                u.setIdUsuario(resultado.getInt("idusuario"));
                   //c.setApellido(resultado.getString("apellido"));
                //c.setNombre(resultado.getString("nombre"));
                //c.setDocumento(resultado.getString("documento"));
                //c.setCiudad(resultado.getString("ciudad"));
                // c.setTipoUsuario(rango);
                //c.setEdad(Integer.toString(resultado.getInt("edad")));
                //c.setFechaDeRegistro(resultado.getString("fechaDeRegistro"));
                return u;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private boolean ComprobarUsuarioYClave(String usuario, String clave) {

        try {
            ResultSet resultado = this.con.getCmd().executeQuery(String.format("Select count(*) as total from Usuarios as c where "
                    + "usuario='%s' and clave='%s' ", usuario, clave));

            while (resultado.next()) {
                return resultado.getInt("total") > 0;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
