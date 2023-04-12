package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.*;

/**
 * The Move class represents a move order in the RISC game.
 * It extends the Order class and defines the behavior for a move action.
 */
public class Move extends Order {

    /**
     * Constructor for the Move class.
     */
    public Move(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
    }

    /**
     * Executes the move action by transferring a specified number of units
     * from the source territory to the destination territory.
     * If the source and destination territories are the same, no action is taken.
     */
    @Override
    public void run(GameMap boardMap) {
        if (this.getType().equals(Type.Move)) {
            if (this.getSrc().equals(this.getDest())) {
                return; // do nothing if they have the same source and destination
            }
            int count = this.getUnitNum();
//            String name = ownership.get(this.getOrderOwnId());
            boardMap.getTerritoryByName(this.getSrc().getName()).minusUnit(count);
            boardMap.getTerritoryByName(this.getDest().getName()).addUnit(count);
//            this.getSrc().minusUnit(count);
//            this.getDest().addUnit(count);
        }
    }
}
