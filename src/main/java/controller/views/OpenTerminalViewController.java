package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import launcher.Main;
import model.SmesCustomer;
import model.UniversalKeyBoard;
import services.LinkAtmTerminalService;

/**
 * @author 1st R. Pörtzgen
 * @author 2nd E. Koo
 */
public class OpenTerminalViewController extends BaseController {
    UniversalKeyBoard universalKeyBoard = UniversalKeyBoard.getINSTANCE();
    LinkAtmTerminalService linkAtmTerminalService = new LinkAtmTerminalService();
    @FXML
    private TextArea displayTextField;

    @FXML
    private void pressOk(ActionEvent actionEvent) {
        if (!universalKeyBoard.getInput().isEmpty()) {
            SmesCustomer thisCustomer = new SmesCustomer(universalKeyBoard.getInput());
            //checken of de ingevoerde rekeningnummer bestaat in de database,
            processMessage(linkAtmTerminalService.validateCustomer(thisCustomer));
        } else {
            // anders op hetzelfde scherm blijven en melding geven en resetten
            Main.getSceneManager().setOpenTerminalView();
        }
    }

    @FXML
    private void pressStop(ActionEvent actionEvent) {
        universalKeyBoard.resetInput();
        Main.getSceneManager().setStartView();
    }

    @FXML
    private void goBack(ActionEvent actionEvent) {
        universalKeyBoard.resetInput();
    }

    private void processMessage(String message) {
        if (message.equals("valid")) {
            Main.setConnectedWinkelier(new SmesCustomer(universalKeyBoard.getInput()));
            universalKeyBoard.resetInput();
            Main.getSceneManager().setOpenTerminalCodeView();
        } else {
            universalKeyBoard.resetInput();
            displayTextField.setText("Probeer het nogmaals");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("GBBC - foutmelding");
            alert.setHeaderText("Er is geen terminalaanvraag gevonden");

            ImageView logoView = new ImageView("file:src/main/resources/assets/logoGBBCmini.png");
            alert.setGraphic(logoView);

            alert.showAndWait();
            Main.getSceneManager().setOpenTerminalView();
        }
    }

}

