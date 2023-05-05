package edu.duke.ece651.riscgame;

import java.net.Socket;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

public class RoomController {

    public int roomID;

    public Socket socket;

    public NetClient netClient;

    public HashMap<Integer, Integer> roomInfo = new HashMap<Integer, Integer>();


    public LoginController loginController1, loginController2, loginController3;

    public LoginController getLoginController(int roomID) {
        if (roomID == 1) {
            return loginController1;
        } else if (roomID == 2) {
            return loginController2;
        } else {
            return loginController3;
        }
    }


    @FXML
    private Button room1_enter_btn;

    @FXML
    private Button room2_enter_btn;

    @FXML
    private Button room3_enter_btn;



    @FXML
    void click_one(ActionEvent event) throws Exception {
        netClient = new NetClient(8888);
        int clientID = netClient.receiveClientID();
        roomInfo.put(1, clientID);
        //gui
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 250);
        loginController1 = loader.getController();
        loginController1.setNetClient(netClient);
        loginController1.setClientId(clientID);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();

        Stage roomStage = (Stage) room1_enter_btn.getScene().getWindow();
        roomStage.close();
    }

    @FXML
    void click_two(ActionEvent event) throws Exception{
        netClient = new NetClient(8889);
        int clientID = netClient.receiveClientID();
        System.out.println("room 2, client ID: " + clientID);
        roomInfo.put(2, clientID);
        //gui
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 250);
        loginController2 = loader.getController();
        loginController2.setNetClient(netClient);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();

        Stage roomStage = (Stage) room2_enter_btn.getScene().getWindow();
        roomStage.close();
    }

    @FXML
    void click_three(ActionEvent event) throws Exception{
        netClient = new NetClient(8890);
        int clientID = netClient.receiveClientID();
        roomInfo.put(3, clientID);
        //gui
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 250);
        loginController3 = loader.getController();
        loginController3.setNetClient(netClient);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login");
        stage.show();
        
        Stage roomStage = (Stage) room3_enter_btn.getScene().getWindow();
        roomStage.close();
    }

    public int getRoomID() {
        return roomID;
    }
}
