package edu.duke.ece651.riscgame.game;

import edu.duke.ece651.riscgame.game.Territory;

import java.util.Vector;

public class Player {

    // TODO add his territory
    private final int clientID;
    private String playerName;
    // private Vector<Territory> ownedTerr;
    private int techLevel;
    private int techResource; // send to server and receive back each turn
    private int foodResource; // send to server and receive back each turn
    public Player (int clientID, String playerName) {
        this.clientID = clientID;
        this.playerName = playerName;
        this.techLevel = 1;
    }

    public int getClientID() {
        return clientID;
    }

    public int getTechResource() {
        return techResource;
    }

    public void setTechResource(int techResource) {
        this.techResource = techResource;
    }

    public int getFoodResource() {
        return foodResource;
    }

    public void setFoodResource(int foodResource) {
        this.foodResource = foodResource;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }
}
