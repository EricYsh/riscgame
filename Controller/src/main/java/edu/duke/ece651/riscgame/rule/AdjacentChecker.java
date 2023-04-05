package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.order.Order;

public class AdjacentChecker<T> extends RuleChecker<T>{
    /**
     * Constructs a RuleChecker to ckeck if adjacent terrtories in the attack action
     *
     * @param next the next rule we want to check
     */
    public AdjacentChecker(RuleChecker<T> next) {
        super(next);
    }
    /**
     * Checks if the destination is legal
     * @param theOrder the order we want to check
     * @return null if the placement is perfect else return the corresponding prompt
     */
    @Override
    protected String checkMyRule(Order theOrder) {
        if (theOrder.getType().equals(Type.Attack)) {//the destination and source must from one player
            if (!theOrder.getSrc().getNeighbors().contains(theOrder.getDest())) {
                return "Error: Units may only attack directly adjacent territories";
            }
            return null;
        }
        return "Error: it is not a attack action";
    }
}
