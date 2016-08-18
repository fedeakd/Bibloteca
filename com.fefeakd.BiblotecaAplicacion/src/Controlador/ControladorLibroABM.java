/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.CuestionarioLibro;
import Vista.LibroABM;
import java.awt.Component;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTextField;
import org.edisoncor.gui.panel.PanelRectTranslucido;
import org.edisoncor.gui.panel.PanelTranslucido;

/**
 *
 * @author Usuario
 */
public class ControladorLibroABM {

    private EABM eABM;
    private Libro libro;
    private Editorial[] editoriales;
    private Autor[] autores;
    private Genero[] generos;
    private LibroABM libroABM;
    private Conexion con;
    private int id;
    public ControladorLibroABM(LibroABM libroABM) {
        this.libroABM = libroABM;
        this.con = new Conexion();
        this.eABM = this.libroABM.getEabm();

    }

    public void CompletarCampos() {
        CompletarCombo();
        boolean mostrar;
        switch (this.eABM) {
            case Alta:
                break;
            case Modificacion:
                mostrar = PreParararLibroBajaYModificar();
                this.libroABM.setEstado(mostrar);
              
                break;
            case Baja:

                mostrar = PreParararLibroBajaYModificar();
                this.libroABM.setEstado(mostrar);
                NoEditableCampo();
                break;
        }

    }

    private void CompletarCombo() {
        JComboBox comboAutor = this.libroABM.getComboAutor();
        JComboBox comboGenero = this.libroABM.getComboGenero();
        JComboBox comboEditorial = this.libroABM.getComboEditorial();

        for (Autor a : TraerAutores()) {
            comboAutor.addItem(a);
        }
        for (Genero g : TraerGenero()) {
            comboGenero.addItem(g);
        }
        for (Editorial e : TraerEditoriales()) {
            comboEditorial.addItem(e);
        }

    }

    private ArrayList<Autor> TraerAutores() {
        ResultSet r = null;
        try {
            ArrayList<Autor> autores = new ArrayList<>();
            r = this.con.getCmd().executeQuery(String.format("SELECT * FROM autores ORDER BY apellido ASC"));
            while (r.next()) {
                autores.add(new Autor(r.getInt("idAutor"), r.getString("nombre"), r.getString("apellido")));

            }
            return autores;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                r.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorLibroABM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public boolean GestionarAceptar() {
        boolean ban=false;
        String mensaje="Algo salio mal";
        switch (this.eABM) {
            case Alta:
                if (ValidarDatos()) {
                    ban= InsertarLibro();
                    mensaje="Libro agregado";
                }
                break;
            case Modificacion:
                if (ValidarDatos()) {
                     mensaje="Libro modificado";
                     ban=  ModificarLibro();
                }
                break;
            case Baja:
                ban=BorrarLibro();
                mensaje=ban?"Libro borrado":mensaje;
                break;
                
              
        }
        if(ban){
            JOptionPane.showMessageDialog(null, mensaje, "Libro", JOptionPane.INFORMATION_MESSAGE);
        }
        return ban;
    }

    private boolean ValidarDatos() {
        float precio = ControladorEmpleadoABM.ValidarDecimal(this.libroABM.getTextPrecio().getText());
        int cantidad = this.libroABM.getTextCantidad().getText().equals("") ? -1 : Integer.parseInt(this.libroABM.getTextCantidad().getText());
        String nombre = this.libroABM.getTextNombre().getText();

        int autor = ((Autor) this.libroABM.getComboAutor().getSelectedItem()).getId();
        int genero = ((Genero) this.libroABM.getComboGenero().getSelectedItem()).getId();
        int editorial = ((Editorial) this.libroABM.getComboEditorial().getSelectedItem()).getId();

        int valoracion = this.libroABM.getSliderValoracion().getValue();

        if (cantidad == -1 || nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Error,Complete campos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (precio == -1d) {
            JOptionPane.showMessageDialog(null, "Error,el precio no es correcta", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (ComprobarSiExiste(this.libroABM.getTextNombre().getText()) && eABM == EABM.Alta) {
            JOptionPane.showMessageDialog(null, "Error,el libro ya se encuentra", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String fecha = ValidarFecha();
        if (fecha.equals("")) {
            JOptionPane.showMessageDialog(null, "Error,formato no valido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (new Date().before(this.libroABM.getCalendario().getDate())) {
            JOptionPane.showMessageDialog(null, "Error,el libro no puede ser publicada despues de hoy", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        this.libro = new Libro(-1, nombre, cantidad, precio,
                fecha, valoracion, genero, autor, editorial, this.libroABM.getIdUsuario());

        return true;
    }

    public boolean ComprobarSiExiste(String nombre) {
        ResultSet r = null;
        try {
            ResultSet resultado = this.con.getCmd().executeQuery(String.format("Select count(*) as total from libros where "
                    + "nombre='%s' ", nombre));

            while (resultado.next()) {
                return resultado.getInt("total") > 0;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorRegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        return false;

    }

    private ArrayList<Genero> TraerGenero() {
        ResultSet r = null;
        try {
            ArrayList<Genero> generos = new ArrayList<>();
            r = this.con.getCmd().executeQuery(String.format("SELECT * FROM generos"));
            while (r.next()) {
                generos.add(new Genero(r.getInt("idGenero"), r.getString("nombre")));
            }
            return generos;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CerrarConsulta(r);
        }
        return null;
    }

    private ArrayList<Editorial> TraerEditoriales() {
        ResultSet r = null;
        try {
            ArrayList<Editorial> editorial = new ArrayList<>();
            r = this.con.getCmd().executeQuery(String.format("SELECT * FROM editoriales"));
            while (r.next()) {
                editorial.add(new Editorial(r.getInt("idEditorial"), r.getString("nombre")));
            }
            return editorial;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            CerrarConsulta(r);
        }
        return null;
    }

    private String ValidarFecha() {

        try {
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            String date = DATE_FORMAT.format(this.libroABM.getCalendario().getDate());

            return date;
        } catch (NullPointerException e) {

            return "";
        }
    }
    private  boolean ModificarLibro( ){
        try {
            System.out.println(id);
            con.getCmd().execute(String.format(Locale.ROOT, "UPDATE libros SET nombre='%s', idautor=%d, "
                    + "cantidad=%d, precio=%f,  fechaDEPublicacion='%s', idGenero=%d,  valoracion=%d, idEditorial=%d, idUsuario=%d "
                    + "WHERE idlibro=%d",
                    libro.getNombre(),
                    libro.getIdAutor(),
                    libro.getCantidad(),
                    libro.getPrecio(),
                    libro.getFechaPublicacion(),
                    libro.getIdGenero(),
                    libro.getValoracion(),
                    libro.getIdEditorial(),
                    libro.getIdUsuario(),
                    this.id
                    
            ));
            return true;
        } catch (SQLException ex) {
           
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }
    private boolean BorrarLibro(){
         try {
            con.getCmd().execute(String.format("DELETE FROM libros WHERE idLibro=%d",this.id));
            return true;
        } catch (SQLException ex) {
           
            Logger.getLogger(ControladorEmpleadoABM.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }
    }
    private boolean InsertarLibro() {
        try {
            con.getCmd().execute(String.format(Locale.ROOT, "INSERT INTO Libros (nombre,idautor,cantidad,precio,fechaDePublicacion,idGenero,"
                    + "valoracion,idEditorial,idUsuario)  VALUES ('%s',%d,%d,%f,'%s',%d,%d,%d,%d) ",
                    libro.getNombre(),
                    libro.getIdAutor(),
                    libro.getCantidad(),
                    libro.getPrecio(),
                    libro.getFechaPublicacion(),
                    libro.getIdGenero(),
                    libro.getValoracion(),
                    libro.getIdEditorial(),
                    libro.getIdUsuario()
            ));
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorLibroABM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void CerrarConsulta(ResultSet r) {
        try {
            r.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorLibroABM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean GenerarAgregarColaboradores(EPopupLibro ePLibro) {
        Panel panel = new Panel(ePLibro);
        JComboBox comboAutor = this.libroABM.getComboAutor();
        JComboBox comboGenero = this.libroABM.getComboGenero();
        JComboBox comboEditorial = this.libroABM.getComboEditorial();

        String consulta = "";
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Complete alguno de los campos!!", JOptionPane.OK_CANCEL_OPTION);
        String nombre = panel.getNombre().getText();
        String apellido = panel.getApellido().getText();
        if (nombre.equals("") || (ePLibro == EPopupLibro.Autor && apellido.equals(""))) {
            JOptionPane.showMessageDialog(null, "Error,Complete campos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (result != JOptionPane.OK_OPTION) {
            return false;
        }
        switch (ePLibro) {
            case Autor:
                consulta = String.format("INSERT INTO Autores (nombre, apellido) VALUES ('%s','%s')  ", nombre, apellido);
                if (this.CargarDatosDeColaboradores(consulta)) {
                    int id = TraerIdDelColaborador("idautor", "autores");
                    comboAutor.addItem(new Autor(id, nombre, apellido));
                    JOptionPane.showMessageDialog(null, "Autor agregado", "Autor", JOptionPane.INFORMATION_MESSAGE);
                }
                return true;
            case Editorial:
                consulta = String.format("INSERT INTO Editoriales (nombre) VALUES ('%s')  ", nombre);
                if (this.CargarDatosDeColaboradores(consulta)) {
                    int id = TraerIdDelColaborador("idEditorial", "editoriales");
                    comboEditorial.addItem(new Editorial(id, nombre));
                    JOptionPane.showMessageDialog(null, "Editorial agregado", "Editorial", JOptionPane.INFORMATION_MESSAGE);
                }
                return true;
            case Genero:
                consulta = String.format("INSERT INTO Generos (nombre) VALUES ('%s')  ", nombre);
                if (this.CargarDatosDeColaboradores(consulta)) {
                    int id = TraerIdDelColaborador("idGenero", "generos");
                    comboGenero.addItem(new Genero(id, nombre));
                    JOptionPane.showMessageDialog(null, "Genero agregado", "Genero", JOptionPane.INFORMATION_MESSAGE);
                }
                return true;
        }
        JOptionPane.showMessageDialog(null, "Error,algo salio mal", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    private boolean CargarDatosDeColaboradores(String consulta) {
        try {
            con.getCmd().execute(consulta);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorLibroABM.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    private int TraerIdDelColaborador(String id, String colaborador) {
        int numero = -1;
        try {
            ResultSet resultado = this.con.getCmd().executeQuery(String.format("SELECT %s  "
                    + "FROM %s "
                    + "ORDER BY %s  DESC "
                    + "LIMIT 1 ", id, colaborador, id));
            while (resultado.next()) {

                return resultado.getInt(id);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorLibroABM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numero;
    }

    public boolean PreParararLibroBajaYModificar() {

        Libro l = this.LibroVentanaDeDatos();
        if (l == null) {
            this.libroABM.setEstado(false);
            return false;
        }
        this.libroABM.getTextCantidad().setText(Integer.toString(l.getCantidad()));
        this.libroABM.getTextNombre().setText(l.getNombre());
        this.libroABM.getTextPrecio().setText(Float.toString(l.getPrecio()));

        RecorrerCombo(this.libroABM.getComboAutor(), l.getIdAutor(), EPopupLibro.Autor);
        RecorrerCombo(this.libroABM.getComboEditorial(), l.getIdEditorial(), EPopupLibro.Editorial);
        RecorrerCombo(this.libroABM.getComboGenero(), l.getIdGenero(), EPopupLibro.Genero);

        this.libroABM.getSliderValoracion().setValue(l.getValoracion());

        //Configurar fecha
        System.out.println(l.getFechaPublicacion());
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dt.parse(l.getFechaPublicacion());
        } catch (ParseException ex) {
            Logger.getLogger(ControladorLibroABM.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.libroABM.getCalendario().setDate(date);

        return true;
    }

    private Libro LibroVentanaDeDatos() {
        CuestionarioLibro cL = new CuestionarioLibro(this.libroABM);
        int result = JOptionPane.showConfirmDialog(null, cL,
                "Complete alguno de los campos!!", JOptionPane.OK_CANCEL_OPTION);

        if (result != JOptionPane.OK_OPTION) {
            return null;
        }
        this.id=Integer.parseInt(cL.getIdLibro().getText());
        return TraerLibro(cL.getNombreLibro().getText(), id);
    }

    private Libro TraerLibro(String nombre, int id) {
        ResultSet r;
        Libro l;
        try {

            r = this.con.getCmd().executeQuery(String.format("SELECT * FROM Libros  where idLibro=%d or nombre='%s'  ", id, nombre));
            while (r.next()) {

                l = new Libro(r.getInt("idLibro"), r.getString("nombre"), r.getInt("cantidad"), r.getFloat("precio"), r.getString("fechaDEPublicacion"), r.getInt("Valoracion"), r.getInt("idgenero"), r.getInt("idautor"), r.getInt("ideditorial"), r.getInt("idUsuario"));
                r.close();
                return l;
            }

            r.close();
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorLibroABM.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private void NoEditableCampo() {
        Component[] c = this.libroABM.getContentPane().getComponents();
        this.libroABM.getSliderValoracion().setEnabled(false);
        this.libroABM.getCalendario().setEditable(false);
        for (Component item : c) {

            if (item instanceof PanelRectTranslucido) {
                for (Component item2 : ((PanelRectTranslucido) item).getComponents()) {

                    if (item2 instanceof JTextField) {
                        ((JTextField) item2).setEditable(false);

                    } else if (item2 instanceof JComboBox) {
                        ((JComboBox) item2).setEnabled(false);
                    }

                }
            }

        }
    }

    private void RecorrerCombo(JComboBox combo, int id, EPopupLibro complemento) {

        for (int i = 0; i < combo.getItemCount(); i++) {
            switch (complemento) {
                case Autor:
                    if (((Autor) combo.getItemAt(i)).getId() == id) {

                        combo.setSelectedIndex(i);
                        return;
                    }
                    break;
                case Genero:
                    if (((Genero) combo.getItemAt(i)).getId() == id) {

                        combo.setSelectedIndex(i);
                        return;

                    }
                    break;
                case Editorial:
                    if (((Editorial) combo.getItemAt(i)).getId() == id) {

                        combo.setSelectedIndex(i);
                        return;

                    }
                    break;
            }

        }
    }

    protected class Panel extends JPanel {

        private JTextField nombre;
        private JTextField apellido;
        private EPopupLibro ePopupLibro;

        public JTextField getNombre() {
            return nombre;
        }

        public void setNombre(JTextField nombre) {
            this.nombre = nombre;
        }

        public JTextField getApellido() {
            return apellido;
        }

        public void setApellido(JTextField apellido) {
            this.apellido = apellido;
        }

        public Panel(EPopupLibro ePopupLibro) {
            this.ePopupLibro = ePopupLibro;
            nombre = new JTextField();
            int fila = this.ePopupLibro == EPopupLibro.Autor ? 2 : 1;
            this.setLayout(new GridLayout(fila, 2));

            apellido = new JTextField();
            super.add(new JLabel("Nombre:"));
            super.add(nombre);
            if (ePopupLibro == EPopupLibro.Autor) {
                super.add(new JLabel("Apellido:"));
                super.add(apellido);
            }

        }

    }
}
