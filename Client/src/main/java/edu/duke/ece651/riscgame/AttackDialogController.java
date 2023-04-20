package edu.duke.ece651.riscgame;

import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Attack;
import edu.duke.ece651.riscgame.order.Order;
import edu.duke.ece651.riscgame.rule.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AttackDialogController {

    private String sourceTerritory;
    private String targetTerritory;
    private String unitsIndex;
    
    public NetClient netClient;
    public BoardGameMap gameMap;
    public int clientID;

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
    private Button attack_btn;

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
    void click_attack(ActionEvent event) {
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

            Attack attackOrder = new Attack(unitsIndexList.size(), src, dest, Type.Attack, clientID, unitsIndexList, null);
            ActionInfo info = new ActionInfo(attackOrder);
            netClient.sendActionInfo(info);
            Stage stage = (Stage) attack_btn.getScene().getWindow();
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


