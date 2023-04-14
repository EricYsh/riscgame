package edu.duke.ece651.riscgame.game;

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
public abstract class Unit implements UnitInterface {
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

