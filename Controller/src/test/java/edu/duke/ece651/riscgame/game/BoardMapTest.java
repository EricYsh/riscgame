package edu.duke.ece651.riscgame.game;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BoardMapTest {
    @Test
    public void test_getTerritories() {
        BoardMap b = new BoardMap(0);
        assert (b.getTerritories().size() == 0);
    }

    @Test
    public void test_setTerritories() {
        BoardMap b = new BoardMap(1);
        ArrayList<Territory> t = new ArrayList<Territory>();
        t.add(new Territory("test", 1, 2));
        t.add(new Territory("test2", 1, 3));
        b.setTerritories(t);
        assert (b.getTerritories().size() == 2);
    }

    @Test
    public void test_getTerritoryByName() {
        BoardMap b = new BoardMap(2);
        ArrayList<Territory> t = new ArrayList<Territory>();
        t.add(new Territory("test", 1, 2));
        t.add(new Territory("test2", 1, 3));
        b.setTerritories(t);
        assert (b.getTerritoryByName("test").getName().equals("test"));
    }

    @Test
    public void test_getTerritoriesByOwnId() {
        BoardMap b = new BoardMap(2);
        ArrayList<Territory> t = new ArrayList<Territory>();
        t.add(new Territory("test", 1, 2));
        t.add(new Territory("test2", 1, 3));
        t.add(new Territory("test3", 2, 3));
        b.setTerritories(t);
        assert (b.getTerritoriesByOwnId(1).size() == 2);
    }

    @Test
    public void test_BoardMap() {
        BoardMap b = new BoardMap(2);
        assert (b.getTerritories().size() == 6);
    }
}
