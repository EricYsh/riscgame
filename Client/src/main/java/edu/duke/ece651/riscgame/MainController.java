package edu.duke.ece651.riscgame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label label;

    @FXML
    private void initialize() {
        label.setText("Hello, World!");
    }

}
