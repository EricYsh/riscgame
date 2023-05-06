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
    public int cloakTimes;


    private ArrayList<Unit> units;
    //  1 : level1
    //  2 : leve 3
    //  3 : level7
    //  user input : 1,2,3

    private ArrayList<Spy> spies;

    private ArrayList<Spy> enemySpies;
    UnitFactory unitFactory = new UnitFactory();

    public Territory(String tname) {
        this.name = tname;
        neighbors = new HashSet<>();
        units = new ArrayList<>();
        for (int i = 0; i < unitNum; i++) {
            units.add((Unit) unitFactory.createUnit(0, 0));
        }
        this.spies = new ArrayList<>();
        this.enemySpies = new ArrayList<>();
        this.cloakTimes = 0;
        this.size = 10;
    }

    public Territory(String tName, String oName, int oId, int unitNum) {
        this.name = tName;
        this.ownerName = oName;
        this.ownId = oId;
        this.unitNum = unitNum;
        neighbors = new HashSet<>();
        units = new ArrayList<>();
        for (int i = 0; i < unitNum; i++) {
            units.add((Unit) unitFactory.createUnit(0, 0));
        }
        this.spies = new ArrayList<>();
        this.enemySpies = new ArrayList<>();
        this.cloakTimes = 0;
        this.size = 10;
    }


    public void addUpgradeUnit(Unit unitToAdd) {
        units.add(unitToAdd);
    }

    public void deleteOldLevelUnit(int level) {
        Iterator<Unit> iterator = units.iterator();
        while (iterator.hasNext()) {
            Unit unit = iterator.next();
            if (unit.getLevel() == level) {
                iterator.remove();
                break;
            }
        }
    }

    public int getCloakTimes() {
        return cloakTimes;
    }

    public void setCloakTimes(int cloakTimes) {
        this.cloakTimes = cloakTimes;
    }

    public void minusCloakByOne() {
        this.cloakTimes--;
    }

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


    public ArrayList<Spy> getSpies() {
        return spies;
    }

    public ArrayList<Spy> getEnemySpies() {
        return enemySpies;
    }

    public void addSpyList(Spy... spyList) {
        spies.addAll(Arrays.asList(spyList));
    }

    public void addEnemySpyList(Spy... spyList) {
        enemySpies.addAll(List.of(spyList));
    }


    public void deleteSpy(int spyId) {
        for (Spy s : spies) {
            if (s.getSpyId() == spyId) {
                spies.remove(s);
                break;
            }
        }
    }

    public void deleteEnemySpy(int spyId) {
        for (Spy s : enemySpies) {
            if (s.getSpyId() == spyId) {
                enemySpies.remove(s);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Territory territory = (Territory) o;
        return ownId == territory.ownId && unitNum == territory.unitNum && name.equals(territory.name) && ownerName.equals(territory.ownerName);
    }


    private String splitString(String input, int length) {
        String[] words = input.split("\\s+"); // 根据空格分隔字符串
        StringBuilder output = new StringBuilder(input.length());
        int lineLength = 0;
        for (String word : words) {
            if (lineLength + word.length() > length) {
                output.append("\n"); // 换行
                lineLength = 0;
            }
            output.append(word).append(" ");
            lineLength += word.length() + 1;
        }
        return output.toString();
    }


    public String getAllUnitsInfo(int viewerId) {
        StringBuilder info = new StringBuilder("");
        info.append("Size: ").append(size).append("\n");
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
            info.append(" with Index: " + "\n " + splitString(levelIndices.get(i).toString(), 30));
            info.append("\n");
        }
        // display all your spy with index
        if (this.getOwnId() == viewerId) {
            int spy_index = 0;
            if (spies.size() > 0) {
                for (Spy s : spies) {
                    if (s.getOwnerId() == this.ownId) {
                        info.append("Spy ").append(s.getSpyId()).append(" with index: ").append(spy_index++).append("\n");
                    }
                }
            } else {
                info.append("Spies: No Your Own Spy in this territory\n");
            }
            if (cloakTimes > 0) {
                info.append("Cloak remaining times: ").append(cloakTimes).append("\n");
            }
            else {
                info.append("No Cloak \n");
            }
        }

        return info.toString();
    }

    private boolean[] visibleForPlayer = {false, false, false, false, false};
    private StringBuilder oldInfo;

    public boolean isVisible(int viewerId) {
        for (Spy s : enemySpies) {
            if (s.getOwnerId() == viewerId) {
                return true;
            }
        }
        if (cloakTimes > 0) return false;
        for (Territory tNeighbor : neighbors) {
            if (tNeighbor.getOwnId() == viewerId) {
                return true;
            }
        }
        return false;
    }

    public String getFogInfo(int viewerId) {
        StringBuilder info = new StringBuilder("");
        // any immediately adjacent enemy territory is visible to the player
        // for any territory that has never been seen, only the outline should be displayed,
        //  but no information about who occurpy it or how many units are there
        if (isVisible(viewerId)) {
            info.append(getAllUnitsInfo(viewerId));
            System.out.println("visible" + viewerId);
            visibleForPlayer[viewerId] = true;
        }
        // TODO if you have previously seen a territory, but no longer see it now (i.e. lose adjacncy)
        //  show what you know in the past and clearly indicate the info is old!!!
        oldInfo = info;


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
