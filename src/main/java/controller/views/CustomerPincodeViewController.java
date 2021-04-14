package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import launcher.Main;
import model.SmesCustomer;
import model.TransactionResult;
import model.UniversalKeyBoard;
import services.TransactionService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: R. Portzgen
 */
public class CustomerPincodeViewController extends BaseController {
    UniversalKeyBoard universalKeyBoard = UniversalKeyBoard.getINSTANCE();
    TransactionService transactionService = new TransactionService();
    @FXML
    private TextArea displayTextField;

    public CustomerPincodeViewController() {
        super();
    }


    @FXML
    private void pressOk(ActionEvent actionEvent) {
        String pincode = universalKeyBoard.getInput();
        if (!pincode.isEmpty()) {
            currentTransaction.setPincodeDebetAcc(pincode);
            Main.setThisTransaction(currentTransaction);
            TransactionResult thisResult = new TransactionResult();
            String message = transactionService.checkTransaction(currentTransaction);
            if(!message.isEmpty()){thisResult.setMessage(message);}
            else {thisResult.setMessage("Er is iets fout gegaan!");}
            Main.setThisTransactionResult(thisResult);
            writeTransaction();
            universalKeyBoard.resetInput();
            Main.getSceneManager().setCustomerTransactionResultView();
        } else {
            Main.getSceneManager().setCustomerPincodeView();
        }
    }

    private void writeTransaction(){
        System.out.println("writeTransaction");
        String companyNaam = getCompanyName(connectedWinkelier);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        File uitvoerBestand = new File("src/main/resources/Journals/Dagjournaal_" + date +"_"+companyNaam+".txt");
        try {
            FileWriter fr = new FileWriter(uitvoerBestand, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println("+ "+java.time.LocalTime.now()+" "+currentTransaction.getDescription()+" "+currentTransaction.getAmount()+" "+currentTransaction.getDebetBankAcc());
            pr.println();
            pr.close();
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCompanyName(SmesCustomer smesCustomer){
        TransactionService transactionService = new TransactionService();
        return transactionService.checkCompanyForAccountNr(smesCustomer);
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
