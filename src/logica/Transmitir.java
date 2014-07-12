/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

/**Transmitir
 * Esta clase se encarga de tomar la informacion ingresada, procesarla y transmitirla
 * @author cristian
 */
public class Transmitir {
    
    //ATRIBUTOS
    private Conector conector;
    private String informacion =""; //Mensaje a ser enviado
    private int indicador = 10000001; //Indicador del frame
    private int ack; //Reconocimiento de llegada de la informacion.
    private int enq; //Ultima trama a ser enviada
    private int ctr; //Identificador de una trama donde se transmite informacion de control
    private int dat; //Identificador de una trama donde se transmite informacion de datos
    private int ppt; //Solicitud para transmision
    private int lpr; //Listo para recibir datos de informacion o control
    private int num; //Numero de trama que esta enviando
    
    private String frame;
    
    
    //CONSTRUCTOR
    public Transmitir(){
        
    }
    
    //METODOS
    
    //sets
    /** setInformacion
     * Este metodo se encarga de asignar la informacion recibida a la variables
     * informacion.
     * @param informacion 
     */
    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    /** setAck
     * Este metodo se encarga de asignar el bit en la variable ack.
     * @param ack 
     */
    public void setAck(int ack) {
        this.ack = ack;
    }

    /** setEnq
     * Este metodo se encarga de asignar el bit en la variable enq.
     * @param enq 
     */
    public void setEnq(int enq) {
        this.enq = enq;
    }

    /** setCtr
     * Este metodo se encarga de asignar el bit en la variable ctr.
     * @param ctr 
     */
    public void setCtr(int ctr) {
        this.ctr = ctr;
    }

    /** setDat
     * Este metodo se encarga de asignar el bit en la variable dat.
     * @param dat 
     */
    public void setDat(int dat) {
        this.dat = dat;
    }

    /** setPpt
     * Este metodo se encarga de asignar el bit en la variable ppt.
     * @param ppt 
     */
    public void setPpt(int ppt) {
        this.ppt = ppt;
    }

    /** setLpr
     * Este metodo se encarga de asignar el bit en la variable lpr.
     * @param lpr 
     */
    public void setLpt(int lpr) {
        this.lpr = lpr;
    }

    /** setNum
     * Este metodo se encarga de asignar el bit en la variable num.
     * @param num 
     */
    public void setNum(int num) {
        this.num = num;
    }

    /** setFrame
     * Este metodo se encarga de asignar el numero del frame enviado
     * en la variable frame.
     * @param frame 
     */
    public void setFrame(String frame) {
        this.frame = frame;
    }
    
    
    //get
    /** getIndicador
     * Este metodo se encarga de retornar el valor del indicador del frame.
     * @return indicador
     */
    public int getIndicador() {
        return indicador;
    }

    /** getInformacion
     * Este metodo se encarga de retornar la variable informacion.
     * @return informacion
     */
    public String getInformacion() {
        return informacion;
    }

    /** getAck
     * Este metodo se encarga de retornar el bit de la variable ack.
     * @return ack
     */
    public int getAck() {
        return ack;
    }

    /** getEnq
     * Este metodo se encarga de retornar el bit de la variable enq.
     * @return enq
     */
    public int getEnq() {
        return enq;
    }

    /** getCtr
     * Este metodo se encarga de retornar el bit de la variable ctr.
     * @return ctr
     */
    public int getCtr() {
        return ctr;
    }

    /** getDat
     * Este metodo se encarga de retornar el bit de la variable dat.
     * @return dat
     */
    public int getDat() {
        return dat;
    }

    /** getPpt
     * Este metodo se encarga de retornar el bit de la variable ppt.
     * @return ppt
     */
    public int getPpt() {
        return ppt;
    }

    /** getLpt
     * Este metodo se encarga de retornar el bit de la variable lpr.
     * @return lpr
     */
    public int getLpt() {
        return lpr;
    }

    /** getNum
     * Este metodo se encarga de retornar el bit de la variable num.
     * @return num
     */
    public int getNum() {
        return num;
    }

    /** getFrame
     * Este metodo se encarga de retornar el numero del frame.
     * @return 
     */
    public String getFrame() {
        return frame;
    }
    //METODOS PERSONALES
    /** construir_Frame
     *  Este metodo se encarga de construir el frame a enviar en el mensaje
     */
    public void construir_Frame(){
        String aux = Integer.toString(getIndicador())+Integer.toString(getAck())
                +Integer.toString(getEnq())+Integer.toString(getCtr())+Integer.toString(getDat())
                +Integer.toString(getPpt())+Integer.toString(getLpt())+Integer.toString(getNum())
                +getInformacion()+Integer.toString(getIndicador());
        setFrame(aux);
    }
    
    /** enviar
     *  Este metodo se encarga de enviar el frame a través de la clase conector.
     * @return String
     */
    public String enviar(){
        conector = Conector.getSingleton();
        String rta = conector.enviarFrame(frame);
        rta = procesar_rta(rta);
        return rta;
    }
    
    /** procesar_rta
     *  Este metodo se encarga de procesar y asignar un mensaje a la respuesta
     *  a los bits recibidos.
     * @param respuesta
     * @return String
     */
    public String procesar_rta(String respuesta){
        
        String arr_rta[] =respuesta.split("");
        String rta="";
        int fin = arr_rta.length-9;
        for(int i=9; i<=fin;i++){
            rta = rta+arr_rta[i];
        }
        if(rta.equals("0010010")){
            rta = "Trama de control, listo para recibir";
        }
        if(rta.equals("101000")){
            rta ="Trama de control, Frame recibido correctamente \n 101000";
        }
        return rta;
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
