package edu.duke.ece651.riscgame;

public class ServerApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        // System.out.println(new ServerApp().getGreeting());

        int numClient = 1;
        GameServer gameServer = new GameServer(numClient);

//        gameServer.GameInit();
//        gameServer.playRounds();
//        gameServer.gameOver();

        //TODO: the two lines below for testing assign units manually
//        String str = gameServer.assignUnit();
//        System.out.println(str);
    }
}
