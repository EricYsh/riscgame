package edu.duke.ece651.riscgame;

import java.net.Socket;
import java.util.concurrent.Callable;

public abstract class SocketThread implements Callable {
    protected Socket socket;

    public SocketThread (Socket socket) {
        this.socket = socket;
    }

}
