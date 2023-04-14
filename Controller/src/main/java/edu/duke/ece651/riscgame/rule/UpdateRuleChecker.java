package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.Territory;

public class UpdateRuleChecker {
    // TODO 检测 upgrade 的时候，tech level 要允许你upgrade到那个等级
    boolean compare(int aimLevel, int maxLevel)
    {
        if(aimLevel <= maxLevel) return true;
        return false;
    }
}
