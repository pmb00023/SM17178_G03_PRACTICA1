package es.ujaen.git.sm17178_g03_practica1;

/**
 * Created by usuario on 04/10/2017.
 */

public class ConnectionUserData extends PersonalData {
    protected String connectionIp="127.0.0.1";
    protected short connectionPort=6000;

    public ConnectionUserData(String user,String pass,String ip,short port){
        super(user,pass);
        this.connectionIp=ip;
        this.connectionPort=port;
    }
}