package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.game.Territory;

import java.io.IOException;
import java.util.Vector;

public class ClientApp {
    public String getGreeting() {
        return "Hello World!";
    }
    public static Vector<Territory> terrGen(int unit1, int unit2, int unit3) {
        Territory t1 = new Territory("A", "1", unit1);
        Territory t2 = new Territory("B", "2", unit2);
        Territory t3 = new Territory("C", "1", unit3);
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

//         the two lines below for testing assign units manually
        gameClient.setOwnedTerr(terrGen(5, 5, 5));
        gameClient.test();

        //order of game
        //gameInit
        //playRounds
        //gameOver
        System.out.println("passes correctly");
    }
}
