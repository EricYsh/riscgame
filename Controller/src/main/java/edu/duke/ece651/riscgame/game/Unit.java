package edu.duke.ece651.riscgame.game;

public abstract class Unit implements UnitInterface {
    private int level;

    private int bonus;

    public Unit(int level, int bonus) {
        this.level = level;
        this.bonus = bonus;
    }

    public int getLevel() {
        return level;
    }

    public int getBonus() {
        return bonus;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}

