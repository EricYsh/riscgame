package edu.duke.ece651.riscgame;

/*
 * This class is used to store the information of a room
 * Every room has a gameserver, which can connect to serveral clients
 * Initialize it by 3
 */
public class Room implements Runnable{
    private int roomID;
    private int port;
    private GameServer gameServer;

    public Room(int roomID, int numClient, int port) {
        this.roomID = roomID;
        this.port = port;
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

    @Override
    public void run() {
        gameServer.connect();
        gameServer.GameInit();
        gameServer.playRounds();
        gameServer.gameOver();
    }



    

    
}
