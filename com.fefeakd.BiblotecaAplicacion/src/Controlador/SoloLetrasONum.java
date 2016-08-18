/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.RegistroCliente;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class SoloLetrasONum extends KeyAdapter {

    private JDialog form;
    private EValidarCaracter eValidar;

    public SoloLetrasONum(JDialog form, EValidarCaracter eValidar) {
        this.form = form;
        this.eValidar = eValidar;
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent ke) {
        char c = ke.getKeyChar();
        switch (eValidar) {
            case Letra:
                
                
                if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {

                    System.out.println("estoy borrando");
                    return;

                }

                if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("estoy borrando");
                    return;
                }
                if (ke.getKeyCode() != KeyEvent.VK_BACK_SPACE && (!Character.isLetter(c))) {
                    this.form.getToolkit().beep();

                    ke.consume();
                    JOptionPane.showMessageDialog(null, "Error,Solo letras", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case Numero:
                try {
                    if (c == KeyEvent.VK_BACK_SPACE) {
                        return;
                    }
                    Double.parseDouble(String.valueOf(c));

                    //Code here for TRUE case (Is Number)
                } catch (NumberFormatException e1) {
                    this.form.getToolkit().beep();

                    ke.consume();
                    JOptionPane.showMessageDialog(null, "Error,Solo Numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    //Code here for FALSE case (Is NOT Number)
                }
                break;
            case Float:
                  try {
                    if (c == KeyEvent.VK_BACK_SPACE || c=='.') {
                        return;
                    }
                    Float.parseFloat(String.valueOf(c));

                    //Code here for TRUE case (Is Number)
                } catch (NumberFormatException e1) {
                    this.form.getToolkit().beep();

                    ke.consume();
                    JOptionPane.showMessageDialog(null, "Error,Solo Numeros", "Error", JOptionPane.ERROR_MESSAGE);
                    //Code here for FALSE case (Is NOT Number)
                }
                
                break;

        }

    }
}
