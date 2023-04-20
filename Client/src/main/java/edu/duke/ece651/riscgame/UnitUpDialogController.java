package edu.duke.ece651.riscgame;

import java.util.ArrayList;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.UpgradeUnit;
import edu.duke.ece651.riscgame.rule.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UnitUpDialogController {

    private String territoryIn;
    private String unitsIndex;
    private String levelTo;

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

    public String getTerritoryIn() {
        return territoryIn;
    }

    public String getUnitsIndex() {
        return unitsIndex;
    }

    public String getLevelTo() {
        return levelTo;
    }

    @FXML
    private Button cancel_btn;

    @FXML
    private Button up_unit_btn;

    @FXML
    private TextField territory_in;

    @FXML
    private TextField units_index;

    @FXML
    private TextField level_to;  

    @FXML
    void click_up_unit(ActionEvent event) {
        territoryIn = territory_in.getText();
        unitsIndex = units_index.getText();
        levelTo = level_to.getText();

        boolean isValidInput = true;
        
        if (territoryIn == null || territoryIn.isEmpty()) {
            isValidInput = false;
        }
        if (unitsIndex == null || unitsIndex.isEmpty()) {
            isValidInput = false;
        }
        if (levelTo == null || levelTo.isEmpty()) {
            isValidInput = false;
        }
        
        if (isValidInput) {
            // for test
            System.out.println("territoryIn: " + territoryIn);
            System.out.println("unitsIndex: " + unitsIndex);
            System.out.println("levelTo: " + levelTo);
            String[] unitsIndexArray = unitsIndex.split(" ");
            ArrayList<Integer> unitsIndexList = new ArrayList<Integer>();
            for (String index : unitsIndexArray) {
                unitsIndexList.add(Integer.parseInt(index));
            }
            String[] unitsLevelTo = levelTo.split(" ");
            ArrayList<Integer> unitsLevelToList = new ArrayList<Integer>();
            for (String level : unitsLevelTo) {
                unitsLevelToList.add(Integer.parseInt(level));
            }
            Territory src = gameMap.getTerritoryByName(territoryIn);
            UpgradeUnit moveOrder = new UpgradeUnit(unitsIndexList.size(), src, null, Type.UpgradeUnit, clientID, unitsIndexList, unitsLevelToList);
            ActionInfo info = new ActionInfo(moveOrder);
            netClient.sendActionInfo(info);
        
            Stage stage = (Stage) up_unit_btn.getScene().getWindow();
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
