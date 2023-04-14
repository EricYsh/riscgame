package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.*;
import edu.duke.ece651.riscgame.game.Territory;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class NetClient {
    private Socket clientSocket;

    public NetClient ( int port) { // String host,
        try {
            this.clientSocket = new Socket("localhost",port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int receiveClientID () {
        GameMessageStream<Integer> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(clientSocket);
    }
    public GameInitInfo receiveGameInitInfo () {
        GameMessageStream<GameInitInfo> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(clientSocket);
    }
    public void sendUnitAssignment (Vector<Territory> territoryVector) {
        GameMessageStream.sendObject(territoryVector, clientSocket);
    }
    public ValidationResult receiveValidationResult () {
        GameMessageStream<ValidationResult> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(clientSocket);
    }
    /**
     * this func is designed to send one action, not send all actions
     */
    public void sendActionInfo (ActionInfo info) {
        GameMessageStream.sendObject(info, clientSocket);
    }
    public RoundResult receiveRoundResult () {
        GameMessageStream<RoundResult> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(clientSocket);
    }

    public GameOverInfo receiveGameOverInfo(){
        GameMessageStream<GameOverInfo> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(clientSocket);
    }

    public void close () {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
legacy code

//        int number = 0;
//        try {
//            ObjectInputStream objectInputStream = new ObjectInputStream(socketInputStream);
//            number = (int) objectInputStream.readObject();
//            // number = objectInputStream.readInt();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return number;
 */