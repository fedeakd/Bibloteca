/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import Controlador.SoloLetrasONum;
import Modelo.EValidarCaracter;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.edisoncor.gui.textField.TextField;
/**
 *
 * @author Usuario
 */
public class CuestionarioLibro extends JPanel {
    private  JTextField idLibro;
    private JTextField nombreLibro;
    private GridLayout diseño;
    private JDialog  dialogo;
    public JTextField getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(JTextField idLibro) {
        this.idLibro = idLibro;
    }

    public JTextField getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(JTextField nombreLibro) {
        this.nombreLibro = nombreLibro;
    }
    
    public CuestionarioLibro(JDialog dialogo){
        this.dialogo=dialogo;
        CargarDatos();
        this.setLayout(diseño);
        this.add(new JLabel("Id libro:"));
        this.add(idLibro);
        this.add(new JLabel("Libro:"));
        this.add(nombreLibro);
        
     
    }
    
    private void CargarDatos(){
        SoloLetrasONum soloNumeros = new SoloLetrasONum(this.dialogo, EValidarCaracter.Numero);
        this.idLibro= new JTextField("3");
        this.idLibro.addKeyListener(soloNumeros);
        this.nombreLibro= new JTextField();
        this.diseño= new GridLayout(2, 2);
        
    }
    
    
}
