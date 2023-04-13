package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.rule.Type;
import edu.duke.ece651.riscgame.game.Territory;

import java.io.Serializable;
import java.util.HashMap;

/**
 * The Order class represents an abstract order in the RISC game.
 * It serves as a base class for different types of orders like Attack, Move, etc.
 * This class implements Serializable for enabling object serialization
 * when transferring orders between clients and servers.
 */
public abstract class Order implements Serializable {
    private int unitNum;
    private Territory src;
    private Territory dest;
    private Type type;
    private int orderOwnId;
    public HashMap<Integer, String> ownership;

    // constructor
    public Order(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        this.unitNum = unitNum;
        this.src = src;
        this.dest = dest;
        this.type = type;
        this.orderOwnId = orderOwnId;
        ownership = new HashMap<>();
        ownership.put(0, "Avalon");
        ownership.put(1, "Braglavia");
        ownership.put(2, "Calador");
        ownership.put(3, "Excrier");
        ownership.put(4, "Ceyland");
    }
    // Getters

    public int getOrderOwnId() {
        return orderOwnId;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public Territory getSrc() {
        return src;
    }

    public Territory getDest() {
        return dest;
    }

    public Type getType() {
        return type;
    }

    // setter
    public void setType(Type type) {
        this.type = type;
    }

    // Abstract method to be implemented by derived classes


    /**
     * Executes the specific order action.
     * This method must be implemented by derived classes like Attack, Move, etc.
     *
     * @param gameMap The game map on which the order will be executed.
     */
    public abstract void run(GameMap gameMap);

    // Other methods

    /**
     * Checks if both source and destination territories belong to the same player.
     *
     * @return true if both territories belong to the same player, false otherwise.
     */
    public boolean isFromOnePlayer() {
        return dest.getOwnId() == src.getOwnId();
    }
}