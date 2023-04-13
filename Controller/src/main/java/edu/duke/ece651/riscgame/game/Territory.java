package edu.duke.ece651.riscgame.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Territory implements Serializable {
    private final String name;
    private String ownerName;
    private int ownId;
    private int unitNum;
    private HashSet<Territory> neighbors;

    // {unitId, Real Unit}
    private HashMap<Integer, Unit> units;

    private int technologyLevel;

    public Territory(String tname) {
        this.name = tname;
        this.technologyLevel = 1;
        neighbors = new HashSet<>();
        units = new HashMap<>();
    }

    public Territory(String tName, String oName, int oId, int unitNum) {
        this.name = tName;
        this.ownerName = oName;
        this.ownId = oId;
        this.unitNum = unitNum;
        this.technologyLevel = 1;
        neighbors = new HashSet<>();
        units = new HashMap<>();
    }


    public Territory(String tName, int ownId, int unitNum) {
        this.name = tName;
        this.ownId = ownId;
        this.unitNum = unitNum;
        this.technologyLevel = 1;
        neighbors = new HashSet<>();
        units = new HashMap<>();
    }


    public HashMap<Integer, Unit> getUnits() {
        return units;
    }

    public void setUnits(HashMap<Integer, Unit> units) {
        this.units = units;
    }

    public int getTechnologyLevel() {
        return technologyLevel;
    }

    public void setTechnologyLevel(int technologyLevel) {
        this.technologyLevel = technologyLevel;
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Territory territory = (Territory) o;
//        return ownId == territory.ownId && unitNum == territory.unitNum && technologyLevel == territory.technologyLevel && name.equals(territory.name) && ownerName.equals(territory.ownerName) && Objects.equals(neighbors, territory.neighbors) && Objects.equals(units, territory.units);
//    }

    public boolean equals(Territory t1) {
        return this.displayInfo().equals(t1.displayInfo());
    }
}
