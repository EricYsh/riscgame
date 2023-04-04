package edu.duke.ece651.riscgame.endSystem;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class NetServer {
    // this class is designed to set up socket connection with clients
    // and be responsible to send information to clients and receive information
    // firstly, the server need to be able to accept multiple socket connection
    private ServerSocket serverSocket;
    private Vector<Socket> clientSockets;

    // this variable is designed to record those players lost (no matter watching or disconnected)
    private Vector<Socket> lostClientSockets;
    private final int numClient;


    /**
     * constructor
     */
    public NetServer (int numClient) {
        this.numClient = numClient;
    }

    /**
     * this function connect with all clients and store their sockets in vector
     */
    public void connectWithMultiClients () {

    }


}
