package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
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
    private String countryName;
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
        this.ownedTerr = (Vector<Territory>) info.getMap().getTerritoriesByOwnId(clientID);
        this.countryName = info.getCountryName(clientID);
        gameView.printPlayerMap(countryName);
        do {
            assignUnit(30);
            netClient.sendUnitAssignment(ownedTerr);
        } while  (!receiveACK());

    }
    //TODO: this is only a testing func, should be deleted latterly
    public void test () {
        this.clientID = netClient.receiveClientID();
        GameInitInfo info = netClient.receiveGameInitInfo();

        do {
            assignUnit(30);
            netClient.sendUnitAssignment(ownedTerr);
        } while  (!receiveACK());
    }
    private void updateLocalGameMap() {}

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
                Order oneOrder =  issueOneOrder(); // three actions: move, attack, commit
                netClient.sendActionInfo(); // para: oneOrder
            }
        }
    }
    //TODO: this function seems need to call socket to communicate, which may block the program
    /**
     *
     * @return
     */
    public boolean receiveACK () {
        IllegalOrder illegal = netClient.receiveIllegalOrder();
        if (!illegal.isLegal())
        System.out.println(illegal.getErrMessage());
        return illegal.isLegal();
    }

    public Order issueOneOrder () {
        System.out.println("Please enter your order: ");
        char c = ' ';
        try {
            c = (char) localIn.read();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        Order temp = new testOrder(c);
        return null;
    }

    public void gameOver () {
        printGameOverInfo();
        closeConnection();
    }
    private void printGameOverInfo () {}
    private void closeConnection () {}
}
