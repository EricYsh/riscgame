package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.rule.*;

import java.util.*;

/**
 * The Attack class represents an attack order in the RISC game.
 * It extends the Order class and defines the behavior for an attack action.
 */
public class Attack extends Order {

    // {index in the terr: the unit}
    HashMap<Integer, Unit> unitForAttack;

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
    public void run(GameMap gameMap) {
        // TODO attack cost 1 food resource per unit to perform

        // attack according to the type
        if (this.getType().equals(Type.Attack)) {
            doNormalAttack(gameMap);
        }

        // attack but use up all units, then these two parts will change home directly
        if (this.getType().equals(Type.AttackAndChangeHome)) {
            doChangeHomeAttack(gameMap);
        }

        // TODO test for evol2 Attack
        if (this.getType().equals(Type.Attack2)) {
            doAttack(gameMap);
        }
    }

    private void doAttack(GameMap gameMap) {
        // TODO minus 1 food per unit attacking
        // player food resource minus unitForAttack.size()


        HashMap<Integer, Integer> attackerBonuses = new HashMap<>();
        for (Map.Entry<Integer, Unit> entry : unitForAttack.entrySet()) {
            Unit unit = entry.getValue();
            attackerBonuses.put(entry.getKey(), unit.getBonus());
        }

        HashMap<Integer, Integer> defenderBonuses = new HashMap<>();
        for (Map.Entry<Integer, Unit> entry : gameMap.getTerritoryByName(this.getDest().getName()).getUnits().entrySet()
        ) {
            Unit unit = entry.getValue();
            defenderBonuses.put(entry.getKey(), unit.getBonus());
        }

        // Create sorted lists of attacker and defender entries
        List<Map.Entry<Integer, Integer>> attackerList = new ArrayList<>(attackerBonuses.entrySet());
        attackerList.sort((a, b) -> b.getValue().compareTo(a.getValue())); // Sort by descending bonus

        List<Map.Entry<Integer, Integer>> defenderList = new ArrayList<>(defenderBonuses.entrySet());
        defenderList.sort(Map.Entry.comparingByValue()); // Sort by ascending bonus

        // Alternate between attacker and defender units
        int attackerIndex = 0;
        int defenderIndex = 0;
        while (attackerIndex < attackerList.size() && defenderIndex < defenderList.size()) {
            Map.Entry<Integer, Integer> attackerEntry = attackerList.get(attackerIndex);
            Map.Entry<Integer, Integer> defenderEntry = defenderList.get(defenderIndex);

            System.out.println("Attacker " + attackerEntry + " vs Defender " + defenderEntry);

            // Simulate combat and update lists based on the result
            if (attackerEntry.getValue() > defenderEntry.getValue()) {
                defenderList.remove(defenderIndex);
            } else {
                attackerList.remove(attackerIndex);
            }

            // Alternate between highest-bonus attacker and lowest-bonus defender
            if (attackerIndex == 0) {
                attackerIndex = attackerList.size() - 1;
            } else {
                attackerIndex = 0;
            }

            if (defenderIndex == 0) {
                defenderIndex = defenderList.size() - 1;
            } else {
                defenderIndex = 0;
            }
        }
    }

    private void doNormalAttack(GameMap gameMap) {
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

    private void doChangeHomeAttack(GameMap gameMap) {
        System.out.println("do change home");
        int changeHomeUnitNum = this.getUnitNum(); // use how many units to attack
        String ownerName = ownership.get(this.getOrderOwnId());
        // no combat
        gameMap.getTerritoryByName(this.getDest().getName()).setOwnerName(ownerName);
        gameMap.getTerritoryByName(this.getDest().getName()).setOwnId(this.getOrderOwnId());
        gameMap.getTerritoryByName(this.getDest().getName()).setUnitNum(changeHomeUnitNum);
    }
}
