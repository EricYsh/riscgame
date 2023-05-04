package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.order.Order;

public class CloakTechLevelChecker extends OrderRuleChecker{
    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public CloakTechLevelChecker(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        if (theOrder.getType().equals(Type.Cloak)) {
            Player player = map.getPlayerById(theOrder.getOrderOwnId());
            int originTechLevel = player.getTechLevel();
            if (originTechLevel >= 3) return null;
            return "The technology resources are not enough to upgrade Spies";
        }
        else return "The type must be UpgradeSpy";
    }
}
