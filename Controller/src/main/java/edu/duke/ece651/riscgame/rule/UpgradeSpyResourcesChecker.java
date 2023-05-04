package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.order.Order;

public class UpgradeSpyResourcesChecker extends OrderRuleChecker {

    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public UpgradeSpyResourcesChecker(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        if (theOrder.getType().equals(Type.UpgradeSpy)) {
            Player player = map.getPlayerById(theOrder.getOrderOwnId());
            int originTech = player.getTechResource();
            int consume = theOrder.getSelectedUnitsIndex().size() * 20;
            if (originTech >= consume) return null;
            return "The technology resources are not enough to upgrade Spies";
        }
        else return "The type must be UpgradeSpy";
    }
}
