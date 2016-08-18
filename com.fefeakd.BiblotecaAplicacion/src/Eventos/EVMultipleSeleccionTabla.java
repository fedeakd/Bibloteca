
package Eventos;

import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import Modelo.Libro;
import java.util.ArrayList;
import javax.swing.JTable;

public class EVMultipleSeleccionTabla extends java.awt.event.MouseAdapter {

    private ListSelectionModel model;
    private ArrayList<Libro> libros;
    private JTable tabla;

    public EVMultipleSeleccionTabla(ListSelectionModel model, ArrayList<Libro> libros) {
        this.model=model;
        this.libros= libros;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        this.tabla=(JTable) me.getSource();
        if (!this.libros.get(this.tabla.getSelectedRow()).isEstado()) {

            this.libros.get(this.tabla.getSelectedRow()).setEstado(true);
        } else {

           this.libros.get(this.tabla.getSelectedRow()).setEstado(false);
            this.model.clearSelection();
        }

        for (int i = 0; i < this.libros.size(); i++) {
            if (this.libros.get(i).isEstado()) {
                this.model.addSelectionInterval(i, i);
            }

        }
    }

}
