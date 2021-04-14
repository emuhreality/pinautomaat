package services;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import model.SmesCustomer;
import model.StoreName;
import model.Transaction;
import model.TransactionResult;

/**
 * @author 1st R. Pörtzgen
 */
public class TransactionService {

    /**
     *
     * @param transaction
     * @return
     */
    public String checkTransaction(Transaction transaction) {
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://localhost:8080/api/saldo");

        Response response = webTarget.request().post(Entity.entity(transaction, MediaType.APPLICATION_JSON_TYPE));

        TransactionResult transactionResult =  response.readEntity(TransactionResult.class);
        System.out.println(transactionResult.message);
        if(transaction==null) return "";
        else return transactionResult.message;
    }

    /**
     *
     * @param smesCustomer
     * @return
     */
    public String checkCompanyForAccountNr(SmesCustomer smesCustomer){
    Client client = ClientBuilder.newClient();

    WebTarget webTarget = client.target("http://localhost:8080/api/check-company");

    Response response = webTarget.request().post(Entity.entity(smesCustomer, MediaType.APPLICATION_JSON_TYPE));

    StoreName storeName =  response.readEntity(StoreName.class);
    return storeName.getMessage();
}

}
