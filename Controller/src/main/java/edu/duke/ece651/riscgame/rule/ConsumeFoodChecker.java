package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.order.Order;

public class ConsumeFoodChecker extends OrderRuleChecker{
    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public ConsumeFoodChecker(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        Player player = map.getPlayerById(theOrder.getOrderOwnId());
        int totalFood = player.getFoodResource();
        int consuming = theOrder.consumeFood();
        if (consuming <= totalFood) return null;
        return "The food is not enough for this order";
    }

}
