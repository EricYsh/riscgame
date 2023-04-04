package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import edu.duke.ece651.riscgame.*;

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
}
