package edu.duke.ece651.riscgame;

import java.net.Socket;
import java.util.Vector;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.commuMedium.GameMessageStream;
import edu.duke.ece651.riscgame.commuMedium.ValidationResult;
import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.*;

/*
 * This class receives and handles the action order
 */
public class ReceiveActionOrderThread extends SocketThread<Vector<Order> >{
    // private volatile ConcurrentHashMap<Integer, Order> orders;
    private GameMap map;
    private final OrderRuleChecker moveChecker;
    private final OrderRuleChecker attackChecker;
    private final OrderRuleChecker upgradePlayerChecker;
    private final OrderRuleChecker upgradeUnitChecker;
    private final GameMessageStream<ActionInfo> gameMsgStream;
    public ReceiveActionOrderThread(Socket socket, GameMap m1) {
        super(socket);
        moveChecker = new DestChecker(new UnitChecker(new MovePathChecker(new ConsumeFoodChecker(null))));
        attackChecker = new DestChecker(new UnitChecker(new AdjacentChecker(new ConsumeFoodChecker(null))));
        upgradePlayerChecker = new UpgradePlayerLevelChecker(new UpgradePlayerResourceChecker(null));
        upgradeUnitChecker = new UpgradeUnitLevelChecker(new UpgradeUnitResourceChecker(null));
        gameMsgStream = new GameMessageStream<>();
        this.map = m1;
    }

    @Override
    public Vector<Order> call() {
        Vector<Order> orders = new Vector<>();
        while (true) {
            Order oneOrder = gameMsgStream.receiveObject(socket).getOrder();
            // System.out.println(oneOrder.toString());
            System.out.println("receive one order");
            String check = null;
            if (oneOrder.getType() == Type.Commit) {
                GameMessageStream.sendObject(new ValidationResult(null, true), socket);
                // orders.add(oneOrder);
                return orders;
            }
            if (oneOrder.getType() == Type.LogOut || oneOrder.getType() == Type.Switch) {
                GameMessageStream.sendObject(new ValidationResult(null, true), socket);
                orders.add(oneOrder);
                return orders;
            }
            // if (oneOrder.getType() == Type.Move) {
            //     check = moveChecker.checkOrder(oneOrder, map);
            // }
            // if (oneOrder.getType() == Type.Attack) {
            //     check = attackChecker.checkOrder(oneOrder, map);
            // }
            // if (oneOrder.getType() == Type.UpgradeTech) {
            //     check = upgradePlayerChecker.checkOrder(oneOrder, map);
            // }
            // if (oneOrder.getType() == Type.UpgradeUnit) {
            //     check = upgradeUnitChecker.checkOrder(oneOrder, map);
            // }
            // GameMessageStream.sendObject(new ValidationResult(check, false), socket);
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
