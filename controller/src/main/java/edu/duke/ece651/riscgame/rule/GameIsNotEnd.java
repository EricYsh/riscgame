package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.order.Order;

public class GameIsNotEnd extends RuleChecker {
    /**
     * Constructs a RuleChecker
     *
     * @param next the next rule we want to check
     */
    public GameIsNotEnd(RuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder) {
        return null;
    }
}
