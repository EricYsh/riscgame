package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.game.Territory;

import java.util.Vector;

public class Player {

    // TODO add his territory
    private final int clientID;
    private String playerName;
    // private Vector<Territory> ownedTerr;
    private int techResource; // send to server and receive back each turn
    private int foodResource; // send to server and receive back each turn
    public Player (int clientID, String playerName) {
        this.clientID = clientID;
        this.playerName = playerName;
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
}
