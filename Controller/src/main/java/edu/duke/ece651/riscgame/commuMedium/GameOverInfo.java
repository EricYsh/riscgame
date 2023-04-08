package edu.duke.ece651.riscgame.commuMedium;

import java.io.Serializable;

/*
 * this class is used to send game over info to client
 */

public class GameOverInfo implements Serializable{
    private String winnerName;

    public GameOverInfo(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getWinnerName() {
        return winnerName;
    }
}
