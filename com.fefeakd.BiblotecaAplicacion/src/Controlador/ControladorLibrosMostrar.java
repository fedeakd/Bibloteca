/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Libro;
import Vista.LibrosMostrar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ControladorLibrosMostrar {
    private ArrayList<Libro> libros;
    private LibrosMostrar lMostrar;
    private Conexion con ;
    
    public ControladorLibrosMostrar(LibrosMostrar lMostrar){
        this.lMostrar=lMostrar;
        con= new Conexion();
        this.libros= new ArrayList<>();
    }
    
    public void CargarTabla(){
        TraerLibros();
         DefaultTableModel dtm = (DefaultTableModel) this.lMostrar.getTablaLibros().getModel();
          for(Libro  l : libros){
              dtm.addRow(new Object[]{
                  l.getIdLibro(),
                  l.getNombre(),
                  l.getCantidad(),
                  l.getValoracion(),
                  l.getPrecio()
              
              });
          }
    }
    
    private boolean  TraerLibros(){
        try{
            ResultSet r = this.con.getCmd().executeQuery(String.format("SELECT * FROM libros"
                  ));
            while(r.next()){
               Libro l= new Libro();
               
               l.setIdLibro(r.getInt("idLibro"));
               l.setNombre(r.getString("nombre"));
               l.setCantidad(r.getInt("cantidad"));
               l.setValoracion(r.getInt("valoracion"));
               l.setPrecio(r.getFloat("precio"));
               libros.add(l);
              
            }
            return true;
        }
        catch(SQLException ex){
             return false;
        }
       
    }
}
