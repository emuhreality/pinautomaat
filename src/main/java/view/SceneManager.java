package view;

import controller.views.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneManager {
    private Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public FXMLLoader getScene(String fxml) {
        Scene scene;
        try {
            URL url = getClass().getResource(fxml);
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            scene = new Scene(root);
            primaryStage.setScene(scene);
            return loader;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }

    public void setStartView() {
        FXMLLoader loader = getScene("/view/fxml/startView.fxml");
        if (loader != null) {
            StartViewController controller = loader.getController();
            controller.setup();
        } else {
            System.out.println("set startView: Loader is not initialized");
            System.out.flush();
        }
    }

    public void setOpenTerminalView(){
        FXMLLoader loader = getScene("/view/fxml/openTerminal/openTerminalView.fxml");
        if (loader != null) {
            OpenTerminalViewController controller = loader.getController();
//            controller.setup();
        } else {
            System.out.println("set openTerminalView: Loader is not initialized");
            System.out.flush();
        }
    }

    public void setOpenTerminalCodeView(){
        FXMLLoader loader = getScene("/view/fxml/openTerminal/openTerminalCodeView.fxml");
        if (loader != null) {
            OpenTerminalCodeViewController controller = loader.getController();
//            controller.setup();
        } else {
            System.out.println("setOpenTerminalCodeView: Loader is not initialized");
            System.out.flush();
        }
    }

    public void setOpenTerminalResultView(){
        FXMLLoader loader = getScene("/view/fxml/openTerminal/openTerminalResultView.fxml");
        if (loader != null) {
            OpenTerminalResultViewController controller = loader.getController();
//            controller.setup();
        } else {
            System.out.println("setOpenTerminalResultView: Loader is not initialized");
            System.out.flush();
        }
    }


    public void setTransactionStartView(){
        FXMLLoader loader = getScene("/view/fxml/transaction/transactionStartView.fxml");
        if (loader != null) {
            TransactionStartViewController controller = loader.getController();
//            controller.setup();
        } else {
            System.out.println("setTransactionStartView: Loader is not initialized");
            System.out.flush();
        }
    }

    public void setCustomerTransactionView(){
        FXMLLoader loader = getScene("/view/fxml/transaction/customerTransactionView.fxml");
        if (loader != null) {
            CustomerTransactionViewController controller = loader.getController();
            controller.setup();
        } else {
            System.out.println("setCustomerTransactionView: Loader is not initialized");
            System.out.flush();
        }
    }

    public void setCustomerPincodeView(){
        FXMLLoader loader = getScene("/view/fxml/transaction/customerPincodeView.fxml");
        if (loader != null) {
            CustomerPincodeViewController controller = loader.getController();
            controller.setup();
        } else {
            System.out.println("setCustomerPincodeView: Loader is not initialized");
            System.out.flush();
        }
    }

    public void setCustomerTransactionResultView(){
        FXMLLoader loader = getScene("/view/fxml/transaction/customerTransactionResultView.fxml");
        if (loader != null) {
            CustomerTransactionResultViewController controller = loader.getController();
            controller.setup();
        } else {
            System.out.println("setCustomerTransactionResultView: Loader is not initialized");
            System.out.flush();
        }
    }
}
