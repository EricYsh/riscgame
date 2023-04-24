package edu.duke.ece651.riscgame.commuMedium;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class GameLoginInfo implements Serializable {
    public static void sendObject(Object object, Socket socket){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
}
