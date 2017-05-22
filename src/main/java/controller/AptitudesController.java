package controller;

import javafx.fxml.FXML;
import main.MainApp;

/**
 * Created by Florent L.
 */
public class AptitudesController {

    private MainController mainController;


    @FXML
    private void initialize() {

    }

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }
}
