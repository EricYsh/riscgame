package edu.duke.ece651.riscgame;

import java.util.ArrayList;

import edu.duke.ece651.riscgame.game.BoardGameMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CloakDialogController {
    private String territoryCloak;

    public String getTerritoryCloak() {
        return territoryCloak;
    }

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
    private TextField territory_cloak;

    @FXML
    private Button cloak_btn;

    @FXML
    private Button cancel_btn;

    @FXML
    void click_cloak(ActionEvent event) {
        territoryCloak = territory_cloak.getText();
        boolean isValidInput = true;
        
        if (territoryCloak == null || territoryCloak.isEmpty()) {
            isValidInput = false;
        }
        
        if (isValidInput) {
            // for test
            System.out.println("Territory Cloak: " + territoryCloak);
            
            String[] territoryCloakArray = territoryCloak.split(" ");

            // TODO: check order!!
            // for (String territory : territoryCloakArray) {
            //     territoryCloakArray.add();
            // }
            // Territory src = gameMap.getTerritoryByName(sourceTerritory);
            // Territory dest = gameMap.getTerritoryByName(targetTerritory);
            // // TODO: check order!!
            // Move moveOrder = new Move(unitsIndexList.size(), src, dest, Type.Move, clientID, unitsIndexList, null);
            // ActionInfo info = new ActionInfo(moveOrder);
            // netClient.sendActionInfo(info);
            // moveOrder.run(gameMap);
            
            Stage stage = (Stage) cloak_btn.getScene().getWindow();
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
