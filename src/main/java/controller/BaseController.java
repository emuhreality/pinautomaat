package controller;

import launcher.Main;
import model.SmesCustomer;
import model.Transaction;
import model.TransactionResult;
import services.TransactionService;

/**
 * @author: R. Portzgen
 */
public class BaseController {
    protected SmesCustomer connectedWinkelier;
    protected Transaction currentTransaction;
    protected TransactionResult currentTransactionResult;


    public BaseController() {
        this.connectedWinkelier = Main.getConnectedWinkelier();
        this.currentTransaction = Main.getThisTransaction();
        this.currentTransactionResult = Main.getThisTransactionResult();
    }
}
