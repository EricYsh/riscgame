package edu.duke.ece651.riscgame.game;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BoardTextViewTest {
    @Test
    public void test_getBoardText() {
        BoardMap boardMap = new BoardMap();
        Territory t1 = new Territory("t1", "Avalon", 1, 1);
        Territory t2 = new Territory("t2", "Avalon", 1, 2);
        Territory t3 = new Territory("t2", "Braglavia", 2, 2);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);
        t1.addNeighbor(t3);
        t3.addNeighbor(t1);
        ArrayList<Territory> t = new ArrayList<Territory>();
        t.add(t1);
        t.add(t2);
        t.add(t3);
        boardMap.setTerritories(t);

        BoardTextView b = new BoardTextView(boardMap);
        String s = "Red player:\n" +
                "-----------\n" +
                "1  units in t1 (next to: t2, t3)\n" +
                "2  units in t2 (next to: t1)\n";
    }
}
