package com.cefalo.school;

public class Withdraw extends Transaction{
    private double amount;
    private Account account;

    public Withdraw(double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    @Override
    public boolean processTransaction(){
        if (processor.processTransaction(amount,account,TransactionType.WITHDRAW)){
            this.setStatus(Status.COMPLETED);
//            System.out.println("transaction succesfull: "+ this.getTransactionID()+ " " + amount+
//                    " deposited to account "+account.getAccountName() + " current balance: " + account.getBalance());
            return true;
        }
        return false;
    }

    @Override
    public boolean rollBackTransaction() {
        if (getStatus() == Status.ROLLBACK_REQUESTED){
            processor.processTransaction(amount, account, TransactionType.DEPOSIT);
            this.setStatus(Status.ROLLBACKED);
            System.out.println("withdraw rollback successfull: "+ this.getTransactionID()+ " "
                    + account.getAccountName() + " current balance: " + account.getBalance());
            return true;
        }
        return false;
    }
}
