package edu.duke.ece651.riscgame;

public class ServerApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        // System.out.println(new ServerApp().getGreeting());

        int numClient = 2;
        GameServer gameServer = new GameServer(numClient);

//        gameServer.GameInit();
//        gameServer.playRounds();
//        gameServer.gameOver();

        // the two lines below for testing assign units manually
        gameServer.GameInit();
        System.out.println("received correctly");
    }
}
