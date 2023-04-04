package edu.duke.ece651.riscgame;

public abstract class RuleChecker<T> {
    private final RuleChecker<T> next;
    /**
     * Constructs a RuleChecker
     * @param next the next rule we want to check
     */
    public RuleChecker(RuleChecker<T> next) {
        this.next = next;
    }
    protected abstract String checkMyRule(Order theOrder);
    /**
     * To check the order with rules if it follows every rule, return null
     * @param theOrder the order we want to check
     * return null if the order follows every rule otherwise return the corresponding prompt
     */
    public String checkOrder (Order theOrder) {
        //if we fail our own rule: stop the order is not legal
        String tmp = checkMyRule(theOrder);
        if (tmp!= null) {
            return tmp;
        }
        //otherwise, ask the rest of the chain.
        if (next != null) {
            return next.checkOrder(theOrder);
        }
        //if there are no more rules, then the placement is legal
        return null;
    }
}