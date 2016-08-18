/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuario;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Conexion {

    private Connection con;
    private Statement cmd;

    public Conexion() {
        try {

   
            this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibloteca", "root", "fefeakd");
            cmd = con.createStatement();

    // Otros y operaciones sobre la base de datos...
        } catch (SQLException ex) {

            // Mantener el control sobre el tipo de error
            System.out.println("SQLException: " + ex.getMessage());

        }
    }

    public Conexion(String BD) {
        try {

            this.con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + BD, "root", "fefeakd");
            cmd = con.createStatement();

    // Otros y operaciones sobre la base de datos...
        } catch (SQLException ex) {

            // Mantener el control sobre el tipo de error
            System.out.println("SQLException: " + ex.getMessage());

        }

    }

    public Connection getCon() {
        return con;
    }

    public Statement getCmd() {
        return cmd;
    }

    public void cerrar() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.err.println("No puede cerrar conexion a la Base de Datos.");
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error Cerrar BD...",
                    "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    public static boolean InsertarUsuario(Usuario user){
         try {
            Conexion  con = new Conexion();
            con.getCmd().execute(String.format("INSERT INTO usuarios(usuario,clave,fecha,idRango,mail ) VALUES ('%s','%s','%s',1,'%s')",
                    user.getUsuario(),user.getClave(),user.getFechaDeRegistro(),user.getMail()));

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
    }
}
