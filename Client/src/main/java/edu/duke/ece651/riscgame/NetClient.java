package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NetClient {
    private Socket clientSocket;
    private InputStream socketInputStream;
    private OutputStream socketOutputStream;
    private int clientID;

    public NetClient ( int port) { // String host,
        try {
            this.clientSocket = new Socket("localhost",port);
            this.socketInputStream = clientSocket.getInputStream();
            this.socketOutputStream = clientSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void socketInit () {

    }
    public GameInitInfo receiveGameInitInfo () {
        GameInitInfo info = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
            info = (GameInitInfo) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }
    public void sendUnitAssignment () {

    }

    /**
     * this func is designed to send one action, not send all actions
     */
    public void sendActionInfo () {

    }
    public void receiveRoundRes () {

    }
    public void receiveGameOverInfo () {

    }
}
