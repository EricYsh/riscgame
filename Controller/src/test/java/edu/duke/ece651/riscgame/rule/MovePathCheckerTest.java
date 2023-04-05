package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.MovePathChecker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovePathCheckerTest {
    @Test
    public void testPathFalse() {
        Territory t1 = new Territory("A", 1, 5);
        Territory t2 = new Territory("B", 2, 5);
        Territory t3 = new Territory("C", 1, 5);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);
        t2.addNeighbor(t3);
        t3.addNeighbor(t2);
        MovePathChecker movePath = new MovePathChecker(null);
        assertFalse(movePath.ifPathExist(t1, t3));
    }
    @Test
    public void testPathTrue() {
        Territory t1 = new Territory("A", 1, 5);
        Territory t2 = new Territory("B", 1, 5);
        Territory t3 = new Territory("C", 1, 5);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);
        t2.addNeighbor(t3);
        t3.addNeighbor(t2);
        MovePathChecker movePath = new MovePathChecker(null);
        assertTrue(movePath.ifPathExist(t1, t3));
    }
    @Test
    public void testPathFalsePlus() {
        Territory t1 = new Territory("A", 1, 5);
        Territory t2 = new Territory("B", 2, 5);
        Territory t3 = new Territory("C", 1, 5);
        Territory t4 = new Territory("E", 2, 5);
        Territory t5 = new Territory("F", 2, 5);
        Territory t6 = new Territory("G", 1, 5);
        t1.addNeighbor(t2);
        t1.addNeighbor(t3);
        t2.addNeighbor(t1);
        t2.addNeighbor(t4);
        t3.addNeighbor(t1);
        t3.addNeighbor(t4);
        t3.addNeighbor(t5);
        t4.addNeighbor(t2);
        t4.addNeighbor(t3);
        t4.addNeighbor(t6);
        t5.addNeighbor(t3);
        t5.addNeighbor(t6);
        t6.addNeighbor(t5);
        t6.addNeighbor(t4);

        MovePathChecker movePath = new MovePathChecker(null);
        assertFalse(movePath.ifPathExist(t1, t6));
        assertTrue(movePath.ifPathExist(t1, t3));

    }
}