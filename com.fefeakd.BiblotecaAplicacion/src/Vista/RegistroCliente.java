/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorRegistroCliente;
import Controlador.SoloLetrasONum;
import Modelo.EValidarCaracter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Usuario
 */
public class RegistroCliente extends javax.swing.JDialog {

    public JTextField getTextApellido() {
        return textApellido;
    }

    public JTextField getTextCiudad() {
        return textCiudad;
    }

    public JPasswordField getTextContraseña() {
        return textContraseña;
    }

    public JTextField getTextDni() {
        return textDni;
    }

    public JTextField getTextEdad() {
        return textEdad;
    }

    public JTextField getTextMail() {
        return textMail;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public JPasswordField getTextRepContraseña() {
        return textRepContraseña;
    }

    public JTextField getTextUsuario() {
        return textUsuario;
    }

    /**
     * Creates new form RegistroCliente
     */
    public RegistroCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        try {

            BufferedImage miImagen = ImageIO.read(getClass().getResource("/Imagen/FondoRegistroCliente.jpg"));
            this.setContentPane(new ImagenFondo(miImagen));
            initComponents();
            //Validar caracter 
            //Caracter solo letras 
            //SoloLetrasONum soloLetras= new SoloLetrasONum(this, EValidarCaracter.Letra);
            //this.textNombre.addKeyListener(soloLetras);
            //this.textApellido.addKeyListener(soloLetras);
            //this.textCiudad.addKeyListener(soloLetras);
            //Caracter solo numeros
            SoloLetrasONum soloNumeros= new SoloLetrasONum(this, EValidarCaracter.Numero);
            this.textDni.addKeyListener(soloNumeros);
            this.textEdad.addKeyListener(soloNumeros);
           
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        panelReflect1 = new org.edisoncor.gui.panel.PanelReflect();
        jLabel1 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textEdad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textContraseña = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        textUsuario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textRepContraseña = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        textDni = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textCiudad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textMail = new javax.swing.JTextField();
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();
        buttonAction1 = new org.edisoncor.gui.button.ButtonAction();
        buttonAction2 = new org.edisoncor.gui.button.ButtonAction();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelReflect1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0), 4));
        panelReflect1.setLayout(new java.awt.GridLayout(5, 4, 40, 15));

        jLabel1.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 255, 51));
        jLabel1.setText("Nombre:");
        jLabel1.setToolTipText("");
        panelReflect1.add(jLabel1);

        textNombre.setBackground(new java.awt.Color(203, 146, 39));
        textNombre.setFont(new java.awt.Font("Segoe WP Black", 0, 11)); // NOI18N
        textNombre.setForeground(new java.awt.Color(51, 204, 0));
        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });
        panelReflect1.add(textNombre);

        jLabel2.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 255, 51));
        jLabel2.setText("Edad:");
        panelReflect1.add(jLabel2);

        textEdad.setBackground(new java.awt.Color(203, 146, 39));
        textEdad.setFont(new java.awt.Font("Segoe WP Black", 0, 11)); // NOI18N
        textEdad.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textEdad);

        jLabel3.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 255, 51));
        jLabel3.setText("Apellido:");
        panelReflect1.add(jLabel3);

        textApellido.setBackground(new java.awt.Color(203, 146, 39));
        textApellido.setFont(new java.awt.Font("Segoe WP Black", 0, 11)); // NOI18N
        textApellido.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textApellido);

        jLabel4.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 51));
        jLabel4.setText("Contraseña:");
        jLabel4.setToolTipText("");
        panelReflect1.add(jLabel4);

        textContraseña.setBackground(new java.awt.Color(203, 146, 39));
        textContraseña.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textContraseña);

        jLabel5.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 255, 51));
        jLabel5.setText("Usuario:");
        panelReflect1.add(jLabel5);

        textUsuario.setBackground(new java.awt.Color(203, 146, 39));
        textUsuario.setFont(new java.awt.Font("Segoe WP Black", 0, 11)); // NOI18N
        textUsuario.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textUsuario);

        jLabel6.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 255, 51));
        jLabel6.setText("Repetir contraseña:");
        panelReflect1.add(jLabel6);

        textRepContraseña.setBackground(new java.awt.Color(203, 146, 39));
        textRepContraseña.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textRepContraseña);

        jLabel7.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 255, 51));
        jLabel7.setText("Documento:");
        panelReflect1.add(jLabel7);

        textDni.setBackground(new java.awt.Color(203, 146, 39));
        textDni.setFont(new java.awt.Font("Segoe WP Black", 0, 11)); // NOI18N
        textDni.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textDni);

        jLabel8.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 255, 51));
        jLabel8.setText("Ciudad:");
        panelReflect1.add(jLabel8);

        textCiudad.setBackground(new java.awt.Color(203, 146, 39));
        textCiudad.setFont(new java.awt.Font("Segoe WP Black", 0, 11)); // NOI18N
        textCiudad.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textCiudad);

        jLabel9.setFont(new java.awt.Font("Myanmar Text", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 255, 51));
        jLabel9.setText("Mail:");
        panelReflect1.add(jLabel9);

        textMail.setBackground(new java.awt.Color(203, 146, 39));
        textMail.setFont(new java.awt.Font("Segoe WP Black", 0, 11)); // NOI18N
        textMail.setForeground(new java.awt.Color(51, 204, 0));
        panelReflect1.add(textMail);

        labelMetric1.setForeground(new java.awt.Color(51, 255, 0));
        labelMetric1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric1.setText("REGISTRASE");
        labelMetric1.setColorDeSombra(new java.awt.Color(0, 153, 51));
        labelMetric1.setFont(new java.awt.Font("Cracked Johnnie", 1, 24)); // NOI18N

        buttonAction1.setBackground(new java.awt.Color(255, 0, 51));
        buttonAction1.setForeground(new java.awt.Color(255, 51, 102));
        buttonAction1.setText("Aceptar");
        buttonAction1.setColorDeSombra(new java.awt.Color(102, 102, 0));
        buttonAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction1ActionPerformed(evt);
            }
        });

        buttonAction2.setBackground(new java.awt.Color(255, 0, 51));
        buttonAction2.setForeground(new java.awt.Color(255, 51, 102));
        buttonAction2.setText("Cancelar");
        buttonAction2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAction2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(376, 376, 376))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(98, Short.MAX_VALUE)
                        .addComponent(panelReflect1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelReflect1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAction2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAction2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction2ActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_buttonAction2ActionPerformed

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped

    }//GEN-LAST:event_textNombreKeyTyped

    private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction1ActionPerformed
        Controlador.ControladorRegistroCliente controlador= new ControladorRegistroCliente(this);
        if( controlador.GestorDeDatos()){
            this.setVisible(false);
        }
    }//GEN-LAST:event_buttonAction1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistroCliente dialog = new RegistroCliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAction buttonAction1;
    private org.edisoncor.gui.button.ButtonAction buttonAction2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private org.edisoncor.gui.panel.PanelReflect panelReflect1;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textCiudad;
    private javax.swing.JPasswordField textContraseña;
    private javax.swing.JTextField textDni;
    private javax.swing.JTextField textEdad;
    private javax.swing.JTextField textMail;
    private javax.swing.JTextField textNombre;
    private javax.swing.JPasswordField textRepContraseña;
    private javax.swing.JTextField textUsuario;
    // End of variables declaration//GEN-END:variables
}
