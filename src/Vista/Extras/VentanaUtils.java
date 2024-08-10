package Vista.Extras;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

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
        int y = (pantalla.height - ventanaSize.height) / 3;
        
        ventana.setLocation(x, y);
    }
}
