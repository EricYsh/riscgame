package edu.duke.ece651.riscgame;

import java.util.HashSet;

public class Territory {
    private final String name;
    private int ownId;
    private int unitNum;
    private HashSet<Territory> neighbors;

    public Territory() {
        name = null;
    }

    public String getName() {
        return name;
    }
}
