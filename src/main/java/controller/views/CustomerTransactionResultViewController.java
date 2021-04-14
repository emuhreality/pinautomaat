package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import launcher.Main;

/**
 * @author: R. Portzgen
 */
public class CustomerTransactionResultViewController extends BaseController {
    @FXML
    public Text resultSymbol;
    @FXML
    public Text resultMessage;

    public CustomerTransactionResultViewController() {
        super();
    }

    public void setup() {
        String message = currentTransactionResult.getMessage();
        SetResultScreen(message);
    }

    private void SetResultScreen(String message) {
        switch (message) {
            case ("Onjuiste invoer"): {
                resultSymbol.setText("X");
                resultSymbol.setStyle("-fx-fill: #f48b7b");
                resultMessage.setText("Onjuiste invoer");
                break;
            }
            case ("Geen saldo"): {
                resultSymbol.setText("X");
                resultSymbol.setStyle("-fx-fill: #f48b7b");
                resultMessage.setText("Saldo ontoereikend");
                break;
            }
            case ("Er is iets fout gegaan!"): {
                resultSymbol.setText("?");
                resultSymbol.setStyle("-fx-fill: #bc7bf4");
                resultMessage.setText("Er is iets fout gegaan!");
                break;
            }
            default:
                resultSymbol.setText("V");
                resultSymbol.setStyle("-fx-fill: #9dd175");
                resultMessage.setText("Transactie geslaagd!");
                break;
        }
    }


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
    private void goBack(ActionEvent actionEvent) {
        Main.getSceneManager().setStartView();
    }
}
