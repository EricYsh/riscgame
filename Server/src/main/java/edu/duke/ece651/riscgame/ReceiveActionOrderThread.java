package edu.duke.ece651.riscgame;

import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

import edu.duke.ece651.riscgame.order.Order;

import static edu.duke.ece651.riscgame.NetServer.receiveActionOrder;

/*
 * This class receives and handles the action order
 */
public class ReceiveActionOrderThread extends SocketThread{
    private volatile ConcurrentHashMap<Integer, Order> orders;
    

    public ReceiveActionOrderThread(Socket socket) {
        super(socket);
    }
<<<<<<< HEAD
    @Override
    public void run () {
        while (true) {
            Order order = receiveActionOrder(socket);
            if (order == null) {
                break;
            }
            orders.put(order.getPlayerID(), order);
        }
=======

    @Override
    public Object call() throws Exception {
        return null;
>>>>>>> 141321b8df1d2d5f3babccb5f3122b2d65da1767
    }
}
