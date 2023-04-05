package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.Type;

public class testOrder extends Order {
    String str;

    public testOrder(int unitNum, Territory src, Territory dest, Type type) {
        super(unitNum, src, dest, type);
    }

    @Override
    public void run() {

    }

}
