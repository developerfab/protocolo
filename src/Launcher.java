
import interfaz.VentanaPrincipal;




/** Launcher
 * Ejecutor principal del programa.
 * @author cristian
 */
public class Launcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VentanaPrincipal miVentana = new VentanaPrincipal();
        miVentana.setTitle("PROTOCOLO DE TRANSMISION");
        miVentana.setVisible(true);
    }
    
}
