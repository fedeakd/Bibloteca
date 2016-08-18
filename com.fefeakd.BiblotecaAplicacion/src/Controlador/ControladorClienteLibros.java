
package Controlador;

import Modelo.Libro;
import Vista.ClienteLibros;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import Eventos.EVMultipleSeleccionTabla;
import javax.swing.JTable;
public class ControladorClienteLibros {

    private ClienteLibros clienteLibros;
    private ArrayList<Libro> librosVenta;
    private ArrayList<Libro> librosCompra;
    private JTable tablaVentaLibros;
    private JTable tablaCompraLibros;
    private Conexion  con ;
    public ControladorClienteLibros(ClienteLibros clienteLibros) {
        this.con= new Conexion ();
        this.clienteLibros = clienteLibros;
        this.tablaVentaLibros = clienteLibros.getTablaVentaLibros();
        this.tablaCompraLibros = clienteLibros.getTablaCompraLibros();

        this.librosVenta = new ArrayList<>();
        this.librosCompra = new ArrayList<>();

        this.clienteLibros.getTablaVentaLibros().setRowSelectionAllowed(true);
        this.clienteLibros.getTablaVentaLibros().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        this.clienteLibros.getComboBoxEmpleado().addItem("fefe");
        this.clienteLibros.getComboBoxEmpleado().addItem("jose");
        this.clienteLibros.getComboBoxEmpleado().addItem("loco");
        CargarLibros();
        //Eventos tabla
        this.tablaVentaLibros.addMouseListener(new EVMultipleSeleccionTabla(
                this.tablaVentaLibros.getSelectionModel(), this.librosVenta));
        this.tablaCompraLibros.addMouseListener(new EVMultipleSeleccionTabla(this.tablaCompraLibros.getSelectionModel(), librosCompra));

    }

    public void HacerTransacion() {
        DefaultTableModel modelTableVenta = (DefaultTableModel) tablaVentaLibros.getModel();
        DefaultTableModel modelTableCompra = (DefaultTableModel) tablaCompraLibros.getModel();
        
        //Transacion  parte  de venta
        for (int i = 0; i < librosVenta.size(); i++) {
            if (librosVenta.get(i).isEstado()) {
                modelTableVenta.removeRow(i);//Remuevo  de la tabla venta los libro que se van a comprar
                modelTableCompra.addRow(new Object[]{//Agrego  en la tabla compra los libros que se van a comprar
                    librosVenta.get(i).getNombre(),
                    librosVenta.get(i).getAutor(),
                    librosVenta.get(i).getCantidad(),
                    librosVenta.get(i).getPrecio()

                });
                librosVenta.get(i).setEstado(false);//Cambio el  estado de seleccion del libro 
                librosCompra.add(librosVenta.get(i));//Agrego al libro  a la lista de compras
                librosVenta.remove(i);//Remuevo el libro de la lista ventas 
                i--;
            }
        }
        //Transacion  parte  de compra
        for (int i = 0; i < librosCompra.size(); i++) {
           if (librosCompra.get(i).isEstado()) {
                modelTableCompra.removeRow(i);
                modelTableVenta.addRow(new Object[]{
                    librosCompra.get(i).getNombre(),
                    librosCompra.get(i).getAutor(),
                    librosCompra.get(i).getCantidad(),
                    librosCompra.get(i).getPrecio()

                });
                librosCompra.get(i).setEstado(false);
                librosVenta.add(librosCompra.get(i));
                librosCompra.remove(i);
                i--;
            }
        }

    }

    public void CargarLibros() {
        DefaultTableModel dtm = (DefaultTableModel) this.tablaVentaLibros.getModel();
        System.out.println("hola mundo ");
        ArrayList<Libro> libros=Libro.TraerTodosLosLibros(con);
        for(Libro l : libros ){
            
            dtm.addRow(new Object[]{
                l.getNombre(),
                l.getAutor(),
                l.getCantidad(),
                l.getPrecio()
            });
            librosVenta.add(l);
        }


    }

}
