package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.order.Order;

public class DestChecker<T> extends RuleChecker<T> {
    /**
     * Constructs a RuleChecker
     *
     * @param next the next rule we want to check
     */
    public DestChecker(RuleChecker<T> next) {
        super(next);
    }
    /**
     * Checks if the destination is legal
     * @param theOrder the order we want to check
     * @return null if the placement is perfect else return the corresponding prompt
     */
    @Override
    protected String checkMyRule(Order theOrder) {
        if (theOrder.getType().equals(Type.Move)){//the destination and source must from one player
            if (!theOrder.isFromOnePlayer()) return "Error: the source and destination should come from one player.";
            return null;
        }
        else if (theOrder.getType().equals(Type.Attack)){
            if (theOrder.isFromOnePlayer()) return "Error: the source and destination should come from different players.";
            return null;
        }
        return "Error: the Order type is illegal";
    }

}
