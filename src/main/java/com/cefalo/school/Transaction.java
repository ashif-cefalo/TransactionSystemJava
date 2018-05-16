package com.cefalo.school;

import java.util.UUID;

public abstract class Transaction {
    private UUID transactionID = UUID.randomUUID();
    private Status status = Status.PENDING;
    protected TransactionProcessor processor = new TransactionProcessor();

    public UUID getTransactionID() {

        return transactionID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public abstract boolean processTransaction();

    public abstract boolean rollBackTransaction();
}
