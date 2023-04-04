package edu.duke.ece651.riscgame.game;

import java.util.HashSet;

public class Territory {
    private final String name;
    private int ownId;
    private int unitNum;
    private HashSet<Territory> neighbors;

    public Territory() {
        name = null;
    }
    public String getName() {
        return name;
    }

    public int getOwnId() {
        return ownId;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public HashSet<Territory> getNeighbors() {
        return neighbors;
    }

    public void setOwnId(int ownId) {
        this.ownId = ownId;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public void setNeighbors(HashSet<Territory> neighbors) {
        this.neighbors = neighbors;
    }

}
