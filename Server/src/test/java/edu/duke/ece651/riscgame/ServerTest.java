package edu.duke.ece651.riscgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.riscgame.*;

public class ServerTest {
    @Test
    public void testSendGameInitInfo() {
        Server ns = new Server(1, 2, 8888);
        Client nc = new Client(8888);
        ns.connectWithMultiClients();
        ns.sendGameInitInfo(new GameInitInfo());
        GameInitInfo info = nc.receiveGameInitInfo();
        assertEquals("HELLO, this is a GameInitInfo", info.printTestInfo());
    }
}
