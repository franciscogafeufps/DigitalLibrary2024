
package digitallibrary;

import vista.Principal;
import javax.swing.JOptionPane;

public class DigitalLibrary {

    
    public static void main(String[] args) {
        
        JOptionPane.showMessageDialog(null, "Inicializa el programa");
        
        Principal vPrincipal = new Principal();
        vPrincipal.setVisible(true);       
    }
    
}
