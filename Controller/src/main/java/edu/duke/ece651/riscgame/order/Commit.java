package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.Type;

public class Commit extends Order {

    public Commit(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
    }

    @Override
    public void run(BoardMap gameMap) {

    }
}
