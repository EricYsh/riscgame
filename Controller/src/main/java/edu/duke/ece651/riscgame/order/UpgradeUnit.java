package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.rule.*;

import java.util.HashMap;

/**
 * The Move class represents a upgrade order in the RISC game.
 */
public class UpgradeUnit extends Order {

    private int[] upgradeUnitIndex;
    private int[] levelToUpgrade;

    private final int[] bonusForUnit = {0, 1, 3, 5, 8, 11, 15};

    private final int[] costForUpgrade = {0, 3, 8, 11, 19, 25, 36, 50};

    // constructor
    public UpgradeUnit(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);

    }

    @Override
    public void run(GameMap boardMap) {
        if (this.getType().equals(Type.UpgradeUnit)) {
            UnitFactory factory = new UnitFactory(); // create new unit by factory
            // TODO upgrade order
            HashMap<Integer, Unit> terrUnits = boardMap.getTerritoryByName(this.getSrc().getName()).getUnits();

            for (int i = 0; i < upgradeUnitIndex.length; i++) {
                Unit unit = terrUnits.get(i); // unit to upgrade
                int oldLevel = unit.getLevel();
                int newLevel = levelToUpgrade[i];
                unit.setLevel(levelToUpgrade[i]);
                unit.setBonus(bonusForUnit[newLevel - 1]);
                int rescourseCost = 0;
                // 0 -> 3
                for (int k = oldLevel; k < newLevel; k++) {
                    rescourseCost += costForUpgrade[k];
                }
                // TODO minus tech resources for the player
            }
        }
    }

    public void combat(GameMap gameMap){

    }
}
