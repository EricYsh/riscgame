package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.order.Order;

import java.util.ArrayList;

public class UpgradeUnitResourceChecker extends OrderRuleChecker{
    private final int[] costForUpgrade = {0, 3, 11, 30, 55, 90, 140};

    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public UpgradeUnitResourceChecker(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        if (theOrder.getType().equals(Type.UpgradeUnit)) {
            Player player = map.getPlayerById(theOrder.getOrderOwnId());
            int originTech = player.getTechResource();
            int maxLevel = player.getTechLevel();
            ArrayList<Unit> units = map.getTerritoryByName(theOrder.getSrc().getName()).getUnits();

            ArrayList<Integer> levelToUpgrade = theOrder.getLevelToUpgrade();
            for (int i = 0; i < levelToUpgrade.size(); i++) {
                int originLevel = units.get(i).getLevel();
                int aimLevel = levelToUpgrade.get(i);
                int TechCost = costForUpgrade[aimLevel] - costForUpgrade[originLevel];
                if (TechCost > originTech) return "The technology resources are not enough to upgrade";
            }
            return null;
        }
        else return "The type should be UpgradeUnit";
    }
}
