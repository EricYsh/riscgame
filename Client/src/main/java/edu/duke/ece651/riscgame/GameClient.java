package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.ValidationResult;
import edu.duke.ece651.riscgame.commuMedium.RoundResult;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.Type;
import javafx.application.Platform;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;


public class GameClient {
    //TODO: GUI display info, GUI interaction
    private NetClient netClient;
    private Player player;
    private GameMap gameMap;
    private BoardTextView gameView;
    private Vector<Territory> ownedTerr;
    private final InputStream localIn;
    private Scanner scanner;
    private Collection<String> playerList;



//    private Collection<Player> playerList;

    public GameClient(InputStream in) {
        this.localIn = in;
        this.scanner = new Scanner(localIn);
        this.netClient = new NetClient(8888);
    }

    public void gameInit() throws IOException {
        int clientID = netClient.receiveClientID();
        GameInitInfo info = netClient.receiveGameInitInfo();
        this.gameMap = info.getMap();
        this.gameView = new BoardTextView(gameMap);
        this.ownedTerr = gameMap.getTerritoriesByOwnId(clientID);
        String playerName = info.getPlayerName(clientID);
        this.player = new Player(clientID, playerName);

        this.playerList = info.getPlayerName();



        // do {
        //     assignUnit(info.getNumUnit());
        //     netClient.sendUnitAssignment(ownedTerr);
        // } while (!receiveACK());
        // updateLocalGameMap();
        // for (String s : info.getPlayerName()) {
        //     gameView.printPlayerMap(s);
        // }

    }

    /**
     * this func is blocking because of netClient.receiveRoundResult()
     */
    private void updateLocalGameMap() {
        RoundResult result = netClient.receiveRoundResult();
        gameMap.setTerritoryNameAndOwnership(result.getOwnership());
        gameMap.setTerritoryNameAndUnitNums(result.getUnits());
        gameMap.setAllPlayerList(result.getPlayers());
//      System.out.println(gameMap.getTerritoryNameAndOwnership());
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("\\d*");
        return pattern.matcher(str).matches();
    }

    /**
     * this func read Integer inputs from player to assign units for each territory
     *
     * @param numUnit
     * @return
     * @throws IOException
     */
    //TODO: maybe implement in textview part
    /*
    public void assignUnit(int numUnit, Vector<Territory> ownedTerr)
     */
    public void assignUnit(int numUnit) {
        System.out.println("Please assign your units in each territory");
        System.out.println("You have " + numUnit + " units");
        for (int i = 0; i < ownedTerr.size(); i++) {
            System.out.println("How many units do you want to place in " + ownedTerr.get(i).getName());
            int numUnitInOneTerr = 0;
            while (true) {
                String input = scanner.nextLine();
                if (isNumeric(input)) {
                    numUnitInOneTerr = Integer.parseInt(input);
                    if (numUnitInOneTerr > 0 && numUnitInOneTerr < numUnit) break;
                }
                System.out.println("Please enter a valid Integer");
            }
            ownedTerr.get(i).setUnitNum(numUnitInOneTerr);
        }
    }

    //merge demo
    public void playRounds() {
        while (!gameMap.isAllTerritoryOccupiedByOne()) {
            try {
                oneRound();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void gameOver () {
        System.out.println("Please wait for the game to end");
        System.out.println(netClient.receiveGameOverInfo().getWinnerName() + " wins!");
        closeConnection();
    }
    private void oneRound() throws IOException {
        boolean isLose = gameMap.isLose(player.getClientID());
        if (!isLose) {
            issueOrders(); // create orders
        }
        updateLocalGameMap();
        for (int i = 0; i < playerList.size(); i++) {
            gameView.printPlayerMap(i);
        }
//        if (isLose && gameView.printDeathInfo(playerName)) {
//            break; // when it disconnected, it does not need to receive gameover info
//        }
    }

    /**
     * lost players do not and should not need to issue orders
     */
    private void issueOrders() {
        boolean isCommitted = false;
        do {
            Order oneOrder = gameView.issueOneOrder(player.getClientID()); // three actions: move, attack, commit
            ActionInfo info = new ActionInfo(oneOrder);
            netClient.sendActionInfo(info);
            isCommitted = receiveCommitted(oneOrder);
        } while (!isCommitted); // loop until one order is ACKed
    }

    /**
     * this function is blocking
     *
     * @return true when no error message while false when error happen
     */
    public boolean receiveACK() {
        ValidationResult illegal = netClient.receiveValidationResult();
        /*
        gameView.printValidationResult(illegal);
         */
        if (!illegal.isLegal())
            System.out.println(illegal.getErrMessage());
        return illegal.isLegal();
    }

    public boolean receiveCommitted(Order oneOrder) {
        ValidationResult illegal = netClient.receiveValidationResult();
        /*
        gameView.printValidationResult(illegal);
         */
        if (!illegal.isLegal()) {
            System.out.println(illegal.getErrMessage());
        } else {
//            if (oneOrder.getType() == Type.Move) {
//                oneOrder.run(gameMap);
//            }
//            if (oneOrder.getType() == Type.Attack) {
//                gameMap.getTerritoryByName(oneOrder.getSrc().getName()).minusUnit(oneOrder.getUnitNum());
//            }
//            if (oneOrder.getType() == Type.UpgradeTech) {
//
//            }
//            if (oneOrder.getType() == Type.UpgradeUnit) {
//
//            }
            //TODO: this func add modifications (unit, resources) from orders to map
            // in this case, the local map is updated and player can issue orders based on preview orders
            oneOrder.run(gameMap);
        }
        return illegal.isCommitted();
    }

    public void closeConnection() {
        netClient.close();
        // scanner.close();
    }
}
