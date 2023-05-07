package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.order.Order;

/**
 * The UnitChecker class is a RuleChecker that checks if the number of units
 * being moved or used for an attack in a given order is legal in the RISC game.
 *
 * this rule checker is for Move and Attack orders
 *
 * @param <T> The generic type for the RuleChecker.
 */
public class UnitChecker<T> extends OrderRuleChecker<T> {

    /**
     * Constructs a UnitChecker.
     *
     * @param next The next rule we want to check.
     */
    public UnitChecker(OrderRuleChecker<T> next) {
        super(next);
    }

    /**
     * Checks if the number of units being moved or used for an attack in a given order is legal.
     * The number of units must be less than or equal to the total number of units in the source territory.
     *
     * @param theOrder The order we want to check.
     * @return null if the number of units is legal, otherwise returns an error message.
     */
    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        if (!theOrder.getSrc().getName().equals(theOrder.getDest().getName()) && map.getTerritoryByName(theOrder.getSrc().getName()).getUnitNum() < theOrder.getUnitNum()) {
            return "There is not that much Units from the source : " + theOrder.getSrc().getName();
        }
        return null;
    }
}
