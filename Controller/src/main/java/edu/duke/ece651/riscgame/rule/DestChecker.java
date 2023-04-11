package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.order.Order;

/**
 * The DestChecker class is a RuleChecker that checks if the destination is legal
 * for a given order in the RISC game.
 *
 * This rule checker is for Move and Attack orders
 *
 * @param <T> The generic type for the RuleChecker.
 */
public class DestChecker<T> extends OrderRuleChecker<T> {

    /**
     * Constructs a DestChecker.
     *
     * @param next The next rule we want to check.
     */
    public DestChecker(OrderRuleChecker<T> next) {
        super(next);
    }

    /**
     * Checks if the destination is legal for a given order.
     * For a Move order, the source and destination territories must belong to the same player.
     * For an Attack order, the source and destination territories must belong to different players.
     *
     * @param theOrder The order we want to check.
     * @return null if the destination is valid, otherwise returns an error message.
     */
    @Override
    protected String checkMyRule(Order theOrder) {
        if (theOrder.getType().equals(Type.Move)) { // the destination and source must belong to the same player
            if (!theOrder.isFromOnePlayer()) {
                return "The source and destination should come from one player.";
            }
            return null;
        } else if (theOrder.getType().equals(Type.Attack)) {
            if (theOrder.isFromOnePlayer()) {
                return "The source and destination should come from different players.";
            }
            return null;
        }
        return "The Order type is neither Move nor Attack";
    }

}
