package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.*;
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
    private final Vector<Socket> clientSockets;

    // this variable is designed to record those players lost (no matter watching or disconnected)
    private final Vector<Socket> lostClientSockets;
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
    public void addLostPlayer (int i) {
        lostClientSockets.add(clientSockets.get(i));
    }

    /**
     * this func is blocking because of accept()
     * this function connect with all clients and store their sockets in vector
     * and send clientID
     */
    public void connectWithMultiClients () {
        for (int i = 0; i < numClient; i++) {
            try {
                Socket socket = serverSocket.accept();
                clientSockets.add(socket);
                System.out.println("Player " + i + " connects");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * this func sends clientID to each player
     */
    public void sendClientID () {
        for (int i = 0; i < numClient; i++) {
            GameMessageStream.sendObject(i, clientSockets.get(i));
        }
    }
    public void broadCast (Object object) {
        for (Socket socket: clientSockets) {
            GameMessageStream.sendObject(object, socket);
        }
    }
    /**
     * this func receive unit assignment info from clients
     * and then record them in Game
     */
    public ArrayList<Territory> validateUnitAssignment (int numUnit) {
        ArrayList<Territory> container = new ArrayList<>();
        ArrayList<Future<Vector<Territory> > > unitAssignmentFutures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Socket socket: clientSockets) {
            ReceiveUnitAssignmentThread task = new ReceiveUnitAssignmentThread(socket, numUnit);
            Future<Vector<Territory> > unitAssignment = executorService.submit(task);
            unitAssignmentFutures.add(unitAssignment);
        }
        executorService.shutdown();
        for (Future<Vector<Territory>> future: unitAssignmentFutures) {
            try {
                container.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return container;
    }
    //TODO: this func needs to be tested after the checks in client are removed
    // task: test whether one client can continuously enter orders when one order is told wrong
    /**
     * this func receive action orders from clients
     * and then record them in Game
     */
    public ArrayList<Order> validateActionOrders () {
        ArrayList<Order> container = new ArrayList<>();
        ArrayList<Future<Vector<Order> > > actionOrderFutures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Socket socket: clientSockets) {
            if (lostClientSockets.contains(socket)) continue;
            ReceiveActionOrderThread task = new ReceiveActionOrderThread(socket);
            Future<Vector<Order> > actionOrder = executorService.submit(task);
            actionOrderFutures.add(actionOrder);
        }
        executorService.shutdown();
        for (Future<Vector<Order>> future: actionOrderFutures) {
            try {
                container.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return container;
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
    public static Order receiveActionOrder (Socket socket) {
        ActionInfo info = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            info = (ActionInfo) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return info.getOrder();
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

/*
legacy code: may be used in testing and modification

//        for (int i = 0; i < numClient; i++) {
//            Socket socket = clientSockets.get(i);
//            try {
//                ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
//                objOut.writeObject(info);
//                objOut.flush(); // end output and prompt cache/buffer to send info
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


    /**
     * this func send necessary GameInitInfo to all players
     */
//public void sendGameInitInfo (GameInitInfo info) {
//    for (Socket socket: clientSockets) {
//        GameMessageStream.sendObject(info, socket);
//    }
//}
//    public void sendRoundResult (RoundResult result) {
//        for (Socket socket: clientSockets) {
//            GameMessageStream.sendObject(result, socket);
//        }
//    }
//
//    public void sendGameOverInfo (GameOverInfo gameOverInfo) {
//        for (Socket socket: clientSockets) {
//            GameMessageStream.sendObject(gameOverInfo, socket);
//        }
//    }
/*
        try {
            for (int i = 0; i < numClient - lostClientSockets.size(); i++) {
                if (lostClientSockets.contains(i)) continue;
                Socket socket = clientSockets.get(i);
                FutureTask<Vector<Order> > temp = new FutureTask<Vector<Order> >(new ReceiveActionOrderThread(socket));
                Thread thread = new Thread(temp);
                thread.start();
                // Future<Order> temp = threadPoolForActionOrder.submit();
                container.addAll(temp.get());
            }
            // while (true) {
            //     if (container.size() == (numClient - lostClientSockets.size()) * 3) {
            //         break;
            //     }
            // }
//            threadPoolForActionOrder.shutdown(); // stop waiting for future tasks, then it cannot open again
//            threadPoolForActionOrder.awaitTermination(300, TimeUnit.SECONDS); // wait 5 min for all thread execution
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
 */
