package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.*;
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
            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
            number = (int) objectInputStream.readObject();
            // number = objectInputStream.readInt();
        } catch (IOException | ClassNotFoundException e) {
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
        GameMessageStream.sendObject(territoryVector, clientSocket);
    }
    public ValidationResult receiveIllegalOrder () {
        ValidationResult illegal = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
            illegal = (ValidationResult) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return illegal;
    }
    /**
     * this func is designed to send one action, not send all actions
     */
    public void sendActionInfo (ActionInfo info) {
        GameMessageStream.sendObject(info, clientSocket);
    }
    public RoundResult receiveRoundResult () {
        RoundResult res = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
            res = (RoundResult) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public GameOverInfo receiveGameOverInfo(){
        GameOverInfo info = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
            info = (GameOverInfo) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

    public void close () {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
