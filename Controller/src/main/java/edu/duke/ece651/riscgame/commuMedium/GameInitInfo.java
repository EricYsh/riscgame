package edu.duke.ece651.riscgame.commuMedium;

import edu.duke.ece651.riscgame.game.BoardMap;
import edu.duke.ece651.riscgame.game.Territory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public class GameInitInfo implements Serializable {
    String testStr = "HELLO, this is a GameInitInfo";
    private BoardMap map;
    private int numUnit; // add this to inform client how units he/she can assign
    private Collection<String> playerName;

    // constructor
    public GameInitInfo(BoardMap gameMap, int numUnit, Collection<String> playerName) {
        this.map = gameMap;
        this.numUnit = numUnit;
        this.playerName = playerName;
    }
    public GameInitInfo () { // for testing
        this.map = null;
        this.numUnit = 0;
        this.playerName = null;
    }
    public int getNumUnit () {
        return numUnit;
    }
    public BoardMap getMap() {
        return map;
    }

    public String getPlayerName(int index) {
        return ((Vector<String>)playerName).get(index);
    }

    public Collection<String> getPlayerName() {
        return playerName;
    }

    //    public Vector<Territory> getTerrList() {
//        return terrList;
//    }
    public String printTestInfo () {
        return testStr;
    }
}
