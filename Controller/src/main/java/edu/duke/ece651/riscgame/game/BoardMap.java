package edu.duke.ece651.riscgame.game;

import java.util.ArrayList;
public class BoardMap {
    private ArrayList<Territory> territories;

    /**
     * @description: This is the constructor of BoardMap
     * @return {*}
     */    
    public BoardMap() {
        territories = new ArrayList<Territory>();
    }

    /**
     * @description: This function sets the territories
     * @param {ArrayList<Territory>} territories: the territories to be set
     * @return {*}
     */    
    public void setTerritories(ArrayList<Territory> territories) {
        this.territories = territories;
    }

    /**
     * @description: This function gets the territories
     * @return {ArrayList<Territory>} territories: the territories
     */
    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    /**
     * @description: This function gets the territory by name
     * @param {String} name
     * @return {*}
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
     * @description: This function gets the territories by owner Name
     * @return {*}
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
    public void callUp () {
        for (Territory t : territories) {
            t.addUnit(1);
        }
    }

}
