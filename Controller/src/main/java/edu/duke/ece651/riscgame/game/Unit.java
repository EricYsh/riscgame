package edu.duke.ece651.riscgame.game;

import java.io.Serializable;

/**
 * The Unit class is an abstract class that implements the UnitInterface and
 * <p>
 * serves as a base class for different unit types in the application. It
 * <p>
 * provides common properties and methods for managing a unit's level and bonus.
 * <p>
 * Classes that extend the Unit class must provide their own implementation of
 * <p>
 * the disPlayUnitInfo() method from the UnitInterface.
 */
public abstract class Unit implements UnitInterface, Serializable {
    private int level;

    private int bonus;

    /**
     * Constructs a new Unit object with the specified level and bonus.
     *
     * @param level The level of the unit.
     * @param bonus The bonus to be applied to the unit.
     */
    public Unit(int level, int bonus) {
        this.level = level;
        this.bonus = bonus;
    }

    public Unit(int bonus) {
        switch (bonus) {
            case 1:
                this.level = 1;
                break;
            case 2:
                this.level = 2;
                break;
            case 3:
                this.level = 3;
                break;
            case 4:
                this.level = 4;
                break;
            case 5:
                this.level = 5;
                break;
            case 6:
                this.level = 6;
                break;
            default:
                this.level = 1;
                break;
        }
        this.bonus = bonus;
    }

    /**
     * Retrieves the level of this unit.
     *
     * @return The level of this unit.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Retrieves the bonus of this unit.
     *
     * @return The bonus of this unit.
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * Sets the level of this unit.
     *
     * @param level The new level for this unit.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the bonus of this unit.
     *
     * @param bonus The new bonus for this unit.
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}

