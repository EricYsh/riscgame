package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.Territory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NetServer {
    // this class is designed to set up socket connection with clients
    // and be responsible to send information to clients and receive information
    // firstly, the server need to be able to accept multiple socket connection

    private ServerSocket serverSocket;
    private ExecutorService threadPool;
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
            this.threadPool = Executors.newFixedThreadPool(poolSize);
            // threadPool.execute(new SocketThread(socket));
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
    //TODO: fulfill the information needed in GameInitInfo
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
    public void validateUnitAssignment () {

        Vector<Territory> terrVec = receiveUnitAssignment(clientSockets.get(0));

    }
    public Vector<Territory> testReceiveUnitAssignment () {
        return receiveUnitAssignment(clientSockets.get(0));
    }
    public Vector<Territory> receiveUnitAssignment (Socket socket) {
        Vector<Territory> territoryVector = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            territoryVector = (Vector<Territory>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
    public void sendRoundResult () {

    }
}
