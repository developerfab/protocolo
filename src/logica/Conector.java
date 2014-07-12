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
    
    private Receptor receptor;
    private static Conector conector = null;
    private String msn_rta = "";
    
    //CONSTRUCTOR
    private Conector(){
        
    }
    
    //METODOS
    
    /** getSingleton
     *  Este metodo se encarga de que solo exista una conexion entre el transmisor
     *  y el receptor.
     * @return Conector
     */
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
    public String enviarFrame(String frame){
        receptor = Receptor.getSingleton();
        receptor.setFrame(frame);
        receptor.procesarFrame();
        msn_rta = receptor.mensajes_respuesta();
        //se confirma que se pueda realizar la conexion
        if(receptor.isCanal_abierto()){
            receptor.mostrar_ventana();
            return msn_rta;
        }
        else{
            msn_rta = "Debe establecer una conexion primero";
        }
        return msn_rta;
        
    }       
    
}
