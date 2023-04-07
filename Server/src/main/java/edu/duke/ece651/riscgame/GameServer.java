package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.BoardTextView;

public class GameServer {
    // this class is designed to contain all funcs of server
    private NetServer netServer;
    private BoardMap gameMap;
    // private BoardTextView gameView; // maybe the server don't need to view the boardMap

    private final int numClient;
    /**
     * constructor
     */
    public GameServer (int numClient) {
        this.numClient = numClient;
        this.gameMap = new BoardMap(numClient);
        this.netServer= new NetServer(1, 2, 8888);
    }

    public void GameInit () {
        int numUnit = 30;
        netServer.connectWithMultiClients();
        //TODO: select primary Map for the game

        // gameMap.getRandomMap(numClient);

        // the assign of territory are determined by server
        // which can be associated with gameID

        assignTerritory();

        netServer.sendGameInitInfo(new GameInitInfo()); // aim to pass map
        netServer.validateUnitAssignment(numUnit);
        // assignUnit(30); // let netServer to communicate with clients and done this job
        netServer.sendRoundResult();
    }
    public void assignTerritory () {}
    /**
     * this func receive unit distribution info from clients
     * and then record them in Game
     */
    //TODO:
    public void assignUnit (int numUnit) {
        // netServer.connectWithMultiClients(); // pass client gameID
        netServer.validateUnitAssignment(numUnit);
    }
    public void playRounds () {
        while (gameIsNotEnd()) {
            oneRound();
        }
        sendGameOverInfo();
    }
    //TODO: this func must be replaced latterly with a ruleChecker
    /**
     * currently, it works simply for operation
     * @return
     */
    private boolean gameIsNotEnd () {
        return true;
    }

    /**
     * this function is responsible for actions in one round
     */
    private void oneRound () {
        int temp = receiveOrders(); // this func is designed to be thread-safe but may not be achieved
        // a barrier until all players commit their order
        int tempContainer = executeOrders(temp);
        gameMap.callUp(); // add one unit in territories
        netServer.sendRoundResult();
    }

    /**
     * this func is designed to receive all orders in one round from each player
     * @return the type of return order is not determined, use int instead
     */
    private int receiveOrders () {
        return netServer.receiveActionOrders();
    }

    /**
     * return a container of information updating/action result
     * @param temp: temp variable, type undefined
     * @return the type of return order is not determined, use int instead
     */
    private int executeOrders (int temp) {
        // make modification to gameMap
        // record the result of battle : modification of units, change of control
        return 1;
    }

    private void sendGameOverInfo () {}

    /**
     * end the game: close socket connection and prompt users to start a new one or exit
     */
    public void gameOver () {

    }
}
