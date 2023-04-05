package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.order.Order;

public class UnitChecker<T> extends RuleChecker<T>{
    /**
     * Constructs a RuleChecker
     *
     * @param next the next rule we want to check
     */
    public UnitChecker(RuleChecker<T> next) {
        super(next);
    }
    /**
     * Checks if the destination is legal
     * @param theOrder the order we want to check
     * @return null if the placement is perfect else return the corresponding prompt
     */
    @Override
    protected String checkMyRule(Order theOrder) {
        if(!theOrder.getSrc().getName().equals(theOrder.getDest().getName()) && theOrder.getSrc().getUnitNum() < theOrder.getUnitNum()) {
            return "Error: there is not that much Units from the source : " + theOrder.getSrc().getName();
        }
        return null;
    }
}
