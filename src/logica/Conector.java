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
    
    //ATRIBUTOS
    
    private int solicitar_conexion = 0;    
    private Receptor receptor = new Receptor();
    private static Conector conector = null;
    //CONSTRUCTOR
    private Conector(){
        
    }
    
    //METODOS
    
    public static Conector getSingleton(){
        if(conector == null){
            conector = new Conector();
        }
        return conector;
    }
    /** ppt
     * Este metodo permite a la clase transmitir hacer la solicitud de conexion
     * @return int
     */
    public void enviarFrame(String frame){
        receptor.setFrame(frame);
        receptor.procesarFrame();
    }       
    
    /** enviar_frame
     *  Este metodo se encarga de confirmar si se puede hacer el envio del frame 
     * @return Boolean
     */
    public Boolean enviar_frame(){
        Boolean retorno = false;
        if(solicitar_conexion==1){
            retorno = true;
        }
        return retorno;
    }
}
