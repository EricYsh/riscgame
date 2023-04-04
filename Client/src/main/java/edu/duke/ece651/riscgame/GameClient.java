package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.order;
import edu.duke.ece651.riscgame.order.testOrder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Vector;


public class GameClient {
    private NetClient netClient;
    private BoardMap gameMap;
    private BoardTextView gameView;
    private Vector<Territory> ownedTerr;
    private final InputStream localIn = System.in;
    public void gameInit () throws IOException {
        netClient.socketInit();
        GameInitInfo info = netClient.receiveGameInitInfo();
        ownedTerr = info.getTerrList();
        updateLocalGameMap(); // based on received info
        assignUnit();
        netClient.sendUnitAssignment();
    }
    private void updateLocalGameMap() {}
    private void assignUnit () throws IOException {
        while (!receiveACK()) {
            HashMap<String, Integer> assignment = null;
            System.out.println("Please assign your units in territories");
            System.out.println("You have 30 units");
            for (int i = 0; i < ownedTerr.size(); i++) {
                System.out.println("How many units do you want to place in " + ownedTerr.get(i).toString());
                assignment.put(ownedTerr.get(i).getName(), localIn.read());
            }
        }

    }
    public void playRounds () {
        while (gameIsNotEnd()) {
            oneRound();
        }
    }
    //TODO: this func must be replaced latterly with a ruleChecker
    /**
     * currently, it works simply for operation
     * @return
     */
    private boolean gameIsNotEnd () {
        return true;
    }
    private void oneRound () {
        issueOrders(); // create orders
        netClient.receiveRoundRes();
    }

    /**
     * lost players do not and should not need to issue orders
     */
    private void issueOrders () {
        boolean isCommitted = false;
        while (!isCommitted) {
            while (!receiveACK()) { // loop until one order is ACKed
                order oneOrder = issueOneOrder(); // three actions: move, attack, commit
                netClient.sendActionInfo(); // para: oneOrder
            }
        }
    }
    //TODO: this function seems need to call socket to communicate, which may block the program
    /**
     *
     * @return
     */
    private boolean receiveACK () {
        return false;
    }

    public order issueOneOrder () {
        System.out.println("Please enter your order: ");
        char c = ' ';
        try {
            c = (char) localIn.read();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        order temp = new testOrder(c);
        return temp;
    }

    public void gameOver () {
        printGameOverInfo();
        closeConnection();
    }
    private void printGameOverInfo () {}
    private void closeConnection () {}
}
