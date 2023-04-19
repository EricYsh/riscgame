package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.GameOverInfo;
import edu.duke.ece651.riscgame.commuMedium.RoundResult;
import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.order.Attack;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.Type;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class GameServer {
    // this class is designed to contain all funcs of server
    private final String[] countries = new String[]{"Avalon", "Braglavia", "Calador", "Excrier", "Ceyland"};
    private NetServer netServer;
    private BoardMapFactory mapFactory;
    private GameMap gameMap;
    private Player player;
    // private BoardTextView gameView; // maybe the server don't need to view the boardMap

    private final int numClient;
    private final Vector<String> countryName;

    /**
     * constructor
     */
    public GameServer(int numClient) {
        this.numClient = numClient;
        this.countryName = new Vector<String>();
        this.mapFactory = new BoardMapFactory();
        this.gameMap = mapFactory.generateMap(numClient);// the map is chosen when declared
        this.netServer = new NetServer(numClient, 8888);
        for (int i = 0; i < numClient; i++) { // playerList
            countryName.add(countries[i]);
        }
    }

    public GameServer(int numClient, int port) {
        this.numClient = numClient;
        this.countryName = new Vector<String>();
        this.mapFactory = new BoardMapFactory();
        this.gameMap = mapFactory.generateMap(numClient);// the map is chosen when declared
        this.netServer = new NetServer(numClient, port);
        for (int i = 0; i < numClient; i++) { // playerList
            countryName.add(countries[i]);
        }
    }

    public void GameInit() {
        int numUnit = 30;
        netServer.connectWithMultiClients();
        netServer.sendClientID();
        System.out.println(1);
        netServer.broadCast(new GameInitInfo(gameMap, numUnit, countryName)); // aim to pass map
        System.out.println(2);
        ArrayList<Territory> assignments = netServer.validateUnitAssignment(numUnit);
        System.out.println(3);
        gameMap.setTerritories(assignments);
        System.out.println(4);
        netServer.broadCast(new RoundResult(gameMap.getTerritoryNameAndUnitNums (),
                                            gameMap.getTerritoryNameAndOwnership(),
                                            gameMap.getAllPlayerList()));
        System.out.println(5);
    }

    public void playRounds() {
        while (!gameMap.isAllTerritoryOccupiedByOne()) {
            oneRound();
        }
        netServer.broadCast(new GameOverInfo(gameMap.getWinner()));
    }

    /**
     * this function is responsible for actions in one round
     */
    private void oneRound() {
        ArrayList<Order> orders = netServer.validateActionOrders(gameMap);
        executeOrders(orders);
        gameMap.callUp(); // add one unit in territories
        playerLost();
        netServer.broadCast(new RoundResult(gameMap.getTerritoryNameAndUnitNums (),
                                            gameMap.getTerritoryNameAndOwnership(),
                                            gameMap.getAllPlayerList()));
        // output results for checking
        System.out.println(gameMap.getTerritoryNameAndUnitNums());
        System.out.println(gameMap.getTerritoryNameAndOwnership());
    }
    //TODO: check this func by tests
    private void playerLost() {
        for (int i = 0; i < numClient; i++) {
            if (gameMap.isLose(i)) {
                netServer.addLostPlayer(i);
            }
        }
    }

    public void executeOrders(ArrayList<Order> orders) {
        System.out.println("orders size:" + orders.size());
        // upgrade unit first
        System.out.println("upgrade units....");
        for (Order o : orders) {
            if (o.getType().equals(Type.UpgradeUnit)) {
                o.run(gameMap);
            }
        }

        // make modification to gameMap
        System.out.println("move units....");
        for (Order o : orders) {
            if (o.getType().equals(Type.Move)) {
                o.run(gameMap);
            }
        }

        // TODO merge attack before actually attack

        for (Order o : orders) {
            if (o.getType().equals(Type.Attack)) {
                for (int i = 0; i < orders.size() - 1; i++) {
                    for (int j = i + 1; j < orders.size(); j++) {
                        if (o.getType().equals(Type.Attack)) {
                            if (orders.get(i).getSrc().equals(orders.get(j).getDest()) &&
                                    orders.get(i).getDest().equals(orders.get(j).getSrc()) &&
                                    orders.get(i).getUnitNum() == orders.get(i).getSrc().getUnitNum() &&
                                    orders.get(j).getUnitNum() == orders.get(j).getSrc().getUnitNum()
                            ) {
                                orders.get(i).setType(Type.AttackAndChangeHome);
                                orders.get(j).setType(Type.AttackAndChangeHome);
                            }
                        }
                    }
                }
            }
        }

        ArrayList<ArrayList<Unit>> attackUnitList = new ArrayList<>();
        for (Order o : orders) {
            if (o.getType().equals(Type.Attack)) {
                ArrayList<Unit> origin = gameMap.getTerritoryByName(o.getSrc().getName()).getUnits();
                ArrayList<Unit> unitForAttack = new ArrayList<>();
                for(Integer i : o.getSelectedUnitsIndex()) {
                    unitForAttack.add(origin.get(i));
                }
                attackUnitList.add(unitForAttack);
                o.run(gameMap);
            }
        }

        for (Order o : orders) {
            if (o.getType().equals(Type.AttackAndChangeHome)) {
                o.combat(gameMap);
            }
        }

        int i = 0;
        for (Order o : orders) {
            if (o.getType().equals(Type.Attack)) {
                o.combat(gameMap, attackUnitList.get(i));
                i++;
//                o.run(gameMap);
            }
        }

    boolean Flag = false;
        for(Order o : orders) {
            if (!Flag && o.getType().equals(Type.UpgradeTech)) {
                Flag = true;
                o.run(gameMap);
            }
        }
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    /**
     * end the game: close socket connection and prompt users to start a new one or exit
     */
    public void gameOver() {
        netServer.close();
    }
}
