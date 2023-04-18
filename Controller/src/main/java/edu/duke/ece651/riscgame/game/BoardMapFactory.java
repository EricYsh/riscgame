package edu.duke.ece651.riscgame.game;

import java.util.ArrayList;
import java.util.Collections;

public class BoardMapFactory {

    public BoardGameMap generateMap(int playerNum) {
        BoardGameMap map = new BoardGameMap();
        ArrayList<Territory> territories = map.getTerritories();
        ArrayList<Player> players = map.getAllPlayerList();
        switch (playerNum) {
            //Just for test!!!!!!!!!!!!!!!
            case 1:
                initByPlayerNumber1(territories);
                break;// Perform the action specific to Player 2
            case 2:
                // Perform the action specific to Player 1
                initByPlayerNumber2(territories);
                players.add(new Player(0, "Avalon"));
                players.add(new Player(1, "Braglavia"));
                break;// Perform the action specific to Player 2
            case 4:
                // Perform the action specific to Player 3
                initByPlayerNumber4(territories);
                players.add(new Player(0, "Avalon"));
                players.add(new Player(1, "Braglavia"));
                players.add(new Player(2, "Calador"));
                players.add(new Player(3, "Excrier"));
                break;
            case 5:
                // Perform the action specific to Player 4
                initByPlayerNumber5(territories);
                players.add(new Player(0, "Avalon"));
                players.add(new Player(1, "Braglavia"));
                players.add(new Player(2, "Calador"));
                players.add(new Player(3, "Excrier"));
                players.add(new Player(4, "Ceyland"));
                break;
            default: // also used for 3 player
                // Handle the case when playerNum is not between 1 and 4
                initByPlayerNumber3(territories);
                players.add(new Player(0, "Avalon"));
                players.add(new Player(1, "Braglavia"));
                players.add(new Player(2, "Calador"));
                break;
        }
        return map;
    }
    private void initByPlayerNumber2(ArrayList<Territory> territories) {

        Territory t1 = new Territory("T1", "Avalon", 0, 10);
        Territory t2 = new Territory("T2", "Avalon", 0, 10);
        Territory t3 = new Territory("T3", "Avalon", 0, 10);

        Territory t4 = new Territory("T4", "Braglavia", 1, 10);
        Territory t5 = new Territory("T5", "Braglavia", 1, 10);
        Territory t6 = new Territory("T6", "Braglavia", 1, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5);
        t5.addNeighbors(t2, t4, t6);
        t6.addNeighbors(t3, t5);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6);
    }

    private void initByPlayerNumber3(ArrayList<Territory> territories) {

        Territory t1 = new Territory("T1", "Avalon", 0, 10);
        Territory t2 = new Territory("T2", "Avalon", 0, 10);
        Territory t3 = new Territory("T3", "Avalon", 0, 10);

        Territory t4 = new Territory("T4", "Braglavia", 1, 10);
        Territory t5 = new Territory("T5", "Braglavia", 1, 10);
        Territory t6 = new Territory("T6", "Braglavia", 1, 10);

        Territory t7 = new Territory("T7", "Calador", 2, 10);
        Territory t8 = new Territory("T8", "Calador", 2, 10);
        Territory t9 = new Territory("T9", "Calador", 2, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5, t7);
        t5.addNeighbors(t2, t4, t6, t8);
        t6.addNeighbors(t3, t5, t9);

        t7.addNeighbors(t4, t8);
        t8.addNeighbors(t5, t7, t9);
        t9.addNeighbors(t6, t8);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6, t7, t8, t9);

    }

    private void initByPlayerNumber4(ArrayList<Territory> territories) {

        Territory t1 = new Territory("t1", "Avalon", 0, 10);
        Territory t2 = new Territory("t2", "Avalon", 0, 10);
        Territory t3 = new Territory("t3", "Avalon", 0, 10);

        Territory t4 = new Territory("t4", "Braglavia", 1, 10);
        Territory t5 = new Territory("t5", "Braglavia", 1, 10);
        Territory t6 = new Territory("t6", "Braglavia", 1, 10);

        Territory t7 = new Territory("t7", "Calador", 2, 10);
        Territory t8 = new Territory("t8", "Calador", 2, 10);
        Territory t9 = new Territory("t9", "Calador", 2, 10);

        Territory t10 = new Territory("t10", "Excrier", 3, 10);
        Territory t11 = new Territory("t11", "Excrier", 3, 10);
        Territory t12 = new Territory("t12", "Excrier", 3, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5, t7);
        t5.addNeighbors(t2, t4, t6, t8);
        t6.addNeighbors(t3, t5, t9);

        t7.addNeighbors(t4, t8, t10);
        t8.addNeighbors(t5, t7, t9, t11);
        t9.addNeighbors(t6, t8, t12);

        t10.addNeighbors(t7, t11);
        t11.addNeighbors(t8, t10, t12);
        t12.addNeighbors(t9, t11);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);

    }

    private void initByPlayerNumber5(ArrayList<Territory> territories) {
        Territory t1 = new Territory("t1", "Avalon", 0, 10);
        Territory t2 = new Territory("t2", "Avalon", 0, 10);
        Territory t3 = new Territory("t3", "Avalon", 0, 10);

        Territory t4 = new Territory("t4", "Braglavia", 1, 10);
        Territory t5 = new Territory("t5", "Braglavia", 1, 10);
        Territory t6 = new Territory("t6", "Braglavia", 1, 10);

        Territory t7 = new Territory("t7", "Calador", 2, 10);
        Territory t8 = new Territory("t8", "Calador", 2, 10);
        Territory t9 = new Territory("t9", "Calador", 2, 10);

        Territory t10 = new Territory("t10", "Excrier", 3, 10);
        Territory t11 = new Territory("t11", "Excrier", 3, 10);
        Territory t12 = new Territory("t12", "Excrier", 3, 10);

        Territory t13 = new Territory("t13", "Ceyland", 4, 10);
        Territory t14 = new Territory("t14", "Ceyland", 4, 10);
        Territory t15 = new Territory("t15", "Ceyland", 4, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5, t7);
        t5.addNeighbors(t2, t4, t6, t8);
        t6.addNeighbors(t3, t5, t9);

        t7.addNeighbors(t4, t8, t10);
        t8.addNeighbors(t5, t7, t9, t11);
        t9.addNeighbors(t6, t8, t12);

        t10.addNeighbors(t7, t11, t13);
        t11.addNeighbors(t8, t10, t12, t14);
        t12.addNeighbors(t9, t11, t15);

        t13.addNeighbors(t10, t14);
        t14.addNeighbors(t11, t13, t15);
        t15.addNeighbors(t12, t14);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15);

    }

    private void initByPlayerNumber1(ArrayList<Territory> territories) {

        Territory t1 = new Territory("T1", "Avalon", 0, 10);
        Territory t2 = new Territory("T2", "Avalon", 0, 10);
        Territory t3 = new Territory("T3", "Avalon", 0, 10);

        Territory t4 = new Territory("T4", "Braglavia", 0, 10);
        Territory t5 = new Territory("T5", "Braglavia", 0, 10);
        Territory t6 = new Territory("T6", "Braglavia", 0, 10);

        Territory t7 = new Territory("T7", "Calador", 0, 10);
        Territory t8 = new Territory("T8", "Calador", 0, 10);
        Territory t9 = new Territory("T9", "Calador", 0, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5, t7);
        t5.addNeighbors(t2, t4, t6, t8);
        t6.addNeighbors(t3, t5, t9);

        t7.addNeighbors(t4, t8);
        t8.addNeighbors(t5, t7, t9);
        t9.addNeighbors(t6, t8);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6, t7, t8, t9);

    }

}
