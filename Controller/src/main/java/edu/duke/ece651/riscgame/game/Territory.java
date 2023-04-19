package edu.duke.ece651.riscgame.game;

import java.io.Serializable;
import java.util.*;

public class Territory implements Serializable {
    private final String name;
    private String ownerName;
    private int ownId;
    private int unitNum;
    private int size;
    private HashSet<Territory> neighbors;

    // {unitId, Real Unit}
//    private HashMap<Integer, Unit> units;
    //  TODO confirm evol2 design. Use hashmap or arraylist?
    private ArrayList<Unit> units;
    //  1 : level1
    //  2 : leve 3
    //  3 : level7
    //  user input : 1,2,3
    UnitFactory unitFactory = new UnitFactory();

    public Territory(String tname) {
        this.name = tname;
        neighbors = new HashSet<>();
//        units = new HashMap<>();
//        for (int i = 0; i < unitNum; i++) {
//            units.put(i, (Unit) unitFactory.createUnit(0, 0));
//        }
        units = new ArrayList<>();
        for (int i = 0; i < unitNum; i++) {
            units.add((Unit) unitFactory.createUnit(0, 0));
        }
        this.size = 10;
    }

    public Territory(String tName, String oName, int oId, int unitNum) {
        this.name = tName;
        this.ownerName = oName;
        this.ownId = oId;
        this.unitNum = unitNum;
        neighbors = new HashSet<>();
//        units = new HashMap<>();
//        for (int i = 0; i < unitNum; i++) {
//            units.put(i, (Unit) unitFactory.createUnit(0, 0));
//        }
        units = new ArrayList<>();
        for (int i = 0; i < unitNum; i++) {
            units.add((Unit) unitFactory.createUnit(0, 0));
        }
        this.size = 10;
    }


//    public Territory(String tName, int ownId, int unitNum) {
//        this.name = tName;
//        this.ownId = ownId;
//        this.unitNum = unitNum;
//        neighbors = new HashSet<>();
////        units = new HashMap<>();
////        for (int i = 0; i < unitNum; i++) {
////            units.put(i, (Unit) unitFactory.createUnit(0, 0));
////        }
//        units = new ArrayList<>();
//        for (int i = 0; i < unitNum; i++) {
//            units.add((Unit) unitFactory.createUnit(0, 0));
//        }
//        this.size = 10;
//    }

    public void addUpgradeUnit(Unit unitToAdd) {
        units.add(unitToAdd);
    }

    public void deleteOldLevelUnit(int level) {
        Iterator<Unit> iterator = units.iterator();
        while(iterator.hasNext()){
            Unit unit = iterator.next();
            if (unit.getLevel() == level) {
                iterator.remove();
                break;
            }
        }
    }


//    public HashMap<Integer, Unit> getUnits() {
//        return units;
//    }
//
//    public void setUnits(HashMap<Integer, Unit> units) {
//        this.units = units;
//    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
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

    public void addOneUnit() {
        units.add((Unit) unitFactory.createUnit(0, 0));
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

    public void addUnitList(Collection<Unit> unitsList) {
        units.addAll(unitsList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Territory territory = (Territory) o;
        return ownId == territory.ownId && unitNum == territory.unitNum && name.equals(territory.name) && ownerName.equals(territory.ownerName);
    }


    public String getAllUnitsInfo() {
        StringBuilder info = new StringBuilder("");
        int[] levelCount = new int[7];
        List<List<Integer>> levelIndices = new ArrayList<>();

        // Initialize the ArrayLists in the list
        for (int i = 0; i < 7; i++) {
            levelIndices.add(new ArrayList<>());
        }

        for (Unit u : units) {
            int level = u.getLevel();
            levelCount[level]++;
            levelIndices.get(level).add(units.indexOf(u));
        }

        for (int i = 0; i < levelCount.length; i++) {
            info.append("Level " + i + " : " + levelCount[i]);
            info.append(" with Index: " + levelIndices.get(i));
            info.append("\n");
        }
        return info.toString();
    }

    public void displayAllUnit() {
        // display all units according to 7 levels
        int[] levelCount = new int[7];
        List<List<Integer>> levelIndices = new ArrayList<>();

        // Initialize the ArrayLists in the list
        for (int i = 0; i < 7; i++) {
            levelIndices.add(new ArrayList<>());
        }

        for (Unit u : units) {
            int level = u.getLevel();
            levelCount[level]++;
            levelIndices.get(level).add(units.indexOf(u));
        }

        for (int i = 0; i < levelCount.length; i++) {
            System.out.print("Level " + i + " : " + levelCount[i]);
            System.out.println(" with Index: " + levelIndices.get(i));
        }
    }


    public String displayInfo() {
        StringBuilder info = new StringBuilder("");
        String format = units.size() + " units in " + name + " (next to: ";
        info.append(format);
        for (Territory t1 : neighbors) {
            info.append(t1.getName()).append(", ");
        }
        info.delete(info.length() - 2, info.length());//delete the last two character (", ")
        info.append(")");
        return info.toString();
    }

    public int getFoodResource() {
        int foodResourceIncreasement = 10;
        return foodResourceIncreasement;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTechResource() {
        int techResourceIncreasement = 50;
        return techResourceIncreasement;
    }

    // remove all units in this territory
    public void removeAllUnits() {
        units.clear();
    }

    //    public boolean equals(Territory t1) {
//        if (this.ownId == t1.ownId && this.getUnitNum() == t1.getUnitNum()) {
//
//        }
//        return this.displayInfo().equals(t1.displayInfo());
//    }
}
