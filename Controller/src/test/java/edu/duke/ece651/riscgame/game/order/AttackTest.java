package edu.duke.ece651.riscgame.game.order;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.BoardMapFactory;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.Type;
import org.junit.jupiter.api.Test;

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
}