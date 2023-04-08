package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
import edu.duke.ece651.riscgame.commuMedium.RoundResult;
import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;


public class GameClient {
    private NetClient netClient;
    private int clientID;
    private String playerName;
    private BoardMap gameMap;
    private BoardTextView gameView;
    private Vector<Territory> ownedTerr;
    private final InputStream localIn;
    private Scanner scanner;
    public GameClient (InputStream in) {
        this.localIn = in;
        this.scanner = new Scanner(localIn);
        this.netClient = new NetClient(8888);
    }

    public void gameInit () throws IOException {
        this.clientID = netClient.receiveClientID();
        GameInitInfo info = netClient.receiveGameInitInfo();
        this.gameMap = info.getMap();
        this.gameView = new BoardTextView(gameMap);
        this.ownedTerr = (Vector<Territory>) gameMap.getTerritoriesByOwnId(clientID);
        this.playerName = info.getPlayerName(clientID);
        for (String s: info.getPlayerName()) {
            gameView.printPlayerMap(s);
        }
        for (Territory terr: gameMap.getTerritories()) {
            System.out.println(terr.displayInfo());
        }

        do {
            assignUnit(info.getNumUnit());
            netClient.sendUnitAssignment(ownedTerr);
        } while  (!receiveACK());
        updateLocalGameMap();
    }
    //TODO: this is only a testing func, should be deleted latterly
    public void test () {
        this.clientID = netClient.receiveClientID();
        GameInitInfo info = netClient.receiveGameInitInfo();
        do {
            assignUnit(30);
            netClient.sendUnitAssignment(ownedTerr);
        } while (!receiveACK());
    }
    //TODO:
    private void updateLocalGameMap() {
        RoundResult result = netClient.receiveRoundResult();
        gameMap.setTerritoryNameAndOwnership(result.getOwnership());
        gameMap.setTerritoryNameAndUnitNums (result.getUnits());
    }

    //TODO: this is only an API for testing, should be deleted latterly
    public void setOwnedTerr (Vector<Territory> terrVec) {
        this.ownedTerr = terrVec;
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("\\d*");
        return pattern.matcher(str).matches();
    }
    /**
     * this func read Integer inputs from player to assign units for each territory
     * @param numUnit
     * @return
     * @throws IOException
     */
    //TODO: maybe implement in textview part
    public void assignUnit (int numUnit) {
        System.out.println("Please assign your units in each territory");
        System.out.println("You have " + numUnit + " units");
        for (int i = 0; i < ownedTerr.size(); i++) {
            System.out.println("How many units do you want to place in " + ownedTerr.get(i).getName());
            int numUnitInOneTerr = 0;
            while(true){
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
    public void playRounds () {
        while (gameIsNotEnd()) {
            oneRound();
        }
        System.out.println("you lose, please wait for the game to end");
        System.out.println(netClient.receiveGameOverInfo());
        closeConnection();
    }
    //TODO: this func must be replaced latterly with a ruleChecker
    /**
     * currently, it works simply for operation
     * @return
     */
    private boolean gameIsNotEnd () {
        //relate to round result.
        return true;
    }
    private void oneRound () {
        issueOrders(); // create orders
        netClient.receiveRoundResult();
    }

    /**
     * lost players do not and should not need to issue orders
     */
    private void issueOrders () {
//        boolean isCommitted = false;
//        do {
//            do {
//                Order oneOrder = gameView.issueOneOrder(clientID); // three actions: move, attack, commit
//                netClient.sendActionInfo(oneOrder);
//            } while (!receiveACK()); // loop until one order is ACKed
//        } while (!isCommitted);
        do {
            Order oneOrder = gameView.issueOneOrder(clientID); // three actions: move, attack, commit
            ActionInfo info = new ActionInfo(oneOrder);

            netClient.sendActionInfo(info);
        } while (!receiveCommitted()); // loop until one order is ACKed
    }

    /**
     * this function is blocking
     * @return true when no error message while false when error happen
     */
    public boolean receiveACK () {
        IllegalOrder illegal = netClient.receiveIllegalOrder();
        if (!illegal.isLegal())
            System.out.println(illegal.getErrMessage());
        return illegal.isLegal();
    }
    public boolean receiveCommitted () {
        IllegalOrder illegal = netClient.receiveIllegalOrder();
        if (!illegal.isLegal())
            System.out.println(illegal.getErrMessage());
        return illegal.isLegal()&&illegal.isCommitted();
    }

    private void closeConnection () {
        netClient.close();
        // scanner.close();
    }
}
