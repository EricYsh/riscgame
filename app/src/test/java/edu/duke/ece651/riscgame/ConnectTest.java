package edu.duke.ece651.riscgame;

//import edu.duke.ece651.riscgame.Server;
//import edu.duke.ece651.riscgame.Client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectTest {
    @Test
    public void testSendGameInitInfo() {
        Server ns = new Server(1, 2, 8888);
        Client nc = new Client(8888);
        ns.connectWithMultiClients();
        GameInitInfo info = nc.receiveGameInitInfo();
        ns.sendGameInitInfo(new GameInitInfo());
        assertEquals("HELLO, this is a GameInitInfo", info.printTestInfo());
    }
}

