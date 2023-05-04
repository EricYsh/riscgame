package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;

import java.util.HashSet;

public class MovePathChecker<T> extends OrderRuleChecker<T> {
    HashSet<Territory> used;
    public boolean ifPathExist(Territory src, Territory dest) {
        if (src.getNeighbors().contains(dest) && src.getOwnId() == dest.getOwnId()){
            return true;
        }
        for(Territory t1 : src.getNeighbors()){
            if (used.contains(t1) || src.getOwnId() != dest.getOwnId()) continue;
            used.add(t1);
            if(ifPathExist(t1, dest)) return true;
            used.remove(t1);
        }
        return false;
    }
    /**
     * Constructs a RuleChecker to ckeck if adjacent terrtories in the attack action
     *
     * @param next the next rule we want to check
     */
    public MovePathChecker(OrderRuleChecker<T> next) {
        super(next);
        used = new HashSet<>();
    }
    /**
     * Checks if the destination is legal
     * @param theOrder the order we want to check
     * @return null if the placement is perfect else return the corresponding prompt
     */
    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        if (theOrder.getType().equals(Type.Move)){
            if (!ifPathExist(map.getTerritoryByName(theOrder.getSrc().getName()), map.getTerritoryByName(theOrder.getSrc().getName())))  return "There is no path to move";
            return null;
        }
        return "It is not a attack order";
    }
}