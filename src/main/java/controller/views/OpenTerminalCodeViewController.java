package controller.views;

import controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import launcher.Main;
import model.RestResponse;
import model.SmesCustomer;
import model.UniversalKeyBoard;
import services.LinkAtmTerminalService;
import services.TransactionService;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 1st R. Pörtzgen
 * @author 2nd E. Koo
 */
public class OpenTerminalCodeViewController extends BaseController {
    UniversalKeyBoard universalKeyBoard = UniversalKeyBoard.getINSTANCE();
    LinkAtmTerminalService linkRequest = new LinkAtmTerminalService();

    public OpenTerminalCodeViewController() {
        super();
    }

    @FXML
    private void pressOk() {
        int linkCode = Integer.parseInt(universalKeyBoard.getInput());

        //TODO Bij fout invoer van Koppelcode
        if (!universalKeyBoard.getInput().isEmpty()) {
            connectedWinkelier.setCode(linkCode);
            RestResponse responseMessage = new RestResponse();
            //Check of combi van ingevoerde rekeningnummer + koppelcode correct is,
            String message = linkRequest.validateLinkTerminalRequest(connectedWinkelier);
            responseMessage.setMessage(message);
            // update connectedwinkelier met info
            Main.setConnectedWinkelier(connectedWinkelier);

            System.out.println("Ingevoerde rekeningnummer: " + Main.getConnectedWinkelier().getRekeningNr());
            startJournal();
            universalKeyBoard.resetInput();
            Main.getSceneManager().setOpenTerminalResultView();
        } else {
            // als het niet correct is alert terug naar Startview - niks setten
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("GBBC - foutmelding");
            alert.setHeaderText("Er is geen terminalaanvraag gevonden");
            alert.setContentText("U word terug gestuurd naar het startscherm");
            ImageView logoView = new ImageView("file:src/main/resources/assets/logoGBBCmini.png");
            alert.setGraphic(logoView);
            alert.showAndWait();

            Main.getSceneManager().setStartView();
        }
    }

    public void startJournal() {
        System.out.println("in startJournal2");
        String companyNaam = getCompanyName(connectedWinkelier);
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        File uitvoerBestand = new File("src/main/resources/Journals/Dagjournaal_" + date +"_"+companyNaam+".txt");
        try {
            FileWriter fr = new FileWriter(uitvoerBestand, true);
            BufferedWriter br = new BufferedWriter(fr);
            PrintWriter pr = new PrintWriter(br);
            pr.println("Start terminal " + date + " " + companyNaam);
            pr.println("------------------------------------------------------------------------------------------------");
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
    private void pressStop() {
        universalKeyBoard.resetInput();
        Main.getSceneManager().setStartView();
    }

    @FXML
    private void goBack() {
        universalKeyBoard.resetInput();
    }
}


