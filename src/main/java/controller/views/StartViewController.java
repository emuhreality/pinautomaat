package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import launcher.Main;
import model.SmesCustomer;
import model.Transaction;
import services.TransactionService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: R. Portzgen
 * @author 2nd E. Koo
 */
public class StartViewController extends BaseController {

    @FXML
    private Button koppelenButton;
    @FXML
    private Button betalingButton;
    @FXML
    private Circle statusLight;
    @FXML
    private Text message;

    public void setup() {
        betalingButton.setDisable(true);
        if (connectedWinkelier != null) {
            koppelenButton.setText("Gekoppeld");
            koppelenButton.setStyle("-fx-font-size:12; -fx-background-color: #f6d375");
            koppelenButton.setDisable(true);
            betalingButton.setDisable(false);
            statusLight.setFill(Paint.valueOf("#9dd175"));
            statusLight.setStroke(Paint.valueOf("#9dd175"));
            String company = getCompanyName(connectedWinkelier);
            message.setText("Welkom bij "+ company+ "!");
        }
    }

    @FXML
    private void goToKoppelen(ActionEvent actionEvent) {
        Main.getSceneManager().setOpenTerminalView();
    }

    @FXML
    private void goToTransaction(ActionEvent actionEvent) {
        //TODO RENS connectWinkelier werkend maken
        String creditAccountNr = connectedWinkelier.getRekeningNr();
        if(!creditAccountNr.isEmpty()){
        Transaction newTransaction = new Transaction();
        newTransaction.setCreditBankAcc(creditAccountNr);
        Main.setThisTransaction(newTransaction);
        }
        Main.getSceneManager().setTransactionStartView();
    }

    @FXML
    private void pressOk(ActionEvent actionEvent) {
    }

    @FXML
    private void pressStop(ActionEvent actionEvent) {
    }

    @FXML
    private void doQuit(ActionEvent actionEvent) {
        closeJournal();
        try {
            copyFileToDifferentPath("src/main/resources/Journals/",System.getProperty("user.home")+"/Desktop/");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private void closeJournal(){
        System.out.println("writeTransaction");
        String companyNaam = getCompanyName(connectedWinkelier);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        File uitvoerBestand = new File("src/main/resources/Journals/Dagjournaal_" + date +"_"+companyNaam+".txt");
        try {
            FileWriter fr = new FileWriter(uitvoerBestand, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println("Close Terminal");
            pr.println("------------------------------------------------------------------------------------------------");
            pr.close();
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyFileToDifferentPath(String from, String to) throws IOException{
        String companyNaam = getCompanyName(connectedWinkelier);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Path src = Paths.get(from+"Dagjournaal_" + date +"_"+companyNaam+".txt");
        Path dest = Paths.get(to+"Dagjournaal_" + date +"_"+companyNaam+".txt");
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }


    @FXML
    private void pressDigit(ActionEvent actionEvent) {
    }

    private String getCompanyName(SmesCustomer Winkelier) {
        TransactionService transactionService = new TransactionService();
        return transactionService.checkCompanyForAccountNr(connectedWinkelier);
    }
}
