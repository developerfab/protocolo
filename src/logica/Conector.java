/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

/** Conector
 * Esta clase se encarga de hacer la conexion y procesarla.
 * @author cristian
 */
public class Conector {
    
    private Receptor receptor = new Receptor();
    public Conector(){
        
    }
    
    /** ppt
     * Este metodo permite a la clase transmitir hacer la solicitud de conexion
     * @return int
     */
    public void enviarFrame(String frame){
        receptor.recibirFrame(frame);
    }       
}
