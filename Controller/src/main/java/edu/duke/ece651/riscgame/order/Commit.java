package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.rule.Type;

import java.util.ArrayList;

/**
 * The Commit class represents a commit order in the RISC game.
 * It works like a signal to show that one player finish giving oerders in one turn.
 */
public class Commit extends Order {

    // constructor
    public Commit(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex, ArrayList<Integer> levelToUpgrade) {
        super(unitNum, src, dest, type, orderOwnId, selectedUnitsIndex, levelToUpgrade);
    }

    /**
     * Executes the commit action in the RISC game.
     * This method is called when the player finalizes their current turn.
     *
     * @param gameMap The game map on which the commit order will be executed. Not for this order
     */
    @Override
    public void run(GameMap gameMap) {

    }

    public void combat(GameMap gameMap) {

    }

    public void combat(GameMap gameMap, ArrayList<Unit> units) {
    }

    @Override
    public int consumeFood() {
        return 0;
    }

}
