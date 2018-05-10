package com.cefalo.school;

import java.util.UUID;

public class Transaction {
    private UUID transactionID = UUID.randomUUID();
    private Status status = Status.PENDING;
    private TransactionType transactionType;

    public void processTransaction(){

    }

    public UUID getTransactionID() {

        return transactionID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
