package edu.duke.ece651.riscgame;
import java.util.ArrayList;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.Move;
import edu.duke.ece651.riscgame.order.SpyMove;
import edu.duke.ece651.riscgame.rule.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static edu.duke.ece651.riscgame.MoveDialogController.validateInputUnitIndex;

public class SpyMoveDialogController {
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
        checkTerritoryName();
        if (!checkUnitIndex(sourceTerritory, unitsIndex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Invalid Unit Index");
            alert.showAndWait();
        } else {
            ArrayList<Integer> num = getUnits();
            if (num.size() != 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You can only move One Spy unit each time");
                alert.showAndWait();
            }
        }
        if (isValidInput) {
            // for test
            System.out.println("sourceTerritory: " + sourceTerritory);
            System.out.println("targetTerritory: " + targetTerritory);
            System.out.println("unitsIndex: " + unitsIndex);
            ArrayList<Integer> unitsIndexList = new ArrayList<Integer>();
            unitsIndexList.add(Integer.parseInt(unitsIndex));
            Territory src = gameMap.getTerritoryByName(sourceTerritory);
            Territory dest = gameMap.getTerritoryByName(targetTerritory);
            // issue spy move order
            SpyMove moveOrder = new SpyMove(1, src, dest, Type.SpyMove, clientID, unitsIndexList, null);
            ActionInfo info = new ActionInfo(moveOrder);
            netClient.sendActionInfo(info);
            moveOrder.run(gameMap);
            
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

    //src must be the clientID's own territory && valid dest

    private void  checkTerritoryName() {
        if (gameMap.getTerritoryByName(sourceTerritory) != null && gameMap.getTerritoryByName(targetTerritory) != null) {
            if (gameMap.getTerritoryByName(sourceTerritory).getOwnId() != clientID) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You don't own " + sourceTerritory);
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter a valid territory name");
            alert.showAndWait();
        }
    }

    //Only one valid index
    private ArrayList<Integer> getUnits() {
        int maxNum = gameMap.getTerritoryByName(sourceTerritory).getUnits().size();

        ArrayList<Integer> numbers;
//            System.out.print("Please enter numbers with max length: " + territoryUnitMaxNum);
        numbers = new ArrayList<>();
        if (validateInputUnitIndex(unitsIndex, numbers, maxNum)) {
            return numbers;
        }
        return null;
    }

    private boolean checkUnitIndex(String srcName, String unitsIndex) {
        int maxNum = gameMap.getTerritoryByName(srcName).getUnits().size();
        ArrayList<Integer> numbers  = new ArrayList<>();
        //    System.out.print("Please enter numbers with max length: " + maxNum);
        return validateInputUnitIndex(unitsIndex, numbers, maxNum);
    }



    @FXML
    void click_cancel(ActionEvent event) {
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }
}
