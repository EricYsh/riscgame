package edu.duke.ece651.riscgame;

import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.*;

import static edu.duke.ece651.riscgame.NetServer.*;

// import static edu.duke.ece651.riscgame.NetServer.receiveActionOrder;

/*
 * This class receives and handles the action order
 */
public class ReceiveActionOrderThread extends SocketThread{
    // private volatile ConcurrentHashMap<Integer, Order> orders;
    private OrderRuleChecker moveChecker;
    private OrderRuleChecker attackOrder;
    public ReceiveActionOrderThread(Socket socket) {
        super(socket);
        moveChecker = new DestChecker(new UnitChecker(new MovePathChecker(null)));
        attackOrder = new DestChecker(new UnitChecker(new AdjacentChecker(null)));
    }

    @Override
    public Object call() {
        Vector<Order> orders = new Vector<>();
        while (true) {
            Order oneOrder = receiveActionOrder(socket);
            System.out.println("receive one order");
            String check = null;
            if (oneOrder.getType() == Type.Commit) {
                sendIllegalOrder(socket, new IllegalOrder(null, true));
                return orders;
            }
            if (oneOrder.getType() == Type.Move) {
                check = moveChecker.checkOrder(oneOrder);
            }
            if (oneOrder.getType() == Type.Attack) {
                check = attackOrder.checkOrder(oneOrder);
            }
            sendIllegalOrder(socket, new IllegalOrder(check, false));
            if (check == null) {
                System.out.println("receive valid order");
                orders.add(oneOrder);
            }
        }
    }
}