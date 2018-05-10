package com.cefalo.school;

public class WithdrawProcessor implements TransactionProcessor {
    @Override
    public boolean processTransaction(Transaction transaction) {
        Withdraw withdraw = (Withdraw) transaction;
        Account account = withdraw.getAccount();
        if (account.getBalance() < withdraw.getAmount()){
            System.out.println("insufficient balance, current balance "+account.getBalance());
            return false;
        }
        account.setBalance(account.getBalance()-withdraw.getAmount());
        transaction.setStatus(Status.COMPLETED);
        System.out.println("transaction succesfull: "+ transaction.getTransactionID()+ " " + withdraw.getAmount()+
                " debited from account "+account.getAccountName() + " current balance: " + account.getBalance());
        return true;
    }
}
