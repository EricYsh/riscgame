package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.Type;

/**
 * The Commit class represents a commit order in the RISC game.
 * It works like a signal to show that one player finish giving oerders in one turn.
 */
public class Commit extends Order {

    // constructor
    public Commit(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
    }

    /**
     * Executes the commit action in the RISC game.
     * This method is called when the player finalizes their current turn.
     *
     * @param gameMap The game map on which the commit order will be executed. Not for this order
     */
    @Override
    public void run(BoardGameMap gameMap) {

    }

}
