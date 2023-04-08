package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.rule.Type;
import edu.duke.ece651.riscgame.game.Territory;

import java.io.Serializable;

/**
 * The Order class represents an abstract order in the RISC game.
 * It serves as a base class for different types of orders like Attack, Move, etc.
 */
public abstract class Order implements Serializable {
    private int unitNum;
    private Territory src;
    private Territory dest;
    private Type type;

    private int orderOwnId;

    public Order(int unitNum, Territory src, Territory dest, Type type, int orderOwnId) {
        this.unitNum = unitNum;
        this.src = src;
        this.dest = dest;
        this.type = type;
        this.orderOwnId = orderOwnId;
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

//    public RuleChecker getRules() {
//        return rules;
//    }

    public Type getType() {
        return type;
    }

    // Abstract method to be implemented by derived classes

    /**
     * Executes the specific order action.
     * This method must be implemented by derived classes like Attack, Move, etc.
     */
    public abstract void run();

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