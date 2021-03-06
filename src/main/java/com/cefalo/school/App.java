package com.cefalo.school;

import java.util.UUID;

public class App {

    public static void main(String[] args) {
//        depositAccountCheckBalanceAndThenWithdraw_AllTransactionsSuccessful();
//        test_WithDrawRequestForAmountGreaterThanAvailableBalance_TransactionExecutedWhenBalanceConstrainMet();
//        testTransferRequestForAmountGreaterThanAvailableBalanceTransactionExecutedWhenBalanceConstrainMet();
//        test_Transfer_ThenRollback_AccountStatusRegainedItsInitialState();
        test_Transfer_ThenWithdrawFromTheSecondAccount_ThenRollback();
    }

    public static void depositAccountCheckBalanceAndThenWithdraw_AllTransactionsSuccessful() {

        TransactionManager transactionManager = new TransactionManager();
        // Create Account with Balance 0
        Account account = new Account("Ashif Iqbal",0);
        // Add a deposit reqest of 100 to that account
        Deposit deposit = new Deposit(100,account);
        transactionManager.addTransactionRequest(deposit);
        // Pre-check : transactionManager Should have pending transactions at this point
        System.out.println("has pending transaction "+transactionManager.hasPendingTransactions());
        // Account Balance Should be 0 at this point
        System.out.println(account.getAccountName()+ " balance: "+account.getBalance());

        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();

        // Check : there should not be any pending Transactions now
        System.out.println("has pending transaction "+transactionManager.hasPendingTransactions());

        // Check balance of the account which should be 100 now
        System.out.println(account.getAccountName()+ " balance after transaction: "+account.getBalance());

        // Create a Withdraw of 50 on that account

        Withdraw withdraw = new Withdraw(50,account);

        transactionManager.addTransactionRequest(withdraw);
        // Perform a ProcessPendingTransactions() to process this
        transactionManager.processPendingTransactions();

        // check : No pending Transactions at this point
        System.out.println("has pending transaction "+transactionManager.hasPendingTransactions());

        // Check balance: should be 100-50 =50
        System.out.println(account.getAccountName()+ " balance after transaction: "+account.getBalance());
    }

    public static void test_WithDrawRequestForAmountGreaterThanAvailableBalance_TransactionExecutedWhenBalanceConstrainMet() {
        // Create account with 75 as initial Balance
        TransactionManager transactionManager = new TransactionManager();
        Account account = new Account("Atiqul Alam",75);
        // Add a withdraw request of 100 (exceeding the available balance)
        Withdraw withdraw = new Withdraw(100,account);
        transactionManager.addTransactionRequest(withdraw);
        // Check Balance: should be 75
        System.out.println(account.getAccountName()+ " balance: "+account.getBalance());

        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();
        System.out.println("has pending transaction "+transactionManager.hasPendingTransactions());

        // Add a deposit request to that account of 75

        Deposit deposit = new Deposit(75,account);
        transactionManager.addTransactionRequest(deposit);
        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();

        // check : there should be pending transactions at this point
        System.out.println("has pending transaction "+transactionManager.hasPendingTransactions());

        // check Balance: should be 150
        System.out.println(account.getAccountName()+ " balance: "+account.getBalance());

        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();

        // Check: there should be no pending transactions at this point
        System.out.println("has pending transaction "+transactionManager.hasPendingTransactions());

        // Balance Check : should be 50 (withdraw request of 100 should be successfull at this point)
        System.out.println(account.getAccountName()+ " balance: "+account.getBalance());
    }

    public static void testTransferRequestForAmountGreaterThanAvailableBalanceTransactionExecutedWhenBalanceConstrainMet() {
        TransactionManager transactionManager = new TransactionManager();
        // Create firstAccount with Initial Balance 100
        Account fromAccount = new Account("Atiqul Alam",100);
        // Create secondAccount with Initial Balance 2000
        Account toAccount = new Account("Asaduzzaman Shuvro",2000);
        // Create a transfer request of 700 from firstAccount to secondAccount
        Transfer transfer = new Transfer(700, fromAccount, toAccount);
        transactionManager.addTransactionRequest(transfer);

        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();

        // Balance Check : FirstAccount -> 100
        System.out.println("fromAccount: " + fromAccount.getAccountName() + "balance: " + fromAccount.getBalance());
        // Balance Check : SecondAccount -> 2000
        System.out.println("toAccount: " + toAccount.getAccountName() + "balance: " + toAccount.getBalance());


        // Add a Deposit request of 900 to FirstAccount

        Deposit deposit = new Deposit(900, fromAccount);
        transactionManager.addTransactionRequest(deposit);
        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();
        // Balance Check : FirstAccount: 1000
        System.out.println("after deposit fromAccount: " + fromAccount.getAccountName() + " balance: " + fromAccount.getBalance());
        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();
        // Check: there should be no pending transactions at this point
        transactionManager.hasPendingTransactions();
        System.out.println("has pending transaction "+transactionManager.hasPendingTransactions());

        // Balance Check : FirstAccount -> 300
        System.out.println("after transaction fromAccount: " + fromAccount.getAccountName() + " balance: " + fromAccount.getBalance());
        // Balance Check : SecondAccount -> 2700
        System.out.println("after transaction toAccount: " + toAccount.getAccountName() + " balance: " + toAccount.getBalance());
    }

    public static void test_Transfer_ThenRollback_AccountStatusRegainedItsInitialState() {
        TransactionManager transactionManager = new TransactionManager();
        // Create firstAccount with Initial Balance 2000
        Account fromAccount = new Account("Ashif Iqbal",2000);
        // Create secondAccount with Initial Balance 100
        Account toAccount = new Account("Atikul Alam",100);

        // Create a transfer request of 700 from firstAccount to secondAccount
        Transfer transfer = new Transfer(700, fromAccount, toAccount);
        transactionManager.addTransactionRequest(transfer);

        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();

        // Balance Check : FirstAccount -> 1300
        System.out.println("fromAccount: " + fromAccount.getAccountName() + "balance: " + fromAccount.getBalance());
        // Balance Check : SecondAccount -> 800
        System.out.println("toAccount: " + toAccount.getAccountName() + "balance: " + toAccount.getBalance());
        // Perform a Rollback with the transaction Id of the transfer which is made
        UUID transactionID = transfer.getTransactionID();
        // Check: there should be no pending transactions at this point
        transactionManager.rollbackTransaction(transactionID);
        transactionManager.processPendingTransactions();

        // Balance Check : FirstAccount -> 2000
        System.out.println("after rollback fromAccount: " + fromAccount.getAccountName() + "balance: " + fromAccount.getBalance());
        // Balance Check : SecondAccount -> 100
        System.out.println("after rollback toAccount: " + toAccount.getAccountName() + "balance: " + toAccount.getBalance());
    }

    public static void test_Transfer_ThenWithdrawFromTheSecondAccount_ThenRollback()
    {
        TransactionManager transactionManager = new TransactionManager();
        // Create firstAccount with Initial Balance 2000
        Account fromAccount = new Account("Ashif Iqbal",2000);
        // Create secondAccount with Initial Balance 100
        Account toAccount = new Account("Asaduzzaman Shuvro",100);

        // Create a transfer request of 700 from firstAccount to secondAccount
        Transfer transfer = new Transfer(700, fromAccount, toAccount);
        transactionManager.addTransactionRequest(transfer);
        UUID transferID = transfer.getTransactionID();
        System.out.println("transfer rollback id: " + transferID);

        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();

        // Balance Check : FirstAccount -> 1300
        System.out.println("fromAccount: " + fromAccount.getAccountName() + " balance: " + fromAccount.getBalance());
        // Balance Check : SecondAccount -> 800
        System.out.println("toAccount: " + toAccount.getAccountName() + " balance: " + toAccount.getBalance());

        // Create a withdraw request of 600 from the secondAccount
        Withdraw withdraw = new Withdraw(600, toAccount);
        transactionManager.addTransactionRequest(withdraw);
        UUID withdrawID = withdraw.getTransactionID();
        System.out.println("withdraw rollback id: " + withdrawID);

        // Run ProcessPendingTransactions() to process Pending TransactionRequests
        transactionManager.processPendingTransactions();

        // Perform a Rollback with the transaction Id of the transfer which is made (Rollback should not be executed because of balance constraint)
        transactionManager.rollbackTransaction(transferID);
        transactionManager.processPendingTransactions();

        // Perform a Rollback with the transaction Id of the withdraw which was made
        transactionManager.rollbackTransaction(withdrawID);
        transactionManager.processPendingTransactions();

        // Run ProcessPendingTransactions() to process Pending TransactionRequests

        transactionManager.processPendingTransactions();

        // Balance Check : FirstAccount -> 2000
        System.out.println("fromAccount: " + fromAccount.getAccountName() + "balance: " + fromAccount.getBalance());
        // Balance Check : SecondAccount -> 100
        System.out.println("toAccount: " + toAccount.getAccountName() + "balance: " + toAccount.getBalance());
    }

}
