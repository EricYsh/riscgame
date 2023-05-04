package edu.duke.ece651.riscgame.game;

public class Spy {
    private final int ownerId;

    static int spyId = 0;

    public Spy(int ownerId) {
        this.ownerId = ownerId;
        spyId++;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getSpyId() {
        return spyId;
    }
}
