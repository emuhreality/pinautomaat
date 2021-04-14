package launcher;

import controller.views.OpenTerminalCodeViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.SmesCustomer;
import model.Transaction;
import services.LinkAtmTerminalService;
import model.TransactionResult;
import services.TransactionService;
import view.SceneManager;


/**
 * @author: R. Portzgen
 */
public class Main extends Application {
    private static SceneManager sceneManager = null;
    private static Stage primaryStage = null;
    private static SmesCustomer connectedSmesCustomer = null;
    private static Transaction thisTransaction = null;
    private static TransactionResult thisTransactionResult = null;


    public static void main(String[] args) {
        System.out.println("Let's make some money today!");
        launch(args);
//        OpenTerminalCodeViewController openTerminalCodeViewController = new OpenTerminalCodeViewController();
        //openTerminalCodeViewController.startJournal();
    }

    public static TransactionResult getThisTransactionResult() {
        return thisTransactionResult;
    }

    public static void setThisTransactionResult(TransactionResult thisTransactionResult) {
        Main.thisTransactionResult = thisTransactionResult;
    }

    public static Transaction getThisTransaction() {
        return thisTransaction;
    }

    public static void setThisTransaction(Transaction thisTransaction) {
        Main.thisTransaction = thisTransaction;
    }

    public static SmesCustomer getConnectedWinkelier() {
        return connectedSmesCustomer;
    }

    public static void setConnectedWinkelier(SmesCustomer connectedSmesCustomer) {
        Main.connectedSmesCustomer = connectedSmesCustomer;
    }

    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("GBBC - Pin Terminal");
        getSceneManager().setStartView();
        primaryStage.show();
    }

    public static SceneManager getSceneManager() {
        if (sceneManager == null) {
            sceneManager = new SceneManager(primaryStage);
        }
        return sceneManager;
    }
}
