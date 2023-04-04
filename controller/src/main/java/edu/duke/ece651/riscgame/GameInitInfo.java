package edu.duke.ece651.riscgame;

import java.io.Serializable;
import java.util.ArrayList;

public class GameInitInfo implements Serializable {
    String testStr = "HELLO, this is a GameInitInfo";
    ArrayList<Territory> terrList;

    public ArrayList<Territory> getTerrList() {
        return terrList;
    }

    public String printTestInfo() {
        return testStr;
    }
}

