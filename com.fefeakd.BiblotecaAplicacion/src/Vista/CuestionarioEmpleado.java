/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.edisoncor.gui.textField.TextField;


public class CuestionarioEmpleado extends JPanel {
    private JTextField textUsuario;
    private JTextField textId;
    private JTextField textDni;

    public JTextField getTextUsuario() {
        return textUsuario;
    }

    public void setTextUsuario(JTextField textUsuario) {
        this.textUsuario = textUsuario;
    }

    public JTextField getTextId() {
        return textId;
    }

    public void setTextId(JTextField textId) {
        this.textId = textId;
    }

    public JTextField getTextDni() {
        return textDni;
    }

    public void setTextDni(JTextField textDni) {
        this.textDni = textDni;
    }


    
    public CuestionarioEmpleado(){
        super();
        LLenarCampos();
        super.setLayout(new GridLayout(3,2));
        super.add(new JLabel("Identificacion:"));
        super.add(this.textId);
        super.add(new JLabel("Documento:"));
        super.add(this.textDni);
        super.add(new JLabel("Usuario:"));
        super.add(this.textUsuario);
        
    }
    
    
    private void LLenarCampos(){
       this.textDni= new TextField();
       this.textId= new TextField();
       this.textUsuario= new TextField();

       
       
    }
}
