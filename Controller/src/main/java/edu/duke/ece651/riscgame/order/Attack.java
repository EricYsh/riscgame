package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.*;

import java.util.HashMap;
import java.util.Random;

/**
 * The Attack class represents an attack order in the RISC game.
 * It extends the Order class and defines the behavior for an attack action.
 */
public class Attack extends Order {
//     private HashMap<Integer, String> ownership;
//    RuleChecker ruleChecker;

    /**
     * Constructor for the Attack class.
     */
    public Attack(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
//        this.ruleChecker = new DestChecker(new UnitChecker(new AdjacentChecker(null)));
//         ownership = new HashMap<>();
//         ownership.put(0, "Avalon");
//         ownership.put(1, "Braglavia");
//         ownership.put(2, "Calador");
//         ownership.put(3, "Excrier");
//         ownership.put(4, "Ceyland");
    }

    /**
     * Executes the attack action by simulating a battle between the attacker and defender.
     * Each iteration, both attacker and defender roll a 20-sided die. If the attacker's roll
     * is higher than the defender's, a defending unit is removed. If the defender's roll is
     * higher, an attacking unit is removed. The battle continues until either side has no units
     * remaining.
     * <p>
     * If the defender wins, the defending territory's unit count is updated.
     * If the attacker wins, the defending territory's ownership and unit count are updated.
     */
    @Override
    public void run(BoardMap gameMap) {
//        BoardTextView b1 = new BoardTextView(gameMap);
        if (this.getType().equals(Type.Attack)) {

//            b1.printPlayerMap(ownership.get(this.getOrderOwnId()));

            int attckUnitNum = this.getUnitNum(); // use how many units to attack
            int defendUnitNum = this.getDest().getUnitNum(); // defender unit count

            // Simulate battle between attacker and defender
            while (attckUnitNum > 0 && defendUnitNum > 0) {
                Random random = new Random();
                int randomNumberAttack = random.nextInt(20) + 1;
                int randomNumberDefend = random.nextInt(20) + 1;
                if (randomNumberAttack > randomNumberDefend) {
                    defendUnitNum--;
                } else {
                    attckUnitNum--;
                }
            }

            // Update territory information based on battle outcome
            if (defendUnitNum > 0) { // defend wins
                gameMap.getTerritoryByName(this.getDest().getName()).setUnitNum(defendUnitNum);
//                this.getDest().setUnitNum(defendUnitNum);
            } else { // attack wins
                String ownerName = ownership.get(this.getOrderOwnId());
                System.out.println(ownerName);
                gameMap.getTerritoryByName(this.getDest().getName()).setOwnerName(ownerName);
                System.out.println(gameMap.getTerritoryByName(this.getDest().getName()).getOwnerName());
                gameMap.getTerritoryByName(this.getDest().getName()).setOwnId(this.getOrderOwnId());
                System.out.println(gameMap.getTerritoryByName(this.getDest().getName()).getOwnId());
                gameMap.getTerritoryByName(this.getDest().getName()).setUnitNum(attckUnitNum);
                System.out.println(gameMap.getTerritoryByName(this.getDest().getName()).getUnitNum());
//                this.getDest().setOwnerName(this.getSrc().getOwnerName());
//                this.getDest().setOwnId(this.getOrderOwnId());
//                this.getDest().setUnitNum(attckUnitNum);
            }
        }

        if(this.getType().equals(Type.AttackAndChangeHome)) {
            int changeHomeUnitNum = this.getUnitNum(); // use how many units to attack
            String ownerName = ownership.get(this.getOrderOwnId());
            gameMap.getTerritoryByName(this.getDest().getName()).setOwnerName(ownerName);
            gameMap.getTerritoryByName(this.getDest().getName()).setOwnId(this.getOrderOwnId());
            gameMap.getTerritoryByName(this.getDest().getName()).setUnitNum(changeHomeUnitNum);
        }
    }
}
