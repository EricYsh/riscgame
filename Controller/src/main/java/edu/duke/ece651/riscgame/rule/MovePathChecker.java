package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;

import java.util.HashSet;

public class MovePathChecker<T> extends RuleChecker<T>{
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
    public MovePathChecker(RuleChecker<T> next) {
        super(next);
        used = new HashSet<>();
    }
    /**
     * Checks if the destination is legal
     * @param theOrder the order we want to check
     * @return null if the placement is perfect else return the corresponding prompt
     */
    @Override
    protected String checkMyRule(Order theOrder) {
        if (theOrder.getType().equals(Type.Move) && !ifPathExist(theOrder.getSrc(), theOrder.getDest())){
            return "Error: there is no path to move";
        }
        return null;
    }
}