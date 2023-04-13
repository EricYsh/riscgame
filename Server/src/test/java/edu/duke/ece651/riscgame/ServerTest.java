package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.BoardMapFactory;
import edu.duke.ece651.riscgame.game.BoardTextView;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Attack;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.riscgame.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

public class ServerTest {
    public Vector<Territory> terrGen(int unit1, int unit2, int unit3) {
        Territory t1 = new Territory("A", 1, unit1);
        Territory t2 = new Territory("B", 2, unit2);
        Territory t3 = new Territory("C", 1, unit3);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);
        t2.addNeighbor(t3);
        t3.addNeighbor(t2);
        Vector<Territory> territoryVector1 = new Vector<Territory>();
        territoryVector1.add(t1);
        territoryVector1.add(t2);
        territoryVector1.add(t3);
        return territoryVector1;
    }

    public boolean receiveACK(NetClient nc, int i) {
        IllegalOrder ill = nc.receiveIllegalOrder();
        if (i == 0)
            assertEquals("Error: The units you have assigned exceeds the total number of units!", ill.getErrMessage());
        if (i == 1)
            assertEquals("Error: you should use all the units!", ill.getErrMessage());
        if (i == 2)
            assertEquals("Receive success", ill.getErrMessage());
        return !ill.isLegal();
    }

    @Test
    public void testSendGameInitInfo() {
        NetServer ns = new NetServer(1, 2, 8888);
        NetClient nc = new NetClient(8888);
        ns.connectWithMultiClients();
        ns.sendGameInitInfo(new GameInitInfo());
        GameInitInfo info = nc.receiveGameInitInfo();
        Assertions.assertEquals("HELLO, this is a GameInitInfo", info.printTestInfo());
    }

    //    @Disabled
//    @Test
//    public void testReceiveUnitAssignment () {
//        // this func only test one time unit assignment passing
//        NetServer ns = new NetServer(1, 2, 8888);
//        NetClient nc = new NetClient(8888);
//        Vector<Territory> territoryVector1 = terrGen(5, 5, 5);
//        ns.connectWithMultiClients();
//        nc.sendUnitAssignment(territoryVector1);
//
//        Vector<Territory> territoryVector2 = ns.testReceiveUnitAssignment();
//        // System.err.println(territoryVector2.size());
//        assertNotNull(territoryVector2);
//        assertEquals(1, territoryVector2.get(0).getOwnId());
//        assertEquals(2, territoryVector2.get(1).getOwnId());
//        assertEquals(1, territoryVector2.get(2).getOwnId());
//        assertEquals("A", territoryVector2.get(0).getName());
//        assertEquals("B", territoryVector2.get(1).getName());
//        assertEquals("C", territoryVector2.get(2).getName());
//    }
    @Test
    public void TestValidateUnitAssignment() {
        NetServer ns = new NetServer(1, 2, 8888);
        NetClient nc = new NetClient(8888);
        ns.connectWithMultiClients();
//        for (int i = 0; receiveACK(nc, i) ; i++) {
//            if (i == 0)
//                nc.sendUnitAssignment(terrGen(11, 11, 11));
//            if (i == 1)
//                nc.sendUnitAssignment(terrGen(11, 1, 1));
//            if (i == 2)
//                nc.sendUnitAssignment(terrGen(10, 10, 10));
//        }
        int i = 0;
        do {
            if (i == 0)
                nc.sendUnitAssignment(terrGen(11, 11, 11));
            if (i == 1)
                nc.sendUnitAssignment(terrGen(11, 1, 1));
            if (i == 2)
                nc.sendUnitAssignment(terrGen(10, 10, 10));
        } while (receiveACK(nc, i));
    }

    @Test
    public void executeTest() {
        BoardMapFactory f1 = new BoardMapFactory();

        ArrayList<Order> orderArrayList = new ArrayList<>();
        BoardGameMap map = f1.generateMap(2);
        BoardTextView v1 = new BoardTextView(map);
        v1.printPlayerMap(0);
        v1.printPlayerMap(1);

        Territory T1 = map.getTerritoryByName("T1");
        Territory T4 = map.getTerritoryByName("T4");
        Attack a1 = new Attack(1, T1, T4, Type.Attack, 0);
        Attack a2 = new Attack(28, T4, T1, Type.Attack, 1);

        orderArrayList.add(a1);
        orderArrayList.add(a2);

        GameServer g1 = new GameServer(2);
        g1.executeOrders(orderArrayList);
//        a1.run(map);
//        a2.run(map);

        v1.printPlayerMap(0);
        v1.printPlayerMap(1);
    }

    @Test
    public void executeTest2() {
        BoardMapFactory f1 = new BoardMapFactory();

        ArrayList<Order> orderArrayList = new ArrayList<>();


        GameServer g1 = new GameServer(2);
        BoardGameMap map = g1.getGameMap();
        Territory T1 = map.getTerritoryByName("T1");
        Territory T4 = map.getTerritoryByName("T4");
        Attack a1 = new Attack(1, T1, T4, Type.Attack, 0);
        Attack a2 = new Attack(28, T4, T1, Type.Attack, 1);
//            Attack a3 = new Attack(1, T1, T4, Type.AttackAndChangeHome, 0);
//            Attack a4 = new Attack(28, T4, T1, Type.AttackAndChangeHome, 1);
        orderArrayList.add(a1);
        orderArrayList.add(a2);
//            orderArrayList.add(a3);
//            orderArrayList.add(a4);

        g1.executeOrders(orderArrayList);
        BoardTextView v1 = new BoardTextView(map);
        v1.printPlayerMap(0);
        v1.printPlayerMap(1);

//        a1.run(map);
//        a2.run(map);

    }

}
