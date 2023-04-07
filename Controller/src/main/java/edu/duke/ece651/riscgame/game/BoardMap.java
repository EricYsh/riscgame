package edu.duke.ece651.riscgame.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BoardMap {
    private ArrayList<Territory> territories;

    /**
     * @return {*}
     */
    public BoardMap() {
        territories = new ArrayList<>();
    }

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


    private void initByPlayerNumber2() {
        Territory t1 = new Territory("T1", 0, 10);
        Territory t2 = new Territory("T2", 0, 10);
        Territory t3 = new Territory("T3", 0, 10);

        Territory t4 = new Territory("T4", 1, 10);
        Territory t5 = new Territory("T5", 1, 10);
        Territory t6 = new Territory("T6", 1, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5);
        t5.addNeighbors(t2, t4, t6);
        t6.addNeighbors(t3, t5);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6);

    }

    private void initByPlayerNumber3() {
        Territory t1 = new Territory("T1", 0, 10);
        Territory t2 = new Territory("T2", 0, 10);
        Territory t3 = new Territory("T3", 0, 10);

        Territory t4 = new Territory("T4", 1, 10);
        Territory t5 = new Territory("T5", 1, 10);
        Territory t6 = new Territory("T6", 1, 10);

        Territory t7 = new Territory("T7", 2, 10);
        Territory t8 = new Territory("T8", 2, 10);
        Territory t9 = new Territory("T9", 2, 10);

        t1.addNeighbors(t2, t4);
        t2.addNeighbors(t1, t3, t5);
        t3.addNeighbors(t2, t6);

        t4.addNeighbors(t1, t5, t7);
        t5.addNeighbors(t2, t4, t6, t8);
        t6.addNeighbors(t3, t5, t9);

        t7.addNeighbors(t4, t8);
        t8.addNeighbors(t5, t7, t9);
        t9.addNeighbors(t6, t8);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6, t7, t8, t9);

    }

    private void initByPlayerNumber4() {
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

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);

    }

    private void initByPlayerNumber5() {
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

        t10.addNeighbors(t7, t11, t13);
        t11.addNeighbors(t8, t10, t12, t14);
        t12.addNeighbors(t9, t11, t15);

        t13.addNeighbors(t10, t14);
        t14.addNeighbors(t11, t13, t15);
        t15.addNeighbors(t12, t14);

        Collections.addAll(territories,t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15);

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
            t.addUnit(1);
        }
    }

    public Collection<Territory> getTerritoriesByOwnId(int ownId) {
        ArrayList<Territory> playerTerritory = new ArrayList<>();
        for (int i = 0; i < this.getTerritories().size(); i++) {
            int tempOwnId = this.getTerritories().get(i).getOwnId();
            if (tempOwnId == ownId) {
                playerTerritory.add(this.getTerritories().get(i));
            }
        }
        return playerTerritory;
    }
}
