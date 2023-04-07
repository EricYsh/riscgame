package edu.duke.ece651.riscgame.game;

import java.util.ArrayList;
public class BoardMap {
    private ArrayList<Territory> territories;

    /**
     * @description: This is the constructor of BoardMap
     * @return {*}
     */
    public BoardMap() {territories = new ArrayList<>();}

    public BoardMap(int playerNum) {
        territories = new ArrayList<>();

        switch (playerNum) {
            case 2:
                // Perform the action specific to Player 1
                initByPlayerNumber2();
                break;// Perform the action specific to Player 2
            case 4:
                // Perform the action specific to Player 3
                initByPlayerNumber4();
                break;
            case 5:
                // Perform the action specific to Player 4
                initByPlayerNumber5();
                break;
            default: // also used for 3 player
                // Handle the case when playerNum is not between 1 and 4
                initByPlayerNumber3();
                break;
        }
    }
    private void initByPlayerNumber2(){

    }
    private void initByPlayerNumber3(){

    }

    private void initByPlayerNumber4(){
        Territory t1 = new Territory("t1", 0, 10);
        Territory t2 = new Territory("t2", 0, 10);
        Territory t3 = new Territory("t3", 0, 10);

        Territory t4 = new Territory("t4", 1, 10);
        Territory t5 = new Territory("t5", 1, 10);
        Territory t6 = new Territory("t6", 1, 10);

        Territory t7 = new Territory("t7", 2, 10);
        Territory t8 = new Territory("t8", 2, 10);
        Territory t9 = new Territory("t9", 2, 10);

        Territory t10 = new Territory("t10", 3, 10);
        Territory t11 = new Territory("t11", 3, 10);
        Territory t12 = new Territory("t12", 3, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);
        t4.addNeighbors(t1, t5, t7);
        t5.addNeighbors(t2, t4, t6, t8);
        t6.addNeighbors(t3, t5, t9);
        t7.addNeighbors(t4, t8, t10);
        t8.addNeighbors(t5, t7, t9, t11);
        t9.addNeighbors(t6, t8, t12);
        t10.addNeighbors(t7, t11);
        t11.addNeighbors(t8, t10, t12);
        t12.addNeighbors(t9, t11);

    }
    private void initByPlayerNumber5(){
        Territory t1 = new Territory("t1", 0, 10);
        Territory t2 = new Territory("t2", 0, 10);
        Territory t3 = new Territory("t3", 0, 10);

        Territory t4 = new Territory("t4", 1, 10);
        Territory t5 = new Territory("t5", 1, 10);
        Territory t6 = new Territory("t6", 1, 10);

        Territory t7 = new Territory("t7", 2, 10);
        Territory t8 = new Territory("t8", 2, 10);
        Territory t9 = new Territory("t9", 2, 10);

        Territory t10 = new Territory("t10", 3, 10);
        Territory t11 = new Territory("t11", 3, 10);
        Territory t12 = new Territory("t12", 3, 10);

        Territory t13 = new Territory("t13", 4, 10);
        Territory t14 = new Territory("t14", 4, 10);
        Territory t15 = new Territory("t15", 4, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5, t7);
        t5.addNeighbors(t2, t4, t6, t8);
        t6.addNeighbors(t3, t5, t9);

        t7.addNeighbors(t4, t8, t10);
        t8.addNeighbors(t5, t7, t9, t11);
        t9.addNeighbors(t6, t8, t12);

        t10.addNeighbors(t7, t11, t13);
        t11.addNeighbors(t8, t10, t12, t14);
        t12.addNeighbors(t9, t11, t15);

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
