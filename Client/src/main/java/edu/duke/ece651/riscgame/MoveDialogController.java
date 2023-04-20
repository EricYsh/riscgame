package edu.duke.ece651.riscgame;

import java.util.ArrayList;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Move;
import edu.duke.ece651.riscgame.rule.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MoveDialogController {

    private String sourceTerritory;
    private String targetTerritory;
    private String unitsIndex;

    public BoardGameMap gameMap;
    public int clientID;
    public NetClient netClient;

    public void setNetClient(NetClient netClient) {
        this.netClient = netClient;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setGameMap(BoardGameMap gameMap) {
        this.gameMap = gameMap;
    }

    @FXML
    private Button cancel_btn;

    @FXML
    private Button move_btn;

    @FXML
    private TextField source_territory;

    @FXML
    private TextField target_territory;

    @FXML
    private TextField units_index;

    public String getSourceTerritory() {
        return sourceTerritory;
    }

    public String getTargetTerritory() {
        return targetTerritory;
    }

    public String getUnitsIndex() {
        return unitsIndex;
    }   

    @FXML
    void click_move(ActionEvent event) {
        sourceTerritory = source_territory.getText();
        targetTerritory = target_territory.getText();
        unitsIndex = units_index.getText();

        boolean isValidInput = true;
        
        if (sourceTerritory == null || sourceTerritory.isEmpty()) {
            isValidInput = false;
        }
        if (targetTerritory == null || targetTerritory.isEmpty()) {
            isValidInput = false;
        }
        if (unitsIndex == null || unitsIndex.isEmpty()) {
            isValidInput = false;
        }
        
        if (isValidInput) {
            // for test
            System.out.println("sourceTerritory: " + sourceTerritory);
            System.out.println("targetTerritory: " + targetTerritory);
            System.out.println("unitsIndex: " + unitsIndex);
            String[] unitsIndexArray = unitsIndex.split(" ");
            ArrayList<Integer> unitsIndexList = new ArrayList<Integer>();
            for (String index : unitsIndexArray) {
                unitsIndexList.add(Integer.parseInt(index));
            }
            Territory src = gameMap.getTerritoryByName(sourceTerritory);
            Territory dest = gameMap.getTerritoryByName(targetTerritory);
            Move moveOrder = new Move(unitsIndexList.size(), src, dest, Type.Move, clientID, unitsIndexList, null);
            ActionInfo info = new ActionInfo(moveOrder);


            netClient.sendActionInfo(info);

        
            Stage stage = (Stage) move_btn.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
    }

    @FXML
    void click_cancel(ActionEvent event) {
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }
}
