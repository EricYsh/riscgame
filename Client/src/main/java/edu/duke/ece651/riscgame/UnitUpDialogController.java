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

import static edu.duke.ece651.riscgame.MoveDialogController.validateInputUnitIndex;

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

        checkSourceName(territoryIn);
        if (!checkUnitIndex(territoryIn, unitsIndex)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Invalid Unit Index");
            alert.showAndWait();
        } else {
            ArrayList<Integer> units = getUnits();
            assert units != null;
            if (!checkUnitLevel(units.size())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Invalid Unit Level");
                alert.showAndWait();
            }
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
            UpgradeUnit upgradeOrder = new UpgradeUnit(unitsIndexList.size(), src, null, Type.UpgradeUnit, clientID, unitsIndexList, unitsLevelToList);
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

    private ArrayList<Integer> getUnits() {
        int maxNum = gameMap.getTerritoryByName(territoryIn).getUnits().size();

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

    private boolean checkUnitLevel(int length) {

        ArrayList<Integer> levels;
        System.out.print("Player " + clientID + ": Please enter " + length + " numbers between 0-6 separated by spaces: ");
        levels = new ArrayList<>();
        return validateInputLevel(levelTo, length, levels);

    }

    private boolean validateInputLevel(String input, int length, ArrayList<Integer> numbers) {
        String[] tokens = input.split(" ");
        if (tokens.length != length) {
            return false;
        }

        for (String token : tokens) {
            try {
                int number = Integer.parseInt(token);
                if (number < 1 || number > 6) {
                    return false;
                } else {
                    numbers.add(number);
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }


    @FXML
    void click_cancel(ActionEvent event) {
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }

}
