package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.order.Order;

import java.util.ArrayList;

public class UpgradePlayerResourceChecker extends OrderRuleChecker {
    int[] cost = {50, 75, 125, 200, 300};

    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public UpgradePlayerResourceChecker(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {

        if (theOrder.getType().equals(Type.UpgradeTech)) {
            Player player = map.getPlayerById(theOrder.getOrderOwnId());
            int originTech = player.getTechResource();
            int originLevel = player.getTechLevel();
            int consume = cost[originLevel - 1];
            if (originTech >= consume) return null;
            return "The technology resources are not enough to upgrade";
        }
        else return "The type must be UpgradeTech";
    }
}
