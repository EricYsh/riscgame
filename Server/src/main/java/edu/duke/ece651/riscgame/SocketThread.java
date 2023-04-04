package edu.duke.ece651.riscgame;

import java.net.Socket;

public class SocketThread extends Thread {
    private Socket socket;

    public SocketThread (Socket newSocket) {
        this.socket = newSocket;
    }
    @Override
    public void run () {

    }
}
