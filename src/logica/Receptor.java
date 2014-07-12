/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import interfaz.VentanaReceptor;

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
    private boolean canal_abierto=false;
    private String frame;
    private String trama;//segmento del mensaje a enviar y procesar.
    private static Receptor receptor = null;
    private VentanaReceptor ventana_receptor=null;
    
    //COSTRUCTOR
    private Receptor(){
        
    }
    
    //METODOS

    /** getSingleton
     *  Este metodo se encarga de que solo se cre una instancia para cada conexion
     * @return Receptor
     */
    public static Receptor getSingleton(){
        if(receptor==null){
            receptor = new Receptor();
        }
        return receptor;
    }
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

    public void setTrama(String trama) {
        this.trama = trama;
    }
    
    //gets

    public String getTrama() {
        return trama;
    }
    
    public int getAck() {
        return ack;
    }

    public int getCtr() {
        return ctr;
    }

    public int getDat() {
        return dat;
    }

    public int getEnq() {
        return enq;
    }

    public String getFrame() {
        return frame;
    }

    public int getIndicador() {
        return indicador;
    }

    public String getInformacion() {
        return informacion;
    }

    public int getLpr() {
        return lpr;
    }

    public int getNum() {
        return num;
    }

    public int getPpt() {
        return ppt;
    }

    public boolean isCanal_abierto() {
        return canal_abierto;
    }
    
    public VentanaReceptor getVentana_receptor() {
        return ventana_receptor;
    }
    
    /** procesarFrame
     *  Este metodo se encarga de procesar el frame que se ha recibido
     */
    public void procesarFrame(){
        String mensaje[] = frame.split("");
        String aux="";
        //indicador:
        setIndicador(Integer.parseInt(partir_frame(0,8,mensaje)));
        setAck(Integer.parseInt(mensaje[9]));
        setEnq(Integer.parseInt(mensaje[10]));
        setCtr(Integer.parseInt(mensaje[11]));
        setDat(Integer.parseInt(mensaje[12]));
        setPpt(Integer.parseInt(mensaje[13]));
        setLpr(Integer.parseInt(mensaje[14]));
        setNum(Integer.parseInt(mensaje[15]));
        setTrama(getAck()+""+getEnq()+""+getCtr()+""+getDat()+""+getPpt()+""+getLpr());
        setInformacion(partir_frame(16, mensaje.length-9, mensaje));
        
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
    /** mensajes_respuesta
     *  Este metodo se encarga de crear los mensajes de respuesta para el 
     *  transmisor.
     * @return 
     */
    public String mensajes_respuesta(){
        String mensaje_rta = "";
        //Permiso para transmitir:
        if(getTrama().equals("001010")){
            //Listo para recibir:
            mensaje_rta = "0010010";
            canal_abierto = true;
        }
        if(getTrama().equals("000100")){
            mensaje_rta = "101000";
        }
        mensaje_rta = getIndicador()+mensaje_rta+getIndicador();
        return mensaje_rta;
    }
    
    /** mostrar_ventana
     *  Este metodo se encarga de crear la ventana de respuesta.
     */
    public void mostrar_ventana(){
        ventana_receptor = VentanaReceptor.getVentana();
        ventana_receptor.setVisible(true);
        ventana_receptor.llenar_campos(this);
    }
}
