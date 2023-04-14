package edu.duke.ece651.riscgame;

import java.net.Socket;
import java.util.Vector;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.commuMedium.GameMessageStream;
import edu.duke.ece651.riscgame.commuMedium.ValidationResult;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.*;

/*
 * This class receives and handles the action order
 */
public class ReceiveActionOrderThread extends SocketThread<Vector<Order> >{
    // private volatile ConcurrentHashMap<Integer, Order> orders;
    private final OrderRuleChecker moveChecker;
    private final OrderRuleChecker attackOrder;
    private final GameMessageStream<ActionInfo> gameMsgStream;
    public ReceiveActionOrderThread(Socket socket) {
        super(socket);
        moveChecker = new DestChecker(new UnitChecker(new MovePathChecker(null)));
        attackOrder = new DestChecker(new UnitChecker(new AdjacentChecker(null)));
        gameMsgStream = new GameMessageStream<>();
    }

    @Override
    public Vector<Order> call() {
        Vector<Order> orders = new Vector<>();
        while (true) {
            Order oneOrder = gameMsgStream.receiveObject(socket).getOrder();
            System.out.println("receive one order");
            String check = null;
            if (oneOrder.getType() == Type.Commit) {
                GameMessageStream.sendObject(new ValidationResult(null, true), socket);
                return orders;
            }
            if (oneOrder.getType() == Type.Move) {
                check = moveChecker.checkOrder(oneOrder);
            }
            if (oneOrder.getType() == Type.Attack) {
                check = attackOrder.checkOrder(oneOrder);
            }
            GameMessageStream.sendObject(new ValidationResult(check, false), socket);
            if (check == null) {
                System.out.println("receive valid order");
                orders.add(oneOrder);
            }
        }
    }
    /**
     * for orders from one player, firstly judge whether it is legal
     * if legal then record; it not, send one info back to ask remake it until receive a commit
     * @return
     */
    public static Order receiveActionOrder (Socket socket) {
        GameMessageStream<ActionInfo> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(socket).getOrder();
    }
}
