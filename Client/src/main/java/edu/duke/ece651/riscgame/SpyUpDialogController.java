package edu.duke.ece651.riscgame;

import java.util.ArrayList;

import edu.duke.ece651.riscgame.commuMedium.ActionInfo;
import edu.duke.ece651.riscgame.game.BoardGameMap;
import edu.duke.ece651.riscgame.game.Territory;
import edu.duke.ece651.riscgame.order.UpgradeSpy;
import edu.duke.ece651.riscgame.order.UpgradeUnit;
import edu.duke.ece651.riscgame.rule.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import static edu.duke.ece651.riscgame.MoveDialogController.validateInputUnitIndex;

public class SpyUpDialogController {
    private String territoryIn;
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

    public String getTerritoryIn() {
        return territoryIn;
    }

    public String getUnitsIndex() {
        return unitsIndex;
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
    void click_up_unit(ActionEvent event) {
        territoryIn = territory_in.getText();
        unitsIndex = units_index.getText();

        boolean isValidInput = true;
        
        if (territoryIn == null || territoryIn.isEmpty()) {
            isValidInput = false;
        }
        if (unitsIndex == null || unitsIndex.isEmpty()) {
            isValidInput = false;
        }
        checkSourceName(territoryIn);
        if (!checkUnitIndex(territoryIn, unitsIndex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Invalid Unit Index");
            alert.showAndWait();
        }
        checkResource();
        if (isValidInput) {
            // for test
            System.out.println("territoryIn: " + territoryIn);
            System.out.println("unitsIndex: " + unitsIndex);

            String[] unitsIndexArray = unitsIndex.split(" ");
            ArrayList<Integer> unitsIndexList = new ArrayList<Integer>();
            for (String index : unitsIndexArray) {
                unitsIndexList.add(Integer.parseInt(index));
            }

            Territory src = gameMap.getTerritoryByName(territoryIn);

            // issue upgrade spy order
             UpgradeSpy upgradeOrder = new UpgradeSpy(unitsIndexList.size(), src, null, Type.UpgradeSpy, clientID, unitsIndexList, null);
             ActionInfo info = new ActionInfo(upgradeOrder);
             netClient.sendActionInfo(info);
             upgradeOrder.run(gameMap);
            
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

//Src must be valid and it is the player's territory
    private void checkSourceName(String srcName) {
        if (gameMap.getTerritoryByName(srcName) != null) {
            if (gameMap.getTerritoryByName(srcName).getOwnId() != clientID) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You don't own " + srcName);
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
//Unit index must be valid
    private boolean checkUnitIndex(String srcName, String unitsIndex) {
        int maxNum = gameMap.getTerritoryByName(srcName).getUnits().size();
        ArrayList<Integer> numbers  = new ArrayList<>();
        //    System.out.print("Please enter numbers with max length: " + maxNum);
        return validateInputUnitIndex(unitsIndex, numbers, maxNum);
    }

    public void checkResource() {
        int resource = gameMap.getPlayerById(clientID).getTechResource();
        if (resource < 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Resource");
            alert.setContentText("Your technology resource is not enough");
            alert.showAndWait();
        }
    }

    @FXML
    void click_cancel(ActionEvent event) {
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }

}
