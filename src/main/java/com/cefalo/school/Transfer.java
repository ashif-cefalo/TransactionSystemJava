package com.cefalo.school;

public class Transfer extends Transaction{
    private double amount;
    private Account fromAccount;
    private Account toAccount;

    public Transfer(double amount, Account fromAccount, Account toAccount) {
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    @Override
    public boolean processTransaction(){
        if (processor.processTransaction(amount,fromAccount,TransactionType.WITHDRAW)){
            processor.processTransaction(amount,toAccount, TransactionType.DEPOSIT);
            this.setStatus(Status.COMPLETED);
//            System.out.println("transaction succesfull: "+ this.getTransactionID()+ " " + amount+
//                    " transferred from account: "+fromAccount.getAccountName() + " to account: " + toAccount.getAccountName());
            return true;
        }
        return false;
    }

    @Override
    public boolean rollBackTransaction() {
        if (getStatus() == Status.ROLLBACK_REQUESTED){
            if (processor.processTransaction(amount,toAccount,TransactionType.WITHDRAW)){
                processor.processTransaction(amount,fromAccount, TransactionType.DEPOSIT);
                this.setStatus(Status.ROLLBACKED);
                System.out.println("transfer rollback successfull: "+ this.getTransactionID());
                return true;
            }
            else{
                System.out.println("transfer rollback not successfull: "+ this.getTransactionID());
            }
        }
        return false;
    }
}
