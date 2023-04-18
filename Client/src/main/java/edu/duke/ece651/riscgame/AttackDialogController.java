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
    private int numUnitsLevel1;
    private int numUnitsLevel2;
    private int numUnitsLevel3;
    private int numUnitsLevel4;
    private int numUnitsLevel5;
    private int numUnitsLevel6;
    private int numUnitsLevel7;

    @FXML
    private TextField source_territory;

    @FXML
    private TextField target_territory;

    @FXML
    private TextField call_up_level_1;

    @FXML
    private TextField call_up_level_2;

    @FXML
    private TextField call_up_level_3;

    @FXML
    private TextField call_up_level_4;

    @FXML
    private TextField call_up_level_5;

    @FXML
    private TextField call_up_level_6;

    @FXML
    private TextField call_up_level_7;

    @FXML
    private Label available_level_1;

    @FXML
    private Label available_level_2;

    @FXML
    private Label available_level_3;

    @FXML
    private Label available_level_4;

    @FXML
    private Label available_level_5;

    @FXML
    private Label available_level_6;

    @FXML
    private Label available_level_7;

    @FXML
    private Button cancel_btn;

    @FXML
    private Button attack_btn;

    @FXML
    void click_attack(ActionEvent event) {
        sourceTerritory = source_territory.getText();
        targetTerritory = target_territory.getText();
        boolean isValidInput = true;

        if (sourceTerritory == null || sourceTerritory.isEmpty()) {
            isValidInput = false;
        }
        if (targetTerritory == null || targetTerritory.isEmpty()) {
            isValidInput = false;
        }
        if (call_up_level_1.getText() != null && !call_up_level_1.getText().isEmpty()) {
            try {
                numUnitsLevel1 = Integer.parseInt(call_up_level_1.getText());
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
        } else {
            isValidInput = false;
        }
        if (call_up_level_2.getText() != null && !call_up_level_2.getText().isEmpty()) {
            try {
                numUnitsLevel2 = Integer.parseInt(call_up_level_2.getText());
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
        } else {
            isValidInput = false;
        }
        if (call_up_level_3.getText() != null && !call_up_level_3.getText().isEmpty()) {
            try {
                numUnitsLevel3 = Integer.parseInt(call_up_level_3.getText());
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
        } else {
            isValidInput = false;
        }
        if (call_up_level_4.getText() != null && !call_up_level_4.getText().isEmpty()) {
            try {
                numUnitsLevel4 = Integer.parseInt(call_up_level_4.getText());
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
        } else {
            isValidInput = false;
        }
        if (call_up_level_5.getText() != null && !call_up_level_5.getText().isEmpty()) {
            try {
                numUnitsLevel5 = Integer.parseInt(call_up_level_5.getText());
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
        } else {
            isValidInput = false;
        }
        if (call_up_level_6.getText() != null && !call_up_level_6.getText().isEmpty()) {
            try {
                numUnitsLevel6 = Integer.parseInt(call_up_level_6.getText());
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
        } else {
            isValidInput = false;
        }
        if (call_up_level_7.getText() != null && !call_up_level_7.getText().isEmpty()) {
            try {
                numUnitsLevel7 = Integer.parseInt(call_up_level_7.getText());
            } catch (NumberFormatException e) {
                isValidInput = false;
            }
        } else {
            isValidInput = false;
        }

        if (isValidInput) {
            // for test
            System.out.println("sourceTerritory: " + sourceTerritory);
            System.out.println("targetTerritory: " + targetTerritory);
            System.out.println("numUnitsLevel1: " + numUnitsLevel1);
            System.out.println("numUnitsLevel2: " + numUnitsLevel2);
            System.out.println("numUnitsLevel3: " + numUnitsLevel3);
            System.out.println("numUnitsLevel4: " + numUnitsLevel4);
            System.out.println("numUnitsLevel5: " + numUnitsLevel5);
            System.out.println("numUnitsLevel6: " + numUnitsLevel6);
            System.out.println("numUnitsLevel7: " + numUnitsLevel7);
        
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

    public String getSourceTerritory() {
        return sourceTerritory;
    }

    public String getTargetTerritory() {
        return targetTerritory;
    }

    public HashMap<String, Integer> getUserAttackInput() {
        HashMap<String, Integer> userAttackInput = new HashMap<>();
        userAttackInput.put("numUnitsLevel1", numUnitsLevel1);
        userAttackInput.put("numUnitsLevel2", numUnitsLevel2);
        userAttackInput.put("numUnitsLevel3", numUnitsLevel3);
        userAttackInput.put("numUnitsLevel4", numUnitsLevel4);
        userAttackInput.put("numUnitsLevel5", numUnitsLevel5);
        userAttackInput.put("numUnitsLevel6", numUnitsLevel6);
        userAttackInput.put("numUnitsLevel7", numUnitsLevel7);
        return userAttackInput;
    }
}


