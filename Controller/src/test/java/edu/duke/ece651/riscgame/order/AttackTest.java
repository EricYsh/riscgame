package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.rule.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AttackTest {

//    @Test
//    void runAttackChangeHomeTest() {
//        BoardMapFactory f1 = new BoardMapFactory();
//
//        BoardGameMap map = f1.generateMap(2);
//        BoardTextView v1 = new BoardTextView(map);
//        v1.printPlayerMap(0);
//        v1.printPlayerMap(1);
//
//        Territory T1 = map.getTerritoryByName("T1");
//        Territory T4 = map.getTerritoryByName("T4");
//        Attack a1 = new Attack(1, T1, T4, Type.AttackAndChangeHome, 0);
//        Attack a2 = new Attack(28, T4, T1, Type.AttackAndChangeHome, 1);
//        a1.run(map);
//        a2.run(map);
//
//        v1.printPlayerMap(0);
//        v1.printPlayerMap(1);
//    }
    @Test
    void runAttackTest() {
        UnitFactory f2 = new UnitFactory();
        BoardMapFactory f1 = new BoardMapFactory();

        BoardGameMap map = f1.generateMap(2);
        BoardTextView v1 = new BoardTextView(map);
        v1.printPlayerMap(0);
        v1.printPlayerMap(1);

        Territory T1 = map.getTerritoryByName("T1");
        Territory T4 = map.getTerritoryByName("T4");
        T1.addUpgradeUnit((Unit) f2.createUnit(4, 8));
        T1.addUpgradeUnit((Unit) f2.createUnit(1, 1));
        T1.addUpgradeUnit((Unit) f2.createUnit(6, 15));
        T1.addUpgradeUnit((Unit) f2.createUnit(6, 15));
        T1.addUpgradeUnit((Unit) f2.createUnit(6, 15));
        T1.addUpgradeUnit((Unit) f2.createUnit(6, 15));
        T1.displayAllUnit();

        ArrayList<Integer> selected = new ArrayList<>();
        List<Integer> elementsToAttack = Arrays.asList(0, 1, 2, 11, 10, 12);
        selected.addAll(elementsToAttack);
        Attack a1 = new Attack(1, T1, T4, Type.Attack, 0, selected, null);
        ArrayList<Unit> unitsForAttack = new ArrayList<>();
        for (Integer k : selected) {
            unitsForAttack.add(T1.getUnits().get(k));
        }

        a1.run(map);
        System.out.println("after run, which means minus unit");
        T1.displayAllUnit();
        // create a arraylist of Unit from T1
        a1.combat(map, unitsForAttack);
        System.out.println("after combat---------");
        T1.displayAllUnit();
        T4.displayAllUnit();

        v1.printPlayerMap(0);
        v1.printPlayerMap(1);
        map.callUp();
        v1.printPlayerMap(0);
        v1.printPlayerMap(1);
    }
}