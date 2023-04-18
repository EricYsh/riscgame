package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.order.Order;

public class UpgradePlayerLevelChecker extends OrderRuleChecker{
    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public UpgradePlayerLevelChecker(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        if (theOrder.getType().equals(Type.UpgradeTech)) {
           Player p =  map.getPlayerById(theOrder.getOrderOwnId());
           if (p.getTechLevel() >= 6) {
               return "You can not get upgrade anymore!";
           }
           return null;
        }
        else return "Level : The type must be UpgradeTech";
    }
}
