package edu.duke.ece651.riscgame;

import java.net.Socket;

public class ReceiveActionOrderThread extends SocketThread{
    public ReceiveActionOrderThread(Socket socket) {
        super(socket);
    }

    @Override
    public Object call() throws Exception {
        return null;
    }
}
