package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.IllegalOrder;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.InputRuleChecker;

import java.net.Socket;
import java.util.Vector;

import static edu.duke.ece651.riscgame.NetServer.receiveUnitAssignment;
import static edu.duke.ece651.riscgame.NetServer.sendIllegalOrder;

public class ReceiveUnitAssignmentThread extends SocketThread{
    int numUnit;
    public ReceiveUnitAssignmentThread (Socket socket, int numUnit) {
        super(socket);
        this.numUnit = numUnit;
    }
    @Override
    public Vector<Territory> call () {
        while (true) {
            Vector<Territory> terrVec = receiveUnitAssignment(socket);
            System.out.println("receive one assignment");
            String check = new InputRuleChecker<>().checkMyRule(terrVec, numUnit);
            System.out.println(check);
            sendIllegalOrder(socket, new IllegalOrder(check, false));
            if (check == null) {
                System.out.println("receive valid assignment");
                return terrVec;
            }
        }
    }
}