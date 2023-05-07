package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.order.Order;

import java.util.ArrayList;

public class UpgradeUnitLevelChecker extends OrderRuleChecker{
    /**
     * Constructs a RuleChecker.
     * @param next The next rule we want to check.
     */
    public UpgradeUnitLevelChecker(OrderRuleChecker next) {
        super(next);
    }


    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        if (theOrder.getType().equals(Type.UpgradeUnit)) {
            ArrayList<Integer> targetLevel = theOrder.getLevelToUpgrade();
            ArrayList<Integer> unitIndex = theOrder.getSelectedUnitsIndex();
            ArrayList<Unit> unitList = map.getTerritoryByName(theOrder.getSrc().getName()).getUnits();

            for (int i = 0; i < targetLevel.size(); i++) {
                int levelToCheck = targetLevel.get(i);
                int indexToCheck = unitIndex.get(i);
                int originLevel = unitList.get(indexToCheck).getLevel();
                if (levelToCheck > 6) return "The maximum unit level is 6!";
                else if (levelToCheck <= originLevel) return "The upgrade level must be higher than its origin level!";
                else return null;
            }
        }
        return "The type should be UpgradeUnit";
    }
}
