package edu.duke.ece651.riscgame.game;

/**
 * The UnitInterface represents a contract for unit objects in the application.
 * <p>
 * This interface defines a method to display the unit's information.
 * <p>
 * Classes implementing this interface must provide their own implementation
 * <p>
 * of the disPlayUnitInfo() method.
 */
public interface UnitInterface {

    /**
     * Displays the information of the unit implementing this interface.
     * The implementing class should define the specific details to be displayed.
     */
    void disPlayUnitInfo();
}
