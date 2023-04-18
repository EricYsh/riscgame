package edu.duke.ece651.riscgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MoveDialogController {

    private String sourceTerritory;
    private String targetTerritory;
    private int numUnits;

    @FXML
    private Button cancel_btn;

    @FXML
    private TextField num_units;

    @FXML
    private TextField source_territory;

    @FXML
    private Button move_btn;

    @FXML
    private TextField target_territory;

    public String getSourceTerritory() {
        return sourceTerritory;
    }

    public String getTargetTerritory() {
        return targetTerritory;
    }

    public int getNumUnits() {
        return numUnits;
    }   

    @FXML
    void click_move(ActionEvent event) {
        String sourceTerritory = source_territory.getText();
        String targetTerritory = target_territory.getText();
        int numUnits = 0;
        boolean isValidInput = true;
        
        if (sourceTerritory == null || sourceTerritory.isEmpty()) {
            isValidInput = false;
        }
        if (targetTerritory == null || targetTerritory.isEmpty()) {
            isValidInput = false;
        }
        if (num_units.getText() != null && !num_units.getText().isEmpty()) {
            try {
                numUnits = Integer.parseInt(num_units.getText());
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
            System.out.println("numUnits: " + numUnits);
        
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
        Stage stage = (Stage) move_btn.getScene().getWindow();
        stage.close();
    }
}
