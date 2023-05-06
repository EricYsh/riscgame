package edu.duke.ece651.riscgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WinDialogController {
    @FXML
    private Button finish_btn;

    @FXML
    private void click_finish(ActionEvent event) {
        Stage stage = (Stage) finish_btn.getScene().getWindow();
        stage.close();
    }
}
