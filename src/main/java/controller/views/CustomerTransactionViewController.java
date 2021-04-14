package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import launcher.Main;
import model.UniversalKeyBoard;

/**
 * @author: R. Portzgen
 */
public class CustomerTransactionViewController extends BaseController {
    UniversalKeyBoard universalKeyBoard = UniversalKeyBoard.getINSTANCE();
    @FXML
    private TextArea displayTextField;

    public CustomerTransactionViewController() {
        super();
    }


    @FXML
    private void pressOk(ActionEvent actionEvent) {
        String debitBankAcc = universalKeyBoard.getInput();
        if (!debitBankAcc.isEmpty()) {
            currentTransaction.setDebetBankAcc(debitBankAcc);
            Main.setThisTransaction(currentTransaction);
            universalKeyBoard.resetInput();
            Main.getSceneManager().setCustomerPincodeView();
        } else {
            Main.getSceneManager().setCustomerTransactionView();
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

    public void setup() {
        displayTextField.setText("€"+currentTransaction.getAmount());
    }
}

