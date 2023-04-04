package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.rule.DestChecker;
import edu.duke.ece651.riscgame.rule.Type;
import edu.duke.ece651.riscgame.rule.UnitChecker;

public class Move extends Order {

    public Move() {
        super();
//        this.ruleChecker = new DestChecker(new UnitChecker());
    }

    @Override
    protected void run() {
        if (type.equals(Type.Move)) {
            if(src.equals(dest)){
                return; // do nothing if they have same src and dest
            }

        }
    }
}
