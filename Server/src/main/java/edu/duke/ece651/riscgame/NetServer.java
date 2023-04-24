package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.*;
import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.Type;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.*;


public class NetServer {
    // this class is designed to set up socket connection with clients
    // and be responsible to send information to clients and receive information
    // firstly, the server need to be able to accept multiple socket connection

    private ServerSocket serverSocket;
    private final Vector<Socket> clientSockets;

    // this variable is designed to record those players lost (no matter watching or disconnected)
    private final HashSet<Socket> lostClientSockets;
    private final HashSet<Socket> logOutClientSockets;
    private final int numClient;

    /**
     * constructor
     */
    public NetServer (int numClient, int port) {
        this.numClient  = numClient;
        this.clientSockets = new Vector<Socket>();
        this.lostClientSockets = new HashSet<Socket>();
        this.logOutClientSockets = new HashSet<Socket>();
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server is listening and waiting for connection");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void addLostPlayer (int i) {
        lostClientSockets.add(clientSockets.get(i));
    }
    public void addLogOutPlayer (int i) {
        logOutClientSockets.add(clientSockets.get(i));
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
    public ArrayList<Order> validateActionOrders (GameMap map) {
        ArrayList<Order> container = new ArrayList<>();
        ArrayList<Future<Vector<Order> > > actionOrderFutures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (Socket socket: clientSockets) {
            if (lostClientSockets.contains(socket)) continue;
            if (logOutClientSockets.contains(socket)) continue;
            ReceiveActionOrderThread task = new ReceiveActionOrderThread(socket, map);
            Future<Vector<Order> > actionOrder = executorService.submit(task);
            actionOrderFutures.add(actionOrder);
        }
        executorService.shutdown();
        int i = 0;
        for (Future<Vector<Order>> future: actionOrderFutures) {
            try {
                Vector<Order> temp = future.get();
                if (temp.lastElement().getType() == Type.Switch ||
                        temp.lastElement().getType() == Type.LogOut) {
                    logOutClientSockets.add(clientSockets.get(i));
                }
                container.addAll(temp);
                i++;
                if (i >= numClient) i -= numClient;
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return container;
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

    public void reLogin(ArrayList<Player> players) {
        try {
            Selector selector = Selector.open();
            for (Socket socket: logOutClientSockets) {
                SocketChannel channel = socket.getChannel();
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
            }
            // while (true) {
            selector.select(2000);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            GameMessageStream<Player> gameMsgStream = new GameMessageStream<>();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isValid() && key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    Socket socket = channel.socket();
                    Player p = gameMsgStream.receiveObject(socket);
                    for (Player pl: players) {
                        if (pl.getClientID() == p.getClientID()) {
                            if (pl.getPassword() == p.getPassword()) {
                                logOutClientSockets.remove(socket);
                            }
                        }
                    }
//                    ByteBuffer buffer = ByteBuffer.allocate(10);
//                    int bytesRead = channel.read(buffer);
//                    if (bytesRead != -1) {
//                        logOutClientSockets.remove(socket);
//                    }
                }
                iterator.remove();
            }
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Player> receivePlayer () {
        GameMessageStream<Player> gameMsgStream = new GameMessageStream<>();
        ArrayList<Player> players = new ArrayList<>();
        for (Socket s : clientSockets) {
            players.add(gameMsgStream.receiveObject(s));
        }

        return players;
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




         Vector<Territory> territoryVector = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            territoryVector = (Vector<Territory>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return territoryVector;
 */
