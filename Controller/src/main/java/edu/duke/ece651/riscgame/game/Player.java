package edu.duke.ece651.riscgame.game;

import edu.duke.ece651.riscgame.game.Territory;

import java.io.Serializable;

public class Player implements Serializable {

    // TODO add his territory
    private final int clientID;
    private String playerName;
    private String password;
    private int cloakFlag;
    // private Vector<Territory> ownedTerr;
    private int techLevel;
    private int techResource; // send to server and receive back each turn
    private int foodResource; // send to server and receive back each turn

    private int territoriesNum;
    public Player (int clientID, String playerName) {
        this.clientID = clientID;
        this.playerName = playerName;
        this.techLevel = 1;
        this.techResource = 150;
        this.foodResource = 300;
        this.cloakFlag = 1;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getClientID() {
        return clientID;
    }

    public int getTechResource() {
        return techResource;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getTerritoriesNum() {
        return territoriesNum;
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

    public void setTerritoriesNum(int territoriesNum) {
        this.territoriesNum = territoriesNum;
    }

    public void disPlayResources() {
        System.out.println("You have " + techResource + " tech resource and " + foodResource + " food resource.");
    }

    public void addTechResource(int techResource) {
        this.techResource += techResource;
    }

    public void addFoodResource(int foodResource) {
        this.foodResource += foodResource;
    }

    public void minusTechResource(int techResource) {
        this.techResource -= techResource;
    }

    public void minusFoodResource(int foodResource) {
        this.foodResource -= foodResource;
    }

    public int getCloakFlag() {
        return cloakFlag;
    }

    public void setCloakFlag(int cloakFlag) {
        this.cloakFlag = cloakFlag;
    }
}
