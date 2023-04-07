package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
import edu.duke.ece651.riscgame.game.Territory;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

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
    public int receiveClientID () {
        int number = 0;
        try {
            DataInputStream in = new DataInputStream(socketInputStream);
            number = in.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }
    public GameInitInfo receiveGameInitInfo () {
        GameInitInfo info = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
            info = (GameInitInfo) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }
    public void sendUnitAssignment (Vector<Territory> territoryVector) {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(socketOutputStream);
            objOut.writeObject(territoryVector);
            objOut.flush(); // end output and prompt cache/buffer to send info
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public IllegalOrder receiveIllegalOrder () {
        IllegalOrder illegal = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
            illegal = (IllegalOrder) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return illegal;
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
