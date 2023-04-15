package edu.duke.ece651.riscgame.game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Territory implements Serializable {
    private final String name;
    private String ownerName;
    private int ownId;
    private int unitNum;
    private int size;
    private HashSet<Territory> neighbors;

    // {unitId, Real Unit}
    private HashMap<Integer, Unit> units;
    //  TODO confirm evol2 design
    //  1 : level1
    //  2 : leve 3
    //  3 : level7
    //  user input : 1,2,3
    UnitFactory unitFactory = new UnitFactory();

    private int technologyLevel;
    private final int foodResourceIncreasement = 10;
    private final int techResourceIncreasement = 50;

    public Territory(String tname) {
        this.name = tname;
        this.technologyLevel = 1;
        neighbors = new HashSet<>();
        units = new HashMap<>();
        for (int i = 0; i < unitNum; i++) {
            units.put(i, (Unit) unitFactory.createUnit(0, 0));
        }
        this.size = 10;
    }

    public Territory(String tName, String oName, int oId, int unitNum) {
        this.name = tName;
        this.ownerName = oName;
        this.ownId = oId;
        this.unitNum = unitNum;
        this.technologyLevel = 1;
        neighbors = new HashSet<>();
        units = new HashMap<>();
        for (int i = 0; i < unitNum; i++) {
            units.put(i, (Unit) unitFactory.createUnit(0, 0));
        }
        this.size = 10;
    }


    public Territory(String tName, int ownId, int unitNum) {
        this.name = tName;
        this.ownId = ownId;
        this.unitNum = unitNum;
        this.technologyLevel = 1;
        neighbors = new HashSet<>();
        units = new HashMap<>();
        for (int i = 0; i < unitNum; i++) {
            units.put(i, (Unit) unitFactory.createUnit(0, 0));
        }
        this.size = 10;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Territory territory = (Territory) o;
        return ownId == territory.ownId && unitNum == territory.unitNum && technologyLevel == territory.technologyLevel && name.equals(territory.name) && ownerName.equals(territory.ownerName) ;
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

    public int getFoodResource() {
        return foodResourceIncreasement;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTechResource() {
        return techResourceIncreasement;
    }

    //    public boolean equals(Territory t1) {
//        if (this.ownId == t1.ownId && this.getUnitNum() == t1.getUnitNum()) {
//
//        }
//        return this.displayInfo().equals(t1.displayInfo());
//    }
}
