package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.order.testOrder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;


public class GameClient {
    private NetClient netClient;
    private BoardMap gameMap;
    private BoardTextView gameView;
    private Vector<Territory> ownedTerr;
    private final InputStream localIn;
    private Scanner scanner;
    public GameClient () {
        this.localIn = System.in;
        this.scanner = new Scanner(localIn);
    }
    public void gameInit () throws IOException {
        netClient.socketInit();
        GameInitInfo info = netClient.receiveGameInitInfo();
        ownedTerr = info.getTerrList();
        updateLocalGameMap(); // based on received info
        assignUnit(30);
        netClient.sendUnitAssignment(ownedTerr);
    }
    private void updateLocalGameMap() {}

    /**
     * this func read Integer inputs from player to assign units for each territory
     * @param numUnit
     * @return
     * @throws IOException
     */
    private void assignUnit (int numUnit) throws IOException {
        // do {

        // ArrayList<Territory> assignment = null;
        System.out.println("Please assign your units in each territory");
        System.out.println("You have " + numUnit + " units");
        for (int i = 0; i < ownedTerr.size(); i++) {
            System.out.println("How many units do you want to place in " + ownedTerr.get(i).getName());
            int numUnitInOneTerr = scanner.nextInt();
            ownedTerr.get(i).setUnitNum(numUnitInOneTerr);
        }
        // } while (!receiveACK());
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
                Order oneOrder = issueOneOrder(); // three actions: move, attack, commit
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

    public Order issueOneOrder () {
        System.out.println("Please enter your order: ");
        char c = ' ';
        try {
            c = (char) localIn.read();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Order temp = new testOrder(c);
        return temp;
    }

    public void gameOver () {
        printGameOverInfo();
        closeConnection();
    }
    private void printGameOverInfo () {}
    private void closeConnection () {}
}
