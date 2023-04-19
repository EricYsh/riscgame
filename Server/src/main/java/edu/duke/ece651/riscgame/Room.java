package edu.duke.ece651.riscgame;

import java.util.Vector;

/*
 * This class is used to store the information of a room
 * Every room has a gameserver, which can connect to serveral clients
 * Initialize it by 3
 */
public class Room {
    private int roomID;
    private int port;
    private GameServer gameServer;
    private Vector<Integer> clientIDs;

    public Room(int roomID, int numClient, int port) {
        this.roomID = roomID;
        this.port = port;
        this.clientIDs = new Vector<Integer>();
        this.gameServer = new GameServer(numClient, port);
    }

    public GameServer getGameServer() {
        return gameServer;
    }



    public int getRoomID() {
        return roomID;
    }

    public int getPort() {
        return port;
    }

    public void start(){
        gameServer.GameInit();
        gameServer.playRounds();
        gameServer.gameOver();
    }



    

    
}
