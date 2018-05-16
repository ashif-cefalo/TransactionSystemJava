package com.cefalo.school;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang.NotImplementedException;


public class TransactionManager {

  //TODO:  Maintain a List of Transactions (Deposit/Withdraw/ Transfer )
  //TODO:  Maintain a List of Transactions rollback
  //TODO:  Add a Method for Adding Transaction
  //TODO: Add a method for Checking if there is any Pending Transtion

  List<Transaction> transactionList = new ArrayList<Transaction>();

  public void addTransactionRequest(Transaction transaction){
    transactionList.add(transaction);
  }

  public boolean hasPendingTransactions() {
    // This should track if there is any pending transaction request (Deposit/Withdraw/ Transfer)
    for (Transaction transaction : transactionList) {
        if(transaction.getStatus() == Status.PENDING || transaction.getStatus() == Status.ROLLBACK_REQUESTED){
          System.out.println("transaction id: "+transaction.getTransactionID() + " is pending");
          return true;
        }
    }
    return false;
  }

  public void processPendingTransactions() {
    // The logic for processing pending transaction sequentially goes here
    // It should track which are already processed and which are pending transactions
    for (Transaction transaction : transactionList) {
      if(transaction.getStatus() == Status.PENDING){
        transaction.processTransaction();
      }
      else if (transaction.getStatus() == Status.ROLLBACK_REQUESTED){
        transaction.rollBackTransaction();
      }
    }
  }

  public void rollbackTransaction(UUID transactionId) {
    // The logic for rolling back a transaction with Id
    List<Transaction> transactions = transactionList.stream()
            .filter(p -> p.getTransactionID() == transactionId).collect(Collectors.toList());
    if (transactions.size() > 0){
      Transaction transaction = transactions.get(0);
      if (transaction.getStatus() == Status.COMPLETED){
        transaction.setStatus(Status.ROLLBACK_REQUESTED);
      }else {
        System.out.println("Can not rollback");
      }

    }
  }
}
