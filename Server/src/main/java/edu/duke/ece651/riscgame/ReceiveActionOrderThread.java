package edu.duke.ece651.riscgame;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import edu.duke.ece651.riscgame.order.Order;

// import static edu.duke.ece651.riscgame.NetServer.receiveActionOrder;

/*
 * This class receives and handles the action order
 */
public class ReceiveActionOrderThread extends SocketThread{
    private volatile ConcurrentHashMap<Integer, Order> orders;
    

    public ReceiveActionOrderThread(Socket socket) {
        super(socket);
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
