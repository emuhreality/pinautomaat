package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import launcher.Main;
import model.SmesCustomer;
import model.Transaction;
import model.UniversalKeyBoard;

import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

/**
 * @author: R. Portzgen
 */
public class TransactionStartViewController extends BaseController implements Observer{
    UniversalKeyBoard universalKeyBoard = UniversalKeyBoard.getINSTANCE();
    private Button button;

    @FXML
    private Text inputField;
    @FXML
    private Button middleButton;


    public TransactionStartViewController() {
        super();
        universalKeyBoard.addObserver(this);
    }


    @FXML
    private void pressDigit(ActionEvent actionEvent) {
        button = (Button)actionEvent.getSource();
        universalKeyBoard.addDigit(button.getText());
        setFieldForAmount(Double.valueOf(universalKeyBoard.getInput()));
    }

    @Override
    public void update(Observable o, Object arg) {
        inputField.setText(universalKeyBoard.getFormat());
    }


    @FXML
    private void pressOk(ActionEvent actionEvent) {
        if (!universalKeyBoard.getInput().isEmpty()) {
            Transaction newTransaction = Main.getThisTransaction();
            newTransaction.setAmount(universalKeyBoard.getInput());
            newTransaction.setDescription(newTransaction.getDescriptionFromGenerator(connectedWinkelier));
            Main.setThisTransaction(newTransaction);
            System.out.println("new: "+ newTransaction.getAmount());
            universalKeyBoard.resetInput();
            Main.getSceneManager().setCustomerTransactionView();
        } else {
            Main.getSceneManager().setTransactionStartView();
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


    @FXML
    private void pressDot(ActionEvent actionEvent) {
        universalKeyBoard.addDigit(".");
        setFieldForAmount(Double.valueOf(universalKeyBoard.getInput()));
        middleButton.setDisable(true);
    }

    private void setFieldForAmount(Double input) {
        String saldoString = input.toString();
        DecimalFormat df = new DecimalFormat("##,###,###.00");
        saldoString = "€"+ df.format(Double.valueOf(saldoString));
        inputField.setText(saldoString);
    }
}
