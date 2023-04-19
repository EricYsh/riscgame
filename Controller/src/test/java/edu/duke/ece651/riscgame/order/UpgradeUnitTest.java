package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardMapFactory;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.Type;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeUnitTest {

    @Test
    void runTest() {
        BoardMapFactory f1 = new BoardMapFactory();
        GameMap map = f1.generateMap(2);

        Territory t1 = map.getTerritoryByName("T1");
        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<Integer> Level = new ArrayList<>();
        List<Integer> elementsToAdd1 = Arrays.asList(0, 2, 9);
        index.addAll(elementsToAdd1);
        List<Integer> levelToUpgrade = Arrays.asList(0, 1, 6);
        Level.addAll(levelToUpgrade);
        t1.displayAllUnit();

        UpgradeUnit u1 = new UpgradeUnit(index.size(), t1 , null, Type.UpgradeUnit, 0, index, Level);
        u1.run(map);
        t1.displayAllUnit();
    }
}