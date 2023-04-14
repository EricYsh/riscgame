package edu.duke.ece651.riscgame.game;

/**
 * The UnitLevel class is a subclass of the Unit class, which represents a specific
 * <p>
 * level of a unit along with its bonus. This class provides an implementation for
 * <p>
 * the disPlayUnitInfo() method from the UnitInterface.
 */
public class UnitLevel extends Unit {

    /**
     * Constructs a new UnitLevel object with the specified level and bonus.
     *
     * @param level The level of the unit.
     * @param bonus The bonus to be applied to the unit.
     */
    public UnitLevel(int level, int bonus) {
        super(level, bonus);
    }

    /**
     * Displays the information of this unit, including its level and bonus.
     * This method implements the disPlayUnitInfo() method from the UnitInterface.
     */
    @Override
    public void disPlayUnitInfo() {
        System.out.println("Level " + getLevel() + " Unit" + " with " + getBonus() + " Bonus.");
    }
}
