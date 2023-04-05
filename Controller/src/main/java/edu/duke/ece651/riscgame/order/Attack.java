package edu.duke.ece651.riscgame.order;

import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.DestChecker;
import edu.duke.ece651.riscgame.rule.RuleChecker;
import edu.duke.ece651.riscgame.rule.Type;
import edu.duke.ece651.riscgame.rule.UnitChecker;

import java.util.Random;

public class Attack extends Order {

    public Attack() {
        super();
//        this.ruleChecker = new DestChecker(new UnitChecker());
    }

    @Override
    protected void run() {
        if (this.getType().equals(Type.Attack)) {
            //
            int attckUnitNum = this.getUnitNum(); // use how many to attack
            int defendUnitNum = this.getDest().getUnitNum(); // defender unit
            while(attckUnitNum > 0 && defendUnitNum > 0) {
                Random random = new Random();
                int randomNumberAttack = random.nextInt(20) + 1;
                int randomNumberDefend = random.nextInt(20) + 1;
                if(randomNumberAttack > randomNumberDefend) {
                    defendUnitNum--;
                }else{
                    attckUnitNum--;
                }
            }

            if(defendUnitNum > 0) { // defend wins
                this.getDest().setUnitNum(defendUnitNum);
            } else{
                this.getDest().setOwnId(this.getSrc().getOwnId());
                this.getDest().setUnitNum(attckUnitNum);
            }
        }
    }


}