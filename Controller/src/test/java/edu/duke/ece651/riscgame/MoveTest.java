package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.BoardMapFactory;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Move;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.MovePathChecker;
import edu.duke.ece651.riscgame.rule.OrderRuleChecker;
import edu.duke.ece651.riscgame.rule.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.riscgame.*;

public class MoveTest {
    @Test
    public void testMoveMyself() {
//        Territory t1 = new Territory("A", 1, 5);
//        Territory t2 = new Territory("B", 2, 5);
//        Territory t3 = new Territory("C", 1, 5);
//        t1.addNeighbor(t2);
//        t2.addNeighbor(t1);
//        t2.addNeighbor(t3);
//        t3.addNeighbor(t2);
//
//        Order o1 = new Move(3, t1, t3, Type.Move);
//        o1.run();
//        assertEquals(8, t3.getUnitNum());
//        assertEquals(2, t1.getUnitNum());
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
}