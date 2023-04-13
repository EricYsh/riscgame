package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.*;

import java.util.Random;

/**
 * The Attack class represents an attack order in the RISC game.
 * It extends the Order class and defines the behavior for an attack action.
 */
public class Attack extends Order {
    /**
     * Constructor for the Attack class.
     */
    public Attack(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        super(unitNum, src, dest, type, orderOwnId);
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
    public void run(BoardGameMap gameMap) {
        // TODO attack cost 1 food resource per unit to perform

        // attack according to the type
        if (this.getType().equals(Type.Attack)) {
            doNormalAttack(gameMap);
        }

        // attack but use up all units, then these two parts will change home directly
        if (this.getType().equals(Type.AttackAndChangeHome)) {
            doChangeHomeAttack(gameMap);
        }
    }

    private void doNormalAttack(BoardGameMap gameMap) {
        // TODO order of execution alternates between
        // TODO  highes-bonus attacker unit paired with the lowest-bonus defender unit
        // TODO lowest-bonus attacker unit paired with the highest-bonus defend unit

        int attckUnitNum = this.getUnitNum(); // use how many units to attack
        int defendUnitNum = this.getDest().getUnitNum(); // defender unit count

        // Simulate battle between attacker and defender
        while (attckUnitNum > 0 && defendUnitNum > 0) {
            Random random = new Random();
            // TODO add a bonus for the type of unit involved
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
        } else { // attack wins
            String ownerName = ownership.get(this.getOrderOwnId());
            gameMap.getTerritoryByName(this.getDest().getName()).setOwnerName(ownerName);
            gameMap.getTerritoryByName(this.getDest().getName()).setOwnId(this.getOrderOwnId());
            gameMap.getTerritoryByName(this.getDest().getName()).setUnitNum(attckUnitNum);
        }
    }

    private void doChangeHomeAttack(BoardGameMap gameMap) {
        System.out.println("do change home");
        int changeHomeUnitNum = this.getUnitNum(); // use how many units to attack
        String ownerName = ownership.get(this.getOrderOwnId());
        // no combat
        gameMap.getTerritoryByName(this.getDest().getName()).setOwnerName(ownerName);
        gameMap.getTerritoryByName(this.getDest().getName()).setOwnId(this.getOrderOwnId());
        gameMap.getTerritoryByName(this.getDest().getName()).setUnitNum(changeHomeUnitNum);
    }
}
