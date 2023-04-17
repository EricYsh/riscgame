package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

/**
 * The Move class represents a move order in the RISC game.
 * It extends the Order class and defines the behavior for a move action.
 */
public class Move extends Order {

    /**
     * Constructor for the Move class.
     */
    public Move(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex) {
        super(unitNum, src, dest, type, orderOwnId, selectedUnitsIndex);
    }

    HashSet<Territory> used = new HashSet<>();
    private int cost = Integer.MAX_VALUE;
    HashSet<String> path = new HashSet<>();
    public void shortestPath(Territory src, Territory dest, int totalCost){
        if (src.getNeighbors().contains(dest) && src.getOwnId() == dest.getOwnId()){
            if (cost > totalCost) {
                cost = totalCost;
//                System.out.println("Path is ---------------");
//                System.out.println(path);
//                System.out.println("FinalCost is ---------------");
//                System.out.println(cost);
            }
            return;
        }
        for(Territory t1 : src.getNeighbors()){
            if (used.contains(t1) || src.getOwnId() != dest.getOwnId()) continue;
            totalCost += t1.getSize();
            used.add(t1);
            path.add(t1.getName());
//            System.out.println("Territory is ---------------");
//            System.out.println(t1.getName());
//            System.out.println("TotalCost is ---------------");
//            System.out.println(totalCost);
            shortestPath(t1, dest, totalCost);
            totalCost -= t1.getSize();
            used.remove(t1);
            path.remove(t1.getName());
        }
    }

    public int getCost() {
        return cost;
    }

    /**
     * Executes the move action by transferring a specified number of units
     * from the source territory to the destination territory.
     * If the source and destination territories are the same, no action is taken.
     */
    @Override
    public void run(GameMap boardMap) {
        // TODO move cost size of territores move through * numebr of units moved
        if (this.getType().equals(Type.Move)) {
            if (boardMap.getTerritoryByName(this.getSrc().getName()).equals(boardMap.getTerritoryByName(this.getDest().getName()))) {
                return; // do nothing if they have the same source and destination
            }
            int count = this.getUnitNum();
            // TODO find a shortest path to move
            cost = Integer.MAX_VALUE;
            shortestPath(this.getSrc(), this.getDest(), 0);
            cost += getDest().getSize();
            cost *= this.getUnitNum();
            //TODO: Need minus the food resources for the corresponding player
//            System.out.println("FinalCost is ---------------");
//            System.out.println(cost);
            boardMap.getTerritoryByName(this.getSrc().getName()).minusUnit(count);
            boardMap.getTerritoryByName(this.getDest().getName()).addUnit(count);
        }
    }

    public void combat(GameMap gameMap){

    }
}
