package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.game.Territory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.riscgame.*;

import java.net.Socket;
import java.util.Vector;

public class ServerTest {
    @Test
    public void testSendGameInitInfo() {
        NetServer ns = new NetServer(1, 2, 8888);
        NetClient nc = new NetClient(8888);
        ns.connectWithMultiClients();
        ns.sendGameInitInfo(new GameInitInfo());
        GameInitInfo info = nc.receiveGameInitInfo();
        Assertions.assertEquals("HELLO, this is a GameInitInfo", info.printTestInfo());
    }
    @Test
    public void testReceiveUnitAssignment () {
        NetServer ns = new NetServer(1, 2, 8888);
        NetClient nc = new NetClient(8888);
        Territory t1 = new Territory("A", 1, 5);
        Territory t2 = new Territory("B", 2, 5);
        Territory t3 = new Territory("C", 1, 5);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);
        t2.addNeighbor(t3);
        t3.addNeighbor(t2);
        Vector<Territory> territoryVector1 = new Vector<Territory>();
        territoryVector1.add(t1);
        territoryVector1.add(t2);
        territoryVector1.add(t3);
        ns.connectWithMultiClients();
        nc.sendUnitAssignment(territoryVector1);

        Vector<Territory> territoryVector2 = ns.testReceiveUnitAssignment();
        // System.err.println(territoryVector2.size());
        assertNotNull(territoryVector2);
        assertEquals(1, territoryVector2.get(0).getOwnId());
        assertEquals(2, territoryVector2.get(1).getOwnId());
        assertEquals(1, territoryVector2.get(2).getOwnId());
        assertEquals("A", territoryVector2.get(0).getName());
        assertEquals("B", territoryVector2.get(1).getName());
        assertEquals("C", territoryVector2.get(2).getName());
    }
}
