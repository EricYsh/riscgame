package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.InputRuleChecker;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class NetServer {
    // this class is designed to set up socket connection with clients
    // and be responsible to send information to clients and receive information
    // firstly, the server need to be able to accept multiple socket connection

    private ServerSocket serverSocket;
    private ExecutorService threadPoolForUnitAssign;
    private ExecutorService threadPoolForActionOrder;
    private Vector<Socket> clientSockets;

    // this variable is designed to record those players lost (no matter watching or disconnected)
    private Vector<Socket> lostClientSockets;
    private final int numClient;

    /**
     * constructor
     */
    public NetServer (int numClient, int poolSize, int port) {
        this.numClient  = numClient;
        this.clientSockets = new Vector<Socket>();
        this.lostClientSockets = new Vector<Socket>();
        try {
            this.threadPoolForUnitAssign = Executors.newFixedThreadPool(poolSize);
            this.threadPoolForActionOrder = Executors.newFixedThreadPool(poolSize);
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server is listening and waiting for connection");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * this func is blocking because of accept()
     * this function connect with all clients and store their sockets in vector
     */
    public void connectWithMultiClients () {
        int count = 0;
        while (count < numClient) {
            try {
                Socket socket = serverSocket.accept();
                clientSockets.add(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            count++;
        }
    }

    /**
     * this func send necessary GameInitInfo to all players
     * @param info contains TODO: what?
     */
    public void sendGameInitInfo (GameInitInfo info) {
        for (int i = 0; i < numClient; i++) {
            Socket socket = clientSockets.get(i);
            try {
                ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
                objOut.writeObject(info);
                objOut.flush(); // end output and prompt cache/buffer to send info
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void validateUnitAssignment (int numUnit) {
        for (int i = 0; i < numClient; i++) {
            Socket socket = clientSockets.get(i);
            threadPoolForUnitAssign.execute(new ReceiveUnitAssignmentThread(socket, numUnit));
        }
        threadPoolForUnitAssign.shutdown(); // stop waiting for future tasks, then it cannot open again
        try {
            threadPoolForUnitAssign.awaitTermination(300, TimeUnit.SECONDS); // wait 5 min for all thread execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //TODO: delete when finished
    public Vector<Territory> testReceiveUnitAssignment () {
        return receiveUnitAssignment(clientSockets.get(0));
    }

    /**
     * this func receive one unit assignment from one player, which may not be valid
     * @param socket determine receive from which player
     * @return received unit assignment information, assigned in each Territory
     */
    public static Vector<Territory> receiveUnitAssignment (Socket socket) {
        Vector<Territory> territoryVector = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            territoryVector = (Vector<Territory>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return territoryVector;
    }

    /**
     * for orders from one player, firstly judge whether it is legal
     * if legal then record; it not, send one info back to ask remake it until receive a commit
     * @return
     */
    public int receiveActionOrders () {

        return 1;
    }
    public static void sendIllegalOrder (Socket socket, IllegalOrder illegal) {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
            objOut.writeObject(illegal);
            objOut.flush(); // end output and prompt cache/buffer to send info
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRoundResult () {

    }
}
