package edu.duke.ece651.riscgame;

public class ServerApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        // System.out.println(new ServerApp().getGreeting());

        if (args.length != 1) {
            System.out.println("usage: <number of players>");
        }
        int numClient = Integer.parseInt(args[0]);

        if (numClient < 2 || numClient > 5) {
            System.out.println("err: number of player must between 2 and 5");
            return;
        }
        GameServer gameServer = new GameServer(numClient);

//        gameServer.GameInit();
//        gameServer.playRounds();
//        gameServer.gameOver();

        //TODO: the two lines below for testing assign units manually
        gameServer.GameInit();
        System.out.println("received correctly");
    }
}
