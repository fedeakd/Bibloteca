/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Modelo.Libro;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.edisoncor.gui.button.ButtonSeven;
import org.jdesktop.swingx.JXComboBox;
import Controlador.ControladorClienteLibros;
/**
 *
 * @author Usuario
 */
public class ClienteLibros extends javax.swing.JDialog {
    private ControladorClienteLibros controladorClienteLibros;
    public ButtonSeven getButtonAceptar() {
        return buttonAceptar;
    }

    public void setButtonAceptar(ButtonSeven buttonAceptar) {
        this.buttonAceptar = buttonAceptar;
    }

    public ButtonSeven getButtonBorrar() {
        return buttonBorrar;
    }

    public void setButtonBorrar(ButtonSeven buttonBorrar) {
        this.buttonBorrar = buttonBorrar;
    }

    public ButtonSeven getButtonCancelar() {
        return buttonCancelar;
    }

    public void setButtonCancelar(ButtonSeven buttonCancelar) {
        this.buttonCancelar = buttonCancelar;
    }

    public ButtonSeven getButtonTransicion() {
        return buttonTransicion;
    }

    public void setButtonTransicion(ButtonSeven buttonTransicion) {
        this.buttonTransicion = buttonTransicion;
    }



    public JXComboBox getComboBoxEmpleado() {
        return comboBoxEmpleado;
    }

    public void setComboBoxEmpleado(JXComboBox comboBoxEmpleado) {
        this.comboBoxEmpleado = comboBoxEmpleado;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public JTable getTablaCompraLibros() {
        return tablaCompraLibros;
    }

    public void setTablaCompraLibros(JTable tablaCompraLibros) {
        this.tablaCompraLibros = tablaCompraLibros;
    }

    public JTable getTablaVentaLibros() {
        return tablaVentaLibros;
    }

    public void setTablaVentaLibros(JTable tablaVentaLibros) {
        this.tablaVentaLibros = tablaVentaLibros;
    }


    private ArrayList<Libro> libros;
    /**
     * Creates new form ClienteLibros
     */
    public ClienteLibros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {
            libros= new ArrayList<>();
            BufferedImage miImagen =ImageIO.read(getClass().getResource("/Imagen/Compra-Alquiler/backgroundBook.jpg"));
            this.setContentPane(new ImagenFondo(miImagen));
            initComponents();
            this.controladorClienteLibros= new ControladorClienteLibros(this);
        } catch (IOException ex) {
            Logger.getLogger(ClienteLibros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentaLibros = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCompraLibros = new javax.swing.JTable();
        comboBoxEmpleado = new org.jdesktop.swingx.JXComboBox();
        buttonTransicion = new org.edisoncor.gui.button.ButtonSeven();
        buttonAceptar = new org.edisoncor.gui.button.ButtonSeven();
        buttonCancelar = new org.edisoncor.gui.button.ButtonSeven();
        buttonBorrar = new org.edisoncor.gui.button.ButtonSeven();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaVentaLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Libro", "Autor", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentaLibros.setRowSelectionAllowed(false);
        tablaVentaLibros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane1.setViewportView(tablaVentaLibros);

        tablaCompraLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Autor", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaCompraLibros);
        if (tablaCompraLibros.getColumnModel().getColumnCount() > 0) {
            tablaCompraLibros.getColumnModel().getColumn(0).setResizable(false);
            tablaCompraLibros.getColumnModel().getColumn(1).setResizable(false);
            tablaCompraLibros.getColumnModel().getColumn(2).setResizable(false);
            tablaCompraLibros.getColumnModel().getColumn(3).setResizable(false);
        }

        buttonTransicion.setBackground(new java.awt.Color(0, 255, 0));
        buttonTransicion.setForeground(new java.awt.Color(153, 255, 0));
        buttonTransicion.setText("Transicion");
        buttonTransicion.setColorBrillo(new java.awt.Color(153, 255, 0));
        buttonTransicion.setColorDeSombra(new java.awt.Color(0, 204, 102));
        buttonTransicion.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        buttonTransicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransicionActionPerformed(evt);
            }
        });

        buttonAceptar.setBackground(new java.awt.Color(0, 255, 255));
        buttonAceptar.setForeground(new java.awt.Color(0, 255, 204));
        buttonAceptar.setText("Aceptar");
        buttonAceptar.setColorBrillo(new java.awt.Color(204, 255, 255));
        buttonAceptar.setColorDeSombra(new java.awt.Color(0, 51, 255));
        buttonAceptar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        buttonCancelar.setBackground(new java.awt.Color(0, 255, 255));
        buttonCancelar.setForeground(new java.awt.Color(0, 255, 204));
        buttonCancelar.setText("Cancelar");
        buttonCancelar.setColorBrillo(new java.awt.Color(204, 255, 255));
        buttonCancelar.setColorDeSombra(new java.awt.Color(0, 51, 255));
        buttonCancelar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        buttonBorrar.setBackground(new java.awt.Color(0, 255, 255));
        buttonBorrar.setForeground(new java.awt.Color(0, 255, 204));
        buttonBorrar.setText("Borrar");
        buttonBorrar.setColorBrillo(new java.awt.Color(204, 255, 255));
        buttonBorrar.setColorDeSombra(new java.awt.Color(0, 51, 255));
        buttonBorrar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/Compra-Alquiler/libros.png"))); // NOI18N
        jLabel1.setText("Libros");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagen/Compra-Alquiler/chango.png"))); // NOI18N
        jLabel2.setText("Mis Libros");

        jLabel3.setFont(new java.awt.Font("MV Boli", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(225, 6, 95));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BIBLIOTECA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(buttonTransicion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(503, 503, 503)
                .addComponent(buttonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addComponent(jLabel2)
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonTransicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171)
                        .addComponent(comboBoxEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTransicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransicionActionPerformed
      
        this.controladorClienteLibros.HacerTransacion();       
    }//GEN-LAST:event_buttonTransicionActionPerformed

    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteLibros dialog = new ClienteLibros(new javax.swing.JFrame(), true);

                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven buttonAceptar;
    private org.edisoncor.gui.button.ButtonSeven buttonBorrar;
    private org.edisoncor.gui.button.ButtonSeven buttonCancelar;
    private org.edisoncor.gui.button.ButtonSeven buttonTransicion;
    private org.jdesktop.swingx.JXComboBox comboBoxEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCompraLibros;
    private javax.swing.JTable tablaVentaLibros;
    // End of variables declaration//GEN-END:variables
}
