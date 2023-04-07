package edu.duke.ece651.riscgame;

import java.net.Socket;

public abstract class SocketThread extends Thread {
    protected Socket socket;

    public SocketThread (Socket socket) {
        this.socket = socket;
    }

}
