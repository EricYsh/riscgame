package edu.duke.ece651.riscgame.endSystem;

import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.BoardTextView;

public class GameServer {
    // this class is designed to contain all funcs of server
    private NetServer netServer;
    private BoardMap gameMap;
    private BoardTextView gameView; // maybe the server don't need to view the boardMap

    private final int numClient;
    /**
     * constructor
     */
    public GameServer (int numClient) {
        this.numClient = numClient;
        this.gameMap = new BoardMap();
    }

    public void GameInit () {
        netServer.connectWithMultiClients(); // pass client gameID
        //TODO: select primary Map for the game
        gameMap.getRandomMap(numClient);
        netServer.passMap(); // aim to pass map information, can be modified
        // the assign of territory are determined by server
        // which can be associated with gameID
        assignTerritory();

        assignUnit(); // let netServer to communicate with clients and done this job

    }
    public void playRounds () {
        while (gameISNotEnd) {
            oneRound();
        }
    }
    /**
     * this function is responsible for actions in one round
     */
    public void oneRound () {
        receiveOrders(); // this func is designed to be thread-safe but may not be achieved
        // a barrier until all players commit their order
        executeOrders();
    }
}
