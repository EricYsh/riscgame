package edu.duke.ece651.riscgame.commuMedium;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class GameMessageStream<T> {
    public static void sendObject(Object object, Socket socket) {
        try {
            ObjectOutputStream objOut = new ObjectOutputStream(socket.getOutputStream());
            objOut.writeObject(object);
            objOut.flush(); // end output and prompt cache/buffer to send info
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
