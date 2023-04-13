package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardGameMap;
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
    public void run(BoardGameMap boardMap) {

        // TODO move cost size of territores move through * numebr of units moved
        if (this.getType().equals(Type.Move)) {
            if (boardMap.getTerritoryByName(this.getSrc().getName()).equals(boardMap.getTerritoryByName(this.getDest().getName()))) {
                return; // do nothing if they have the same source and destination
            }
            int count = this.getUnitNum();
            // TODO find a shortest path to move
            boardMap.getTerritoryByName(this.getSrc().getName()).minusUnit(count);
            boardMap.getTerritoryByName(this.getDest().getName()).addUnit(count);
        }
    }
}
