package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Spy;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.rule.Type;

import java.util.ArrayList;

public class SpyMove extends Order{
    public SpyMove(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex, ArrayList<Integer> levelToUpgrade) {
        super(1, src, dest, type, orderOwnId, selectedUnitsIndex, levelToUpgrade);
    }

    @Override
    public void run(GameMap gameMap) {
        int spyIndex = this.getSelectedUnitsIndex().get(0);
        Spy tmp = gameMap.getTerritoryByName(getSrc().getName()).getSpies().get(spyIndex);
        int spyId = tmp.getSpyId();
        gameMap.getTerritoryByName(getSrc().getName()).deleteSpy(spyId);
        gameMap.getTerritoryByName(getDest().getName()).addSpyList(tmp);
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
