package edu.duke.ece651.riscgame;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// <?xml version="1.0" encoding="UTF-8"?>

// <?import javafx.scene.control.*?>
// <?import java.lang.*?>
// <?import javafx.scene.layout.*?>


// <AnchorPane prefHeight="400.0" prefWidth="478.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
//    <children>
//       <Label layoutX="138.0" layoutY="105.0" text="ID:" />
//       <Label layoutX="73.0" layoutY="172.0" text="Password:" />
//       <TextField fx:id="id" layoutX="199.0" layoutY="97.0" prefHeight="48.0" prefWidth="232.0" />
//       <TextField fx:id="password" layoutX="199.0" layoutY="164.0" prefHeight="48.0" prefWidth="232.0" />
//       <Button fx:id="cancel_btn" layoutX="188.0" layoutY="276.0" mnemonicParsing="false" onAction="#click_cancel" text="Cancel" />
//       <Button fx:id="login_btn" layoutX="334.0" layoutY="276.0" mnemonicParsing="false" onAction="#click_login" text="Login" />
//    </children>
// </AnchorPane>


public class LoginController {

    private String ClientId;
    private String ClientPassword;

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
    void click_login(ActionEvent event){
        this.ClientId = id.getText();
        this.ClientPassword = password.getText();
        if (this.id.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("ID cannot be empty");
            alert.setContentText("Please enter your ID");
            alert.showAndWait();
        }
        else if (this.password.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password cannot be empty");
            alert.setContentText("Please enter your password");
            alert.showAndWait();
        }
        else{
            //TODO: how to interact with map??
            System.out.println("ID: " + this.ClientId);
            System.out.println("Password: " + this.ClientPassword);
            Stage stage = (Stage) login_btn.getScene().getWindow();
            stage.close();
        }
    }

    public String getClientId() {
        return ClientId;
    }

    public String getClientPassword() {
        return ClientPassword;
    }

    
}
