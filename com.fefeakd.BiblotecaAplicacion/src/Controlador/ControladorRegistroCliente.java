/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.*;
import Modelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControladorRegistroCliente {

    private RegistroCliente formRegistro;
    private Conexion con;

    public ControladorRegistroCliente(RegistroCliente formRegistro) {
        con = new Conexion();
        this.formRegistro = formRegistro;
    }

    public boolean ComprobarLogin() {
 
        return false;
    }

    public boolean GestorDeDatos() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(dt);

        Cliente cliente = new Cliente(
                formRegistro.getTextNombre().getText(),
                formRegistro.getTextApellido().getText(),
                formRegistro.getTextUsuario().getText(),
                formRegistro.getTextContraseña().getText(),
                formRegistro.getTextDni().getText(),
                formRegistro.getTextMail().getText(),
                formRegistro.getTextEdad().getText(),
                formRegistro.getTextCiudad().getText(),
                fecha,
                ETipoDeRango.Cliente
        );

        if ((cliente.getNombre().equals("")) || (cliente.getApellido().equals("")) || (cliente.getUsuario().equals(""))
                || (cliente.getClave().equals("")) || (cliente.getDocumento().equals("")) || (cliente.getMail().equals(""))
                || (cliente.getEdad().equals("")) || (cliente.getCiudad().equals(""))) {
            JOptionPane.showMessageDialog(null, "Error,algun campo se encuentra vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (ComprobarSiExiste(cliente.getUsuario(), cliente.getMail())) {
            JOptionPane.showMessageDialog(null, "Error,El usuario o mail  ya se encuentra disponible ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cliente.getClave().equals(formRegistro.getTextRepContraseña().getText())) {

            JOptionPane.showMessageDialog(null, "Error,las contraseña no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!ValidarMail(cliente.getMail())) {
            JOptionPane.showMessageDialog(null, "Error,El mail  no es correcto \n ej pepito@algo.com", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!ValidarEdad(cliente.getEdad())) {
            JOptionPane.showMessageDialog(null, "Error,La edad no es correcta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        InsertarCliente(cliente);
        JOptionPane.showMessageDialog(null, "Felicitaciones, te has registrado", "Error", JOptionPane.ERROR_MESSAGE);
        return true;
    }

    public boolean ValidarEdad(String numero) {
        try {
            int num = Integer.parseInt(numero);
            if ((num > 0) && (num < 100)) {
                return true;
            }
        } catch (NumberFormatException e1) {

            return false;
        }
        return false;
    }

    public boolean ValidarMail(String mail) {
        char[] arrayChar = mail.toCharArray();
        boolean arroba = false;

        for (int i = 0; i < arrayChar.length; i++) {
            if (arrayChar[i] == '@') {

                arroba = true;

            }
            try {
                if (arroba && arrayChar[i] == '.' && arrayChar[i + 1] == 'c'
                        && arrayChar[i + 2] == 'o' && arrayChar[i + 3] == 'm' && (i + 4) == arrayChar.length) {

                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    public void InsertarCliente(Cliente cli) {
        try {
            int numero=-1;
           if(Conexion.InsertarUsuario((Usuario)cli)){
               ResultSet resultado = this.con.getCmd().executeQuery("SELECT idUsuario "
                       + "FROM usuarios "
                       + "ORDER BY idusuario  DESC "
                       + "LIMIT 1 ");

            while (resultado.next()) {
               numero=  resultado.getInt("idUsuario") ;

            }
               con.getCmd().execute(String.format("INSERT INTO clientes (idUsuario,nombre,apellido,edad,documento,ciudad) VALUES (%d,'%s','%s',%d,%d,'%s')",
                       numero,
                       cli.getNombre(),
                       cli.getApellido(),
                       Integer.parseInt(cli.getEdad()),
                       Integer.parseInt(cli.getDocumento()),
                       cli.getCiudad()));
           }
           else{
               JOptionPane.showMessageDialog(null, "Algo salio mal", "Error", JOptionPane.ERROR_MESSAGE);
           }
            
            
            

        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public boolean ComprobarSiExiste(String usuario, String mail) {
        
        try {
            ResultSet resultado = this.con.getCmd().executeQuery(String.format("Select count(*) as total from usuarios where "
                    + "usuario='%s' or mail='%s' ", usuario, mail));

            while (resultado.next()) {
                return resultado.getInt("total") > 0;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}
