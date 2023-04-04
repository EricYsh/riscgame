package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.rule.Type;

public class Commit extends Order {

    public Commit() {
        super();
//        this.ruleChecker = new DestChecker(new UnitChecker());
    }

    @Override
    protected void run() {
        if (type.equals(Type.Commit)) {

        }
    }
}
