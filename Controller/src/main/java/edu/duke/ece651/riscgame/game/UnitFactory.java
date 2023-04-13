package edu.duke.ece651.riscgame.game;

public class UnitFactory {
    public UnitInterface createUnit(int level, int bonus) {
        return new UnitLevel(level, bonus);
    }
}