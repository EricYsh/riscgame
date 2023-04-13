package edu.duke.ece651.riscgame.rule;


/**
 * The Type enum represents the different types of actions that can be performed
 * by a player in the RISC game. This helps in defining the behavior of the game
 * and provides a clear understanding of the available options for a player.
 *
 * Enum constants:
 *  - Move: Represents a move order, where a player moves their units from one territory to another.
 *  - Attack: Represents an attack order, where a player initiates an attack on an opponent's territory.
 *  - Commit: Represents a commit order, where a player finalizes their current turn.
 *  - AttackAndChangeHome: Represents a combination of attack order and changing the home territory.
 */
public enum Type {

    Move,
    Attack,
    Commit,
    AttackAndChangeHome,
    Upgrade
}
