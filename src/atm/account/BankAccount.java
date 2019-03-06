package atm.account;

import atm.account.CurrencyImpl.DOLLAR;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class provides methods and functionality for users to have bank accounts that can store their balances and make deposits, withdrawals, or transfers.
 *
 */
public class BankAccount extends Account {

    private static String Path = "/Users/kobale/IdeaProjects/Bank ATM/src/History.txt";

    /**
     * Stores the user's account balance saved to two significant decimal places.
     */
    BigDecimal accountBalance;

    /**
     * Opens a new bank account with the user's name, PIN, and starting balance. Upon calling the constructor in Account, this account is added to the account database.
     * If any of the parameters are not valid, the account is closed and removed from the account map.
     *
     * @param accountName
     *        The account holder's name.
     * @param accountPin
     *        The user's 4 digit PIN.
     * @param accountBalance
     *        The account's starting balance.
     * @throws IllegalArgumentException
     *         Thrown if a negative amount is entered for the account's balance.
     */
    public BankAccount(String accountName, Pin accountPin, BigDecimal accountBalance) throws IllegalArgumentException {
        super(accountName, accountPin);
        try {
            if (IS_POSITIVE_AMOUNT(accountBalance.doubleValue())) {
                this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);		// Sets accountBalance to round to 2 significant digits.
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please enter a positive amount for your account balance.");
        }
    }

    /**
     * Performs a check to see if the user has entered a positive amount for any currency values.
     *
     * @param amount
     *        The amount to check if positive.
     * @return True, if the amount is greater than zero.
     * @throws IllegalArgumentException
     *         Thrown if the amount is less than zero.
     */
    public static final boolean IS_POSITIVE_AMOUNT(double amount) throws IllegalArgumentException {
        if (amount >= 0) {
            return true;
        } else {
            throw new IllegalArgumentException("Please enter a positive amount.");
        }
    }

    /**
     * Performs a check to see if the user has a sufficient balance to complete a transaction.
     *
     * @param amount
     *        The amount to see if the user has in their account balance.
     * @return True, if the user has enough money in their balance to complete a transaction.
     * @throws IllegalArgumentException
     *         Thrown if the amount the user needs is greater than their balance.
     */
    public final boolean hasSufficientBalance(double amount) throws IllegalArgumentException {
        if (accountBalance.compareTo(new BigDecimal(amount)) >= 0) {
            return true;
        } else {
            throw new IllegalArgumentException("You have an insufficient balance to complete this transaction.");
        }
    }

    /**
     * User chooses another account to send money to. Money is withdrawn from their account and deposited in the account they specify. This method adds interest if
     * the accounts in use are savings accounts.
     *
     * @param receivingAccount
     *        The account to receive the money.
     * @param transferAmount
     *        The amount of money to transfer.
     * @throws IllegalArgumentException
     *         Thrown if the user enters a negative amount of money to transfer, or they have an insufficient balance.
     * @throws NullPointerException
     *         Thrown if the account the user wants to transfer money to does not exist.
     */
    public final void transfer(int receivingAccount, double transferAmount) throws IllegalArgumentException, NullPointerException {
        if (ACCOUNT_EXISTS(receivingAccount) && IS_POSITIVE_AMOUNT(transferAmount) && hasSufficientBalance(transferAmount)) {
            accountBalance = accountBalance.subtract(new BigDecimal(transferAmount + transferAmount * 0.02));
            if (GET_ACCOUNT_MAP().get(receivingAccount) instanceof SavingsAccount) {
                SavingsAccount tempReceivingAccount = (SavingsAccount)GET_ACCOUNT_MAP().get(receivingAccount);
                tempReceivingAccount.accountBalance = tempReceivingAccount.accountBalance
                        .add(new BigDecimal(transferAmount + tempReceivingAccount.getInterest(transferAmount)));
            } else {
                BankAccount tempReceivingAccount = (CheckingsAccount)GET_ACCOUNT_MAP().get(receivingAccount);
                tempReceivingAccount.accountBalance = tempReceivingAccount.accountBalance.add(new BigDecimal(transferAmount));
            }
        }
    }

    /**
     * Deposits a specified amount of money into the user's account.
     *
     * @param depositAmount
     *        The amount to deposit.
     * @throws IllegalArgumentException
     *         Thrown if the deposit amount is negative.
     */
    public void deposit(double depositAmount) throws IllegalArgumentException {
        if (IS_POSITIVE_AMOUNT(depositAmount)) {
            accountBalance = accountBalance.add(new BigDecimal(depositAmount));
        }
    }

    /**
     * Loans a specified amount of money into the user's account.
     *
     * @param loanAmount
     *        The amount to deposit.
     * @throws IllegalArgumentException
     *         Thrown if the deposit amount is negative.
     */
    public void loan(double loanAmount) throws IllegalArgumentException {
        if (IS_POSITIVE_AMOUNT(loanAmount)) {
            accountBalance = accountBalance.add(new BigDecimal(loanAmount));
        }
    }

    /**
     * Withdrawals a specified amount of money from the user's account.
     *
     * @param withdrawalAmount
     *        The amount to withdrawal.
     * @throws IllegalArgumentException
     *         Thrown if the withdrawal amount is negative or the user has an insufficient balance.
     */
    public void withdraw(double withdrawalAmount) throws IllegalArgumentException {
        if (IS_POSITIVE_AMOUNT(withdrawalAmount) && hasSufficientBalance(withdrawalAmount)) {
            accountBalance = accountBalance.subtract(new BigDecimal(withdrawalAmount));

        }
    }

    /**
     * Create the transaction history file.
     */
    public static boolean createFile(String path) {
        boolean flag = false;
        File fileName = new File(path);
        try {
            if(!fileName.exists()) {
                fileName.createNewFile();
                flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Write information to transaction file
     */
    public static boolean writeTxtFile(String content, String path, boolean append) {
        boolean flag = false;
        File fileName = new File(path);
        try {
            if(!fileName.exists()) {
                fileName.createNewFile();
            }
            FileWriter fw = new FileWriter(path, append);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        flag = true;
        return flag;
    }

    /**
     * Open the transaction history file.
     */
    public static void OpenFile() {
        try {
            Process p = new ProcessBuilder("open", Path).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Clear the transaction history file.
     */
    public static void ClearFile() {
        try {
            PrintWriter writer = new PrintWriter(Path);
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compares the contents of two bank account objects to test for equality.
     *
     * @param bankAccount
     *        The account to test equality with.
     * @return True, if the account's contents are equal, false if otherwise.
     */
    @Override
    public boolean equals(Object bankAccount) {
        if (this.toString().equalsIgnoreCase(((BankAccount)bankAccount).toString())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return A concatenated string with all of the information stored in the account.
     */
    @Override
    public String toString() {
        return "Account Number: " + ACCOUNT_NUMBER + " \nAccount Name: " + accountName + " \nAccount Pin: " + accountPin + " \nAccount Balance: "
                + DOLLAR.TO_US_CURRENCY_FORMAT(accountBalance);
    }

    /**
     * @return The account's balance.
     */
    public final BigDecimal getAccountBalance() {
        return accountBalance;
    }

    /**
     * @param accountBalance
     *        The new account balance.
     */
    final void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance.setScale(2, RoundingMode.HALF_UP);	  // Sets two significant decimal places.
    }

}
