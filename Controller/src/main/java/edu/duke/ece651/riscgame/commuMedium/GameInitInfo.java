package edu.duke.ece651.riscgame.commuMedium;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.GameMap;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public class GameInitInfo implements Serializable {
    String testStr = "HELLO, this is a GameInitInfo";
    private GameMap gameMap;
    private int numUnit; // add this to inform client how units he/she can assign
    private Vector<String> playerName;

    // constructor
    public GameInitInfo(GameMap gameMap, int numUnit, Vector<String> playerName) {
        this.gameMap = gameMap;
        this.numUnit = numUnit;
        this.playerName = playerName;
    }
    public GameInitInfo () { // for testing
        this.gameMap = null;
        this.numUnit = 0;
        this.playerName = null;
    }
    public int getNumUnit () {
        return numUnit;
    }
    public GameMap getMap() {
        return gameMap;
    }

    public String getPlayerName(int index) {
        return playerName.get(index);
    }

    public Collection<String> getPlayerName() {
        return playerName;
    }


    public String printTestInfo () {
        return testStr;
    }
}
