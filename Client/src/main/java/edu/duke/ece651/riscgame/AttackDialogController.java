package edu.duke.ece651.riscgame;

import java.util.HashMap;

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


