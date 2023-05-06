package edu.duke.ece651.riscgame.rule;

import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.order.Order;

public class CloakTechResourceChecker extends OrderRuleChecker {
    /**
     * Constructs a RuleChecker.
     *
     * @param next The next rule we want to check.
     */
    public CloakTechResourceChecker(OrderRuleChecker next) {
        super(next);
    }

    @Override
    protected String checkMyRule(Order theOrder, GameMap gameMap) {
        Player p = gameMap.getPlayerById(theOrder.getOrderOwnId());
        int resource = p.getTechResource();
        if (p.getCloakFlag() == 1 && resource < 120) {
           return "Your Technology Resource is not enough";
        } else if (p.getCloakFlag() == 0 && resource < 20) {
            return "Your Technology Resource is not enough";
        }
        return null;
    }
}
