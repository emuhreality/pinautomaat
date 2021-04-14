package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import launcher.Main;


/**
 * @author: R. Portzgen
 */
public class OpenTerminalResultViewController extends BaseController {

    @FXML
    private void pressDigit(ActionEvent actionEvent) {
    }

    @FXML
    private void pressOk(ActionEvent actionEvent) {
        Main.getSceneManager().setStartView();
    }

    @FXML
    private void pressStop(ActionEvent actionEvent) {
        Main.getSceneManager().setStartView();
    }

    @FXML
    private void goBack(ActionEvent actionEvent) { Main.getSceneManager().setStartView(); }


}
