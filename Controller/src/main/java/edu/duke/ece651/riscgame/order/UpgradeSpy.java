package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.rule.Type;

import java.util.ArrayList;

public class UpgradeSpy extends Order {
    public UpgradeSpy(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex, ArrayList<Integer> levelToUpgrade) {
        super(unitNum, src, dest, type, orderOwnId, selectedUnitsIndex, levelToUpgrade);
    }

    @Override
    public void run(GameMap gameMap) {
        if (this.getType().equals(Type.UpgradeSpy)) {
            ArrayList<Unit> terrUnits = gameMap.getTerritoryByName(this.getSrc().getName()).getUnits();
            for (int i = 0; i < this.getSelectedUnitsIndex().size(); i++) {
                Unit unit = terrUnits.get(i); // unit to upgrade
                Spy spy = new Spy(this.getOrderOwnId());
                //TODO delete old level unit
                int oldLevel = unit.getLevel();
                gameMap.getTerritoryByName(this.getSrc().getName()).deleteOldLevelUnit(oldLevel);
                //TODO add spy to spy arraylist
                gameMap.getTerritoryByName(this.getSrc().getName()).addSpyList(spy);
            }

            //TODO reduce tech resouorce to upgrade spy, which equals to size * 20
            int rescourseCost = 20 * this.getSelectedUnitsIndex().size();
            System.out.println("rescourseCost : " + rescourseCost);
            // minus tech resources for the player for each unit upgrade
            int oldTechResource = gameMap.getPlayerById(this.getOrderOwnId()).getTechResource();
            gameMap.getPlayerById(this.getOrderOwnId()).setTechResource(oldTechResource - rescourseCost);
        }
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
