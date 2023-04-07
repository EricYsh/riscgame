package edu.duke.ece651.riscgame.game;

import org.junit.jupiter.api.Test;

public class TerritoryTest {
    @Test
    public void test_getName() {
        Territory t = new Territory("test");  
        assert (t.getName().equals("test"));
    }

    @Test
    public void test_getOwnId() {
        Territory t = new Territory("test", 1, 2);  
        assert (t.getOwnId() == 1);
    }

    @Test
    public void test_getUnitNum() {
        Territory t = new Territory("test", 1, 2);  
        assert (t.getUnitNum() == 2);
    }

    @Test
    public void test_getNeighbors() {
        Territory t = new Territory("test", 1, 2);  
        assert (t.getNeighbors().size() == 0);
    }

    @Test
    public void test_addNeighbor() {
        Territory t = new Territory("test", 1, 2);  
        Territory t1 = new Territory("test1", 1, 2);  
        t.addNeighbor(t1);
        assert (t.getNeighbors().size() == 1);
    }

    @Test
    public void test_setOwnId() {
        Territory t = new Territory("test", 1, 2);  
        t.setOwnId(3);
        assert (t.getOwnId() == 3);
    }

    @Test
    public void test_setUnitNum() {
        Territory t = new Territory("test", 1, 2);  
        t.setUnitNum(3);
        assert (t.getUnitNum() == 3);
    }

    @Test
    public void test_addUnit() {
        Territory t = new Territory("test", 1, 2);  
        t.addUnit(3);
        assert (t.getUnitNum() == 5);
    }

    @Test
    public void test_minusUnit() {
        Territory t = new Territory("test", 1, 2);  
        t.minusUnit(1);
        assert (t.getUnitNum() == 1);
    }

    @Test
    public void test_displayInfo() {
        Territory t = new Territory("test", 1, 2);  
        Territory t1 = new Territory("test1", 1, 2);
        Territory t2 = new Territory("test2", 1, 2);
        t.addNeighbor(t1);
        t.addNeighbor(t2);
        assert (t.displayInfo().equals("2 units in test (next to: test1, test2)"));
    }
}
