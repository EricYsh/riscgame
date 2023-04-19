package edu.duke.ece651.riscgame.game.order;

import edu.duke.ece651.riscgame.game.*;
import edu.duke.ece651.riscgame.order.UpgradeTech;
import edu.duke.ece651.riscgame.rule.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeTechTest {

    @Test
    void runTest() {
        BoardMapFactory f1 = new BoardMapFactory();
        GameMap map = f1.generateMap(2);
        BoardTextView v1 = new BoardTextView(map);
        Player p = map.getPlayerById(0);
        UpgradeTech u1 = new UpgradeTech(0, null, null, Type.UpgradeTech, 0, null, null);
        u1.run(map);
        assertEquals(p.getTechLevel(), 2);
        u1.run(map);
        assertEquals(p.getTechLevel(), 3);
        u1.run(map);
        assertEquals(p.getTechLevel(), 4);
        u1.run(map);
        assertEquals(p.getTechLevel(), 5);
        u1.run(map);
        assertEquals(p.getTechLevel(), 6);
    }

}