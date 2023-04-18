package edu.duke.ece651.riscgame;


import edu.duke.ece651.riscgame.game.BoardGameMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class RoomController {

//     <?xml version="1.0" encoding="UTF-8"?>

// <?import javafx.scene.text.*?>
// <?import javafx.scene.*?>
// <?import javafx.scene.shape.*?>
// <?import javafx.scene.control.*?>
// <?import java.lang.*?>
// <?import javafx.scene.layout.*?>

// <AnchorPane prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
//    <children>
//       <Button fx:id="room1_enter_btn" layoutX="399.0" layoutY="79.0" onAction="#click_one" mnemonicParsing="false" text="Enter" />
//       <Button fx:id="room2_enter_btn" layoutX="399.0" layoutY="176.0" onAction="#click_two" mnemonicParsing="false" text="Enter" />
//       <Button fx:id="room3_enter_btn" layoutX="399.0" layoutY="264.0" onAction="#click_three" mnemonicParsing="false" text="Enter" />
//       <Text layoutX="110.0" layoutY="112.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Room 1" />
//       <Line endX="200.0" layoutX="300.0" layoutY="150.0" startX="-200.0" />
//       <Line endX="200.0" layoutX="300.0" layoutY="250.0" startX="-200.0" />
//       <Text layoutX="110.0" layoutY="209.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Room 2" />
//       <Text layoutX="110.0" layoutY="297.0" strokeType="OUTSIDE" strokeWidth="0.0" text="Room 3" />
//    </children>
// </AnchorPane>
    private int roomID;

    @FXML
    private Button room1_enter_btn;

    @FXML
    private Button room2_enter_btn;

    @FXML
    private Button room3_enter_btn;

    @FXML
    void click_one() {
        this.roomID = 1;
    }

    @FXML
    void click_one(ActionEvent event) throws Exception {
        this.roomID = 1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        LoginController loginController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void click_two() {
        this.roomID = 2;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        LoginController loginController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void click_three() {
        this.roomID = 3;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        LoginController loginController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public int getRoomID() {
        return roomID;
    }
}
