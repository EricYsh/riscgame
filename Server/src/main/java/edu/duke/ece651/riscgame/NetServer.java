package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
import edu.duke.ece651.riscgame.commuMedium.RoundResult;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.*;


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
     * and send clientID
     */
    public void connectWithMultiClients () {
        int count = 0;
        while (count < numClient) {
            try {
                Socket socket = serverSocket.accept();
                clientSockets.add(socket);
                //send client id
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeInt(count);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Player " + count + " connects");
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
    /**
     * this func receive unit assignment info from clients
     * and then record them in Game
     */
    public ArrayList<Territory> validateUnitAssignment (int numUnit) {
        ArrayList<Territory> container = new ArrayList<>();
        try {
            for (int i = 0; i < numClient; i++) {
                Socket socket = clientSockets.get(i);
                Future<Vector<Territory>> temp = threadPoolForUnitAssign.submit(new ReceiveUnitAssignmentThread(socket, numUnit));
                container.addAll(temp.get());
            }
            threadPoolForUnitAssign.shutdown(); // stop waiting for future tasks, then it cannot open again
            threadPoolForUnitAssign.awaitTermination(300, TimeUnit.SECONDS); // wait 5 min for all thread execution
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return container;
    }

    /**
     * this func receive action orders from clients
     * and then record them in Game
     */
    public void validateActionOrders () {
        for (int i = 0; i < numClient; i++) {
            Socket socket = clientSockets.get(i);
            threadPoolForActionOrder.execute(new ReceiveActionOrderThread(socket));
        }
        threadPoolForActionOrder.shutdown(); // stop waiting for future tasks, then it cannot open again
        try {
            threadPoolForActionOrder.awaitTermination(300, TimeUnit.SECONDS); // wait 5 min for all thread execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
    public Order receiveActionOrder (Socket socket) {
        Order order = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            order = (Order) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return order;
    }
    public static Order receiveOneOrder (Socket socket) {
        Order oneOrder = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            oneOrder = (Order) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return oneOrder;
    }
    public static Order receiveOneOrder (Socket socket) {
        Order oneOrder = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            oneOrder = (Order) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return oneOrder;
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

    public void sendRoundResult (HashMap<String, Integer> ownership, HashMap<String, Integer> units) {
//        if (ownership != null) {
//            sendOwnershipChange(ownership);
//        }
        sendOwnershipChange(ownership);
        sendUnitsChange(units);
    }

    private void sendUnitsChange(HashMap<String, Integer> units) {
    }

    private void sendOwnershipChange(HashMap<String, Integer> ownership) {
    }

    public void close () {
        try {
            for (Socket s: clientSockets) {
                s.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
