package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.rule.*;

import java.util.ArrayList;

/**
 * The Move class represents a upgrade order in the RISC game.
 */
public class UpgradeUnit extends Order {

    private ArrayList<Integer> levelToUpgrade;

    private final int[] bonusForUnit = {0, 1, 3, 5, 8, 11, 15};

    private final int[] costForUpgrade = {3, 8, 19, 25, 35, 50};

    // constructor
    public UpgradeUnit(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex, ArrayList<Integer> levelToUpgrade) {
        super(unitNum, src, dest, type, orderOwnId, selectedUnitsIndex, levelToUpgrade);
//        this.levelToUpgrade = levelToUpgrade;
    }


    @Override
    public void run(GameMap boardMap) {
        if (this.getType().equals(Type.UpgradeUnit)) {
            UnitFactory factory = new UnitFactory(); // create new unit by factory
//            HashMap<Integer, Unit> terrUnits = boardMap.getTerritoryByName(this.getSrc().getName()).getUnits();
            ArrayList<Unit> terrUnits = boardMap.getTerritoryByName(this.getSrc().getName()).getUnits();
            for (int i = 0; i < this.getSelectedUnitsIndex().size(); i++) {
                Unit unit = terrUnits.get(i); // unit to upgrade
                int oldLevel = unit.getLevel();
                int newLevel = this.getLevelToUpgrade().get(i);
                this.getSrc().deleteOldLevelUnit(oldLevel);
                Unit unitToAdd = (Unit) factory.createUnit(newLevel, bonusForUnit[newLevel]);
                this.getSrc().addUpgradeUnit(unitToAdd);
                System.out.print("Bonus : ");
                System.out.println(unitToAdd.getBonus());
                System.out.print("Level : ");
                System.out.println(unitToAdd.getLevel());
                int rescourseCost = 0;
                // 0 -> 3
                for (int k = oldLevel; k < newLevel; k++) {
                    rescourseCost += costForUpgrade[k];
                }
                System.out.println("rescourseCost : " + rescourseCost);
                // minus tech resources for the player for each unit upgrade
                int oldTechResource = boardMap.getPlayerById(this.getOrderOwnId()).getTechResource();
                boardMap.getPlayerById(this.getOrderOwnId()).setTechResource(oldTechResource - rescourseCost);
            }
        }
    }

    public void combat(GameMap gameMap, ArrayList<Unit> unitsForAttack) {
    }
    public void combat(GameMap gameMap) {

    }

    @Override
    public int consumeFood() {
        return 0;
    }
}
