package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.game.Territory;

import java.io.IOException;
import java.util.Vector;

public class ClientApp {
    public String getGreeting() {
        return "Hello World!";
    }
    public static Vector<Territory> terrGen() {
        Territory t1 = new Territory("A", 1, 5);
        Territory t2 = new Territory("B", 2, 5);
        Territory t3 = new Territory("C", 1, 5);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);
        t2.addNeighbor(t3);
        t3.addNeighbor(t2);
        Vector<Territory> territoryVector1 = new Vector<Territory>();
        territoryVector1.add(t1);
        territoryVector1.add(t2);
        territoryVector1.add(t3);
        return territoryVector1;
    }
    public static void main(String[] args) {
        System.out.println(new ClientApp().getGreeting());

        GameClient gameClient = new GameClient(System.in);
//        try {
//            gameClient.gameInit();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        gameClient.playRounds();
//        gameClient.gameOver();
        gameClient.setOwnedTerr(terrGen());
        gameClient.assignUnit(30);
    }
}
