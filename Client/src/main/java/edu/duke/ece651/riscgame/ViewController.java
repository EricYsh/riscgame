package edu.duke.ece651.riscgame;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Commit;
import edu.duke.ece651.riscgame.order.LogOut;
import edu.duke.ece651.riscgame.order.Switch;
import edu.duke.ece651.riscgame.order.UpgradeTech;
import edu.duke.ece651.riscgame.rule.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable{


    


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
    private Button upgrade_unit_btn;
    
    @FXML
    private Button commit_btn;




    // <Button fx:id="attack_btn" layoutX="790.0" layoutY="385.0" mnemonicParsing="false" onAction="#click_attack" prefHeight="50.0" prefWidth="150.0" text="Attack"/>
    // <Button fx:id="move_btn" layoutX="790.0" layoutY="146.0" mnemonicParsing="false" onAction="#click_move" prefHeight="50.0" prefWidth="150.0" text="Move" />
    // <Button fx:id="upgrade_unit_btn" layoutX="991.0" layoutY="146.0" mnemonicParsing="false" onAction="#click_up_unit" prefHeight="50.0" prefWidth="150.0" text="Unit Up" />
    // <Button fx:id="commit_btn" layoutX="986.0" layoutY="470.0" mnemonicParsing="false" onAction="#click_commit" prefHeight="50.0" prefWidth="150.0" text="Commit" />

    // <Button fx:id="upgrade_tech_btn" layoutX="986.0" layoutY="387.0" mnemonicParsing="false" onAction="#click_up_tech" prefHeight="50.0" prefWidth="150.0" text="Tech Up" />
    // <Button fx:id="switch_btn" layoutX="30.0" layoutY="15.0" mnemonicParsing="false" onAction="#click_switch" prefHeight="30.0" prefWidth="100.0" text="Switch" />
    // <Button fx:id="quit_btn" layoutX="150.0" layoutY="15.0" mnemonicParsing="false" onAction="#click_quit" prefHeight="30.0" prefWidth="100.0" text="Quit" />
    // <Button fx:id="cloak_btn" layoutX="790.0" layoutY="470.0" mnemonicParsing="false" onAction="#click_cloak" prefHeight="50.0" prefWidth="150.0" text="Cloak" />
    // <Button fx:id="spy_move_btn" layoutX="790.0" layoutY="233.0" mnemonicParsing="false" onAction="#click_spy_move" prefHeight="50.0" prefWidth="150.0" text="Spy Move" />
    // <Button fx:id="upgrade_spy_unit_btn" layoutX="990.0" layoutY="233.0" mnemonicParsing="false" onAction="#click_spy_up_unit" prefHeight="50.0" prefWidth="150.0" text="Spy Up" />

    @FXML
    private Button upgrade_tech_btn;

    @FXML
    private Button switch_btn;

    @FXML
    private Button quit_btn;

    @FXML
    private Button cloak_btn;

    @FXML
    private Button spy_move_btn;

    @FXML
    private Button upgrade_spy_unit_btn;

    public void initialize(URL location, ResourceBundle resources) {
        setButtonWithHoverEffect(attack_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(move_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(commit_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(upgrade_unit_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(upgrade_tech_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(switch_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(quit_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(cloak_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(spy_move_btn, "/images/button.png", "/images/hover_button.png");
        setButtonWithHoverEffect(upgrade_spy_unit_btn, "/images/button.png", "/images/hover_button.png");

        // ... (repeat for all buttons)

        Media audioFile = new Media(getClass().getResource("/audio/bgm.mp3").toExternalForm());

        // Create a new MediaPlayer with the audio file
        MediaPlayer mediaPlayer = new MediaPlayer(audioFile);

        // Set the MediaPlayer to loop indefinitely
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Start playing the audio
        mediaPlayer.play();
    }

    private void setButtonWithHoverEffect(Button button, String defaultImagePath, String hoverImagePath) {
        Image defaultImage = new Image(getClass().getResourceAsStream(defaultImagePath));
        Image hoverImage = new Image(getClass().getResourceAsStream(hoverImagePath));
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage defaultBackground = new BackgroundImage(defaultImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        BackgroundImage hoverBackground = new BackgroundImage(hoverImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);

        button.setBackground(new Background(defaultBackground));

        button.setOnMouseEntered(e -> button.setBackground(new Background(hoverBackground)));
        button.setOnMouseExited(e -> button.setBackground(new Background(defaultBackground)));
    }

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
                    + "\n" + boardGameMap.getTerritoryByName(territoryName).getAllUnitsInfo(clientID);

        } else { // otherwise display info by fog
            return "Territory Name: " + territoryName +
                    "\nFood Resource increase:" + boardGameMap.getTerritoryByName(territoryName).getFoodResource()
                    + "\nTechnology Resource increase:" + boardGameMap.getTerritoryByName(territoryName).getTechResource()
                    + "\n" + boardGameMap.getTerritoryByName(territoryName).getFogInfo(clientID);
        }
    }

    @FXML
    void click_player1_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t1.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T1"));
            game_info_text.setText(getTextToSet("T1"));
        }
    }

    @FXML
    void click_player1_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t2.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T2"));
            game_info_text.setText(getTextToSet("T2"));
        }
    }

    @FXML
    void click_player1_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player1_t3.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T3"));
            game_info_text.setText(getTextToSet("T3"));
        }
    }

    @FXML
    void click_player2_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t1.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T4"));
            game_info_text.setText(getTextToSet("T4"));
        }
    }

    @FXML
    void click_player2_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t2.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T5"));
            game_info_text.setText(getTextToSet("T5"));
        }
    }

    @FXML
    void click_player2_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player2_t3.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T6"));
            game_info_text.setText(getTextToSet("T6"));
        }
    }

    @FXML
    void click_player3_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t1.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T7"));
            game_info_text.setText(getTextToSet("T7"));
        }
    }

    @FXML
    void click_player3_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t2.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T8"));
            game_info_text.setText(getTextToSet("T8"));
        }
    }

    @FXML
    void click_player3_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player3_t3.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T9"));
            game_info_text.setText(getTextToSet("T9"));
        }
    }

    @FXML
    void click_player4_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t1.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T10"));
            game_info_text.setText(getTextToSet("T10"));
        }
    }

    @FXML
    void click_player4_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t2.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T11"));
            game_info_text.setText(getTextToSet("T11"));
        }
    }

    @FXML
    void click_player4_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player4_t3.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T12"));
            game_info_text.setText(getTextToSet("T12"));
        }
    }

    @FXML
    void click_player5_t1(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t1.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T13"));
            game_info_text.setText(getTextToSet("T13"));
        }
    }

    @FXML
    void click_player5_t2(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t2.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T14"));
            game_info_text.setText(getTextToSet("T14"));
        }
    }

    @FXML
    void click_player5_t3(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            resetTerritoryOpacity();
            player5_t3.setOpacity(0.5);
            // territory_info.setText(getTextToSet("T15"));
            game_info_text.setText(getTextToSet("T15"));
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
        if (!isTechUpgrade) {
            UpgradeTech techUpgradeOrder = new UpgradeTech(0, null, null, Type.UpgradeTech, clientID, null, null);
            ActionInfo info = new ActionInfo(techUpgradeOrder);
            netClient.sendActionInfo(info);
            techUpgradeOrder.run(boardGameMap);
            setTechUpgrade(true);
        }
        // setBoardGameMap(netClient.receiveGameMap());
    }

    @FXML
    void click_commit(ActionEvent event) {
        Commit commitOrder = new Commit(0, null, null, Type.Commit, clientID, null, null);
        ActionInfo info = new ActionInfo(commitOrder);
        netClient.sendActionInfo(info);
        setCommit(true);
        setTechUpgrade(false);

        netClient.receiveValidationResult();
        setBoardGameMap(netClient.receiveGameMap());
        System.out.println("player " + clientID + " : " + boardGameMap.isLose(clientID));
        if (boardGameMap.isLose(clientID)) {// return true when the player lost
            //TODO: jump to lose dialog and shut down this page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoseDialog.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 300);
                LoseDialogController loseController = loader.getController();
                // close lost player window
//                Stage currentStage = (Stage) commit_btn.getScene().getWindow();
//                currentStage.close();

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Lose");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (boardGameMap.isAllTerritoryOccupiedByOne()) { //
            System.out.println("one player wins");
            // TODO: give one info to indicate winner and shut down this page
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/WinDialog.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 300);
                WinDialogController winController = loader.getController();
                // close lost player window
//                Stage currentStage = (Stage) commit_btn.getScene().getWindow();
//                currentStage.close();

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Win");
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(boardGameMap.getTerritoryNameAndUnitNums());
        System.out.println(boardGameMap.getTerritoryNameAndOwnership());
        refreshMap();
    }


    @FXML
    void click_switch(ActionEvent event) throws Exception{
        Switch LogOutOrder = new Switch(0, null, null, Type.Switch, clientID, null, null);
        ActionInfo info = new ActionInfo(LogOutOrder);
        netClient.sendActionInfo(info);
        netClient.receiveValidationResult();

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
    void click_cloak(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CloakDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        CloakDialogController cloakController = loader.getController();
        cloakController.setGameMap(boardGameMap);
        cloakController.setClientID(clientID);
        cloakController.setNetClient(netClient);
        cloakController.setGameMap(boardGameMap);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Unit Up");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void click_spy_move(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpyMoveDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        SpyMoveDialogController spyMoveController = loader.getController();
        spyMoveController.setGameMap(boardGameMap);
        spyMoveController.setClientID(clientID);
        spyMoveController.setNetClient(netClient);
        spyMoveController.setGameMap(boardGameMap);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Move SPY");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void click_spy_up_unit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SpyUpDialog.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        SpyUpDialogController unitSpyUpController = loader.getController();
        unitSpyUpController.setGameMap(boardGameMap);
        unitSpyUpController.setClientID(clientID);
        unitSpyUpController.setNetClient(netClient);
        unitSpyUpController.setGameMap(boardGameMap);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Spy Unit Up");
        stage.setResizable(false);
        stage.show();
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
        switch (territoryName) {
            case "T1":
                setColor(clientID, player1_t1);
                break;
            case "T2":
                setColor(clientID, player1_t2);
                break;
            case "T3":
                setColor(clientID, player1_t3);
                break;
            case "T4":
                setColor(clientID, player2_t1);
                break;
            case "T5":
                setColor(clientID, player2_t2);
                break;
            case "T6":
                setColor(clientID, player2_t3);
                break;
            case "T7":
                setColor(clientID, player3_t1);
                break;
            case "T8":
                setColor(clientID, player3_t2);
                break;
            case "T9":
                setColor(clientID, player3_t3);
                break;
            case "T10":
                setColor(clientID, player4_t1);
                break;
            case "T11":
                setColor(clientID, player4_t2);
                break;
            case "T12":
                setColor(clientID, player4_t3);
                break;
            case "T13":
                setColor(clientID, player5_t1);
                break;
            case "T14":
                setColor(clientID, player5_t2);
                break;
            case "T15":
                setColor(clientID, player5_t3);
                break;
        }
    }

    private void setColor(int clientID, Polygon player1_t1) {
        switch (clientID + 1) {
            case 1:
                player1_t1.setFill(Color.web("#f44336"));
                break;
            case 2:
                player1_t1.setFill(Color.web("#4caf50"));
                break;
            case 3:
                player1_t1.setFill(Color.web("#2196f3"));
                break;
            case 4:
                player1_t1.setFill(Color.web("#ffeb3b"));
                break;
            case 5:
                player1_t1.setFill(Color.web("#9c27b0"));
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
