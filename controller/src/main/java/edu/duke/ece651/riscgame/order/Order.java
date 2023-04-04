package edu.duke.ece651.riscgame.order;
import edu.duke.ece651.riscgame.rule.Type;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.RuleChecker;

public abstract class Order {
    int unitNum;
    Territory src;
    Territory dest;
    RuleChecker rules;
    public Type type;

    protected abstract void run();
    protected abstract void checkRule();
}

