package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.GameOverInfo;
import edu.duke.ece651.riscgame.commuMedium.RoundResult;
import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.Type;

import java.util.ArrayList;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class GameServer {
    // this class is designed to contain all funcs of server
    private final String[] countries = new String[]{"Avalon", "Braglavia", "Calador", "Excrier", "Ceyland"};
    private NetServer netServer;
    private BoardMapFactory mapFactory;
    private GameMap gameMap;
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
        this.gameMap =  mapFactory.generateMap(numClient);// the map is chosen when declared
        this.netServer = new NetServer(numClient, numClient, 8888);
        for (int i = 0; i < numClient; i++) {
            countryName.add(countries[i]);
        }
    }

    public void GameInit() {
        int numUnit = 30;
        netServer.connectWithMultiClients();
        System.out.println(1);
        netServer.sendGameInitInfo(new GameInitInfo( gameMap, numUnit, countryName)); // aim to pass map
        System.out.println(2);
        ArrayList<Territory> assignments = netServer.validateUnitAssignment(numUnit);
        System.out.println(3);
        gameMap.setTerritories(assignments);
        System.out.println(4);
        netServer.sendRoundResult(new RoundResult(gameMap.getTerritoryNameAndUnitNums(),
                gameMap.getTerritoryNameAndOwnership()));
        System.out.println(5);
    }

    public void playRounds() {
        while (!gameMap.isAllTerritoryOccupiedByOne()) {
            oneRound();
        }
        netServer.sendGameOverInfo(new GameOverInfo(gameMap.getWinner()));
    }

    /**
     * this function is responsible for actions in one round
     */
    private void oneRound() {
        ArrayList<Order> orders = netServer.validateActionOrders();
        // a barrier until all players commit their order
        executeOrders(orders);
        gameMap.callUp(); // add one unit in territories
        playerLost();
        System.out.println(gameMap.getTerritoryNameAndOwnership());

//        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + value);
//        }
        netServer.sendRoundResult(new RoundResult(gameMap.getTerritoryNameAndUnitNums(),
                gameMap.getTerritoryNameAndOwnership()));
    }

    private void playerLost() {
        for (int i = 0; i < numClient; i++) {
            if (gameMap.isLose(i)) {
                netServer.addLostPlayer(i);
            }
        }
    }

    private void executeOrders(ArrayList<Order> orders) {
        // make modification to gameMap
        System.out.println("orders size:" + orders.size());
        for (Order o : orders) {
            if (o.getType().equals(Type.Move)) {
                o.run(gameMap);
            }
        }

        for (Order o : orders) {
            if (o.getType().equals(Type.Attack)) {
                gameMap.getTerritoryByName(o.getSrc().getName()).minusUnit(o.getUnitNum());
//                System.out.print(gameMap.getTerritoryByName(o.getSrc().getName()).getName() + " has units ");
//                System.out.println(gameMap.getTerritoryByName(o.getSrc().getName()).getUnitNum());
//                gameMap.getEqualTerritory(o.getSrc()).minusUnit(o.getUnitNum());
//                o.getSrc().minusUnit(o.getUnitNum());
                for (int i = 0; i < orders.size() - 1; i++) {
                    for (int j = i + 1; j < orders.size(); j++) {
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

        for (Order o : orders) {
            if (o.getType().equals(Type.Attack) || o.getType().equals(Type.AttackAndChangeHome)) {
                o.run(gameMap);
            }
        }
    }


    /**
     * end the game: close socket connection and prompt users to start a new one or exit
     */
    public void gameOver() {
        netServer.close();
    }
}
