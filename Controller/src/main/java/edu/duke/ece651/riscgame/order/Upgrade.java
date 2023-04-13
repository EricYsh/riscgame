package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.rule.*;

/**
 * The Move class represents a upgrade order in the RISC game.
 */
public class Upgrade extends Order {

    // constructor
    public Upgrade(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
    }

    @Override
    public void run(GameMap boardMap) {
        if (this.getType().equals(Type.Upgrade)) {
            // TODO upgrade order
            UnitFactory factory = new UnitFactory();
            UnitInterface level1Unit = factory.createUnit(1, 0);
        }
    }
}

