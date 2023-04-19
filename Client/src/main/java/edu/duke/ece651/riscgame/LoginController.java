package edu.duke.ece651.riscgame;

import java.io.IOException;
import java.util.HashMap;

import edu.duke.ece651.riscgame.game.GameMap;
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

public class LoginController {

    private String ClientId;
    private String ClientPassword;

    private ViewController viewController;

    public ViewController getViewController() {
        return viewController;
    }

    private int playerNums = 3;

    public void setPlayerNums(int playerNums) {
        this.playerNums = playerNums;
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
    void click_login(ActionEvent event) throws IOException {
        this.ClientId = id.getText();
        this.ClientPassword = password.getText();
        if (this.ClientId.equals("") || this.ClientId.isEmpty() || this.ClientId == null) {
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
            Scene scene = new Scene(root, 1000, 600);
            viewController = loader.getController();

            // TODO: set playerNums before show the view!!!
            for (int i = 0; i < 5 - playerNums; i++) {
                viewController.setTerritoryToWhite(5 - i);
            }

            // TODO: set playerName before show the view!!!
            viewController.setPlayerName(ClientId); // need to change!!!

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Risc Game");
            stage.setResizable(false);
            stage.show();
            
            Stage loginStage = (Stage) login_btn.getScene().getWindow();
            loginStage.close();
        }
    }

    public String getClientId() {
        return ClientId;
    }

    public String getClientPassword() {
        return ClientPassword;
    }

}
