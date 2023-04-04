package edu.duke.ece651.riscgame;

import java.io.IOException;

public class ClientApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new ClientApp().getGreeting());

        GameClient gameClient = new GameClient();
        try {
            gameClient.gameInit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameClient.playRounds();
        gameClient.gameOver();
    }
}
