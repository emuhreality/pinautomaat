package model;

import services.TransactionService;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author 1st R. Pörtzgen
 */
public class Transaction {
    private String creditBankAcc;
    private String debetBankAcc;
    private String pincodeDebetAcc;
    private String amount;
    private String description;
    TransactionService transactionService = new TransactionService();
    public Transaction() {
    }

    public String getCreditBankAcc() {
        return creditBankAcc;
    }

    public void setCreditBankAcc(String creditBankAcc) {
        this.creditBankAcc = creditBankAcc;
    }

    public String getDebetBankAcc() {
        return debetBankAcc;
    }

    public void setDebetBankAcc(String debetBankAcc) {
        this.debetBankAcc = debetBankAcc;
    }

    public String getPincodeDebetAcc() {
        return pincodeDebetAcc;
    }

    public void setPincodeDebetAcc(String pincodeDebetAcc) {
        this.pincodeDebetAcc = pincodeDebetAcc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionFromGenerator(SmesCustomer creditBankAcc) {
        String generatedDescription = "";
        String storeName = transactionService.checkCompanyForAccountNr(creditBankAcc);
        String storeID = "*****" + creditBankAcc.getRekeningNr().substring(5);

        String randomTransactionNumber = generateTransactionNumber();
        generatedDescription = " Pintransaction at: "+ storeName +", with Transactionnr: " + randomTransactionNumber + " Storeid: " + storeID;
        return generatedDescription;
    }

    private String generateTransactionNumber() {
        Random random = new Random();
        StringBuilder transactionNumber = new StringBuilder();
        int amount = 12;
        for (int i = 0; i < amount; i++) {
            transactionNumber.append(random.nextInt(10));
        }
        return String.valueOf(transactionNumber);
    }
}
