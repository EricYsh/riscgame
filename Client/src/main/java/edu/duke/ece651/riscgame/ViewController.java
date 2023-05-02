package edu.duke.ece651.riscgame;

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
            territory_info.setText(boardGameMap.getTerritoryByName("T1").getAllUnitsInfo() + 
            "\n Territory Name: T1" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T1").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T1").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T1").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player1_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t2.setOpacity(0.5);
            territory_info.setText(boardGameMap.getTerritoryByName("T2").getAllUnitsInfo()  + 
            "\n Territory Name: T2" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T2").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T2").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T2").getOwnId()).getTechLevel());
            
        }
    }

    @FXML
    void click_player1_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t3.setOpacity(0.5);
            territory_info.setText(boardGameMap.getTerritoryByName("T3").getAllUnitsInfo()  + 
            "\n Territory Name: T3" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T3").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T3").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T3").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player2_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t1.setOpacity(0.5);
            territory_info.setText(boardGameMap.getTerritoryByName("T4").getAllUnitsInfo()  + 
            "\n Territory Name: T4" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T4").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T4").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T4").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player2_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t2.setOpacity(0.5);
            territory_info.setText(boardGameMap.getTerritoryByName("T5").getAllUnitsInfo()  + 
            "\n Territory Name: T5" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T5").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T5").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T5").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player2_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t3.setOpacity(0.5);
            territory_info.setText(boardGameMap.getTerritoryByName("T6").getAllUnitsInfo()  + 
            "\n Territory Name: T6" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T6").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T6").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T6").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player3_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t1.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 3 Territory 1");
            territory_info.setText(boardGameMap.getTerritoryByName("T7").getAllUnitsInfo()  + 
            "\n Territory Name: T7" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T7").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T7").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T7").getOwnId()).getTechLevel());

        }
    }

    @FXML
    void click_player3_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t2.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 3 Territory 2");
            territory_info.setText(boardGameMap.getTerritoryByName("T8").getAllUnitsInfo()  + 
            "\n Territory Name: T8" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T8").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T8").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T8").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player3_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t3.setOpacity(0.5);
            territory_info.setText(boardGameMap.getTerritoryByName("T9").getAllUnitsInfo()  +
            "\n Territory Name: T9" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T9").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T9").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T9").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player4_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t1.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 4 Territory 1");
            territory_info.setText(boardGameMap.getTerritoryByName("T10").getAllUnitsInfo()  +
            "\n Territory Name: T10" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T10").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T10").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T10").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player4_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t2.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 4 Territory 2");
            territory_info.setText(boardGameMap.getTerritoryByName("T11").getAllUnitsInfo()  +
            "\n Territory Name: T11" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T11").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T11").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T11").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player4_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t3.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 4 Territory 3");
            territory_info.setText(boardGameMap.getTerritoryByName("T12").getAllUnitsInfo()  +
            "\n Territory Name: T12" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T12").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T12").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T12").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player5_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t1.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 5 Territory 1");
            territory_info.setText(boardGameMap.getTerritoryByName("T13").getAllUnitsInfo()  +
            "\n Territory Name: T13" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T13").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T13").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T13").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player5_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t2.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 5 Territory 2");
            territory_info.setText(boardGameMap.getTerritoryByName("T14").getAllUnitsInfo()  +
            "\n Territory Name: T14" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T14").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T14").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T14").getOwnId()).getTechLevel());
        }
    }

    @FXML
    void click_player5_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t3.setOpacity(0.5);
            // territory_info.setText("Territory Info:\nPlayer 5 Territory 3");
            territory_info.setText(boardGameMap.getTerritoryByName("T15").getAllUnitsInfo()  +
            "\n Territory Name: T15" +
            "\n The Food Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T15").getOwnId()).getFoodResource()
            + "\n The Technology Resources:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T15").getOwnId()).getTechResource()
            + "\n The Technology Level:" + boardGameMap.getPlayerById(boardGameMap.getTerritoryByName("T15").getOwnId()).getTechLevel());
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
    public void setTerritoryToWhite(int ClientID){
        if(ClientID == 1){
            player1_t1.setFill(Color.WHITE);
            player1_t1.setOnMouseClicked(event -> {event.consume();});
            player1_t2.setFill(Color.WHITE);
            player1_t2.setOnMouseClicked(event -> {event.consume();});
            player1_t3.setFill(Color.WHITE);
            player1_t3.setOnMouseClicked(event -> {event.consume();});
        }
        else if(ClientID == 2){
            player2_t1.setFill(Color.WHITE);
            player2_t1.setOnMouseClicked(event -> {event.consume();});
            player2_t2.setFill(Color.WHITE);
            player2_t2.setOnMouseClicked(event -> {event.consume();});
            player2_t3.setFill(Color.WHITE);
            player2_t3.setOnMouseClicked(event -> {event.consume();});
        }
        else if(ClientID == 3){
            player3_t1.setFill(Color.WHITE);
            player3_t1.setOnMouseClicked(event -> {event.consume();});
            player3_t2.setFill(Color.WHITE);
            player3_t2.setOnMouseClicked(event -> {event.consume();});
            player3_t3.setFill(Color.WHITE);
            player3_t3.setOnMouseClicked(event -> {event.consume();});
        }
        else if(ClientID == 4){
            player4_t1.setFill(Color.WHITE);
            player4_t1.setOnMouseClicked(event -> {event.consume();});
            player4_t2.setFill(Color.WHITE);
            player4_t2.setOnMouseClicked(event -> {event.consume();});
            player4_t3.setFill(Color.WHITE);
            player4_t3.setOnMouseClicked(event -> {event.consume();});
        }
        else if(ClientID == 5){
            player5_t1.setFill(Color.WHITE);
            player5_t1.setOnMouseClicked(event -> {event.consume();});
            player5_t2.setFill(Color.WHITE);
            player5_t2.setOnMouseClicked(event -> {event.consume();});
            player5_t3.setFill(Color.WHITE);
            player5_t3.setOnMouseClicked(event -> {event.consume();});
        }
    } 


}
