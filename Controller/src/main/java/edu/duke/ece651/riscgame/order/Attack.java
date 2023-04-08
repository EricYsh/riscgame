package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.*;

import java.util.HashMap;
import java.util.Random;

/**
 * The Attack class represents an attack order in the RISC game.
 * It extends the Order class and defines the behavior for an attack action.
 */
public class Attack extends Order {
    // private HashMap<Integer, String> ownership;
//    RuleChecker ruleChecker;

    /**
     * Constructor for the Attack class.
     */
    public Attack(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
//        this.ruleChecker = new DestChecker(new UnitChecker(new AdjacentChecker(null)));
        // ownership = new HashMap<>();
        // ownership.put(0, "Avalon");
        // ownership.put(1, "Braglavia");
        // ownership.put(2, "Calador");
        // ownership.put(3, "Excrier");
        // ownership.put(4, "Ceyland");
    }

    /**
     * Executes the attack action by simulating a battle between the attacker and defender.
     * Each iteration, both attacker and defender roll a 20-sided die. If the attacker's roll
     * is higher than the defender's, a defending unit is removed. If the defender's roll is
     * higher, an attacking unit is removed. The battle continues until either side has no units
     * remaining.
     *
     * If the defender wins, the defending territory's unit count is updated.
     * If the attacker wins, the defending territory's ownership and unit count are updated.
     */
    @Override
    public void run() {
        if (this.getType().equals(Type.Attack)) {
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
                this.getDest().setUnitNum(defendUnitNum);
            } else { // attack wins
                this.getDest().setOwnerName(this.getSrc().getOwnerName());
                this.getDest().setOwnId(this.getSrc().getOwnId());
                this.getDest().setUnitNum(attckUnitNum);
            }
        }
        System.out.println("Attack order is executed");
        System.out.println("The source territory is " + this.getSrc().getName());
        System.out.println("The destination territory is " + this.getDest().getName());
        System.out.println("The number of units is " + this.getUnitNum());
        System.out.println("The owner of the source territory is " + this.getSrc().getOwnerName());
        System.out.println("The owner of the destination territory is " + this.getDest().getOwnerName());
    }
}
