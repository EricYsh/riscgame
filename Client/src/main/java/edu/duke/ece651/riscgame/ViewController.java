package edu.duke.ece651.riscgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewController {

    @FXML
    private Button move_btn;

    @FXML
    private Button attack_btn;

    @FXML
    private Button upgrade_btn;

    @FXML
    private Button commit_btn;

    @FXML
    private Text territory_info;

    @FXML
    private Polygon player1_t1;

    @FXML
    private Polygon player1_t2;

    @FXML
    private Polygon player1_t3;

    @FXML
    private Polygon player2_t1;

    @FXML
    private Polygon player2_t2;

    @FXML
    private Polygon player2_t3;

    @FXML
    private Polygon player3_t1;

    @FXML
    private Polygon player3_t2;

    @FXML
    private Polygon player3_t3;

    @FXML
    private Polygon player4_t1;

    @FXML
    private Polygon player4_t2;

    @FXML
    private Polygon player4_t3;

    @FXML
    private Polygon player5_t1;

    @FXML
    private Polygon player5_t2;

    @FXML
    private Polygon player5_t3;

    @FXML 
    void click_player4_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            territory_info.setText("Player 4 Territory 1");
        }
    }

    @FXML
    void click_move(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoveDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void click_commit(ActionEvent event) {

    }

}
