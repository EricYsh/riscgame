package edu.duke.ece651.riscgame;

public abstract class Order {
    int unitNum;
    Territory src;
    Territory dest;
    RuleChecker rules;
    Type type;

    protected abstract void run();
    protected abstract void checkRule();
}
