package edu.duke.ece651.riscgame.commuMedium;

import edu.duke.ece651.riscgame.game.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class is designed to send map info, which contains unit distribution and control
 * while the relation of territories will also be sent, but they are not important
 */
public class RoundResult implements Serializable {
    private HashMap<String, Integer> units;
    private HashMap<String, Integer> ownership;
    private ArrayList<Player> players;
    private HashMap<String, String> updatePlayerName;

    public RoundResult() {
        this.ownership = null;
        this.units = null;
        this.players = null;
    }

    public RoundResult(HashMap<String, Integer> units, HashMap<String, Integer> ownership, ArrayList<Player> players) {
        this.ownership = ownership;
        this.units = units;
        this.players = players;
//        this.updatePlayerName = updatePlayerName;
    }

    public HashMap<String, Integer> getOwnership() {
        return ownership;
    }

    public HashMap<String, Integer> getUnits() {
        return units;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public HashMap<String, String> getUpdatePlayerName() {
        return updatePlayerName;
    }


}
