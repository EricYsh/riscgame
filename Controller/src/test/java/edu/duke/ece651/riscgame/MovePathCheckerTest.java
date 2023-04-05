package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.MovePathChecker;
import edu.duke.ece651.riscgame.rule.RuleChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.riscgame.*;

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
        t2.addNeighbor(t1);
        t2.addNeighbor(t3);
        t3.addNeighbor(t2);
        MovePathChecker movePath = new MovePathChecker(null);
        assertFalse(movePath.ifPathExist(t1, t3));
    }
}