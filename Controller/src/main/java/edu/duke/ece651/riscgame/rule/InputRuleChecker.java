package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.Territory;

import java.util.Vector;

/**
 * The InputRuleChecker is an abstract class that represents a input rule checker
 * for a given input in the RISC game.
 *
 * @param <T> The generic type for the RuleChecker.
 */

public class InputRuleChecker<T> {
    /**
     * Abstract method to be implemented by derived classes.
     * Checks if the unit assignment follows the rule defined by this RuleChecker.
     *
     * @param terrVec terrVec The territory list we want to check.
     * @param numUnit The total number of unit.
     * @return null if the order follows the rule, otherwise returns an error message.
     */
    public String checkMyRule(Vector<Territory> terrVec, int numUnit) {
        int sum = 0;
        for (Territory t : terrVec) {
            sum += t.getUnitNum();
            if (sum > numUnit) return "The units you have assigned exceeds the total number of units!"; // Error:
        }
        if (sum != numUnit) return "You should use all the units!"; // Error:
        return null;
    }


}
