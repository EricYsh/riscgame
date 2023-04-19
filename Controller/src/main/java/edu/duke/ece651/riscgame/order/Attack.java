package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.game.Unit;
import edu.duke.ece651.riscgame.game.UnitFactory;
import edu.duke.ece651.riscgame.rule.*;

import java.util.*;

/**
 * The Attack class represents an attack order in the RISC game.
 * It extends the Order class and defines the behavior for an attack action.
 */
public class Attack extends Order {

//    // {index in the terr: the unit}
//    HashMap<Integer, Unit> unitForAttack;

    /**
     * Constructor for the Attack class.
     */
    public Attack(int unitNum, Territory src, Territory dest, Type type, int orderOwnId, ArrayList<Integer> selectedUnitsIndex, ArrayList<Integer> levelToUpgrade) {
        super(unitNum, src, dest, type, orderOwnId, selectedUnitsIndex, levelToUpgrade);
    }

    @Override
    public void run(GameMap gameMap) {
        gameMap.getTerritoryByName(this.getSrc().getName()).minusUnit(this.getUnitNum());

        ArrayList<Unit> origin = gameMap.getTerritoryByName(this.getSrc().getName()).getUnits();
        ArrayList<Unit> u1 = new ArrayList<>();
        for (Integer i : this.getSelectedUnitsIndex()) {
            u1.add(origin.get(i));
        }
        origin.removeAll(u1);
        gameMap.getTerritoryByName(this.getSrc().getName()).setUnits(origin);
    }

    @Override
    public int consumeFood() {
        return this.getUnitNum();
    }

    public void combat(GameMap gameMap) {
        int oldFood = gameMap.getPlayerById(this.getOrderOwnId()).getFoodResource();
        gameMap.getPlayerById(this.getOrderOwnId()).setFoodResource(oldFood - getSelectedUnitsIndex().size());
        // attack but use up all units, then these two parts will change home directly
        if (this.getType().equals(Type.AttackAndChangeHome)) {
            doChangeHomeAttack(gameMap);
        }
    }

    @Override
    public void combat(GameMap gameMap, ArrayList<Unit> unitsForAttack) {
        // TODO attack cost 1 food resource per unit to perform
        int oldFood = gameMap.getPlayerById(this.getOrderOwnId()).getFoodResource();
        gameMap.getPlayerById(this.getOrderOwnId()).setFoodResource(oldFood - getSelectedUnitsIndex().size());

        // TODO test for evol2 Attack
        if (this.getType().equals(Type.Attack)) {
            doAttack(gameMap, unitsForAttack);
        }
    }

    private void doAttack(GameMap gameMap, ArrayList<Unit> unitForAttack) {
        // TODO order of execution alternates between
        // TODO highes-bonus attacker unit paired with the lowest-bonus defender unit
        // TODO lowest-bonus attacker unit paired with the highest-bonus defend unit

        // if the attacker has units with bonuses 15,8,1,0
        // and the defender has units with bonuses 1,3,8,8,
        // then combat starts with A15 vs D1.
        // Assuming the defender loses,
        // you have now Attacker: 15,8,1,0. Defender: 3, 8, 8.
        // Next you have A0 vs D8.
        // Assuming the attacker loses,
        // you have now Attacker 15,8,1. Defender 8,8,3, so now you do A15 v D3.

        // Get attackBonus and defendBonus for each unit
        List<Integer> attackBonus = new ArrayList<>();
        List<Integer> defendBonus = new ArrayList<>();
        for (Unit unit : unitForAttack) {
            attackBonus.add(unit.getBonus());
        }
        for (Unit unit : gameMap.getTerritoryByName(this.getDest().getName()).getUnits()) {
            defendBonus.add(unit.getBonus());
        }

        // Sort attackBonus and defendBonus lists
        attackBonus.sort(Collections.reverseOrder()); // sort in descending order
        Collections.sort(defendBonus); // sort in ascending order

        // Pair and execute attacks
//        int numberOfAttacks = Math.min(attackBonus.size(), defendBonus.size());
        int switchHighLow = 0;
        while (attackBonus.size() > 0 && defendBonus.size() > 0) {
            int attackerBonusIndex = switchHighLow % 2 == 0 ? 0 : attackBonus.size() - 1;
            int defenderBonusIndex = switchHighLow % 2 == 0 ? defendBonus.size() - 1 : 0;
            boolean attackResult = performAttack(attackBonus.get(attackerBonusIndex), defendBonus.get(defenderBonusIndex));
            if (attackResult) { // attacker wins
                defendBonus.remove(defenderBonusIndex);
                System.out.println("Attacker wins " + attackBonus.size() + " " + defendBonus.size());
            } else { // defender wins
                attackBonus.remove(attackerBonusIndex);
                System.out.println("Defender wins " + attackBonus.size() + " " + defendBonus.size());
            }
//            numberOfAttacks = Math.min(attackBonus.size(), defendBonus.size());
            switchHighLow++;
        }

        System.out.println("Attacker units remaining: " + attackBonus.size());
        System.out.println("Defender units remaining: " + defendBonus.size());
        UnitFactory unitFactory = new UnitFactory();
        if (attackBonus.size() > 0) {
            System.out.println("Attacker wins");
            // TODO attacker wins, remove defender units and add attacker units to the territory
            String ownerName = ownership.get(this.getOrderOwnId());
            gameMap.getTerritoryByName(this.getDest().getName()).setOwnerName(ownerName);
            gameMap.getTerritoryByName(this.getDest().getName()).setOwnId(this.getOrderOwnId());
            this.getDest().removeAllUnits();
            for (Integer bonus : attackBonus) {
                System.out.println("to add bonus is : " + bonus);
                Unit unit = (Unit) unitFactory.createUnit(bonus);
                gameMap.getTerritoryByName(this.getDest().getName()).addUpgradeUnit(unit);
            }
        } else {
            System.out.println("Defender wins");
            // TODO defender wins, remove attacker units
            // create unit according the remaining bonus and add to the dest territory
            this.getDest().removeAllUnits();
            for (Integer bonus : defendBonus) {
                Unit unit = (Unit) unitFactory.createUnit(bonus);
                gameMap.getTerritoryByName(this.getDest().getName()).addUpgradeUnit(unit);
            }
        }
    }

    private boolean performAttack(int attackerBonus, int defenderBonus) {
        System.out.println("Attacker bonus: " + attackerBonus + " Attack Defender bonus:" + defenderBonus);
        Random random = new Random();
        int randomNumberAttack = random.nextInt(20) + 1;
        int randomNumberDefend = random.nextInt(20) + 1;
        return randomNumberAttack + attackerBonus > randomNumberDefend + defenderBonus; // attacker wins if larger
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
