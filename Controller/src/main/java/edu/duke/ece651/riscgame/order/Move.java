package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.rule.Type;

/**
 * The Move class represents a move order in the RISC game.
 * It extends the Order class and defines the behavior for a move action.
 */
public class Move extends Order {

    /**
     * Constructor for the Move class.
     */
    public Move() {
        super();
        // this.ruleChecker = new DestChecker(new UnitChecker());
    }

    /**
     * Executes the move action by transferring a specified number of units
     * from the source territory to the destination territory.
     * If the source and destination territories are the same, no action is taken.
     */
    @Override
    protected void run() {
        if (this.getType().equals(Type.Move)) {
            if (this.getSrc().equals(this.getDest())) {
                return; // do nothing if they have the same source and destination
            }
            int count = this.getUnitNum();
            this.getSrc().minusUnit(count);
            this.getDest().addUnit(count);
        }
    }
}
