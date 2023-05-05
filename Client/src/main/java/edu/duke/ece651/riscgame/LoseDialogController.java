package edu.duke.ece651.riscgame;

import javax.swing.Action;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoseDialogController {
    @FXML
    private Button yes_btn;
    
    @FXML
    private Button no_btn;

    private boolean ifWatching;

    public boolean getIfWatching() {
        return ifWatching;
    }

    @FXML
    void click_yes(Action event) {
        ifWatching = true;
        Stage stage = (Stage) yes_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void click_no(Action event) {
        ifWatching = false;
        Stage stage = (Stage) no_btn.getScene().getWindow();
        stage.close();
    }
}
