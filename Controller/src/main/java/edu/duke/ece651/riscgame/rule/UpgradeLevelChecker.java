package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.order.Order;

public class UpgradeLevelChecker extends OrderRuleChecker{
    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public UpgradeLevelChecker(OrderRuleChecker next) {
        super(next);
    }

    // TODO 检测 upgrade 的时候，tech level 要允许你upgrade到那个等级
    boolean compare(int aimLevel, int maxLevel)
    {
        if(aimLevel <= maxLevel) return true;
        return false;
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap map) {
        return null;
    }
}
