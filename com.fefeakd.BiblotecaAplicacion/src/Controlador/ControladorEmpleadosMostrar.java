/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ETipoDeRango;
import Modelo.Empleado;
import Vista.EmpleadoMostrar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ControladorEmpleadosMostrar {

    private ArrayList<Empleado> empleados;
    private EmpleadoMostrar eMostrar;
    private Conexion  con;
    public ControladorEmpleadosMostrar(EmpleadoMostrar eMostrar) {
        this.eMostrar = eMostrar;
        con = new Conexion();
        this.empleados= new ArrayList<>();
    }

    public void CargarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.eMostrar.getTablaEmpleados().getModel();
        for (Empleado e : empleados) {
            dtm.addRow(new Object[]{
                e.getIdUsuario(),
                e.getUsuario(),
                e.getNombre(),
                e.getApellido(),
                e.getEdad(),
                e.getSueldo()
            });
        }

    }
    
    public boolean TraerEmpleados(){
       try {
            ResultSet r = this.con.getCmd().executeQuery(String.format("SELECT e.* , u.*  FROM empleados as e  "
                    + "INNER JOIN usuarios as u ON  u.idusuario= e.idusuario "
                  ));

            while (r.next()) {
                Empleado e = new Empleado();
                e.setApellido(r.getString("apellido"));
                e.setNombre(r.getString("nombre"));
                e.setUsuario(r.getString("usuario"));
                e.setClave(r.getString("clave"));
                e.setIdUsuario(r.getInt("idUsuario"));
                e.setRango(ETipoDeRango.Empleado);
                e.setMail(r.getString("mail"));
                e.setSueldo(r.getFloat("sueldo"));
                e.setIdUsuario(r.getInt("idUsuario"));
                e.setIdAdministrador(r.getInt("idAdministrador"));
                e.setEdad(r.getInt("edad"));
                e.setDocumento(r.getInt("documento"));
                e.setFechaDeRegistro(r.getString("fecha"));
                this.empleados.add(e);
              
            }
              return true;

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

     }

}
