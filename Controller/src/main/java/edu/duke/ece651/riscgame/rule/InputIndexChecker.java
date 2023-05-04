package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.order.Order;

public class InputIndexChecker extends OrderRuleChecker{
    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public InputIndexChecker(OrderRuleChecker next) {
        super(next);
    }


    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        for(Integer i : theOrder.getSelectedUnitsIndex()) {
            if (i < 1) {
                return "Unit index should be greater than 0!";
            } else if (i > map.getTerritoryByName(theOrder.getSrc().getName()).getUnitNum()){
                return "Unit index can not be larger than " + theOrder.getSrc().getUnitNum() + "!";
            }
        }
        return null;
    }
}