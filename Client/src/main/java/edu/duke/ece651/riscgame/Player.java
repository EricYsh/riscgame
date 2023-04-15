package edu.duke.ece651.riscgame;

public class Player {

    // TODO add his territory

    private String playerName;
    private int techResource; // send to server and receive back each turn
    private int foodResource; // send to server and receive back each turn

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
