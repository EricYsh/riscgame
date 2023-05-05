package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.order.LogOut;
import edu.duke.ece651.riscgame.order.Switch;
import org.checkerframework.checker.fenum.qual.Fenum;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Commit;
import edu.duke.ece651.riscgame.order.UpgradeTech;
import edu.duke.ece651.riscgame.rule.Type;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class ViewController {

    public BoardGameMap boardGameMap;

    public NetClient netClient;

    public int clientID;

    public void setClientId(int clientID) {
        this.clientID = clientID;
    }

    public void setNetClient(NetClient netClient) {
        this.netClient = netClient;
    }


    public void setBoardGameMap(BoardGameMap boardGameMap) {
        this.boardGameMap = boardGameMap;
    }

    public BoardGameMap getBoardGameMap() {
        return boardGameMap;
    }

    @FXML
    private Label player_info;

    public void printPlayerInfo(String playerName) {
        player_info.setText(playerName);
    }

    private boolean isTechUpgrade = false;

    public void setTechUpgrade(boolean techUpgrade) {
        isTechUpgrade = techUpgrade;
    }

    public boolean isTechUpgrade() {
        return isTechUpgrade;
    }

    private boolean isCommit = false;

    public void setCommit(boolean commit) {
        isCommit = commit;
    }

    public boolean isCommit() {
        return isCommit;
    }

    @FXML
    private Text game_info_text;

    @FXML
    private Button move_btn;

    @FXML
    private Button attack_btn;

    @FXML
    private Button upgrade_btn;

    @FXML
    private Button commit_btn;

    @FXML
    private Button switch_btn;

    @FXML
    private Button quit_btn;

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

    String getTextToSet(String territoryName) {
        // players own territores are visible to them, which is initial state as below
        if (boardGameMap.getTerritoryByName(territoryName).getOwnId() == clientID) {
            return "Territory Name: " + territoryName +
                    "\nYour Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName(territoryName).getOwnId()).getFoodResource()
                    + "\nYour Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName(territoryName).getOwnId()).getTechResource()
                    + "\nYour Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName(territoryName).getOwnId()).getTechLevel()
                    + "\n\n" + boardGameMap.getTerritoryByName(territoryName).getAllUnitsInfo();
        } else { // otherwise display info by fog
            return "Territory Name: " + territoryName +
                    "\nFood Resource increase:" + boardGameMap.getTerritoryByName(territoryName).getFoodResource()
                    + "\nTechnology Resource increase:" + boardGameMap.getTerritoryByName(territoryName).getTechResource()
                    + "\n" + boardGameMap.getTerritoryByName(territoryName).getFogInfo();
        }
    }

    @FXML
    void click_player1_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t1.setOpacity(0.5);
            territory_info.setText(getTextToSet("T1"));
        }
    }

    @FXML
    void click_player1_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t2.setOpacity(0.5);
            territory_info.setText(getTextToSet("T2"));
        }
    }

    @FXML
    void click_player1_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t3.setOpacity(0.5);
            territory_info.setText(getTextToSet("T3"));
        }
    }

    @FXML
    void click_player2_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t1.setOpacity(0.5);
            territory_info.setText(getTextToSet("T4"));
        }
    }

    @FXML
    void click_player2_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t2.setOpacity(0.5);
            territory_info.setText(getTextToSet("T5"));
        }
    }

    @FXML
    void click_player2_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t3.setOpacity(0.5);
            territory_info.setText(getTextToSet("T6"));
        }
    }

    @FXML
    void click_player3_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t1.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 3 Territory 1");
            territory_info.setText(getTextToSet("T7"));
        }
    }

    @FXML
    void click_player3_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t2.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 3 Territory 2");
            territory_info.setText(getTextToSet("T8"));
        }
    }

    @FXML
    void click_player3_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t3.setOpacity(0.5);
            territory_info.setText(getTextToSet("T9"));
        }
    }

    @FXML
    void click_player4_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t1.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 4 Territory 1");
            territory_info.setText(getTextToSet("T10"));
        }
    }

    @FXML
    void click_player4_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t2.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 4 Territory 2");
            territory_info.setText(getTextToSet("T11"));
        }
    }

    @FXML
    void click_player4_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t3.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 4 Territory 3");
            territory_info.setText(getTextToSet("T12"));
        }
    }

    @FXML
    void click_player5_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t1.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 5 Territory 1");
            territory_info.setText(getTextToSet("T13"));
        }
    }

    @FXML
    void click_player5_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t2.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 5 Territory 2");
            territory_info.setText(getTextToSet("T14"));
        }
    }

    @FXML
    void click_player5_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t3.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 5 Territory 3");
            territory_info.setText(getTextToSet("T15"));
        }
    }

    @FXML
    void click_move(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MoveDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        MoveDialogController moveController = loader.getController();
        moveController.setGameMap(boardGameMap);
        moveController.setClientID(clientID);
        moveController.setNetClient(netClient);
        moveController.setGameMap(boardGameMap);


        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Move");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void click_attack(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AttackDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        AttackDialogController attackController = loader.getController();
        attackController.setGameMap(boardGameMap);
        attackController.setClientID(clientID);
        attackController.setNetClient(netClient);
        attackController.setGameMap(boardGameMap);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Attack");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void click_up_unit(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UnitUpDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        UnitUpDialogController unitUpController = loader.getController();
        unitUpController.setGameMap(boardGameMap);
        unitUpController.setClientID(clientID);
        unitUpController.setNetClient(netClient);
        unitUpController.setGameMap(boardGameMap);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Unit Up");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void click_up_tech(ActionEvent event) {
        UpgradeTech techUpgradeOrder = new UpgradeTech(0, null, null, Type.UpgradeTech, clientID, null, null);
        ActionInfo info = new ActionInfo(techUpgradeOrder);
        netClient.sendActionInfo(info);
        techUpgradeOrder.run(boardGameMap);
        setTechUpgrade(true);
        // setBoardGameMap(netClient.receiveGameMap());
    }

    @FXML
    void click_commit(ActionEvent event) {
        Commit commitOrder = new Commit(0, null, null, Type.Commit, clientID, null, null);
        ActionInfo info = new ActionInfo(commitOrder);
        netClient.sendActionInfo(info);
        setCommit(true);

        netClient.receiveValidationResult();
        setBoardGameMap(netClient.receiveGameMap());
        System.out.println(boardGameMap.getTerritoryNameAndUnitNums());
        System.out.println(boardGameMap.getTerritoryNameAndOwnership());
        refreshMap();
    }


    @FXML
    void click_switch(ActionEvent event) throws Exception{
//        Switch LogOutOrder = new Switch(0, null, null, Type.Switch, clientID, null, null);
//        ActionInfo info = new ActionInfo(LogOutOrder);
//        netClient.sendActionInfo(info);
//        netClient.receiveValidationResult();

        //close the current window
        Stage currentStage = (Stage) switch_btn.getScene().getWindow();
        currentStage.close();

        // jump to room login window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Room.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 300);
        RoomController roomController = loader.getController();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Room");
        stage.show();
    }

    @FXML
    /*
     *This method is used to quit the game
     */
    void click_quit(ActionEvent event){
        LogOut LogOutOrder = new LogOut(0, null, null, Type.LogOut, clientID, null, null);
        ActionInfo info = new ActionInfo(LogOutOrder);
        netClient.sendActionInfo(info);
        netClient.receiveValidationResult();
        Stage stage = (Stage) quit_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void click_cloak(ActionEvent event) {

    }

    @FXML
    void click_spy_move(ActionEvent event) {

    }

    @FXML
    void click_spy_up_unit(ActionEvent event) {

    }

    @FXML
    private ScrollPane infoScrollPane;


    public void initializeScrollPane() {
        infoScrollPane.setContent(game_info_text);
    }

    public void printGameInfo(String message) {
        String currText = game_info_text.getText();
        game_info_text.setText(currText + message);
    }

    @FXML
    private AnchorPane infoAnchorPane;

    /*
     * This method is called by the FXMLLoader when initialization is complete
     *
     */
    public void displayInfo(String infoText) {
        Text infoTextNode = new Text(infoText);
        infoAnchorPane.getChildren().add(infoTextNode);
    }

    public void refreshMap() {
        //refresh the map
        for (int i = 0; i < boardGameMap.getTerritories().size(); i++) {
            Territory territory = boardGameMap.getTerritories().get(i);
            changeTerritoryColor(territory.getOwnId(), territory.getName());
        }
    }

    //change the color of the territory, according to the winner name
    public void changeTerritoryColor(int clientID, String territoryName) {
        switch (clientID) {
            case 1:
                switch (territoryName) {
                    case "Player 1 Territory 1":
                        player1_t1.setFill(Color.RED);
                        break;
                    case "Player 1 Territory 2":
                        player1_t2.setFill(Color.RED);
                        break;
                    case "Player 1 Territory 3":
                        player1_t3.setFill(Color.RED);
                        break;
                }
                break;
            case 2:
                switch (territoryName) {
                    case "Player 2 Territory 1":
                        player2_t1.setFill(Color.BLUE);
                        break;
                    case "Player 2 Territory 2":
                        player2_t2.setFill(Color.BLUE);
                        break;
                    case "Player 2 Territory 3":
                        player2_t3.setFill(Color.BLUE);
                        break;
                }
                break;
            case 3:
                switch (territoryName) {
                    case "Player 3 Territory 1":
                        player3_t1.setFill(Color.GREEN);
                        break;
                    case "Player 3 Territory 2":
                        player3_t2.setFill(Color.GREEN);
                        break;
                    case "Player 3 Territory 3":
                        player3_t3.setFill(Color.GREEN);
                        break;
                }
                break;
            case 4:
                switch (territoryName) {
                    case "Player 4 Territory 1":
                        player4_t1.setFill(Color.YELLOW);
                        break;
                    case "Player 4 Territory 2":
                        player4_t2.setFill(Color.YELLOW);
                        break;
                    case "Player 4 Territory 3":
                        player4_t3.setFill(Color.YELLOW);
                        break;
                }
                break;
            case 5:
                switch (territoryName) {
                    case "Player 5 Territory 1":
                        player5_t1.setFill(Color.PURPLE);
                        break;
                    case "Player 5 Territory 2":
                        player5_t2.setFill(Color.PURPLE);
                        break;
                    case "Player 5 Territory 3":
                        player5_t3.setFill(Color.PURPLE);
                        break;
                }
                break;
        }
    }

    //this function is used to set the polyon to white in order to ignore it
    public void setTerritoryToWhite(int ClientID) {
        if (ClientID == 1) {
            player1_t1.setFill(Color.WHITE);
            player1_t1.setOnMouseClicked(event -> {
                event.consume();
            });
            player1_t2.setFill(Color.WHITE);
            player1_t2.setOnMouseClicked(event -> {
                event.consume();
            });
            player1_t3.setFill(Color.WHITE);
            player1_t3.setOnMouseClicked(event -> {
                event.consume();
            });
        } else if (ClientID == 2) {
            player2_t1.setFill(Color.WHITE);
            player2_t1.setOnMouseClicked(event -> {
                event.consume();
            });
            player2_t2.setFill(Color.WHITE);
            player2_t2.setOnMouseClicked(event -> {
                event.consume();
            });
            player2_t3.setFill(Color.WHITE);
            player2_t3.setOnMouseClicked(event -> {
                event.consume();
            });
        } else if (ClientID == 3) {
            player3_t1.setFill(Color.WHITE);
            player3_t1.setOnMouseClicked(event -> {
                event.consume();
            });
            player3_t2.setFill(Color.WHITE);
            player3_t2.setOnMouseClicked(event -> {
                event.consume();
            });
            player3_t3.setFill(Color.WHITE);
            player3_t3.setOnMouseClicked(event -> {
                event.consume();
            });
        } else if (ClientID == 4) {
            player4_t1.setFill(Color.WHITE);
            player4_t1.setOnMouseClicked(event -> {
                event.consume();
            });
            player4_t2.setFill(Color.WHITE);
            player4_t2.setOnMouseClicked(event -> {
                event.consume();
            });
            player4_t3.setFill(Color.WHITE);
            player4_t3.setOnMouseClicked(event -> {
                event.consume();
            });
        } else if (ClientID == 5) {
            player5_t1.setFill(Color.WHITE);
            player5_t1.setOnMouseClicked(event -> {
                event.consume();
            });
            player5_t2.setFill(Color.WHITE);
            player5_t2.setOnMouseClicked(event -> {
                event.consume();
            });
            player5_t3.setFill(Color.WHITE);
            player5_t3.setOnMouseClicked(event -> {
                event.consume();
            });
        }
    }


}
