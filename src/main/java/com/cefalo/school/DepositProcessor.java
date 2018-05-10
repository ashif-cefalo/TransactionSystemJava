package com.cefalo.school;

public class DepositProcessor implements TransactionProcessor {
    @Override
    public boolean processTransaction(Transaction transaction) {
        Deposit deposit = (Deposit) transaction;
        Account account = deposit.getAccount();
        account.setBalance(account.getBalance()+deposit.getAmount());
        transaction.setStatus(Status.COMPLETED);
        System.out.println("transaction succesfull: "+ transaction.getTransactionID()+ " " + deposit.getAmount()+
                " deposited to account "+account.getAccountName() + " current balance: " + account.getBalance());
        return true;
    }
}
