/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Extras;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author rubenor
 */
public class VentanaUtils {
    JFrame ventana;
    public VentanaUtils(JFrame ventana){
        this.ventana = ventana;
    }
    
    public void centrarEnPantalla(){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventanaSize = ventana.getSize();
        
        // Calcular la posicion central
        int x = (pantalla.width - ventanaSize.width) / 2;
        int y = (pantalla.height - ventanaSize.height) / 2;
        
        ventana.setLocation(x, y);
    }
}
