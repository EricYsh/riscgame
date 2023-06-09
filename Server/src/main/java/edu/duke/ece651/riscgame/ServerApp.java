package edu.duke.ece651.riscgame;

import java.io.IOException;

public class ServerApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws IOException{
        // Parse command-line arguments
        if (args.length != 1) {
            System.out.println("usage: <number of players>");
            return;
        }
        int numClient = Integer.parseInt(args[0]);

        // Check that number of players is within range
        if (numClient < 2 || numClient > 5) {
            System.out.println("err: number of players must be between 2 and 5");
            return;
        }
        // GameServer gameServer = new GameServer(numClient);
        // gameServer.GameInit();
        // gameServer.playRounds();
        // gameServer.gameOver();
    



        Room room1 = new Room(1, numClient, 8888);
        Room room2 = new Room(2, numClient, 8889);
        Room room3 = new Room(3, numClient, 8890);
        Thread thread1 = new Thread(room1);
        Thread thread2 = new Thread(room2);
        Thread thread3 = new Thread(room3);

        thread1.start();
        thread2.start();
        thread3.start();

        // // Load FXML file and create RoomController object
        // // how to load the fxml file
        // //TODO: toolkit bug, why??

        // Platform.startup(() -> {});
        // FXMLLoader loader = new FXMLLoader();
        // loader.setLocation(ServerApp.class.getResource("/Room.fxml"));
        // AnchorPane room = loader.load();
        // RoomController roomController = loader.getController();

        // // Get room ID from RoomController
        // int roomID = roomController.getRoomID();

        // // Start corresponding Room object based on room ID
        // switch (roomID) {
        //     case 1:
        //         room1.start();
        //         break;
        //     case 2:
        //         room2.start();
        //         break;
        //     case 3:
        //         room3.start();
        //         break;
        //     default:
        //         System.out.println("err: invalid room ID");
        //         break;
        // }

    }

}
