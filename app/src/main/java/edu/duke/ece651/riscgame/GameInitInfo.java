package edu.duke.ece651.riscgame;

import java.util.ArrayList;

public class GameInitInfo {
    String testStr = "HELLO, this is a GameInitInfo";
    ArrayList<Territory> terrList;

    public ArrayList<Territory> getTerrList() {
        return terrList;
    }

    public String printTestInfo() {
        return testStr;
    }
}
