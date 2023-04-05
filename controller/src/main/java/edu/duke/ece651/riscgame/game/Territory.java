package edu.duke.ece651.riscgame.game;

import java.util.HashSet;

public class Territory {
    private final String name;
    private int ownId;
    private int unitNum;
    private HashSet<Territory> neighbors;

    public Territory(String tname) {
        this.name = tname;
        neighbors = new HashSet<>();
    }

    public Territory(String tname, int oId, int units) {
        this.name = tname;
        this.ownId = oId;
        this.unitNum = units;
        neighbors = new HashSet<>();
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

    public void setOwnId(int oid) {
        ownId = oid;
    }

    public void setUnitNum(int num) {
        unitNum = num;
    }

    public void addUnit(int num) {
        unitNum += num;
    }

    public void minusUnit(int num) {
        unitNum -= num;
    }

    public void addNeighbor(Territory t1) {
        neighbors.add(t1);
    }

    public String displayInfo() {
        StringBuilder info = new StringBuilder("");
        String format = unitNum + " units in " + name + " (next to: ";
        info.append(format);
        for (Territory t1 : neighbors) {
            info.append(t1.getName()).append(", ");
        }
        info.delete(info.length() - 2, info.length());//delete the last two character (", ")
        info.append(")");
        return info.toString();
    }
}
