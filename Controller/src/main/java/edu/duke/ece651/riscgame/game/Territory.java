package edu.duke.ece651.riscgame.game;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

import javax.print.DocFlavor.STRING;

public class Territory implements Serializable {
    private final String name;
    private String ownerName;
    private int ownId;
    private int unitNum;
    private HashSet<Territory> neighbors;

    public Territory(String tname) {
        this.name = tname;
        neighbors = new HashSet<>();
    }

    public Territory(String tName, String oName, int oId, int units) {
        this.name = tName;
        this.ownerName = oName;
        this.ownId = oId;
        this.unitNum = units;
        neighbors = new HashSet<>();
    }

    public Territory(String tName, int ownId, int units) {
        this.name = tName;
        this.ownId = ownId;
        this.unitNum = units;
        neighbors = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
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

    public void setOwnerName(String oName) {
        ownerName = oName;
    }

    public void setOwnId(int oId) {
        ownId = oId;
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

    public void addNeighbors(Territory... territories) {
        for (Territory t : territories) {
            addNeighbor(t);
        }
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
