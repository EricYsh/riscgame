package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.Type;

public class UpgradeTech extends Order {
    //2   3    4    5    6
    int[] cost = {50, 75, 125, 200, 300};

    public UpgradeTech(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
    }

    @Override
    public void run(GameMap gameMap) {
        // TODO upgrade terrtority tech level
        if (this.getType().equals(Type.UpgradeTech)) {
            int oldTechLevel = gameMap.getTerritoryByName(this.getSrc().getName()).getTechnologyLevel();
            gameMap.getTerritoryByName(this.getSrc().getName()).setTechnologyLevel(oldTechLevel + 1);
            // TODO reduce player tech resources
            switch (oldTechLevel) {
                case 1:
                    // - 50
                    // -cost[oldTechLevel - 1];
                    break;
                case 2:
                    // - 75
                    break;
                case 3:
                    // - 125
                    break;
                case 4:
                    // - 200
                    break;
                case 5:
                    // - 300
                    break;
                default:
                    System.out.println("invalid old technology level");
            }
        }
    }

    public void combat(GameMap gameMap){

    }
}
