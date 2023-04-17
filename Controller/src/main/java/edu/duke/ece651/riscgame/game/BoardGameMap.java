package edu.duke.ece651.riscgame.game;

import java.io.Serializable;
import java.util.*;

public class BoardGameMap implements GameMap, Serializable {
    private ArrayList<Territory> territories;

    private ArrayList<Player> allPlayerList;

    /**
     * @return {*}
     */
    public BoardGameMap() {
        territories = new ArrayList<>();
        allPlayerList = new ArrayList<>();
    }

    public Player getPlayerById(int id) {
        for (Player p : allPlayerList) {
            if (p.getClientID() == id) {
                return p;
            }
        }
        return null;
    }

    public Player getPlayerByName(String name) {
        for (Player p : allPlayerList) {
            if (p.getPlayerName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Player> getAllPlayerList() {
        return allPlayerList;
    }

    public void setAllPlayerList(ArrayList<Player> allPlayerList) {
        this.allPlayerList = allPlayerList;
    }

    /**
     * @param {ArrayList<Territory>} territories: the territories to be set
     * @return {*}
     * @description: This function sets the territories
     */
    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

    /**
     * @return {ArrayList<Territory>} territories: the territories
     * @description: This function gets the territories
     */
    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    /**
     * @param {String} name
     * @return {*}
     * @description: This function gets the territory by name
     */
    public Territory getTerritoryByName(String name) {
        for (Territory t : territories) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * @return {*}
     * @description: This function gets the territories by owner Name
     */
    public ArrayList<Territory> getTerritoriesByOwnerName(String ownerName) {
        ArrayList<Territory> res = new ArrayList<Territory>();
        for (Territory t : territories) {
            if (t.getOwnerName() == ownerName) {
                res.add(t);
            }
        }
        return res;
    }

    /**
     * this function adds one unit in all territories
     */
    public void callUp() {
        for (Territory t : territories) {
//            t.addUnit(1);
            t.addOneUnit();
        }
    }

    public Vector<Territory> getTerritoriesByOwnId(int ownId) {
        Vector<Territory> playerTerritory = new Vector<>();
        for (int i = 0; i < this.getTerritories().size(); i++) {
            int tempOwnId = this.getTerritories().get(i).getOwnId();
            if (tempOwnId == ownId) {
                playerTerritory.add(this.getTerritories().get(i));
            }
        }
        return playerTerritory;
    }

    public HashMap<String, Integer> getTerritoryNameAndUnitNums() {
        HashMap<String, Integer> territoryNameAndUnitNums = new HashMap<>();
        for (Territory t : this.getTerritories()) {
            territoryNameAndUnitNums.put(t.getName(), t.getUnitNum());
        }
        return territoryNameAndUnitNums;
    }
    public HashMap<String, Integer> getTerritoryNameAndOwnership() {
        HashMap<String, Integer> territoryNameAndOwnership = new HashMap<>();
        for (Territory t : this.getTerritories()) {
            territoryNameAndOwnership.put(t.getName(), t.getOwnId());
        }
        return territoryNameAndOwnership;
    }

    public void setTerritoryNameAndUnitNums(HashMap<String, Integer> territoryNameAndUnitNums) {
        for (Territory t : this.getTerritories()) {
            t.setUnitNum(territoryNameAndUnitNums.get(t.getName()));
        }
    }

    public void setTerritoryNameAndOwnership(HashMap<String, Integer> territoryNameAndOwnership) {
        for (Territory t : this.getTerritories()) {
            t.setOwnId(territoryNameAndOwnership.get(t.getName()));
        }
    }

    public boolean isAllTerritoryOccupiedByOne() {
        int ownId = this.getTerritories().get(0).getOwnId();
        for (Territory t : this.getTerritories()) {
            if (t.getOwnId() != ownId) {
                return false;
            }
            // System.out.println(t.getOwnId());
        }
//        System.out.println("true");
        return true;
    }

    public String getWinner() {
        if (isAllTerritoryOccupiedByOne()) {
            return this.getTerritories().get(0).getOwnerName();
        } else {
            return null;
        }
    }
    /* Determines whether the specified player has lost the game based on the territories they currently own.
    * @param playerId the ID of the player to check for loss
    * @return true if the player has lost (i.e. does not own any territories), false otherwise
    */
    public boolean isLose(int playerId) {
        for(Territory t : territories) {
            if (t.getOwnId() == playerId) return false;
        }
        return true;
    }
}
