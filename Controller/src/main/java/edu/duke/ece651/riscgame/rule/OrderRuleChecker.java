package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.order.Order;

/**
 * The RuleChecker is an abstract class that represents a rule checker
 * for a given order in the RISC game.
 *
 * @param <T> The generic type for the RuleChecker.
 */
public abstract class OrderRuleChecker<T> {
    private final OrderRuleChecker<T> next;

    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public OrderRuleChecker(OrderRuleChecker<T> next) {
        this.next = next;
    }

    /**
     * Abstract method to be implemented by derived classes.
     * Checks if the order follows the rule defined by this RuleChecker.
     *
     * @param theOrder The order we want to check.
     * @return null if the order follows the rule, otherwise returns an error message.
     */
    protected abstract String checkMyRule(Order theOrder);

    /**
     * To check the order with rules if it follows every rule, return null.
     * This method checks the current rule and then continues checking the next rule in the chain.
     *
     * @param theOrder The order we want to check.
     * @return null if the order follows every rule, otherwise returns the corresponding prompt.
     */
    public String checkOrder(Order theOrder) {
        // If the order fails the current rule: stop, the order is not legal
        String tmp = checkMyRule(theOrder);
        if (tmp != null) {
            return tmp;
        }

        // Otherwise, ask the rest of the chain.
        if (next != null) {
            return next.checkOrder(theOrder);
        }

        // If there are no more rules, then the placement is legal
        return null;
    }
}
