/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.EABM;
import Modelo.ETipoDeRango;
import Modelo.Empleado;
import Vista.CuestionarioEmpleado;
import Vista.EmpleadoABM;
import Vista.ImagenFondo;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.edisoncor.gui.panel.PanelTranslucido;

/**
 *
 * @author Usuario
 */
public class ControladorEmpleadoABM {

    private EmpleadoABM VistaempleadoABM;
    private Empleado empleado;
    private Conexion con;

    public ControladorEmpleadoABM(EmpleadoABM empleadoABM) {
        con = new Conexion();
        this.VistaempleadoABM = empleadoABM;
        if (this.VistaempleadoABM.geteABM() != EABM.Alta) {
            this.VistaempleadoABM.setShow(CompletarCamposBajaOModificacion());
        }

    }

    public boolean CompletarCamposBajaOModificacion() {
        int dni = -1, id = -1;
        CuestionarioEmpleado cE = new CuestionarioEmpleado();

        int result = JOptionPane.showConfirmDialog(null, cE,
                "Complete alguno de los campos!!", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            return false;
        }

        dni = ValidarNumero(cE.getTextDni().getText());
        id = ValidarNumero(cE.getTextId().getText());

        if (!TraerEmpleado(dni, cE.getTextUsuario().getText(), id)) {
            System.out.println("hola");
            return false;
        }

        VistaempleadoABM.getTextUsuario().setText(this.empleado.getUsuario());
        VistaempleadoABM.getTextNombre().setText(this.empleado.getNombre());
        VistaempleadoABM.getTextApellido().setText(this.empleado.getApellido());
        VistaempleadoABM.getTextSueldo().setText(Float.toString(this.empleado.getSueldo()));
        VistaempleadoABM.getTextDocumento().setText(Integer.toString(this.empleado.getDocumento()));
        VistaempleadoABM.getTextClave().setText(this.empleado.getClave());
        VistaempleadoABM.getTextMail().setText(this.empleado.getMail());
        VistaempleadoABM.getTextEdad().setText(Integer.toString(this.empleado.getEdad()));

        if (this.VistaempleadoABM.geteABM() == EABM.Baja) {
            Component[] c = this.VistaempleadoABM.getContentPane().getComponents();
            for (Component item : c) {
                if (item instanceof PanelTranslucido) {
                    for (Component item2 : ((PanelTranslucido) item).getComponents()) {
                        if (item2 instanceof JTextField) {
                            ((JTextField) item2).setEditable(false);

                        }
                    }
                }

            }
        } else if (this.VistaempleadoABM.geteABM() == EABM.Modificacion) {
            this.VistaempleadoABM.getTextUsuario().setEditable(false);

        }
        return true;
    }

    public boolean GestionarEmpelado() {
        //Cuando se da aceptar

        switch (this.VistaempleadoABM.geteABM()) {
            case Alta:
                if (ValidarDatos()) {
                    return AltaEmpleado();
                }
                return false;

            case Modificacion:
                if (ValidarDatos()) {
                    return ModificarEmpleado();
                }
                return false;

            case Baja:
                return BajaEmpleado(empleado.getIdUsuario());

            //JOptionPane.showMessageDialog(null, "Valido", "T", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private boolean ValidarDatos() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(dt);
        ControladorRegistroCliente CRC = new ControladorRegistroCliente(null);
        if (!CRC.ValidarEdad(VistaempleadoABM.getTextEdad().getText())) {
            JOptionPane.showMessageDialog(null, "Error,La edad no es correcta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (VistaempleadoABM.getTextEdad().getText().equals("") || VistaempleadoABM.getTextDocumento().getText().equals("")
                || VistaempleadoABM.getTextSueldo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Error,campo vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            int id = empleado == null ? -1 : empleado.getIdUsuario();
            this.empleado = new Empleado(VistaempleadoABM.getTextNombre().getText(),
                    VistaempleadoABM.getTextApellido().getText(),
                    Integer.parseInt(VistaempleadoABM.getTextEdad().getText()),
                    Integer.parseInt(VistaempleadoABM.getTextDocumento().getText()),
                    Float.parseFloat(VistaempleadoABM.getTextSueldo().getText()),
                    VistaempleadoABM.getIdAdministrador(),
                    id,
                    VistaempleadoABM.getTextUsuario().getText(),
                    VistaempleadoABM.getTextClave().getText(),
                    VistaempleadoABM.getTextMail().getText(),
                    fecha,
                    ETipoDeRango.Empleado
            );
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Error,formato  de numero no valido ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (empleado.getNombre().equals("") || empleado.getApellido().equals("") || empleado.getUsuario().equals("") || empleado.getMail().equals("")) {
            JOptionPane.showMessageDialog(null, "Error,campo vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!CRC.ValidarMail(empleado.getMail())) {
            JOptionPane.showMessageDialog(null, "Error,Mail  erroneo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (ComprobarSiExiste(empleado.getUsuario(), empleado.getMail()) && (this.VistaempleadoABM.geteABM() == EABM.Alta)) {
            JOptionPane.showMessageDialog(null, "Error,El usuario o mail  ya se encuentra disponible ", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean TraerEmpleado(int dni, String usuario, int id) {
        try {
            ResultSet r = this.con.getCmd().executeQuery(String.format("SELECT e.* , u.*  FROM empleados as e  "
                    + "INNER JOIN usuarios as u ON  u.idusuario= e.idusuario "
                    + "WHERE e.idusuario=%d OR e.documento=%d OR u.usuario='%s'", id, dni, usuario));

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
                this.empleado = e;
   
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    private boolean ModificarEmpleado() {
        try {
            con.getCmd().execute(String.format("UPDATE usuarios SET  clave='%s', mail='%s' WHERE  idUsuario=%d ", empleado.getClave(), empleado.getMail(), empleado.getIdUsuario()));
            con.getCmd().execute(String.format(Locale.ROOT, "UPDATE empleados SET  nombre='%s', apellido='%s', sueldo=%f,  documento=%d, edad=%d"
                    + " WHERE  idUsuario=%d ", empleado.getNombre(), empleado.getApellido(), empleado.getSueldo(), empleado.getDocumento(), empleado.getEdad(), empleado.getIdUsuario()));
            JOptionPane.showMessageDialog(null, "Empleado Modificado!!! ", "Modificacion", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Empleado no se puedo Modificar!!! ", "Modficacion", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }

    private boolean BajaEmpleado(int idUsuario) {
        try {
            con.getCmd().execute(String.format("Delete from Empleados where idUsuario=%d ", idUsuario));
            con.getCmd().execute(String.format("Delete from Usuarios where idUsuario=%d ", idUsuario));
            JOptionPane.showMessageDialog(null, "Empleado dado de baja!!! ", "Baja", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Empleado no se pudo dar de baja!!! ", "Baja", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);

            return false;

        }
    }

    public boolean AltaEmpleado() {
        try {
            int numero = -1;
            if (Conexion.InsertarUsuario(empleado)) {

                ResultSet resultado = this.con.getCmd().executeQuery("SELECT idUsuario "
                        + "FROM usuarios "
                        + "ORDER BY idusuario  DESC "
                        + "LIMIT 1 ");
                while (resultado.next()) {
                    numero = resultado.getInt("idUsuario");

                }
                con.getCmd().execute(String.format(Locale.ROOT, "INSERT INTO  empleados(idUsuario,nombre,apellido,sueldo,idAdministrador,documento,edad  )"
                        + "VALUES (%d ,'%s' , '%s' , %.2f , %d , %d , %d)", numero, empleado.getNombre(), empleado.getApellido(), empleado.getSueldo(),
                        empleado.getIdAdministrador(), empleado.getDocumento(), empleado.getEdad()));

            } else {
                JOptionPane.showMessageDialog(null, "Algo salio mal", "Error", JOptionPane.ERROR_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Empleado registrado!!! ", "Registro", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lo siento,  no se puedo registrar ", "Registro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
    
    private void CambiarDeColorLasEtiqueta(java.awt.Color color){
          Component[] c = this.VistaempleadoABM.getContentPane().getComponents();
            for (Component item : c) {
                if (item instanceof PanelTranslucido) {
                    for (Component item2 : ((PanelTranslucido) item).getComponents()) {
                        if (item2 instanceof JLabel) {
                            ((JLabel) item2).setForeground(color);

                        }
                    }
                    return;
                }

            }
    }
    
    public void ModificarVista(){
        JLabel  titulo= this.VistaempleadoABM.getEtiquetaTitulo();
        titulo.setText(this.VistaempleadoABM.geteABM()+" " +titulo.getText());
        int [] colorP= new int[3];
        int [] colorS= new int[3];
        
        
        switch(this.VistaempleadoABM.geteABM()){
            case Alta:
                colorP[0]=204;
                colorP[1]=255;
                colorP[2]=204;
                
                colorS[0]=255;
                colorS[1]=255;
                colorS[2]=204;
                break;
            case Baja:
                colorP[0]=0;
                colorP[1]=0;
                colorP[2]=0;
                
                colorS[0]=0;
                colorS[1]=0;
                colorS[2]=0;
                titulo.setForeground(java.awt.Color.white);
                CambiarDeColorLasEtiqueta(java.awt.Color.white) ;
                break;
            case Modificacion:
                colorP[0]=255;
                colorP[1]=204;
                colorP[2]=0;

                colorS[0]=255;
                colorS[1]=204;
                colorS[2]=255;
                titulo.setForeground(java.awt.Color.green);
                CambiarDeColorLasEtiqueta(java.awt.Color.white) ;
                break;
                
        }
        this.VistaempleadoABM.getPanel().setColorPrimario(new java.awt.Color(colorP[0],colorP[1],colorP[2]));
        this.VistaempleadoABM.getPanel().setColorSecundario(new java.awt.Color(colorS[0],colorS[1],colorS[2]));
    }
    public static int ValidarNumero(String number) {
        try {
            return Integer.parseInt(number);

        } catch (NumberFormatException e1) {
            return -1;

        }
    }
    
     public static float ValidarDecimal(String number) {
        try {
            return Float.parseFloat(number);

        } catch (NumberFormatException e1) {
            return -1;

        }
    }
}
