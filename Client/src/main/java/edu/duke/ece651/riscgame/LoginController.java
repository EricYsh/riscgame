package edu.duke.ece651.riscgame;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.GameMap;
import edu.duke.ece651.riscgame.game.Player;
import edu.duke.ece651.riscgame.game.Territory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.duke.ece651.riscgame.ViewController;
import edu.duke.ece651.riscgame.commuMedium.GameInitInfo;
import edu.duke.ece651.riscgame.commuMedium.GameLoginInfo;

public class LoginController {

    private String userId;
    private NetClient netClient;
    private int ClientId;
    private String ClientPassword;
    private Socket socket;
    private Player player;

    public void setNetClient(NetClient netClient) {
        this.netClient = netClient;
    }

    public void setClientId(int clientId) {
        this.ClientId = clientId;
    }

    private ViewController viewController;

    public ViewController getViewController() {
        return viewController;
    }



    @FXML
    private TextField id;

    @FXML
    private TextField password;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button login_btn;

    @FXML
    void click_cancel(ActionEvent event) {
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public Player getPlayer(){
        return player;
    }



    @FXML
    void click_login(ActionEvent event) throws IOException {
        userId = id.getText();
        this.ClientPassword = password.getText();
        player = new Player(Integer.parseInt(userId), "current");
        player.setPassword(ClientPassword);
        netClient.sendPlayer(player);



        if (this.userId.equals("") || this.userId.isEmpty() || this.userId == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ID cannot be empty");
            alert.setContentText("Please enter your ID");
            alert.showAndWait();
        } else if (this.ClientPassword.equals("") || this.ClientPassword.isEmpty() || this.ClientPassword == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password cannot be empty");
            alert.setContentText("Please enter your password");
            alert.showAndWait();
        } else {
            // System.out.println("ID: " + this.ClientId);
            // System.out.println("Password: " + this.ClientPassword);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 600);
            GameInitInfo info = netClient.receiveGameInitInfo();
            for(Territory t: info.getMap().getTerritories()) {
                System.out.println(t.displayInfo());
            }
            viewController = loader.getController();
            viewController.initializeScrollPane();
            viewController.setClientId(ClientId);
            viewController.setNetClient(netClient);
            for (int i = 0; i < 5 - info.getPlayerName().size(); i++) {
                viewController.setTerritoryToWhite(5 - i);
            }
            viewController.setBoardGameMap(info.getMap());
            // TODO: set playerName before show the view!!!
            viewController.printPlayerInfo("You are the Player" + String.valueOf(ClientId + 1));
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Risc Game");
            stage.setResizable(false);
            stage.show();
            
            Stage loginStage = (Stage) login_btn.getScene().getWindow();
            loginStage.close();
        }
    }

    public String getClientPassword() {
        return ClientPassword;
    }

}
