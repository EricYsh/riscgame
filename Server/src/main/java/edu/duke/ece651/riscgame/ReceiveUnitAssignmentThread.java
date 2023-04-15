package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameMessageStream;
import edu.duke.ece651.riscgame.commuMedium.ValidationResult;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.rule.InputRuleChecker;

import java.net.Socket;
import java.util.Vector;

public class ReceiveUnitAssignmentThread extends SocketThread <Vector<Territory> >{
    int numUnit;
    private final GameMessageStream<Vector<Territory>> gameMsgStream;
    public ReceiveUnitAssignmentThread (Socket socket, int numUnit) {
        super(socket);
        this.numUnit = numUnit;
        this.gameMsgStream = new GameMessageStream<>();
    }
    @Override
    public Vector<Territory> call () {
        while (true) {
            Vector<Territory> terrVec = gameMsgStream.receiveObject(socket);
            System.out.println("receive one assignment");
            String check = new InputRuleChecker<>().checkMyRule(terrVec, numUnit);
            GameMessageStream.sendObject(new ValidationResult(check, false), socket);
            if (check == null) {
                System.out.println("receive valid assignment");
                return terrVec;
            }
        }
    }
    /**
     * this func receive one unit assignment from one player, which may not be valid
     * @param socket determine receive from which player
     * @return received unit assignment information, assigned in each Territory
     */
    public static Vector<Territory> receiveUnitAssignment (Socket socket) {
        GameMessageStream<Vector<Territory>> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(socket);
    }
}
