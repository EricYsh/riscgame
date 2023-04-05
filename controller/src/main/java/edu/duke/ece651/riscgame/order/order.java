package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.rule.Type;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.RuleChecker;

public abstract class Order {
    private int unitNum;
    private Territory src;
    private Territory dest;
    private RuleChecker rules;
    private Type type;

    public int getUnitNum() {
        return unitNum;
    }

    public Territory getSrc() {
        return src;
    }

    public Territory getDest() {
        return dest;
    }

    public RuleChecker getRules() {
        return rules;
    }

    public Type getType() {
        return type;
    }

    protected abstract void run();

    protected abstract void checkRule();

    public boolean isFromOnePlayer() {
        return dest.getOwnId() == src.getOwnId();
    }
}
