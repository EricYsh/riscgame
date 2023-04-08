package edu.duke.ece651.riscgame.commuMedium;

import java.io.Serializable;
import java.util.HashMap;

/**
 * this class is designed to send map info, which contains unit distribution and control
 * while the relation of territories will also be sent, but they are not important
 */
public class RoundResult implements Serializable {
    private HashMap<String, Integer> units;
    private HashMap<String, Integer> ownership;
    public RoundResult () {
        this.ownership = null;
        this.units = null;
    }
    public RoundResult (HashMap<String, Integer> units, HashMap<String, Integer> ownership) {
        this.ownership = ownership;
        this.units = units;
    }

    public HashMap<String, Integer> getOwnership() {
        return ownership;
    }

    public HashMap<String, Integer> getUnits() {
        return units;
    }
}
