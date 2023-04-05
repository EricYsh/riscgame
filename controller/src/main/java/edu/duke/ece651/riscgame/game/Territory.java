package edu.duke.ece651.riscgame.game;

import java.io.Serializable;
import java.util.HashSet;

/**
 * The Territory class represents a territory in the RISC game.
 * Each territory has a name, an owner, a number of units, and a set of neighboring territories.
 */
public class Territory implements Serializable {
    private final String name;
    private int ownId;
    private int unitNum;
    private HashSet<Territory> neighbors;

    /**
     * Constructor for the Territory class.
     *
     * @param tname The name of the territory.
     */
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
    // Getters
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

    // Setters

    public void setOwnId(int oid) {
        ownId = oid;
    }

    public void setUnitNum(int num) {
        unitNum = num;
    }

    // Other methods

    /**
     * Adds a number of units to the territory.
     *
     * @param num The number of units to add.
     */
    public void addUnit(int num) {
        unitNum += num;
    }

    /**
     * Removes a number of units from the territory.
     *
     * @param num The number of units to remove.
     */
    public void minusUnit(int num) {
        unitNum -= num;
    }

    /**
     * Adds a neighboring territory to the territory's set of neighbors.
     *
     * @param t1 The neighboring territory to add.
     */
    public void addNeighbor(Territory t1) {
        neighbors.add(t1);
    }

    /**
     * Generates a formatted string with information about the territory, including
     * the number of units, the territory name, and the names of neighboring territories.
     *
     * @return A formatted string with information about the territory.
     */
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
