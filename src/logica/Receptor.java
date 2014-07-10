/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

/** Receptor
 * Esta clase se encarga de recibir la informacion.
 * @author cristian
 */
public class Receptor {
    
    //ATRIBUTOS
    
    private String informacion =""; //Mensaje a ser enviado
    private int indicador; //Indicador del frame
    private int ack; //Reconocimiento de llegada de la informacion.
    private int enq; //Ultima trama a ser enviada
    private int ctr; //Identificador de una trama donde se transmite informacion de control
    private int dat; //Identificador de una trama donde se transmite informacion de datos
    private int ppt; //Solicitud para transmision
    private int lpr; //Listo para recibir datos de informacion o control
    private int num; //Numero de trama que esta enviando
    private int solicitar_conexion = 0;
    private String frame;
    
    //COSTRUCTOR
    public Receptor(){
        
    }
    
    //METODOS

    public void setAck(int ack) {
        this.ack = ack;
    }

    public void setCtr(int ctr) {
        this.ctr = ctr;
    }

    public void setDat(int dat) {
        this.dat = dat;
    }

    public void setEnq(int enq) {
        this.enq = enq;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public void setLpr(int lpr) {
        this.lpr = lpr;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPpt(int ppt) {
        this.ppt = ppt;
    }
    
    /** recibirFrame
     *  Este metodo se encarga de recibir el frame enviado por el transmisor
     * @param frame 
     */
    public void recibirFrame(String frame){
        setFrame(frame);
        procesarFrame();
        
    }
    
    /** procesarFrame
     *  Este metodo se encarga de procesar el frame que se ha recibido
     */
    public void procesarFrame(){
        String mensaje[] = frame.split("");
        //indicador:
        setIndicador(Integer.parseInt(partir_frame(0,8,mensaje)));
        System.out.println(indicador);
    }
    
    /** partir_frame
     *  Esta funcion se encarga de partir el frame en las seleccion indicada por 
     *  el parametro inicio.
     * @param inicio
     * @param msn
     * @param fin
     * @return 
     */
    public String partir_frame(int inicio, int fin, String msn[]){
        String str_aux="";
        for(int i = inicio; i<=fin;i++){
            str_aux = str_aux+msn[i];
        }
        return str_aux;
    }
    
    
}
