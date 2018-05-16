package com.cefalo.school;

public class Deposit extends Transaction{
    private double amount;
    private Account account;

    public Deposit(double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    @Override
    public boolean processTransaction(){
        if (processor.processTransaction(amount,account,TransactionType.DEPOSIT)){
            this.setStatus(Status.COMPLETED);
        }
        return true;
    }

    @Override
    public boolean rollBackTransaction() {
        if (getStatus() == Status.ROLLBACK_REQUESTED){
            if (processor.processTransaction(amount, account, TransactionType.WITHDRAW)){
                this.setStatus(Status.ROLLBACKED);
                System.out.println("deposit rollback successfull: "+ this.getTransactionID()+ " "
                        + account.getAccountName() + " current balance: " + account.getBalance());
                return true;
            }
            else{
                System.out.println("deposit rollback not successfull: "+ this.getTransactionID());
            }
        }
        return false;
    }
}
