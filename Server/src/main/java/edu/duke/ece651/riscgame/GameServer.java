package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.RoundResult;
import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class GameServer {
    // this class is designed to contain all funcs of server
    private final String[] countries = new String[]{"Avalon", "Braglavia", "Calador", "Excrier", "Ceyland"};
    private NetServer netServer;
    private BoardMap gameMap;
    // private BoardTextView gameView; // maybe the server don't need to view the boardMap

    private final int numClient;
    private final Vector<String> countryName;
    /**
     * constructor
     */
    public GameServer (int numClient) {
        this.numClient = numClient;
        this.countryName = new Vector<String>();
        this.gameMap = new BoardMap(numClient); // the map is chosen when declared
        this.netServer= new NetServer(numClient, numClient, 8888);
        for (int i = 0; i < numClient; i++) {
            countryName.add(countries[i]);
        }
    }

    public void GameInit () {
        int numUnit = 30;
        netServer.connectWithMultiClients();
        System.out.println(1);
        netServer.sendGameInitInfo(new GameInitInfo(gameMap, numUnit, countryName)); // aim to pass map
        System.out.println(2);
        ArrayList<Territory> assignments = netServer.validateUnitAssignment(numUnit);
        System.out.println(3);
        gameMap.setTerritories(assignments);
        System.out.println(4);
        netServer.sendRoundResult(new RoundResult(gameMap.getTerritoryNameAndOwnership(), gameMap.getTerritoryNameAndUnitNums()));
        System.out.println(5);
    }

    public void playRounds () {
        while (!gameMap.isAllTerritoryOccupiedByOne()) {
            oneRound();
        }
        sendGameOverInfo();
    }

    /**
     * this function is responsible for actions in one round
     */
    private void oneRound () {
        ArrayList<Order> orders = netServer.validateActionOrders();
        // a barrier until all players commit their order
        executeOrders(orders);
        gameMap.callUp(); // add one unit in territories
        netServer.sendRoundResult(new RoundResult(gameMap.getTerritoryNameAndOwnership(), gameMap.getTerritoryNameAndUnitNums()));
    }

    private void executeOrders (ArrayList<Order> orders) {
        // make modification to gameMap
        // record the result of battle : modification of units, change of control

        // classify
        // move
        // attack

    }

    private void sendGameOverInfo () {}

    /**
     * end the game: close socket connection and prompt users to start a new one or exit
     */
    public void gameOver () {
        netServer.close();
    }
}
