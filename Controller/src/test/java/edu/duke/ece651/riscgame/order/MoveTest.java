package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.order.Move;
import edu.duke.ece651.riscgame.rule.Type;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

public class MoveTest {
    @Test
    public void testMoveIndex() {
        BoardMapFactory f1 = new BoardMapFactory();
        Player p1 = new Player(0, "test0");
        BoardGameMap map =  f1.generateMap(1);
        map.getAllPlayerList().add(p1);
        Territory T1 = map.getTerritoryByName("T1");
        Territory T7 = map.getTerritoryByName("T7");
        ArrayList<Integer> a1 = new ArrayList<>();
        UnitFactory uf = new UnitFactory();


        T1.addUpgradeUnit((Unit) uf.createUnit(1, 1));//10
        T1.addUpgradeUnit((Unit) uf.createUnit(2, 3));//11
        T1.addUpgradeUnit((Unit) uf.createUnit(3, 5));//12
        System.out.println("Before:");
        System.out.println("-------------------T1----------------------");
        T1.displayAllUnit();
        System.out.println("-------------------T7---------------------");
        T7.displayAllUnit();
        a1.add(1);
        a1.add(12);
        a1.add(11);
        a1.add(7);

        System.out.println(map.getAllPlayerList());
        Move m1 = new Move(1, T1, T7, Type.Move, 0, a1, null);
        m1.run(map);
        System.out.println("After:");

        System.out.println("-------------------T1----------------------");
        T1.displayAllUnit();
        System.out.println("-------------------T7---------------------");
        T7.displayAllUnit();
//        assertEquals(m1.(), 20);
    }
//    @Test
//    public void testShortPath() {
//        BoardMapFactory f1 = new BoardMapFactory();
//        BoardGameMap map =  f1.generateMap(1);
//        Territory T1 = map.getTerritoryByName("T1");
//        Territory T7 = map.getTerritoryByName("T7");
//        Move m1 = new Move(1, T1, T7, Type.Move, 0, null, null);
//        m1.run(map);
//        assertEquals(m1.getCost(), 20);
//    }

    @Test
    public void testFood() {
        BoardMapFactory f1 = new BoardMapFactory();
        Player p1 = new Player(0, "test0");
        p1.setFoodResource(150);
        BoardGameMap map =  f1.generateMap(1);
        map.getAllPlayerList().add(p1);
        Territory T1 = map.getTerritoryByName("T1");
        Territory T7 = map.getTerritoryByName("T7");
        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(1);
        a1.add(3);
        a1.add(5);
        a1.add(7);
        System.out.println(map.getAllPlayerList());
        Move m1 = new Move(a1.size(), T1, T7, Type.Move, 0, a1, null);
        m1.run(map);
        System.out.println("-------------------T1----------------------");
        assertEquals(70, p1.getFoodResource());
        System.out.println("-------------------T7---------------------");

//        assertEquals(m1.(), 20);
    }
}