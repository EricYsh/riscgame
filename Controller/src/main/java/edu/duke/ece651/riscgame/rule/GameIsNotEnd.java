package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.order.Order;
//TODO: it is strange to extends OrderRuleChecker
// may be deleted latterly
public class GameIsNotEnd extends OrderRuleChecker {
    /**
     * Constructs a RuleChecker
     *
     * @param next the next rule we want to check
     */
    public GameIsNotEnd(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder) {
        return null;
    }
}
