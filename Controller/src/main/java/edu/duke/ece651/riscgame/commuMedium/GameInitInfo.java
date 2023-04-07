package edu.duke.ece651.riscgame.commuMedium;

import edu.duke.ece651.riscgame.game.Territory;

import java.io.Serializable;
import java.util.Vector;

public class GameInitInfo implements Serializable {
    String testStr = "HELLO, this is a GameInitInfo";
    Vector<Territory> terrList;
    int numUnit; // add this to inform client how units he/she can assign

    // constructor
    public GameInitInfo() {

    }

    public Vector<Territory> getTerrList() {
        return terrList;
    }
    public String printTestInfo () {
        return testStr;
    }
}
