package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.GameMessageStream;

import java.net.Socket;

public class reLoginThread extends SocketThread<Boolean>{
    private Socket socket;
    public reLoginThread (Socket socket) {
        super(socket);
    }
    @Override
    public Boolean call () {

        return false;
    }
    public int receiveReLogin () {
        GameMessageStream<Integer> gameMsgStream = new GameMessageStream<>();
        return gameMsgStream.receiveObject(socket);
    }
}
