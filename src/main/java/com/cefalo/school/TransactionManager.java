package com.cefalo.school;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.NotImplementedException;


public class TransactionManager {

  //TODO:  Maintain a List of Transactions (Deposit/Withdraw/ Transfer )
  //TODO:  Maintain a List of Transactions rollback
  //TODO:  Add a Method for Adding Transaction
  //TODO: Add a method for Checking if there is any Pending Transtion

  List<Transaction> transactionList = new ArrayList<Transaction>();
  List<Transaction> pendingTransactionList = new ArrayList<Transaction>();

  public void addTransactionRequest(Transaction transaction){
    transactionList.add(transaction);
    pendingTransactionList.add(transaction);
  }

  public boolean hasPendingTransactions() {
    // This should track if there is any pending transaction request (Deposit/Withdraw/ Transfer)
//    for (Transaction transaction : transactionList) {
//        if(transaction.getStatus()== Status.PENDING){
//          System.out.println("transaction id: "+transaction.getTransactionID() + " is pending");
//          return true;
//        }
//    }
    return pendingTransactionList.size() > 0;
  }

  public void processPendingTransactions() {
    // The logic for processing pending transaction sequentially goes here
    // It should track which are already processed and which are pending transactions
    for (Transaction transaction : transactionList) {
      if(transaction.getStatus() == Status.PENDING){
        transaction.processTransaction();
      }
    }
  }

  public void rollbackTransaction(UUID transactionId) {
    // The logic for rolling back a transaction with Id

  }
}
