package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.game.BoardGameMap;
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

    private BoardGameMap boardGameMap;

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

    // reset all territories to be transparent
    private void resetTerritoryOpacity() {
        player1_t1.setOpacity(1);
        player1_t2.setOpacity(1);
        player1_t3.setOpacity(1);
        player2_t1.setOpacity(1);
        player2_t2.setOpacity(1);
        player2_t3.setOpacity(1);
        player3_t1.setOpacity(1);
        player3_t2.setOpacity(1);
        player3_t3.setOpacity(1);
        player4_t1.setOpacity(1);
        player4_t2.setOpacity(1);
        player4_t3.setOpacity(1);
        player5_t1.setOpacity(1);
        player5_t2.setOpacity(1);
        player5_t3.setOpacity(1);
    }

    @FXML
    void click_player1_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t1.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 1 Territory 1");
        }
    }

    @FXML
    void click_player1_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t2.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 1 Territory 2");
        }
    }

    @FXML
    void click_player1_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t3.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 1 Territory 3");
        }
    }

    @FXML
    void click_player2_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t1.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 2 Territory 1");
        }
    }

    @FXML
    void click_player2_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t2.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 2 Territory 2");
        }
    }

    @FXML
    void click_player2_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t3.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 2 Territory 3");
        }
    }

    @FXML
    void click_player3_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t1.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 3 Territory 1");
        }
    }

    @FXML
    void click_player3_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t2.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 3 Territory 2");
        }
    }

    @FXML
    void click_player3_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t3.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 3 Territory 3");
        }
    }
    
    @FXML 
    void click_player4_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t1.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 4 Territory 1");
        }
    }

    @FXML
    void click_player4_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t2.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 4 Territory 2");
        }
    }

    @FXML
    void click_player4_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t3.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 4 Territory 3");
        }
    }

    @FXML
    void click_player5_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t1.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 5 Territory 1");
        }
    }

    @FXML
    void click_player5_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t2.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 5 Territory 2");
        }
    }

    @FXML
    void click_player5_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t3.setOpacity(0.5);
            territory_info.setText("Territory Info:\nPlayer 5 Territory 3");
        }
    }

    @FXML
    void click_move(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoveDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        MoveDialogController moveController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void click_attack(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AttackDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 800);
        AttackDialogController attackController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void click_commit(ActionEvent event) {

    }

    public void setBoardGameMap(BoardGameMap boardGameMap) {
        this.boardGameMap = boardGameMap;
    }

    public BoardGameMap getBoardGameMap() {
        return boardGameMap;
    }

}
