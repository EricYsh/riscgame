package edu.duke.ece651.riscgame;

public class ServerApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new ServerApp().getGreeting());

        int numClient = 3;
        GameServer gameServer = new GameServer(numClient);
        gameServer.GameInit();
        gameServer.playRounds();
        gameServer.gameOver();
    }
}
