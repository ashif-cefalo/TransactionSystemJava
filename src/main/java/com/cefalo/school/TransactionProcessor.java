package com.cefalo.school;

public class TransactionProcessor {
    public boolean processTransaction(double amount, Account account, TransactionType transactionType) {

        if (transactionType == TransactionType.DEPOSIT){
            return depositAmount(amount,account);
        }else{
            return withdrawAmount(amount,account);
        }
    }

    private boolean depositAmount(double amount, Account account){
        account.setBalance(account.getBalance()+amount);
        return true;
    }

    private boolean withdrawAmount(double amount, Account account){
        if (account.getBalance() < amount){
            System.out.println("insufficient balance, current balance "+account.getBalance());
            return false;
        }
        account.setBalance(account.getBalance()-amount);
        return true;
    }
}

