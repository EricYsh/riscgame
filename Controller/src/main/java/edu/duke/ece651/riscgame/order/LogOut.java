package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.rule.Type;

import java.util.ArrayList;

public class LogOut extends Order{
    public LogOut(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex, ArrayList<Integer> levelToUpgrade) {
        super(unitNum, src, dest, type, orderOwnId, selectedUnitsIndex, levelToUpgrade);
    }
    @Override
    public void run(GameMap gameMap) {

    }

    public void combat(GameMap gameMap, ArrayList<Unit> unitsForAttack){}
    public void combat(GameMap gameMap) {

    }

    @Override
    public int consumeFood() {
        return 0;
    }
}
