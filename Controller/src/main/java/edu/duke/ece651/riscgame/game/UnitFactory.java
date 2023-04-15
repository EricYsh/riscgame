package edu.duke.ece651.riscgame.game;

import java.io.Serializable;

/**
 * The UnitFactory class is responsible for creating instances of UnitInterface.
 * <p>
 * This factory class follows the Factory Design Pattern and encapsulates the
 * <p>
 * instantiation logic of UnitInterface objects in a single place.
 */
public class UnitFactory implements Serializable {

    /**
     * Creates a new UnitInterface object with the specified level and bonus.
     *
     * @param level The level of the unit to be created.
     * @param bonus The bonus to be applied to the unit.
     * @return A new UnitLevel object implementing the UnitInterface with the given level and bonus.
     */
    public UnitInterface createUnit(int level, int bonus) {
        return new UnitLevel(level, bonus);
    }
}