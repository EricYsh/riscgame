package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.rule.Type;

import java.util.ArrayList;

public class Cloak extends Order{

    public Cloak(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex, ArrayList<Integer> levelToUpgrade) {
        super(unitNum, src, dest, type, orderOwnId, selectedUnitsIndex, levelToUpgrade);
    }

    @Override
    public void run(GameMap gameMap) {

    }

    @Override
    public void combat(GameMap gameMap) {

    }

    @Override
    public void combat(GameMap gameMap, ArrayList<Unit> unitsForAttack) {

    }

    @Override
    public int consumeFood() {
        return 0;
    }
}
