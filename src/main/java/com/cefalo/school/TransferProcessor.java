package com.cefalo.school;

public class TransferProcessor implements TransactionProcessor {
    @Override
    public boolean processTransaction(Transaction transaction) {
        Transfer transfer = (Transfer) transaction;

        Account fromAccount = transfer.getFromAccount();
        Account toAccount = transfer.getToAccount();

        Withdraw withdraw = new Withdraw(transfer.getAmount(),fromAccount);
        WithdrawProcessor withdrawProcessor = new WithdrawProcessor();

        if (withdrawProcessor.processTransaction(withdraw)){
            DepositProcessor depositProcessor = new DepositProcessor();
            Deposit deposit = new Deposit(transfer.getAmount(),toAccount);
            depositProcessor.processTransaction(deposit);
            transaction.setStatus(Status.COMPLETED);
            return true;
        }
        return false;
    }
}
