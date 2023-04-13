package edu.duke.ece651.riscgame.game;

public class UnitLevel extends Unit {

    public UnitLevel(int level, int bonus) {
        super(level, bonus);
    }

    @Override
    public void disPlayUnitInfo() {
        System.out.println("Level " + getLevel() + " Unit" + " with " + getBonus() + " Bonus.");
    }
}
